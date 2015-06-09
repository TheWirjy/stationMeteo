
package ch.hearc.meteo.imp.afficheur.real.local.panel;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;

public class JPanelAffichage extends JPanel implements AfficheurService_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelAffichage()
		{
		geometry();
		control();
		apparance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void setEnableAllSlider(boolean enable)
		{
		panelAltitude.getPanelDigital().setEnableSlider(enable);
		panelPression.getPanelDigital().setEnableSlider(enable);
		panelTemperature.getPanelDigital().setEnableSlider(enable);
		}

	@Override
	public void printPression(MeteoEvent event)
		{
		panelPression.printPression(event);
		reduceListSize(listPression, event);
		fillDigital(panelPression, listPression);

		}

	@Override
	public void printAltitude(MeteoEvent event)
		{
		panelAltitude.printAltitude(event);
		reduceListSize(listAltitude, event);
		fillDigital(panelAltitude, listAltitude);

		}

	@Override
	public void printTemperature(MeteoEvent event)
		{
		panelTemperature.printTemperature(event);
		reduceListSize(listTemperature, event);
		fillDigital(panelTemperature, listTemperature);

		}

	@Override
	public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{
		// TODO Auto-generated method stub

		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public JPanelGraphInfo getPanelTemperature()
		{
		return this.panelTemperature;
		}

	public JPanelGraphInfo getPanelPression()
		{
		return this.panelPression;
		}

	public JPanelGraphInfo getPanelAltitude()
		{
		return this.panelAltitude;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	protected void apparance()
		{
		//rien

		}

	private void geometry()
		{
		panelTemperature = new JPanelGraphInfo("Température", "°C");
		panelPression = new JPanelGraphInfo("Pression", "hPa");
		panelAltitude = new JPanelGraphInfo("Altitude", "m");
		listAltitude = new ArrayList<MeteoEvent>(N);
		listPression = new ArrayList<MeteoEvent>(N);
		listTemperature = new ArrayList<MeteoEvent>(N);
		}

	private void control()
		{
		GridLayout gridLayout = new GridLayout(3, 1);
		gridLayout.setVgap(3);

		setLayout(gridLayout);

		this.add(panelTemperature);
		this.add(panelPression);
		this.add(panelAltitude);
		}

	private void fillDigital(JPanelGraphInfo panelMeteoEvent, List<MeteoEvent> list)
		{
		tabMinMoyMax = findMinMoyMax(list);
		panelMeteoEvent.getPanelDigital().setValue(tabMinMoyMax[3]);
		panelMeteoEvent.getPanelDigital().setMaxValue(tabMinMoyMax[2]);
		panelMeteoEvent.getPanelDigital().setMinValue(tabMinMoyMax[0]);
		panelMeteoEvent.getPanelDigital().setMoyValue(tabMinMoyMax[1]);
		panelMeteoEvent.getPanelDigital().refresh();
		}

	/**
	 *
	 * @param list
	 * @return tabMinMeanMax[0] MIN
	 * 						[1] AVG
	 * 						[2] MAX
	 * 						[3] CURRENT
	 */
	private float[] findMinMoyMax(List<MeteoEvent> list)
		{
		float[] tabMinMeanMax = new float[4];

		tabMinMeanMax[0] = list.get(0).getValue();
		tabMinMeanMax[2] = list.get(0).getValue();
		float somme = 0;

		for(MeteoEvent event:list)
			{
			//System.out.println(event.getValue() + " ");

			if (event.getValue() < tabMinMeanMax[0])
				{
				tabMinMeanMax[0] = event.getValue();
				}

			if (event.getValue() > tabMinMeanMax[2])
				{
				tabMinMeanMax[2] = event.getValue();
				}
			tabMinMeanMax[3] = event.getValue();

			somme += event.getValue();

			}

		tabMinMeanMax[1] = somme / list.size();

		return tabMinMeanMax;
		}

	private void reduceListSize(List<MeteoEvent> list, MeteoEvent event)
		{

		while(list.size() >= N)
			{
			list.remove(0);
			}

		list.add(event);
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public static final int getN()
		{
		return N;
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	//Tools
	private JPanelGraphInfo panelTemperature;
	private JPanelGraphInfo panelPression;
	private JPanelGraphInfo panelAltitude;
	private List<MeteoEvent> listAltitude;
	private List<MeteoEvent> listPression;
	private List<MeteoEvent> listTemperature;
	private float[] tabMinMoyMax;
	private static final int N = 30;

	}
