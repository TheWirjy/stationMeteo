
package ch.hearc.meteo.imp.afficheur.real.local;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import ch.hearc.meteo.imp.afficheur.real.local.panel.JPanelBackground;
import ch.hearc.meteo.imp.afficheur.real.local.panel.JPanelStationMeteo;
import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.MeteoService_I;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;

public class JFrameStationMeteo extends JFrame implements AfficheurService_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameStationMeteo(MeteoService_I meteoService)
		{
		this.meteoService = meteoService;

		geometry();
		control();
		apparence();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void printPression(MeteoEvent event)
		{
		panelStationMeteo.printPression(event);
		}

	@Override
	public void printAltitude(MeteoEvent event)
		{
		panelStationMeteo.printAltitude(event);
		}

	@Override
	public void printTemperature(MeteoEvent event)
		{
		panelStationMeteo.printTemperature(event);
		}

	@Override
	public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{
		panelStationMeteo.updateMeteoServiceOptions(meteoServiceOptions);

		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		setLayout(new BorderLayout());
		panelStationMeteo = new JPanelStationMeteo("Station météo", meteoService);



		add(panelStationMeteo,BorderLayout.CENTER);
		}

	private void control()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void apparence()
		{
		setSize(650, 650);
		setResizable(true);
		setTitle("Station Météo");
		setVisible(true);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelStationMeteo panelStationMeteo;
	private JPanelBackground panelBackGround;

	// Input
	private MeteoService_I meteoService;
	}
