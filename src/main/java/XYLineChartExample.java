import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * This program demonstrates how to draw XY line chart with XYDataset
 * using JFreechart library.
 * @author www.codejava.net
 *
 */
public class XYLineChartExample extends JFrame {

	private List<Integer> xCoods;
	private List<Integer> yCoods;

	public XYLineChartExample(List<Integer> xValues,List<Integer> yValues) {
		super("Time v/s Heart Rate");

		xCoods = new ArrayList<>(xValues);
		yCoods = new ArrayList<>(yValues);

		JPanel chartPanel = createChartPanel();
		add(chartPanel, BorderLayout.CENTER);

		setSize(640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	private JPanel createChartPanel() {
		String chartTitle = "Time v/s Heart Rate";
		String xAxisLabel = "Time(minutes)";
		String yAxisLabel = "Heart Rate(Beats)";

		
		XYDataset dataset = createDataset();

		boolean showLegend = false;
		boolean createURL = false;
		boolean createTooltip = false;

		JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
				xAxisLabel, yAxisLabel, dataset,
				PlotOrientation.VERTICAL, showLegend, createTooltip, createURL);
		
		customizeChart(chart);
		
		// saves the chart as an image files
		File imageFile = new File("XYLineChart.png");
		int width = 640;
		int height = 480;
		
		try {
			ChartUtilities.saveChartAsPNG(imageFile, chart, width, height);
		} catch (IOException ex) {
			System.err.println(ex);
		}
		
		return new ChartPanel(chart);
	}

	private XYDataset createDataset() {
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries series1 = new XYSeries("Object 1");

		for(int i = 0 ; i < xCoods.size(); i++){
			series1.add(xCoods.get(i), yCoods.get(i));
		}

		dataset.addSeries(series1);
		return dataset;
	}
	
	private void customizeChart(JFreeChart chart) {
		XYPlot plot = chart.getXYPlot();
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

		// sets paint color for each series
		renderer.setSeriesPaint(0, Color.RED);

		// sets thickness for series (using strokes)
		renderer.setSeriesStroke(0, new BasicStroke(4.0f));
		
		// sets paint color for plot outlines
		plot.setOutlinePaint(Color.BLUE);
		plot.setOutlineStroke(new BasicStroke(2.0f));
		
		// sets renderer for lines
		plot.setRenderer(renderer);
		
		// sets plot background
		plot.setBackgroundPaint(Color.DARK_GRAY);
		
		// sets paint color for the grid lines
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		
		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);
		
	}

	public List<Integer> getxCoods() {
		return xCoods;
	}

	public void setxCoods(List<Integer> xCoods) {
		this.xCoods = xCoods;
	}

	public List<Integer> getyCoods() {
		return yCoods;
	}

	public void setyCoods(List<Integer> yCoods) {
		this.yCoods = yCoods;
	}
}