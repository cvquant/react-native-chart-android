package cn.mandata.react_native_mpchart;

import android.graphics.Color;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.HorizontalBarChart;
/*import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;*/
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 2015/11/6.
 */
public class MPHorizontalBarChartManager extends MPBarLineChartManager {
    private String CLASS_NAME="MPHorizontalBarChart";
    @Override
    public String getName() {
        return this.CLASS_NAME;
    }

    @Override
    protected HorizontalBarChart createViewInstance(ThemedReactContext reactContext) {
        HorizontalBarChart chart=new HorizontalBarChart(reactContext);

        // initialise event listener to bind to chart
        new MPChartSelectionEventListener(chart);

        return  chart;
    }

    //{XValues:[],YValues:[{Data:[],Label:""},{}]}
    @ReactProp(name="data")
    public void setData(HorizontalBarChart chart,ReadableMap rm){

        ReadableArray xArray=rm.getArray("xValues");
        ArrayList<String> xVals=new ArrayList<String>();
        for(int m=0;m<xArray.size();m++){
            xVals.add(xArray.getString(m));
        }
        ReadableArray ra=rm.getArray("yValues");
        BarData barData=new BarData(xVals);
        for(int i=0;i<ra.size();i++){
            ReadableMap map=ra.getMap(i);
            ReadableArray data=map.getArray("data");
            String label=map.getString("label");
            float[] vals=new float[data.size()];
            ArrayList<BarEntry> entries=new ArrayList<BarEntry>();
            for (int j=0;j<data.size();j++){
                vals[j]=(float)data.getDouble(j);
                BarEntry be=new BarEntry((float)data.getDouble(j),j);
                entries.add(be);
            }
            /*BarEntry be=new BarEntry(vals,i);
            entries.add(be);*/
            BarDataSet dataSet=new BarDataSet(entries,label);
            ReadableMap config= map.getMap("config");
            if(config.hasKey("colors")){
                ReadableArray colorsArray = config.getArray("colors");
                ArrayList<Integer> colors = new ArrayList<>();
                for(int c = 0; c < colorsArray.size(); c++){
                    colors.add(Color.parseColor(colorsArray.getString(c)));
                }
                dataSet.setColors(colors);
            }else
            if(config.hasKey("color")) {
                int[] colors=new int[]{Color.parseColor(config.getString("color"))};
                dataSet.setColors(colors);
            }
            if(config.hasKey("barSpacePercent")) {
                dataSet.setBarSpacePercent((float)config.getDouble("barSpacePercent"));
            }
            if(config.hasKey("valueTextSize")) {
                dataSet.setValueTextSize((float)config.getDouble("valueTextSize"));
            }
            barData.addDataSet(dataSet);

        }
        chart.setBackgroundColor(Color.WHITE);
        chart.setData(barData);
        chart.invalidate();
    }
}
