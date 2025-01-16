/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import tool.Constant;
import tool.Variable;

/**
 *
 * @author sandeepk
 */
public class RunningScatterGraph extends javax.swing.JFrame {

    private XYSeries datasetScatterStraightLineGraph = new XYSeries("", false);
    private XYSeries datasetScatterGraph = new XYSeries("", false);
    private static final Random rand = new Random();
    int i = 0;
    boolean scatter_with_smooth_line_graph;
    int rover_index;

    public RunningScatterGraph(boolean scatter_with_smooth_line_graph, int rover_index, String rover) {
        initComponents();
        setTitle("Graph " + rover + " Fixed Data Points");
        this.rover_index = rover_index;
        this.scatter_with_smooth_line_graph = scatter_with_smooth_line_graph;

        try {
            InputStream is = new BufferedInputStream(getClass().getResourceAsStream(
                    "/img/icon.png"));
            Image image = ImageIO.read(is);
            setIconImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            jPanel_graph.setLayout(new BorderLayout());
            jPanel_graph.removeAll();
            jPanel_graph.repaint();
            jPanel_graph.validate();

            String first_value = getFirstValue(rover_index);

            if (scatter_with_smooth_line_graph) {
                jPanel_graph.add(createPaneScatterStraightLineGraph(first_value));
            } else {
                jPanel_graph.add(createDemoPanel(first_value));
            }

            jPanel_graph.repaint();
            jPanel_graph.validate();
            updateScatterStraightLineGraph(rover_index);
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_graph = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Graph");
        setPreferredSize(new java.awt.Dimension(850, 800));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel_graph.setPreferredSize(new java.awt.Dimension(850, 800));

        javax.swing.GroupLayout jPanel_graphLayout = new javax.swing.GroupLayout(jPanel_graph);
        jPanel_graph.setLayout(jPanel_graphLayout);
        jPanel_graphLayout.setHorizontalGroup(
            jPanel_graphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
        );
        jPanel_graphLayout.setVerticalGroup(
            jPanel_graphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_graph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel_graph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        i = 9999;
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_formWindowClosing

    private ChartPanel createDemoPanel(String first_value) {

        String xy[] = first_value.split(",");
        double x = Double.parseDouble(xy[0]);
        double y = Double.parseDouble(xy[1]);

        ChartPanel chartPanel = null;
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        xySeriesCollection.addSeries(datasetScatterGraph);

        JFreeChart jfreechart = ChartFactory.createScatterPlot(
                "", "Lattitude (" + Variable.reporting_unit + ")", "Longitude (" + Variable.reporting_unit + ")", xySeriesCollection,
                PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) jfreechart.getPlot();
        XYItemRenderer renderer = plot.getRenderer();
        NumberAxis domain = (NumberAxis) plot.getDomainAxis();
        domain.setRange(x - 0.005, x + 0.005);
        NumberAxis range = (NumberAxis) plot.getRangeAxis();
        range.setRange(y - 0.005, y + 0.005);
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.GREEN);
        plot.setRangeGridlinePaint(Color.GREEN);
        plot.getRenderer().setSeriesVisibleInLegend(false);

        plot.setRangePannable(true);
        plot.setDomainPannable(true);

        chartPanel = new ChartPanel(jfreechart);

        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setDomainZoomable(true);
        chartPanel.setRangeZoomable(true);

        return chartPanel;
    }

    private ChartPanel createPaneScatterStraightLineGraph(String first_value) {

        String xy[] = first_value.split(",");
        double x = Double.parseDouble(xy[0]);
        double y = Double.parseDouble(xy[1]);

        ChartPanel chartPanel = null;

        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        xySeriesCollection.addSeries(datasetScatterStraightLineGraph);

        final JFreeChart jfreechart = ChartFactory.createScatterPlot(
                "", "Lattitude (" + Variable.reporting_unit + ")", "Longitude (" + Variable.reporting_unit + ")", xySeriesCollection,
                PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) jfreechart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        plot.setRenderer(renderer);

        NumberAxis domain = (NumberAxis) plot.getDomainAxis();
        domain.setRange(x - 0.005, x + 0.005);
        NumberAxis range = (NumberAxis) plot.getRangeAxis();
        range.setRange(y - 0.005, y + 0.005);

        plot.setBackgroundPaint(Color.white);

        plot.setDomainGridlinePaint(Color.GREEN);
        plot.setRangeGridlinePaint(Color.GREEN);
        plot.getRenderer().setSeriesVisibleInLegend(false);

        plot.setRangePannable(true);
        plot.setDomainPannable(true);

        chartPanel = new ChartPanel(jfreechart);

        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setDomainZoomable(true);
        chartPanel.setRangeZoomable(true);

        return chartPanel;

    }

    private String getFirstValue(int index) {
        try {
            if (Variable.graph_data_change[rover_index]) {
                Variable.graph_data_change[rover_index] = false;
                return get_x_y(index);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    private void updateScatterStraightLineGraph(int rover_index) {
        try {
            new Thread() {
                @Override
                public void run() {

                    for (i = 0; i < Constant.MAX_GRAPH_DATA_POINT;) {

                        if (Variable.graph_data_change[rover_index]) {
                            i++;
                            update(rover_index);
                            Variable.graph_data_change[rover_index] = false;
                        }

                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    dispose_it();
                }
            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String get_x_y(int index) {
        try {
            double x;
            double y;
            x = Double.parseDouble("" + Variable.graph_data_array[index][Variable.graph_data_array_counter[index] - 1][0]);
            y = Double.parseDouble("" + Variable.graph_data_array[index][Variable.graph_data_array_counter[index] - 1][1]);
            return "" + x + "," + y;
        } catch (Exception e) {
            return null;
        }
    }

    private void update(int index) {
        try {
            double x;
            double y;
            x = Double.parseDouble("" + Variable.graph_data_array[index][Variable.graph_data_array_counter[index] - 1][0]);
            y = Double.parseDouble("" + Variable.graph_data_array[index][Variable.graph_data_array_counter[index] - 1][1]);

            if (scatter_with_smooth_line_graph) {
                datasetScatterStraightLineGraph.add(new XYDataItem(x, y));
            } else {
                datasetScatterGraph.add(new XYDataItem(x, y));
            }
        } catch (Exception e) {
        }
    }

    private void dispose_it() {
        try {
            this.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel_graph;
    // End of variables declaration//GEN-END:variables
}
