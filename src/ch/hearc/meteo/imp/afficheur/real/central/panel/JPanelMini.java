
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

public class JPanelMini extends JPanel
	{

	public JPanelMini(int numero, String stationName)
		{

		TitledBorder titlesborder = BorderFactory.createTitledBorder("Station " + numero);
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
						JPanelMini.this.setBackground(LightBlue);
						swap = true;
						}
					else
						{
						swap = false;
						JPanelMini.this.setBackground(UIManager.getColor("Button.background"));

						}
					JPanelMini.this.isSelected = swap;

					}
			});
		setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblLocation = new JLabel("Location : " + stationName);
		lblLocation.setFont(new Font("Calibri", Font.PLAIN, 10));
		add(lblLocation);

		JLabel lblTemperature = new JLabel("Temp\u00E9rature : ");
		lblTemperature.setFont(new Font("Calibri", Font.PLAIN, 10));
		add(lblTemperature);

		JLabel lblPression = new JLabel("Pression :");
		lblPression.setFont(new Font("Calibri", Font.PLAIN, 10));
		add(lblPression);

		JLabel lblNewLabel_2 = new JLabel("Altitude : ");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 10));
		add(lblNewLabel_2);

		}

	public Boolean isSelected()
		{
		return isSelected;
		}

	private Boolean isSelected;

	}
