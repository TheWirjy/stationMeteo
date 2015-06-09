
package ch.hearc.meteo.imp.afficheur.real.local.panel;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;

public class JPanelGraphInfo extends JPanel implements AfficheurService_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelGraphInfo(String titre, String unite)
		{
		this.titre = titre;
		this.unite = unite;
		geometry();
		control();
		apparance();

		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public JPanelInfo getPanelDigital()
		{
		return this.panelDigital;
		}

	public JPanelGraph getPanelGraph()
		{
		return this.panelGraph;
		}

	@Override
	public void printPression(MeteoEvent event)
		{
		panelGraph.printPression(event);

		}

	@Override
	public void printAltitude(MeteoEvent event)
		{
		panelGraph.printAltitude(event);

		}

	@Override
	public void printTemperature(MeteoEvent event)
		{
		panelGraph.printTemperature(event);

		}

	@Override
	public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{
		// TODO Auto-generated method stub

		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		panelDigital = new JPanelInfo(titre, unite);
		panelGraph = new JPanelGraph(titre, unite);
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setHgap(5);
		setLayout(borderLayout);

		TitledBorder border = BorderFactory.createTitledBorder("");
		panelDigital.setBorder(border);

		add(panelGraph, BorderLayout.CENTER);
		add(panelDigital, BorderLayout.EAST);
		add(Box.createHorizontalStrut(1), BorderLayout.WEST);
		}

	private void control()
		{
		//rien
		}

	private void apparance()
		{
		//Rien


		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Tools
	private JPanelInfo panelDigital;
	private JPanelGraph panelGraph;

	//Input
	private String titre;
	private String unite;

	}
