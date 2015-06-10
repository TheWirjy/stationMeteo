
package ch.hearc.meteo.imp.afficheur.real.local;

import javax.swing.JFrame;

import ch.hearc.meteo.imp.afficheur.real.local.panel.JPanelStationMeteo;
import ch.hearc.meteo.imp.afficheur.real.manage.AfficheurServiceMOO;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;

public class JFrameStationMeteo extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameStationMeteo(AfficheurServiceMOO afficheurServiceMOO)
		{
		this.afficheurServiceMOO = afficheurServiceMOO;

		geometry();
		control();
		apparence();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void refresh()
		{
		panelStationMeteo.update();
		}

	public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{
		panelStationMeteo.updateMeteoServiceOptions( meteoServiceOptions);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		panelStationMeteo = new JPanelStationMeteo(afficheurServiceMOO);
		add(panelStationMeteo);
		}

	private void control()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void apparence()
		{
		setTitle(afficheurServiceMOO.getTitre());
		setSize(650, 650);
		setResizable(true);
		setVisible(true);
		}


	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private AfficheurServiceMOO afficheurServiceMOO;

	// Tools
	private JPanelStationMeteo panelStationMeteo;

	}


//
//package ch.hearc.meteo.imp.afficheur.real.local;
//
//import java.awt.BorderLayout;
//
//import javax.swing.JFrame;
//
//import ch.hearc.meteo.imp.afficheur.real.local.panel.JPanelStationMeteo;
//import ch.hearc.meteo.imp.afficheur.simulateur.moo.AfficheurServiceMOO;
//
//public class JFrameStationMeteo extends JFrame
//	{
//
//	/*------------------------------------------------------------------*\
//	|*							Constructeurs							*|
//	\*------------------------------------------------------------------*/
//
//	public JFrameStationMeteo(AfficheurServiceMOO afficheurServiceMOO)
//		{
//		this.afficheurServiceMOO = afficheurServiceMOO;
//
//		geometry();
//		control();
//		apparence();
//		}
//
//	/*------------------------------------------------------------------*\
//	|*							Methodes Public							*|
//	\*------------------------------------------------------------------*/
//
//
//
//	/*------------------------------------------------------------------*\
//	|*							Methodes Private						*|
//	\*------------------------------------------------------------------*/
//
//	private void geometry()
//		{
//		setLayout(new BorderLayout());
//		panelStationMeteo = new JPanelStationMeteo("Station météo", afficheurServiceMOO);
//
//
//
//		add(panelStationMeteo,BorderLayout.CENTER);
//		}
//
//	private void control()
//		{
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		}
//
//	private void apparence()
//		{
//		setSize(650, 650);
//		setResizable(true);
//		setTitle("Station Météo");
//		setVisible(true);
//		}
//
//	/*------------------------------------------------------------------*\
//	|*							Attributs Private						*|
//	\*------------------------------------------------------------------*/
//
//	// Tools
//	private JPanelStationMeteo panelStationMeteo;
//
//	// Input
//	private AfficheurServiceMOO afficheurServiceMOO;
//	}
