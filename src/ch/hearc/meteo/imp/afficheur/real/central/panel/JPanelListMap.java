
package ch.hearc.meteo.imp.afficheur.real.central.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jxmapviewer.viewer.GeoPosition;

import ch.hearc.meteo.imp.afficheur.real.central.ComSim.SimulationStations;
import ch.hearc.meteo.imp.afficheur.real.central.geoloc.TabGeoLoc;

public class JPanelListMap extends JPanel
	{

	public JPanelListMap(JPanelMap panelMap)
		{
		this.panelMap = panelMap;

		geometry();
		control();
		apparance();

		}

	private void apparance()
		{
		// TODO Auto-generated method stub

		}

	private void control()
		{
		list.addListSelectionListener(new ListSelectionListener()
			{

				@Override
				public void valueChanged(ListSelectionEvent e)
					{
					// TODO Auto-generated method stub
					selectedIndex = list.getSelectedIndex();
					panelMap.updateAll();

					}
			});

		mybutton.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					SimulationStations.removeOneStation();
					peupler();
					}
			});

		}

	private void geometry()
		{
		setLayout(new BorderLayout(0, 0));
		listModel = new DefaultListModel<String>();
		peupler();
		list = new JList<String>(listModel);

		JScrollPane pane = new JScrollPane(list);
		DefaultListSelectionModel m = new DefaultListSelectionModel();
		m.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		m.setLeadAnchorNotificationEnabled(false);
		list.setSelectionModel(m);

		add(pane, BorderLayout.CENTER);

		mybutton = new JButton("Test add points");
		add(mybutton, BorderLayout.SOUTH);

		}

	public static int getSelectedIndex()
		{
		return selectedIndex;
		}

	public void peupler()
		{
		listModel.clear();
		Map<String, GeoPosition> map = TabGeoLoc.getMapsPoints();
		Iterator<Entry<String, GeoPosition>> iterator = map.entrySet().iterator();
		for(int i = 1; i <= SimulationStations.getNombreDeStation(); i++)
			{
			String stationName = iterator.next().getKey();

			listModel.addElement("Station " + i + ": " + stationName);
			}
		}

	private JPanelMap panelMap;
	private JList<String> list;
	private static int selectedIndex;
	private DefaultListModel<String> listModel;
	private JButton mybutton;
	}
