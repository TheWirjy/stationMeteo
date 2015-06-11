
package ch.hearc.meteo.imp.afficheur.real.central;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import ch.hearc.meteo.imp.afficheur.real.central.panel.JPanelDefault;
import ch.hearc.meteo.imp.afficheur.real.central.panel.JPanelListMap;
import ch.hearc.meteo.imp.afficheur.real.central.panel.JPanelMap;
import ch.hearc.meteo.imp.afficheur.real.manage.AfficheurServiceMOO;

@SuppressWarnings("serial") public class JFrameMeteoCentral extends JFrame
	{

	private JFrameMeteoCentral()
		{

		geometry();
		control();
		appearance();
		}

	public synchronized static JFrameMeteoCentral getInstance()
		{
		if (frameMeteoCentral == null)
			{
			frameMeteoCentral = new JFrameMeteoCentral();
			}
		return frameMeteoCentral;
		}

	private void geometry()
		{
		JPanel panelOnglet = new JPanel();
		getContentPane().add(panelOnglet);
		panelOnglet.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		panelOnglet.add(tabbedPane, BorderLayout.CENTER);

		panelDefaultTabbed = new JPanelDefault();
		tabbedPane.addTab("Default", null, panelDefaultTabbed, null);

		JPanel panelMapTabbed = new JPanel();
		tabbedPane.addTab("Map", null, panelMapTabbed, null);
		panelMapTabbed.setLayout(new BorderLayout());

		JPanelMap panelMap = new JPanelMap();
		panelMapTabbed.add(panelMap, BorderLayout.CENTER);

		JPanelListMap panelListMap = new JPanelListMap(panelMap);
		panelMapTabbed.add(panelListMap, BorderLayout.EAST);

		}

	private void control()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	public void addStation(AfficheurServiceMOO afficheurServiceMOO)
		{
		panelDefaultTabbed.addStation(afficheurServiceMOO);
		}

	private void appearance()
		{
		setSize(600, 400);
		setLocationRelativeTo(null); // frame centrer
		setVisible(true); // last!
		}


	private JPanelDefault panelDefaultTabbed;
	private static JFrameMeteoCentral frameMeteoCentral;

	}
