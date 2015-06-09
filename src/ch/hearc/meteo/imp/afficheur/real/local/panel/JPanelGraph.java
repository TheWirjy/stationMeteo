
package ch.hearc.meteo.imp.afficheur.real.local.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;

public class JPanelGraph extends JComponent implements AfficheurService_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public JPanelGraph(String titre, String unite)
		{
		this.titre = titre;
		this.unite = unite;
		compteurGraph = 0;
		geometry();
		control();
		apparance();

		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	private Image bg;

	/** Surcharge de la fonction paintComponent() pour afficher notre image */
	@Override
	public void paintComponent(Graphics g)
		{
		g.drawImage(bg, 0, 0, null);
		}

	@Override
	public void printPression(MeteoEvent event)
		{
		draw(event.getValue());

		}

	@Override
	public void printAltitude(MeteoEvent event)
		{
		draw(event.getValue());

		}

	@Override
	public void printTemperature(MeteoEvent event)
		{
		draw(event.getValue());

		}

	@Override
	public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{

		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	private void draw(float value)
		{

		compteurGraph++;
		deltat = 0.1;
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
		//		final ImageIcon backgroundImg = ImageLoader.loadSynchroneJar("ressources/background.png");
		//		JLabel lblNewLabel = new JLabel("New label");
		//		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//		lblNewLabel.setIcon(backgroundImg);
		//		add(lblNewLabel,BorderLayout.CENTER);

		}

	private void geometry()
		{

		setLayout(new java.awt.BorderLayout());
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


		JPanelBackground background = new JPanelBackground("/background3.png");
		chart.setBackgroundImage(background.bgimage);

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
	}
