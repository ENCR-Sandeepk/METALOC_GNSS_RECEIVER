/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

/**
 *
 * @author sandeepk
 */
import java.awt.Color;
import java.awt.BasicStroke;
import java.util.Random;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class XYLineChart_AWT extends ApplicationFrame {

    private static final Random rand = new Random();
    private static final int N = 999;

    public XYLineChart_AWT(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                chartTitle,
                "Category",
                "Score",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(xylineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        final XYPlot plot = xylineChart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);
        renderer.setSeriesPaint(2, Color.YELLOW);
        renderer.setSeriesStroke(0, new BasicStroke(1.0f));
        renderer.setSeriesStroke(1, new BasicStroke(1.0f));
        renderer.setSeriesStroke(2, new BasicStroke(1.0f));
        plot.setRenderer(renderer);
        setContentPane(chartPanel);
    }

    private XYDataset createDataset() {
        final XYSeries firefox = new XYSeries("Firefox");

        for (int i = 0; i < N; i++) {
            firefox.add(rand.nextGaussian(), rand.nextGaussian());
        }

        firefox.add(1.0, 1.0);
        firefox.add(2.0, 4.0);
        firefox.add(3.0, 3.0);

        final XYSeries chrome = new XYSeries("Chrome");
        chrome.add(1.0, 4.0);
        chrome.add(2.0, 5.0);
        chrome.add(3.0, 6.0);

        final XYSeries iexplorer = new XYSeries("InternetExplorer");
        iexplorer.add(3.0, 4.0);
        iexplorer.add(4.0, 5.0);
        iexplorer.add(5.0, 4.0);

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(firefox);
        dataset.addSeries(chrome);
        dataset.addSeries(iexplorer);
        return dataset;
    }

    public static void main(String[] args) {
        XYLineChart_AWT chart = new XYLineChart_AWT("Browser Usage Statistics",
                "Which Browser are you using?");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}
