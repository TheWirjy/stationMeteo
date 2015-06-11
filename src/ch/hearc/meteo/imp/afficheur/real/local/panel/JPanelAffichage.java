
package ch.hearc.meteo.imp.afficheur.real.local.panel;

import java.awt.GridLayout;
import java.rmi.RemoteException;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.hearc.meteo.imp.afficheur.real.manage.AfficheurServiceMOO;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;

public class JPanelAffichage extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelAffichage(AfficheurServiceMOO afficheurServiceMOO)
		{
		this.afficheurServiceMOO = afficheurServiceMOO;
		geometry();
		control();
		apparance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void setEnableAllSlider(boolean enable)
		{
		panelAltitude.getPanelGraphInfo().setEnableSlider(enable);
		panelPression.getPanelGraphInfo().setEnableSlider(enable);
		panelTemperature.getPanelGraphInfo().setEnableSlider(enable);
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

		panelTemperature = new JPanelGraphInfo("Température", "°C", afficheurServiceMOO.getStatTemperature(),afficheurServiceMOO);
		panelPression = new JPanelGraphInfo("Pression", "hPa", afficheurServiceMOO.getStatPression(),afficheurServiceMOO);
		panelAltitude = new JPanelGraphInfo("Altitude", "m", afficheurServiceMOO.getStatAltitude(),afficheurServiceMOO);
		}

	private void control()
		{
		GridLayout gridLayout = new GridLayout(3, 1);
		gridLayout.setVgap(3);

		setLayout(gridLayout);

		this.add(panelTemperature);
		this.add(panelPression);
		this.add(panelAltitude);
		saveSliderValue();
		updateSliderValue();

		}

	public void saveSliderValue()
		{
		slideTemp = panelTemperature.getSlider();
		slideTemp.addChangeListener(new ChangeListener()
			{

				@Override public void stateChanged(ChangeEvent e)
					{
					try
						{
						meteoOptions = afficheurServiceMOO.getMeteoServiceOptions();
						meteoOptions.setTemperatureDT(slideTemp.getValue());
						afficheurServiceMOO.setMeteoServiceOptions(meteoOptions);

						}
					catch (RemoteException e1)
						{
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
					}
			});

		slidPres = panelPression.getSlider();
		slidPres.addChangeListener(new ChangeListener()
			{

				@Override public void stateChanged(ChangeEvent e)
					{
					try
						{
						meteoOptions = afficheurServiceMOO.getMeteoServiceOptions();
						meteoOptions.setPressionDT(slidPres.getValue());
						afficheurServiceMOO.setMeteoServiceOptions(meteoOptions);

						}
					catch (RemoteException e1)
						{
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
					}
			});

		slidAlt = panelAltitude.getSlider();
		slidAlt.addChangeListener(new ChangeListener()
			{

				@Override public void stateChanged(ChangeEvent e)
					{
					try
						{
						meteoOptions = afficheurServiceMOO.getMeteoServiceOptions();
						meteoOptions.setAltitudeDT(slidAlt.getValue());
						afficheurServiceMOO.setMeteoServiceOptions(meteoOptions);

						}
					catch (RemoteException e1)
						{
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
					}
			});

		}

	public void updateSliderValue()
		{
		Thread t1 = new Thread(new Runnable()
			{

				@Override public void run()
					{
					JSlider slideTemp = panelTemperature.getSlider();
					JSlider slidPres = panelPression.getSlider();
					JSlider slideAlt = panelAltitude.getSlider();

					while(true)
						{

						try
							{
							slideTemp.setValue((int)afficheurServiceMOO.getMeteoServiceOptions().getTemperatureDT());
							slidPres.setValue((int)afficheurServiceMOO.getMeteoServiceOptions().getPressionDT());
							slideAlt.setValue((int)afficheurServiceMOO.getMeteoServiceOptions().getAltitudeDT());
							System.out.println((int)afficheurServiceMOO.getMeteoServiceOptions().getTemperatureDT());
							}
						catch (RemoteException e1)
							{
							// TODO Auto-generated catch block
							e1.printStackTrace();
							}

						try
							{
							Thread.sleep(3000);
							}
						catch (InterruptedException e)
							{
							e.printStackTrace();
							}
						}
					}
			});
		t1.start();

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
	private JSlider slidAlt;
	private JSlider slidPres;
	private JSlider slideTemp;
	private MeteoServiceOptions meteoOptions;

	private static final int N = 30;
	private AfficheurServiceMOO afficheurServiceMOO;

	}
