
package ch.hearc.meteo.imp.afficheur.real.central.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import ch.hearc.meteo.imp.afficheur.real.manage.AfficheurServiceMOO;

public class JPanelTestMini extends JPanel
	{

	public JPanelTestMini(AfficheurServiceMOO afficheurServiceMOO)
		{
		this.afficheurServiceMOO = afficheurServiceMOO;
		System.out.println("testmini");
		TitledBorder titlesborder = BorderFactory.createTitledBorder("Station ");
		//titlesborder.setTitleColor(Color.blue);
		titlesborder.setTitleFont(new Font("Calibri", Font.BOLD, 13));
		setBorder(titlesborder);
		setForeground(Color.BLACK);
		addMouseListener(new MouseAdapter()
			{

				Boolean swap = false;

				@Override
				public void mousePressed(MouseEvent e)
					{
					if (swap == false)
						{
						Color LightBlue = new Color(170, 170, 170);
						JPanelTestMini.this.setBackground(LightBlue);
						swap = true;
						}
					else
						{
						swap = false;
						JPanelTestMini.this.setBackground(UIManager.getColor("Button.background"));

						}
					JPanelTestMini.this.isSelected = swap;

					}
			});
		setLayout(new GridLayout(0, 1, 0, 0));

		lblLocation = new JLabel("Location : ");
		lblLocation.setFont(new Font("Calibri", Font.PLAIN, 10));
		add(lblLocation);

		lblTemperature = new JLabel("Temp\u00E9rature : " + afficheurServiceMOO.getStatTemperature().getLast());
		lblTemperature.setFont(new Font("Calibri", Font.PLAIN, 10));
		add(lblTemperature);

		lblPression = new JLabel("Pression :" + afficheurServiceMOO.getStatPression().getLast());
		lblPression.setFont(new Font("Calibri", Font.PLAIN, 10));
		add(lblPression);

		lblALtitude = new JLabel("Altitude : " + afficheurServiceMOO.getStatAltitude().getLast());
		lblALtitude.setFont(new Font("Calibri", Font.PLAIN, 10));
		add(lblALtitude);
		}

	public void refresh()
		{
		System.out.println("val:" + afficheurServiceMOO.getStatTemperature().getLast());
		lblTemperature.setText("Temp\u00E9rature : " + afficheurServiceMOO.getStatTemperature().getLast());
		lblPression.setText("Pression : " + afficheurServiceMOO.getStatPression().getLast());
		lblALtitude.setText("Altitude : " + afficheurServiceMOO.getStatAltitude().getLast());
		}

	private JPanelTestMini panelTestMini;
	private JLabel lblLocation;
	private JLabel lblTemperature;
	private JLabel lblPression;
	private JLabel lblALtitude;
	private AfficheurServiceMOO afficheurServiceMOO;

	public Boolean isSelected()
		{
		return isSelected;
		}

	private Boolean isSelected;

	}
