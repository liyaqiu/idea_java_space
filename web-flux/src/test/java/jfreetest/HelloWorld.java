package jfreetest;

import lombok.extern.java.Log;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.Layer;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.util.LogFormat;
import org.jfree.chart.util.UnitType;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.*;

@Log
public class HelloWorld {
    /**
     *
     */
    @Test
    public void 饼图案例() throws IOException {
        // create a dataset...
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Category 1", 43.2);
        dataset.setValue("Category 2", 27.9);
        dataset.setValue("Category 3", 79.5);

        // create a chart...
        JFreeChart chart = ChartFactory.createPieChart(
                "Sample Pie Chart -Yves", // chart title: 图表标题
                dataset, // data: 数据集
                true, // legend: 图例
                true, // tooltips: 图表工具条
                false // URLs: URLS
        );

        // create and display a frame...
        ChartUtils.saveChartAsPNG(new File("C:\\Users\\admin\\Desktop\\image\\1.png"), chart, 1200, 800);
    }

    @Test
    public void 多线折线图案例() throws IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1.10, "First", "5");
        dataset.addValue(1.05, "First", "10");
        dataset.addValue(1.00, "First", "15");
        dataset.addValue(0.95, "First", "20");
        dataset.addValue(0.90, "First", "25");

        dataset.addValue(1.05, "Second", "5");
        dataset.addValue(1.00, "Second", "10");
        dataset.addValue(0.96, "Second", "15");
        dataset.addValue(0.91, "Second", "20");
        dataset.addValue(0.88, "Second", "25");

        dataset.addValue(1.02, "Third", "5");
        dataset.addValue(0.90, "Third", "10");
        dataset.addValue(0.88, "Third", "15");
        dataset.addValue(0.85, "Third", "20");
        dataset.addValue(0.7, "Third", "25");
        JFreeChart chart = ChartFactory.createLineChart("", "1111", "222", dataset);
        //标题设置
        TextTitle title = new TextTitle("FM颗粒度", JFreeChart.DEFAULT_TITLE_FONT); // 还可以设置颜色,位置等...
        chart.setTitle(title);
        ChartUtils.saveChartAsPNG(new File("C:\\Users\\admin\\Desktop\\image\\1.png"), chart, 1200, 800);

    }

    final static String UCL = "ucl=";
    final static String CL = "cl=";
    final static String LCL = "lcl=";


    public MarkLineDto genMarkLineData(Map<String, Double> map) {
        MarkLineDto markLineDto = new MarkLineDto();
        //上线和下线存在(中线存在则使用，不存在则利用上下线计算)
        if (Objects.nonNull(map.get(UCL)) && Objects.nonNull(map.get(LCL))) {
            Double ucl = map.get(UCL);
            Double cl = map.get(CL);
            Double lcl = map.get(LCL);
            if (Objects.isNull(cl)) {
                //中间线如果为空，则需要计算
                cl = (ucl + lcl) / 2;
            }
            Double topAvg = (ucl - cl) / 3;
            Double bottomAvg = (cl - lcl) / 3;

            DecimalFormat fm = new DecimalFormat("0.00");
            markLineDto.setMarkLineNames(new String[]{UCL, "A", "", "B", "", "C", CL, "C", "", "B", "", "A", LCL});
            markLineDto.setMarkLineValues(new Double[]{
                    ucl,
                    Double.valueOf(fm.format(new BigDecimal(topAvg * 2 + cl).doubleValue())),//A区域
                    Double.valueOf(fm.format(new BigDecimal(topAvg * 2 + cl).doubleValue())),
                    Double.valueOf(fm.format(new BigDecimal(topAvg + cl).doubleValue())),//B区域
                    Double.valueOf(fm.format(new BigDecimal(topAvg + cl).doubleValue())),
                    cl,//C区
                    cl,
                    Double.valueOf(fm.format(new BigDecimal(cl - bottomAvg).doubleValue())),//C区域
                    Double.valueOf(fm.format(new BigDecimal(cl - bottomAvg).doubleValue())),
                    Double.valueOf(fm.format(new BigDecimal(cl - bottomAvg * 2).doubleValue())),//B区域
                    Double.valueOf(fm.format(new BigDecimal(cl - bottomAvg * 2).doubleValue())),
                    lcl,//A区域
                    lcl});
            markLineDto.setLabelPositions(new MarkLineDto.LabelPosition[]{
                    MarkLineDto.LabelPosition.RIGHT,
                    MarkLineDto.LabelPosition.Top,
                    MarkLineDto.LabelPosition.RIGHT,
                    MarkLineDto.LabelPosition.Top,
                    MarkLineDto.LabelPosition.RIGHT,
                    MarkLineDto.LabelPosition.Top,
                    MarkLineDto.LabelPosition.RIGHT,
                    MarkLineDto.LabelPosition.Top,
                    MarkLineDto.LabelPosition.RIGHT,
                    MarkLineDto.LabelPosition.Top,
                    MarkLineDto.LabelPosition.RIGHT,
                    MarkLineDto.LabelPosition.Top,
                    MarkLineDto.LabelPosition.RIGHT});
            return markLineDto;
        }
        //上线和中线存在
        if (Objects.nonNull(map.get(UCL)) && Objects.nonNull(map.get(CL))) {
            Double ucl = map.get(UCL);
            Double cl = map.get(CL);
            Double topAvg = (ucl - cl) / 3;
            DecimalFormat fm = new DecimalFormat("0.00");

            markLineDto.setMarkLineNames(new String[]{UCL, "A", "", "B", "", "C", CL});
            markLineDto.setMarkLineValues(new Double[]{
                    ucl,
                    Double.valueOf(fm.format(new BigDecimal(topAvg * 2 + cl).doubleValue())),//A区域
                    Double.valueOf(fm.format(new BigDecimal(topAvg * 2 + cl).doubleValue())),
                    Double.valueOf(fm.format(new BigDecimal(topAvg + cl).doubleValue())),//B区域
                    Double.valueOf(fm.format(new BigDecimal(topAvg + cl).doubleValue())),
                    cl,//C区
                    cl});
            markLineDto.setLabelPositions(new MarkLineDto.LabelPosition[]{
                    MarkLineDto.LabelPosition.RIGHT,
                    MarkLineDto.LabelPosition.Top,
                    MarkLineDto.LabelPosition.RIGHT,
                    MarkLineDto.LabelPosition.Top,
                    MarkLineDto.LabelPosition.RIGHT,
                    MarkLineDto.LabelPosition.Top,
                    MarkLineDto.LabelPosition.RIGHT});
            return markLineDto;
        }
        //下线和中线存在
        if (Objects.nonNull(map.get(CL)) && Objects.nonNull(map.get(LCL))) {
            Double cl = map.get(CL);
            Double lcl = map.get(LCL);
            Double bottomAvg = (cl - lcl) / 3;
            DecimalFormat fm = new DecimalFormat("0.00");
            markLineDto.setMarkLineNames(new String[]{CL, "C", "", "B", "", "A", LCL});
            markLineDto.setMarkLineValues(new Double[]{
                    cl,
                    Double.valueOf(fm.format(new BigDecimal(cl - bottomAvg).doubleValue())),//C区域
                    Double.valueOf(fm.format(new BigDecimal(cl - bottomAvg).doubleValue())),
                    Double.valueOf(fm.format(new BigDecimal(cl - bottomAvg * 2).doubleValue())),//B区域
                    Double.valueOf(fm.format(new BigDecimal(cl - bottomAvg * 2).doubleValue())),
                    lcl,//A区域
                    lcl});
            markLineDto.setLabelPositions(new MarkLineDto.LabelPosition[]{
                    MarkLineDto.LabelPosition.RIGHT,
                    MarkLineDto.LabelPosition.Top,
                    MarkLineDto.LabelPosition.RIGHT,
                    MarkLineDto.LabelPosition.Top,
                    MarkLineDto.LabelPosition.RIGHT,
                    MarkLineDto.LabelPosition.Top,
                    MarkLineDto.LabelPosition.RIGHT});
            return markLineDto;
        }
        if (Objects.nonNull(map.get(UCL))) {
            markLineDto.setMarkLineNames(new String[]{UCL});
            markLineDto.setMarkLineValues(new Double[]{map.get(UCL)});
            markLineDto.setLabelPositions(new MarkLineDto.LabelPosition[]{MarkLineDto.LabelPosition.RIGHT});
            return markLineDto;
        }
        if (Objects.nonNull(map.get(CL))) {
            markLineDto.setMarkLineNames(new String[]{CL});
            markLineDto.setMarkLineValues(new Double[]{map.get(CL)});
            markLineDto.setLabelPositions(new MarkLineDto.LabelPosition[]{MarkLineDto.LabelPosition.RIGHT});
            return markLineDto;
        }
        if (Objects.nonNull(map.get(LCL))) {
            markLineDto.setMarkLineNames(new String[]{LCL});
            markLineDto.setMarkLineValues(new Double[]{map.get(LCL)});
            markLineDto.setLabelPositions(new MarkLineDto.LabelPosition[]{MarkLineDto.LabelPosition.RIGHT});
            return markLineDto;
        }
        return markLineDto;
    }

    @Test
    public void 单线折线图案例() throws IOException, InterruptedException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //String[] xAxis = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        String[] xAxis = new String[]{"2023-12-08 00:00:01", "2023-12-08 00:00:02", "2023-12-08 00:00:03", "2023-12-08 00:00:04", "2023-12-08 00:00:05", "2023-12-08 00:00:06", "2023-12-08 00:00:07"};
        String titleText = "FM颗粒度";

        Double[] seriesData = new Double[]{110D, 110D, 110D, 110D, 110D, 110D, 110D};
        for (int i = 0; i < xAxis.length; i++) {
            dataset.addValue(seriesData[i], "", xAxis[i]+i);
        }

        Map<String, Double> map = new HashMap<>();
        map.put("ucl=", 200D);
        map.put("cl=", 160D);
        map.put("lcl=", 90D);

        MarkLineDto markLineDto = genMarkLineData(map);
        Double[] markLineValues = markLineDto.getMarkLineValues();
        String[] markLineNames = markLineDto.getMarkLineNames();
        MarkLineDto.LabelPosition[] labelPositions = markLineDto.getLabelPositions();
        Double ucl = map.get(UCL);
        Double lcl = map.get(LCL);


        int triggerIndex = seriesData.length;

        JFreeChart chart = ChartFactory.createLineChart("", "", "", dataset);
        //配置chart
        chartOption(titleText, chart);
        //配置plot
        plotOption(chart);
        //配置CategoryPlot
        CategoryPlotOption(ucl,lcl,seriesData, labelPositions, markLineValues, markLineNames, triggerIndex, chart);
        //保存配置
        ChartUtils.saveChartAsPNG(new File("C:\\Users\\admin\\Desktop\\xuexi\\1.png"), chart, 1200, 600);

//        ChartFrame chartFrame = new ChartFrame("Test", chart);
//        chartFrame.pack();
//        chartFrame.setVisible(true);
//        Thread.sleep(Integer.MAX_VALUE);
    }

    private static void CategoryPlotOption(Double ucl,Double lcl, Double[] seriesData, MarkLineDto.LabelPosition[] labelPositions, Double[] markLineValues, String[] markLineNames, int triggerIndex, JFreeChart chart) {
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        Double yMin = null;
        Double yMax = null;
        if(Objects.nonNull(lcl)){
            yMin = Math.min(Collections.min(Arrays.asList(seriesData)),lcl);
        }else{
            yMin =  Collections.min(Arrays.asList(seriesData));
        }
        if(Objects.nonNull(ucl)){
            yMax =  Math.max(Collections.max(Arrays.asList(seriesData)),ucl);
        }else{
            yMax =  Collections.max(Arrays.asList(seriesData));
        }


        //设置Y轴
        ValueAxis rangeAxis = categoryPlot.getRangeAxis();
        //乘0.1是防止边界的点被遮挡
        rangeAxis.setRange(yMin - (yMin * 0.1), yMax + (yMax * 0.1));
        rangeAxis.setVisible(false);
        //设置X轴
        categoryPlot.getDomainAxis().setVisible(false);
        //设置线上的点
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        //设置数值显示
        renderer.setDefaultItemLabelsVisible(true);

        //最后一个点进行显示数值
        NumberFormat numberFormat = new LogFormat() {
            int count = 1;
            @Override
            public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos) {
                if(count == triggerIndex){
                    return new StringBuffer(String.valueOf(number));
                }
                count++;
                return new StringBuffer();
            }
        };


        StandardCategoryItemLabelGenerator itemLabelGenerator = new StandardCategoryItemLabelGenerator(StandardCategoryItemLabelGenerator.DEFAULT_LABEL_FORMAT_STRING, numberFormat);
        renderer.setDefaultItemLabelGenerator(itemLabelGenerator);
        //设置线的颜色
        renderer.setSeriesPaint(0, Color.decode("#1A1AFF"));
        categoryPlot.setRenderer(renderer);

        //标记范围值设置
        for (int i = 0; i < markLineValues.length; i++) {
            Color color = null;
            if (UCL.equals(markLineNames[i]) || LCL.equals(markLineNames[i])) {
                color = Color.RED;
            } else if (CL.equals(markLineNames[i])) {
                color = Color.BLACK;
            } else {
                color = Color.decode("#A7A731");
            }
            ValueMarker valueMarker = new ValueMarker(markLineValues[i], color, new BasicStroke(1.0F, 1, 1, 1.0F, new float[]{6F, 6F}, 0.0F));
            valueMarker.setLabelFont(new Font("Arial", Font.BOLD, 10));
            if (labelPositions[i] == MarkLineDto.LabelPosition.Top) {
                valueMarker.setLabel(markLineNames[i]);
            } else {
                valueMarker.setLabel(markLineNames[i] + markLineValues[i]);
            }
            valueMarker.setLabelBackgroundColor(Color.WHITE);
            if (labelPositions[i] == MarkLineDto.LabelPosition.Top) {
                valueMarker.setLabelOffset(new RectangleInsets(UnitType.ABSOLUTE, 20, 1150, 0, 0));
            } else if (labelPositions[i] == MarkLineDto.LabelPosition.RIGHT) {
                valueMarker.setLabelOffset(new RectangleInsets(UnitType.ABSOLUTE, 0, 1150, 0, 0));
            }
            categoryPlot.addRangeMarker(valueMarker, Layer.BACKGROUND);
        }
    }

    private static void plotOption(JFreeChart chart) {
        //图表绘制背景设置
        Plot plot = chart.getPlot();
        // 图表绘制线条的透明度
        plot.setForegroundAlpha(1);// 1为不透明
        // 图表绘制背景透明度
        plot.setBackgroundAlpha(1);
        // 图表绘制背景颜色
        plot.setBackgroundPaint(Color.WHITE);// 比如画图区域的背景颜色,要与报表的整个背景颜色区分开来
        // 图表绘制背景边框线设置
        plot.setOutlinePaint(Color.WHITE);// 颜色
        plot.setOutlineStroke(new BasicStroke(1));// 大小
        plot.setOutlineVisible(false);// 是否显示
    }

    private static void chartOption(String titleText, JFreeChart chart) {
        //标题设置
        chart.setTitle(new TextTitle(titleText, JFreeChart.DEFAULT_TITLE_FONT));
        //图表整个背景颜色设置
        chart.setBackgroundPaint(Color.WHITE);
        //设置Legend不显示
        chart.getLegend().setVisible(false);
    }

    @Test
    public void 柱状图案例() throws IOException {
        // create a dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1.0, "Row 1", "Column 1");
        dataset.addValue(5.0, "Row 1", "Column 2");
        dataset.addValue(3.0, "Row 1", "Column 3");
        dataset.addValue(2.0, "Row 2", "Column 1");
        dataset.addValue(3.0, "Row 2", "Column 2");
        dataset.addValue(2.0, "Row 2", "Column 3");

        // create a chart...
        JFreeChart chart = ChartFactory.createBarChart("", "1111", "222", dataset);

        //标题设置
        TextTitle title = new TextTitle("FM颗粒度", JFreeChart.DEFAULT_TITLE_FONT); // 还可以设置颜色,位置等...
        chart.setTitle(title);

        //图例设置
        chart.removeLegend();// 图例是默认显示的,使用该方法不显示图例
        LegendTitle legend = chart.getLegend();
        if (legend != null) {
            legend.setFrame(new BlockBorder(Color.RED));// 通过Color.WHITE隐藏
            legend.setItemPaint(Color.RED);// 设置图例画笔颜色(也就是文字颜色)
            legend.setBackgroundPaint(Color.DARK_GRAY);// 设置图例背景色
        }

        //图表背景
        // 图表整个背景颜色设置
        chart.setBackgroundPaint(Color.WHITE);
        //图表绘制背景设置
        Plot plot = chart.getPlot();
        // 图表绘制线条的透明度
        plot.setForegroundAlpha(1f);// 1为不透明
        // 图表绘制背景透明度
        plot.setBackgroundAlpha(0.5F);
        // 图表绘制背景颜色
        plot.setBackgroundPaint(Color.WHITE);// 比如画图区域的背景颜色,要与报表的整个背景颜色区分开来
        // 图表绘制背景边框线设置
        plot.setOutlinePaint(Color.WHITE);// 颜色
        plot.setOutlineStroke(new BasicStroke(2.f));// 大小
        plot.setOutlineVisible(true);// 是否显示


        CategoryPlot categoryPlot = chart.getCategoryPlot();
        //图表绘制中网格线设置
        // 设置背景中的网格线颜色,和是否显示
        categoryPlot.setDomainGridlinePaint(Color.white);
        categoryPlot.setDomainGridlinesVisible(true);// 竖线
        categoryPlot.setRangeGridlinePaint(Color.white);
        categoryPlot.setRangeGridlinesVisible(true);// 横线

        //X轴设置
        CategoryAxis domainAxis = categoryPlot.getDomainAxis();
        // X轴标签位置设置
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);//设置种类标签旋转为90度
        // X轴的标签设置
        domainAxis.setLabel(null);//通过实例化Chart的时候设置,实例化后可以通过该方法设置,null为不显示

        //Y轴设置
        NumberAxis numberaxis = (NumberAxis) categoryPlot.getRangeAxis();
        //Y轴增长单元设置
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        numberaxis.setAutoRangeIncludesZero(true);// 起始坐标是否包括0
        // Y轴的标签设置
        numberaxis.setLabel(null);//通过实例化Chart的时候设置,实例化后可以通过该方法设置,null为不显示
        // Y轴显示格式定制  (定制成显示百分比)
        NumberAxis newNumberaxis = new NumberAxis("Percentage");//构造参数为Y轴显示的label
        newNumberaxis.setNumberFormatOverride(new DecimalFormat("0.00%"));
        categoryPlot.setRangeAxis(newNumberaxis);

        //标记范围值设置
        ValueMarker valueMarker1 = new ValueMarker(1.50D, Color.RED, new BasicStroke(3.0F, 1, 1, 1.0F, new float[]{6F, 6F}, 0.0F));
        valueMarker1.setLabel("UCL=700");
        valueMarker1.setLabelBackgroundColor(Color.WHITE);
        valueMarker1.setLabelOffset(new RectangleInsets(UnitType.ABSOLUTE, 10, 1080, 3, 3));
        categoryPlot.addRangeMarker(valueMarker1, Layer.BACKGROUND);

        ValueMarker valueMarker2 = new ValueMarker(2.50D, Color.RED, new BasicStroke(3.0F, 1, 1, 1.0F, new float[]{6F, 6F}, 0.0F));
        valueMarker2.setLabel("UCL=700");
        valueMarker2.setLabelBackgroundColor(Color.WHITE);
        valueMarker2.setLabelOffset(new RectangleInsets(UnitType.ABSOLUTE, 10, 1080, 3, 3));
        categoryPlot.addRangeMarker(valueMarker2, Layer.BACKGROUND);

        ValueMarker valueMarker3 = new ValueMarker(3.50D, Color.RED, new BasicStroke(3.0F, 1, 1, 1.0F, new float[]{6F, 6F}, 0.0F));
        valueMarker3.setLabel("UCL=700");
        valueMarker3.setLabelBackgroundColor(Color.WHITE);
        valueMarker3.setLabelOffset(new RectangleInsets(UnitType.ABSOLUTE, 10, 1080, 3, 3));
        categoryPlot.addRangeMarker(valueMarker3, Layer.BACKGROUND);


        BarRenderer renderer = new BarRenderer();
        renderer.setSeriesPaint(0, Color.GRAY);
        categoryPlot.setRenderer(renderer);


        ChartUtils.saveChartAsPNG(new File("C:\\Users\\admin\\Desktop\\image\\1.png"), chart, 1200, 800);
    }

}
