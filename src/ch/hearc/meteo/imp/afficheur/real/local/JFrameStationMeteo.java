
package ch.hearc.meteo.imp.afficheur.real.local;

import javax.swing.JFrame;
import javax.swing.UIManager;

import ch.hearc.meteo.imp.afficheur.real.local.panel.JPanelStationMeteo;
import ch.hearc.meteo.imp.afficheur.real.manage.AfficheurServiceMOO;

public class JFrameStationMeteo extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameStationMeteo(AfficheurServiceMOO afficheurServiceMOO)
		{
		try
		{
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}
	catch (Throwable e)
		{
		e.printStackTrace();
		}
		this.afficheurServiceMOO = afficheurServiceMOO;

		geometry();
		control();
		apparence();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/



	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		JPanelStationMeteo panelStationMeteo= new JPanelStationMeteo(afficheurServiceMOO);
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



	}


