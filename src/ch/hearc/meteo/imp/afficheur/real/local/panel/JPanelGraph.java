
package ch.hearc.meteo.imp.afficheur.real.local.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import ch.hearc.meteo.imp.afficheur.real.local.ImageLoader;
import ch.hearc.meteo.imp.afficheur.real.manage.Stat;

public class JPanelGraph extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public JPanelGraph(String titre, String unite, Stat stat)
		{
		this.stat = stat;
		this.titre = titre;
		this.unite = unite;
		compteurGraph = 0;
		geometry();
		control();
		apparance();
		useDraw();

		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void useDraw()
		{
		Thread t1 = new Thread(new Runnable()
			{

				@Override public void run()
					{

					while(true)
						{
						draw(stat.getLast());
						attendre(1000);
						}

					}
			});
		t1.start();
		}

	private static void attendre(long delay)
		{
		try
			{
			Thread.sleep(delay);
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		}

	private void draw(float value)
		{

		compteurGraph++;
		deltat = 1;
		if (series.getItemCount() > JPanelAffichage.getN())
			{
			series.clear();
			}
		series.add(deltat * compteurGraph, value);
		}

	private void control()
		{
		//rien
		}

	private void apparance()
		{
		//rien

		}

	private void geometry()
		{

		setLayout(new BorderLayout());
		series = new XYSeries(unite);
		// Add the series to your data set
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		// Generate the graph
		JFreeChart chart = ChartFactory.createXYLineChart(titre, // Title
				"DeltaT (ms)", // x-axis Label
				titre + " (" + unite + ")", // y-axis Label
				dataset, // Dataset
				PlotOrientation.VERTICAL, // Plot Orientation
				false, // Show Legend
				true, // Use tooltips
				true // Configure chart to generate URLs?
				);
		//background graph
		XYPlot plot = (XYPlot)chart.getPlot();
		plot.setBackgroundPaint(new Color(21, 105, 199, 70));
		plot.setDomainGridlinePaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.WHITE);

		//line
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		java.awt.geom.Ellipse2D.Double formRond = new java.awt.geom.Ellipse2D.Double(-4D, -4D, 8D, 8D);
		renderer.setSeriesShape(0, formRond);
		renderer.setSeriesPaint(0, Color.BLACK); //Couleur des petit rond sur le graphique
		plot.setRenderer(renderer);
		plot.getRenderer().setSeriesPaint(0, Color.BLACK);
		//background
		//chart.setBackgroundPaint(UIManager.getColor("Button.background"));
		//chart.setBackgroundPaint(new Color(0, 0, 0, 0)); //rendre le background transparant

		final ImageIcon bg = ImageLoader.loadSynchroneJar("ressources/background3.png");

		//JPanelBackground background = new JPanelBackground(bg.getImage());
		chart.setBackgroundImage(bg.getImage());

		//Font
		Font font = new Font("Arial", Font.PLAIN, 15);
		plot.getDomainAxis().setTickLabelFont(font);
		plot.getRangeAxis().setTickLabelFont(font);
		plot.getDomainAxis().setTickLabelPaint(Color.BLACK);
		plot.getRangeAxis().setTickLabelPaint(Color.BLACK);
		plot.getDomainAxis().setLabelPaint(Color.BLACK);
		plot.getRangeAxis().setLabelPaint(Color.BLACK);

		ChartPanel CP = new ChartPanel(chart);
		add(CP, BorderLayout.CENTER);
		validate();

		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private double deltat;
	private int compteurGraph;
	private XYSeries series;
	private String titre;
	private String unite;
	private Stat stat;

	}
