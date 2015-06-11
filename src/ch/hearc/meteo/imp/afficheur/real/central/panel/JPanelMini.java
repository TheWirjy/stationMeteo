
package ch.hearc.meteo.imp.afficheur.real.central.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.hearc.meteo.imp.afficheur.real.local.JFrameStationMeteo;
import ch.hearc.meteo.imp.afficheur.real.manage.AfficheurServiceMOO;
import ch.hearc.meteo.imp.afficheur.simulateur.vue.atome.MathTools;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;

@SuppressWarnings("serial") public class JPanelMini extends JPanel
	{

	public JPanelMini(AfficheurServiceMOO afficheurServiceMOO, String stationName, int stationNo)
		{
		this.afficheurServiceMOO = afficheurServiceMOO;
		this.stationName = stationName;
		this.stationNo = stationNo;
		geometry();
		control();
		apparance();

		}

	private void apparance()
		{
		// RIEN
		}

	private void control()
		{

		addMouseListener(new MouseAdapter()
			{

				Boolean swap = false;

				@Override public void mousePressed(MouseEvent e)
					{
					if (swap == false)
						{
						Color LightBlue = new Color(170, 170, 170);
						JPanelMini.this.setBackground(LightBlue);
						swap = true;
						JFrameStationMeteo frameMeteo = new JFrameStationMeteo(afficheurServiceMOO);
						frameMeteo.setTitle("Station " + stationNo + " : " + stationName);
						frameMeteo.addWindowListener(new WindowAdapter()
							{

								@Override public void windowClosing(WindowEvent e)
									{
									frameMeteo.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
									JPanelMini.this.setBackground(UIManager.getColor("Button.background"));
									}

							});
						}
					else
						{
						swap = false;
						JPanelMini.this.setBackground(UIManager.getColor("Button.background"));

						}
					JPanelMini.this.isSelected = swap;

					}
			});
		slidMiniPres.addChangeListener(new ChangeListener()
			{

				@Override public void stateChanged(ChangeEvent e)
					{
					try
						{
						MeteoServiceOptions meteoOption = afficheurServiceMOO.getMeteoServiceOptions();
						meteoOption.setPressionDT(slidMiniPres.getValue());
						afficheurServiceMOO.setMeteoServiceOptions(meteoOption);
						}
					catch (RemoteException e1)
						{
						e1.printStackTrace();
						}

					}
			});
		slidMiniAlt.addChangeListener(new ChangeListener()
			{

				@Override public void stateChanged(ChangeEvent e)
					{
					try
						{
						MeteoServiceOptions meteoOption = afficheurServiceMOO.getMeteoServiceOptions();
						meteoOption.setAltitudeDT(slidMiniAlt.getValue());
						afficheurServiceMOO.setMeteoServiceOptions(meteoOption);
						}
					catch (RemoteException e1)
						{
						e1.printStackTrace();
						}

					}
			});
		slidMiniTemp.addChangeListener(new ChangeListener()
			{

				@Override public void stateChanged(ChangeEvent e)
					{
					try
						{
						MeteoServiceOptions meteoOption = afficheurServiceMOO.getMeteoServiceOptions();
						meteoOption.setTemperatureDT(slidMiniTemp.getValue());
						afficheurServiceMOO.setMeteoServiceOptions(meteoOption);

						}
					catch (RemoteException e1)
						{
						e1.printStackTrace();
						}

					}
			});
		refreshslides();
		}

	private void geometry()
		{
		setLayout(new GridLayout(0, 1, 0, 0));
		TitledBorder titlesborder = BorderFactory.createTitledBorder("Station " + stationNo);
		titlesborder.setTitleFont(new Font("Calibri", Font.BOLD, 13));
		setBorder(titlesborder);
		setForeground(Color.BLACK);
		slidMiniPres = new JSlider();
		slidMiniPres.setPreferredSize(new Dimension(50, 20));
		slidMiniPres.setMinimum(500);
		slidMiniPres.setMaximum(10000);

		slidMiniTemp = new JSlider();
		slidMiniTemp.setPreferredSize(new Dimension(50, 20));
		slidMiniTemp.setMinimum(500);
		slidMiniTemp.setMaximum(10000);

		slidMiniAlt = new JSlider();
		slidMiniAlt.setPreferredSize(new Dimension(50, 20));
		slidMiniAlt.setMinimum(500);
		slidMiniAlt.setMaximum(10000);

		lblLocation = new JLabel("Location : " + stationName);
		lblLocation.setFont(new Font("Calibri", Font.PLAIN, 10));
		add(lblLocation);

		lblTemperature = new JLabel("Temp\u00E9rature : " + afficheurServiceMOO.getStatTemperature().getLast());
		lblTemperature.setFont(new Font("Calibri", Font.PLAIN, 10));
		add(lblTemperature);
		add(slidMiniTemp);

		lblPression = new JLabel("Pression :" + afficheurServiceMOO.getStatPression().getLast());
		lblPression.setFont(new Font("Calibri", Font.PLAIN, 10));
		add(lblPression);
		add(slidMiniPres);

		lblALtitude = new JLabel("Altitude : " + afficheurServiceMOO.getStatAltitude().getLast());
		lblALtitude.setFont(new Font("Calibri", Font.PLAIN, 10));
		add(lblALtitude);
		add(slidMiniAlt);

		}

	public void refreshslides()
		{
		Thread t1 = new Thread(new Runnable()
			{

				@Override public void run()
					{
					while(true)
						{
						try
							{
							refresh();
							slidMiniTemp.setToolTipText("Dt température " + slidMiniTemp.getValue() + " ms");
							slidMiniPres.setToolTipText("Dt pression " + slidMiniPres.getValue() + " ms");
							slidMiniAlt.setToolTipText("Dt altitude " + slidMiniAlt.getValue() + " ms");

							slidMiniPres.setValue((int)afficheurServiceMOO.getMeteoServiceOptions().getPressionDT());
							slidMiniTemp.setValue((int)afficheurServiceMOO.getMeteoServiceOptions().getTemperatureDT());
							slidMiniAlt.setValue((int)afficheurServiceMOO.getMeteoServiceOptions().getAltitudeDT());
							Thread.sleep(100);
							}
						catch (Exception e1)
							{
							// TODO Auto-generated catch block
							e1.printStackTrace();
							}
						}

					}
			});
		t1.start();
		}

	public void refresh()
		{
		lblTemperature.setText("Temp\u00E9rature : " + MathTools.arrondir(afficheurServiceMOO.getStatTemperature().getLast()));
		lblPression.setText("Pression : " + MathTools.arrondir(afficheurServiceMOO.getStatPression().getLast()));
		lblALtitude.setText("Altitude : " + MathTools.arrondir(afficheurServiceMOO.getStatAltitude().getLast()));
		}

	private JLabel lblLocation;
	private JLabel lblTemperature;
	private JLabel lblPression;
	private JLabel lblALtitude;
	private AfficheurServiceMOO afficheurServiceMOO;
	private JSlider slidMiniPres;
	private JSlider slidMiniAlt;
	private JSlider slidMiniTemp;
	private String stationName;
	private int stationNo;

	public Boolean isSelected()
		{
		return isSelected;
		}

	private Boolean isSelected;

	}
