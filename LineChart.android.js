import React, { Component } from 'react';
import { requireNativeComponent, ViewPropTypes } from 'react-native';
import PropTypes from 'prop-types';

class LineChart extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <MPLineChart {...this.props}/>
        );
    }
}

LineChart.propTypes = {
    ...ViewPropTypes,
    data:PropTypes.object,
    touchEnabled:PropTypes.bool,
    dragEnabled:PropTypes.bool,
    scaleEnabled:PropTypes.bool,
    scaleXEnabled:PropTypes.bool,
    scaleYEnabled:PropTypes.bool,
    pinchZoom:PropTypes.bool,
    doubleTapToZoomEnabled:PropTypes.bool,
    highlightPerDragEnabled:PropTypes.bool,
    highlightPerTapEnabled:PropTypes.bool,
    dragDecelerationEnabled:PropTypes.bool,
    dragDecelerationFrictionCoef:PropTypes.number,
    maxVisibleValueCount:PropTypes.number,
    limitLine:PropTypes.object,
    description:PropTypes.string,
    noDataText: PropTypes.string,
    backgroundColor:PropTypes.string,
    drawGridBackground:PropTypes.bool,
    gridBackgroundColor:PropTypes.string,
    visibleXRange:PropTypes.array,
    borderColor:PropTypes.string,
    borderWidth:PropTypes.number,
    xAxis:PropTypes.object,
    yAxisLeft:PropTypes.object,
    yAxisRight:PropTypes.object,
    yAxis:PropTypes.object,
    fitScreen:PropTypes.bool,
    chartPadding:PropTypes.string,
    extraOffsets:PropTypes.string,
    viewPortOffsets:PropTypes.string,
    legend:PropTypes.object,
    viewCenter: PropTypes.array,
    zoomTo: PropTypes.object
}

var MPLineChart = requireNativeComponent('MPLineChart', LineChart);

export default LineChart;
