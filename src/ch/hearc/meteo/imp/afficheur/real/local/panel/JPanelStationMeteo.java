
package ch.hearc.meteo.imp.afficheur.real.local.panel;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JPanel;

import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.MeteoService_I;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;

public class JPanelStationMeteo extends JPanel implements AfficheurService_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelStationMeteo(String titre, MeteoService_I meteoService)
		{
		this.meteoService = meteoService;
		this.titre = titre;
		geometry();
		control();
		apparance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	@Override
	public void printPression(MeteoEvent event)
		{
		panelAfficheur.printPression(event);

		}

	@Override
	public void printAltitude(MeteoEvent event)
		{
		panelAfficheur.printAltitude(event);

		}

	@Override
	public void printTemperature(MeteoEvent event)
		{
		panelAfficheur.printTemperature(event);

		}

	@Override
	public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{
		// TODO Auto-generated method stub

		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public String getTitreStation()
		{
		return this.titre;
		}

	public JPanelAffichage getPanelAfficheur()
		{
		return this.panelAfficheur;
		}

	public JPanelControl getPanelBoutons()
		{
		return this.panelControl;
		}

	public MeteoService_I getMeteoService()
		{
		return this.meteoService;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void apparance()
		{
		//RIEN
		}

	private void control()
		{
		Box boxH = Box.createHorizontalBox();
		boxH.add(Box.createHorizontalGlue());

		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setHgap(5);
		borderLayout.setVgap(5);
		setLayout(borderLayout);

		add(boxH, BorderLayout.NORTH);
		add(panelAfficheur, BorderLayout.CENTER);
		add(panelControl, BorderLayout.SOUTH);
		}

	private void geometry()
		{

		this.panelAfficheur = new JPanelAffichage();
		this.panelControl = new JPanelControl(panelAfficheur, meteoService);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//Input
	private String titre;
	private MeteoService_I meteoService;

	//Tools
	private JPanelAffichage panelAfficheur;
	private JPanelControl panelControl;

	}
