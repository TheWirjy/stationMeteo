
package ch.hearc.meteo.imp.afficheur.real.central.panel;

import java.awt.FlowLayout;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;

import org.jxmapviewer.viewer.GeoPosition;

import ch.hearc.meteo.imp.afficheur.real.central.ComSim.SimulationStations;
import ch.hearc.meteo.imp.afficheur.real.central.geoloc.TabGeoLoc;

public class JPanelDefault extends JPanel
	{

	public JPanelDefault()
		{
		tabPanelMini = new JPanelMini[SimulationStations.getNombreDeStation()];
		setLayout(new FlowLayout());

		update();

		}

	public void update()
		{
		Map<String, GeoPosition> map = TabGeoLoc.getMapsPoints();
		Iterator<Entry<String, GeoPosition>> iterator = map.entrySet().iterator();
		removeAll();
		revalidate();
		repaint();
		for(int i = 1; i <= SimulationStations.getNombreDeStation(); i++)
			{
			//JPanelMini panelMini= new JPanelMini(i);
			String stationName = iterator.next().getKey();
			tabPanelMini[i - 1] = new JPanelMini(i, stationName);
			add(tabPanelMini[i - 1]);
			}
		}

	public JPanelMini[] getTabPanelMini()
		{
		return tabPanelMini;
		}

	private JPanelMini[] tabPanelMini;

	}
