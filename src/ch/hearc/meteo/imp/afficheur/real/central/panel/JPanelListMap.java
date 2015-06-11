
package ch.hearc.meteo.imp.afficheur.real.central.panel;

import java.awt.BorderLayout;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jxmapviewer.viewer.GeoPosition;

import ch.hearc.meteo.imp.afficheur.real.central.geoloc.TabGeoLoc;

@SuppressWarnings("serial") public class JPanelListMap extends JPanel
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
		// RIEN

		}

	private void control()
		{
		list.addListSelectionListener(new ListSelectionListener()
			{

				@Override public void valueChanged(ListSelectionEvent e)
					{
					if (list.getSelectedIndex() != -1)
						{
						selectedIndex = list.getSelectedIndex();
						}
					panelMap.updateAll();

					}
			});

		refreshList();

		}

	private void geometry()
		{
		setLayout(new BorderLayout(0, 0));
		listModel = new DefaultListModel<String>();
		list = new JList<String>(listModel);
		peupler();
		JScrollPane pane = new JScrollPane(list);
		DefaultListSelectionModel m = new DefaultListSelectionModel();
		m.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		m.setLeadAnchorNotificationEnabled(false);
		list.setSelectionModel(m);
		add(pane, BorderLayout.CENTER);
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
		for(int i = 1; i <= TabGeoLoc.getNbStation(); i++)
			{
			String stationName = iterator.next().getKey();

			listModel.addElement("Station " + i + ": " + stationName);
			}
		list.setSelectedIndex(selectedIndex);
		}

	public void refreshList()
		{
		Thread t1 = new Thread(new Runnable()
			{

				@Override public void run()
					{
					while(true)
						{
						peupler();
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

	private JPanelMap panelMap;
	private JList<String> list;
	private static int selectedIndex;
	private DefaultListModel<String> listModel;
	}
