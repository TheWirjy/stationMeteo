
package ch.hearc.meteo.imp.afficheur.real.local.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.MeteoService_I;
import ch.hearc.meteo.spec.com.meteo.exception.MeteoServiceException;

public class JPanelControl extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public JPanelControl(final JPanelAffichage panelAfficheur, final MeteoService_I meteoService)
		{
		this.panelAfficheur = panelAfficheur;

		createComposant();
		addComposant();
		setPropriete();

		addListener(meteoService);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public boolean getIsRunning()
		{
		return this.isRunning;
		}

	public JPanelAffichage getPanelAfficheur()
		{
		return this.panelAfficheur;
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setEnableStart(boolean enable)
		{
		boutonStart.setEnabled(enable);
		}

	public void setEnableStop(boolean enable)
		{
		boutonStop.setEnabled(enable);
		}

	public void setTextLabelEtat(String text)
		{
		LabelEtat.setText(text);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void setPropriete()
		{
		this.boutonPause.setBorderPainted(false);
		this.boutonPause.setContentAreaFilled(false);
		this.boutonStart.setEnabled(true);
		this.boutonStop.setEnabled(false);
		this.LabelEtat.setText(JPanelControl.DISCONNECTED);
		}

	private void addComposant()
		{
		this.add(boutonStart);
		this.add(boutonStop);
		this.add(boutonPause);
		this.add(LabelEtat);
		}

	private void createComposant()
		{
		this.imagePause = new ImageIcon("pause.jpg");
		this.imagePlay = new ImageIcon("play.jpg");
		this.boutonPause = new JButton(imagePause);
		this.boutonStart = new JButton("Start");
		this.boutonStop = new JButton("Stop");
		this.LabelEtat = new JLabel();
		}

	private void addListener(final MeteoService_I meteoService)
		{

		this.boutonStart.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					Thread threadConnection = new Thread(new Runnable()
						{
							final int altitudeDT = panelAfficheur.getPanelAltitude().getPanelDigital().getSliderTimeValue();
							final int pressionDT = panelAfficheur.getPanelPression().getPanelDigital().getSliderTimeValue();
							final int temperatureDT = panelAfficheur.getPanelTemperature().getPanelDigital().getSliderTimeValue();


							@Override
							public void run()
								{
								try
									{
									LabelEtat.setText(CONNECTING);
									setEnableStart(false);
									setEnableStop(false);
									panelAfficheur.setEnableAllSlider(false);
									meteoService.connect();
									LabelEtat.setText(CONNECTED);
									meteoService.start(new MeteoServiceOptions(altitudeDT, pressionDT, temperatureDT));
									setEnableStart(false);
									setEnableStop(true);
									}
								catch (Exception e)
									{
									JOptionPane.showMessageDialog(null, MESSAGE_ERROR_CONNEXION, "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
									LabelEtat.setText(DISCONNECTED);
									setEnableStart(true);
									setEnableStop(false);
									panelAfficheur.setEnableAllSlider(true);
									}
								}
						});

					threadConnection.start();
					}
			});

		this.boutonStop.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					Thread threadDeconnection = new Thread(new Runnable()
						{

							@Override
							public void run()
								{
								LabelEtat.setText(DISCONNECTING);
								setEnableStart(false);
								setEnableStop(false);
								meteoService.stop();
								try
									{
									meteoService.disconnect();
									}
								catch (MeteoServiceException e)
									{
									// TODO Auto-generated catch block
									e.printStackTrace();
									}
								LabelEtat.setText(DISCONNECTED);
								setEnableStart(true);
								setEnableStop(false);
								panelAfficheur.setEnableAllSlider(true);
								}
						});

					threadDeconnection.start();
					}
			});

		this.boutonPause.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					isRunning = true;

					if (isRunning == true)
						{
						boutonPause.setIcon(imagePause);
						}
					else
						{
						boutonPause.setIcon(imagePlay);
						}
					}
			});
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//Inputs
	JPanelAffichage panelAfficheur;

	//Tools
	private JButton boutonStart;
	private JButton boutonStop;
	private JButton boutonPause;
	private JLabel LabelEtat;

	private ImageIcon imagePause;
	private ImageIcon imagePlay;

	private boolean isRunning = true;

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	public static final String CONNECTED = "Connecté";
	public static final String DISCONNECTED = "Deconnecté";
	public static final String CONNECTING = "Connexion en cours...";
	public static final String DISCONNECTING = "Deconnexion en cours...";
	private static final String MESSAGE_ERROR_CONNEXION = "Verifiez que le materiel soit connecté correctement";

	}
