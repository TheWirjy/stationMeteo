
package ch.hearc.meteo.imp.afficheur.real.central;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import ch.hearc.meteo.imp.afficheur.real.central.panel.JPanelDefault;
import ch.hearc.meteo.imp.afficheur.real.central.panel.JPanelListMap;
import ch.hearc.meteo.imp.afficheur.real.central.panel.JPanelMap;
import ch.hearc.meteo.imp.afficheur.real.manage.AfficheurServiceMOO;

public class JFrameMeteoCentral extends JFrame
	{

	public JFrameMeteoCentral(AfficheurServiceMOO afficheurServiceMOO)
		{
		this.afficheurServiceMOO = afficheurServiceMOO;
		geometry();
		control();
		appearance();
		}

	private void geometry()
		{
		JPanel panelOnglet = new JPanel();
		getContentPane().add(panelOnglet);
		panelOnglet.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		panelOnglet.add(tabbedPane, BorderLayout.CENTER);

		panelDefaultTabbed = new JPanelDefaultafficheurServiceMOO);
		tabbedPane.addTab("Default", null, panelDefaultTabbed, null);

		JPanel panelMapTabbed = new JPanel();
		tabbedPane.addTab("Map", null, panelMapTabbed, null);
		panelMapTabbed.setLayout(new GridLayout(0, 2, 0, 0));

		JPanelMap panelMap = new JPanelMap(panelDefaultTabbed);
		panelMapTabbed.add(panelMap, BorderLayout.CENTER);

		JPanelListMap panelListMap = new JPanelListMap(panelMap);
		panelMapTabbed.add(panelListMap);

		JPanel panelMenu = new JPanel();
		getContentPane().add(panelMenu, BorderLayout.NORTH);
		panelMenu.setLayout(new BorderLayout(0, 0));

		JToolBar toolBarMenu = new JToolBar();
		toolBarMenu.setFloatable(false);
		panelMenu.add(toolBarMenu, BorderLayout.NORTH);

		JButton JButtonFile = new JButton("File");

		toolBarMenu.add(JButtonFile);

		JButton JButtonEdit = new JButton("Edit");
		toolBarMenu.add(JButtonEdit);
		JButton JButtonTest = new JButton("test");
		JButtonTest.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
					{
					JFrameMeteoCentral.this.panelDefaultTabbed.update();
					}
			});
		toolBarMenu.add(JButtonTest);

		}

	private void control()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void appearance()
		{
		setSize(600, 400);
		setLocationRelativeTo(null); // frame centrer
		setVisible(true); // last!
		}

	private JPanelDefault panelDefaultTabbed;
	private AfficheurServiceMOO afficheurServiceMOO;

	}
