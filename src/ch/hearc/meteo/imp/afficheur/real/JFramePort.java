
package ch.hearc.meteo.imp.afficheur.real;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import ch.hearc.meteo.imp.com.real.MeteoPortDetectionService;

public class JFramePort extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFramePort()
		{
		this.meteoPort = new MeteoPortDetectionService();
		geometry();
		control();
		appearance();
		refreshList();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void refreshList()
	{
		this.combo.removeAllItems();
		this.listPort = meteoPort.findListPortSerie();
		for(String string:listPort)
			{
			combo.addItem(string);
			}
	}

	private void geometry()
		{
			// JComponent : Instanciation
			combo = new JComboBox<String>();
			buttonValider = new JButton("Valider");
			button_refresh = new JButton("Refresh");

			Box layout2 = Box.createHorizontalBox();
			layout2.add(combo);

			Box layoutVertical = Box.createVerticalBox();

			Box layout = Box.createHorizontalBox();
			layout.add(buttonValider);
			layout.add(button_refresh);
			layoutVertical.add(Box.createVerticalStrut(10));
			layoutVertical.add(layout2);
			layoutVertical.add(Box.createVerticalStrut(10));
			layoutVertical.add(layout);

			// Layout : Specification
			{
			BorderLayout borderLayout = new BorderLayout();
			setLayout(new FlowLayout(FlowLayout.CENTER));

			// borderLayout.setHgap(20);
			// borderLayout.setVgap(20);
			}

		// JComponent : add
		//add(combo,BorderLayout.CENTER);
		add(layoutVertical);
		}

	private void control()
		{
		buttonValider.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					// TODO Auto-generated method stub
					System.out.println("ouvre frame");
					}
			});

		button_refresh.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					// TODO Auto-generated method stub
					refreshList();
					}
			});

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void appearance()
		{
		setSize(200, 130);
		setLocationRelativeTo(null); // frame centrer
		setVisible(true); // last!
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JComboBox<String> combo;
	private JButton buttonValider;
	private JButton button_refresh;
	private MeteoPortDetectionService meteoPort;
	private List<String> listPort;
	}

