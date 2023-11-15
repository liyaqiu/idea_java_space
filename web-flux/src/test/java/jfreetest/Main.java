package jfreetest;

import org.jfree.chart.*;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {
    /**
     *
     * */
    @Test
    public void test() throws InterruptedException, IOException {
        ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries("Series 1");
        series.add(130, 130);
        series.add(130, 130);
        series.add(130, 130);
        //series.add(140, 140);
//        series.add(2, 3);
//        series.add(3, 2);
//        series.add(4, 5);
        dataset.addSeries(series);
        // 创建JFreeChart对象
        JFreeChart chart = ChartFactory.createXYLineChart(
                "FM趋势图", // 图标题
                "x轴标题", // x轴标题
                "y轴标题", // y轴标题
                dataset, //数据集
                PlotOrientation.VERTICAL, //图表方向
                false, true, false);
       /* ,
                140,
                180,
                {
                        value: 160,
                itemStyle: {
            color: 'red'
        },
        show: true
      },
        135,
                210,
                145*/

        XYPlot plot = chart.getXYPlot();
        plot.getDomainAxis().setVisible(false); // 隐藏x轴
        plot.getRangeAxis().setVisible(false); // 隐藏y轴
        plot.setBackgroundPaint(null);
        plot.setOutlinePaint(null);
//        Marker marker=new ValueMarker();
//        marker.setPaint(Color.ORANGE);
//        marker.setLabel(entry.getKey());
//        plot.addRangeMarker(marker);// 添加第二条辅助线，对应y=2.0
        ChartUtils.saveChartAsPNG(new File("C:\\Users\\admin\\Desktop\\image\\1.png"),chart,1200,800);
        // 利用awt进行显示
        /*ChartFrame chartFrame = new ChartFrame("Test", chart);
        chartFrame.pack();
        chartFrame.setVisible(true);
        Thread.sleep(Integer.MAX_VALUE);*/

    }

    // 根据CategoryDataset创建JFreeChart对象
    public static JFreeChart createChart(CategoryDataset categoryDataset) {
        // 创建JFreeChart对象：ChartFactory.createLineChart
        JFreeChart jfreechart = ChartFactory.createLineChart("不同类别按小时计算拆线图", // 标题
                "年分", // categoryAxisLabel （category轴，横轴，X轴标签）
                "数量", // valueAxisLabel（value轴，纵轴，Y轴的标签）
                categoryDataset, // dataset
                PlotOrientation.VERTICAL, true, // legend
                false, // tooltips
                false); // URLs
        // 使用CategoryPlot设置各种参数。以下设置可以省略。
        CategoryPlot plot = (CategoryPlot)jfreechart.getPlot();
        // 背景色 透明度
        plot.setBackgroundAlpha(0.5f);
        // 前景色 透明度
        plot.setForegroundAlpha(0.5f);
        // 其他设置 参考 CategoryPlot类
        LineAndShapeRenderer renderer = (LineAndShapeRenderer)plot.getRenderer();
        renderer.setDefaultLinesVisible(true); // series 点（即数据点）可见
        renderer.setUseSeriesOffset(true); // 设置偏移量
        return jfreechart;
    }
}
