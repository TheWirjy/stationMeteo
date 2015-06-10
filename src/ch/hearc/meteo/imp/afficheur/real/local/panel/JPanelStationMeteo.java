
package ch.hearc.meteo.imp.afficheur.real.local.panel;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JPanel;

import ch.hearc.meteo.imp.afficheur.real.manage.AfficheurServiceMOO;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;

public class JPanelStationMeteo extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelStationMeteo(AfficheurServiceMOO afficheurServiceMOO)
		{
		this.afficheurServiceMOO = afficheurServiceMOO;
		geometry();
		control();
		apparance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

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
		setLayout(borderLayout);

		add(panelAfficheur, BorderLayout.CENTER);
		//add(panelControl, BorderLayout.SOUTH);
		}

	private void geometry()
		{

		this.panelAfficheur = new JPanelAffichage(afficheurServiceMOO);
		//this.panelControl = new JPanelControl(panelAfficheur, afficheurServiceMOO);
		}

	public void update()
		{
		panelAfficheur.update();
		}

	public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{
		//	panelSlider.updateMeteoServiceOptions( meteoServiceOptions);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//Input
	private String titre;
	private AfficheurServiceMOO afficheurServiceMOO;

	//Tools
	private JPanelAffichage panelAfficheur;
	//private JPanelControl panelControl;

	}
