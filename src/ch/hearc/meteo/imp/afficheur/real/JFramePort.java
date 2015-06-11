
package ch.hearc.meteo.imp.afficheur.real;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import ch.hearc.meteo.imp.com.real.MeteoPortDetectionService;
import ch.hearc.meteo.imp.reseau.RemoteAfficheurCreator;
import ch.hearc.meteo.imp.use.remote.pclocal.PCLocal;
import ch.hearc.meteo.spec.afficheur.AffichageOptions;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;

import com.bilat.tools.reseau.rmi.RmiTools;
import com.bilat.tools.reseau.rmi.RmiURL;

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
		if (listPort.size() > 0)
			{
			for(String string:listPort)
				{
				combo.addItem(string);
				}
			buttonValider.setEnabled(true);
			}
		}

	private void geometry()
		{
		// JComponent : Instanciation
		combo = new JComboBox<String>();
		buttonValider = new JButton("Valider");
		buttonValider.setEnabled(false);
		buttonRefresh = new JButton("Refresh");
		buttonSimulateur = new JButton("Simulateur");
		String strIp = System.getProperty("ip", "157.26.110.116");
		textFieldIP = new JTextField(strIp);


		Box layoutHCombo = Box.createVerticalBox();
		layoutHCombo.add(textFieldIP);
		layoutHCombo.add(Box.createVerticalStrut(10));
		layoutHCombo.add(combo);

		Box layoutVertical = Box.createVerticalBox();

		Box layoutHButton = Box.createHorizontalBox();
		layoutHButton.add(buttonValider);
		layoutHButton.add(buttonRefresh);
		layoutHButton.add(buttonSimulateur);
		layoutVertical.add(Box.createVerticalStrut(10));
		layoutVertical.add(layoutHCombo);
		layoutVertical.add(Box.createVerticalStrut(10));
		layoutVertical.add(layoutHButton);

		setLayout(new FlowLayout(FlowLayout.CENTER));

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
					createPCLocal(false);
					JFramePort.this.dispose();
					}
			});

		buttonRefresh.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					// TODO Auto-generated method stub
					refreshList();
					}
			});

		buttonSimulateur.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					// TODO Auto-generated method stub
					createPCLocal(true);
					JFramePort.this.dispose();
					}
			});

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void createPCLocal(boolean simulateur)
		{
		try
			{
			String strIp = textFieldIP.getText();
			InetAddress ip = InetAddress.getByName(strIp);

			RmiURL rmiUrl = new RmiURL(RemoteAfficheurCreator.RMI_ID, ip, RmiTools.PORT_RMI_DEFAUT);

			String portCom = "";
			if(combo.getItemCount() > 0 && simulateur == false)
				{
				portCom = (String)combo.getSelectedItem();
				}
			else
				{
				portCom = "COM SIMU";
				}

			MeteoServiceOptions meteoServiceOptions = new MeteoServiceOptions(800, 1000, 1200);
			AffichageOptions affichageOptions = new AffichageOptions(3, "Station : " + strIp + " - " + portCom);

			PCLocal pc = new PCLocal(meteoServiceOptions, portCom, affichageOptions, rmiUrl, simulateur);
			pc.run();
			}
		catch (UnknownHostException e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}

	private void appearance()
		{
		setSize(300, 160);
		setLocationRelativeTo(null); // frame centrer
		setVisible(true); // last!
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JComboBox<String> combo;
	private JButton buttonValider;
	private JButton buttonRefresh;
	private JButton buttonSimulateur;
	private JTextField textFieldIP;
	private MeteoPortDetectionService meteoPort;
	private List<String> listPort;
	}
