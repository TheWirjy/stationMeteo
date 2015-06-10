
package ch.hearc.meteo.imp.afficheur.real.local.panel;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;

import ch.hearc.meteo.imp.afficheur.real.manage.AfficheurServiceMOO;
import ch.hearc.meteo.imp.afficheur.real.manage.Stat;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;

public class JPanelGraphInfo extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelGraphInfo(String titre, String unite, Stat stat, List<MeteoEvent> list, AfficheurServiceMOO afficheurServiceMOO)
		{
		this.afficheurServiceMOO = afficheurServiceMOO;
		this.stat = stat;
		this.list = list;
		this.titre = titre;
		this.unite = unite;

		geometry();

		control();
		apparance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public void update()
		{
		panelInfo.update();
		}

	public JPanelInfo getPanelGraphInfo()
		{
		return this.panelInfo;
		}

	public JPanelGraph getPanelGraph()
		{
		return this.panelGraph;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{

		panelInfo = new JPanelInfo(titre, unite, stat, afficheurServiceMOO);
		panelGraph = new JPanelGraph(titre, unite, stat, list, afficheurServiceMOO);
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setHgap(5);
		setLayout(borderLayout);

		TitledBorder border = BorderFactory.createTitledBorder("");
		panelInfo.setBorder(border);

		add(panelGraph, BorderLayout.CENTER);
		add(panelInfo, BorderLayout.EAST);
		add(Box.createHorizontalStrut(1), BorderLayout.WEST);
		}

	private void control()
		{

		}

	private void apparance()
		{
		//Rien

		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public int getSliderValue()
		{
		return panelInfo.getSliderValue();
		}

	public JSlider getSlider()
		{
		return panelInfo.getSlider();
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	public void setSliderValue(int value)
	{
	panelInfo.setSliderValue(value);
	}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Tools
	private JPanelInfo panelInfo;
	private JPanelGraph panelGraph;
	private List<MeteoEvent> list;
	private Stat stat;
	private AfficheurServiceMOO afficheurServiceMOO;

	//Input
	private String titre;
	private String unite;

	}
