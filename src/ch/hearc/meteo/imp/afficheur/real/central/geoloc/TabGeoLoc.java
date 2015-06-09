
package ch.hearc.meteo.imp.afficheur.real.central.geoloc;

import java.awt.Color;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jxmapviewer.viewer.GeoPosition;

import ch.hearc.meteo.imp.afficheur.real.central.ComSim.SimulationStations;
import ch.hearc.meteo.imp.afficheur.real.central.panel.JPanelListMap;
import ch.hearc.meteo.imp.afficheur.real.central.panel.jxmap.MyWaypoint;

public class TabGeoLoc
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public static Set<MyWaypoint> peupler()
		{
		//Points aléatoire car la carte ne possède pas de géolocalisation
		mapspoint = new LinkedHashMap<String, GeoPosition>();
		mapspoint = getMapsPoints();

		Iterator<Entry<String, GeoPosition>> iteratormap = mapspoint.entrySet().iterator();

		waypoints = new HashSet<MyWaypoint>();
		int i = 0;
		nombreDeStationDetecte = SimulationStations.getNombreDeStation();
		while(iteratormap.hasNext() && i < nombreDeStationDetecte)
			{
			Entry<String, GeoPosition> myvalue = iteratormap.next();
			if (i == JPanelListMap.getSelectedIndex())
				{
				waypoints.add(new MyWaypoint(i + 1 + "", Color.BLUE, myvalue.getValue()));
				}
			else
				{
				waypoints.add(new MyWaypoint(i + 1 + "", Color.GRAY, myvalue.getValue()));
				}

			i++;
			}

		return waypoints;
		}

	public static Map<String, GeoPosition> getMapsPoints()
		{
		mapspoint = new LinkedHashMap<String, GeoPosition>();
		mapspoint.put("frankfurt", new GeoPosition(50, 7, 0, 8, 41, 0));
		mapspoint.put("wiesbaden", new GeoPosition(50, 5, 0, 8, 14, 0));
		mapspoint.put("mainz", new GeoPosition(50, 0, 0, 8, 16, 0));
		mapspoint.put("darmstadt", new GeoPosition(49, 52, 0, 8, 39, 0));
		mapspoint.put("offenbach", new GeoPosition(50, 6, 0, 8, 46, 0));
		mapspoint.put("paris", new GeoPosition(48, 51, 24, 2, 21, 8));
		return mapspoint;
		}

	public static void remove(String key)
		{
		mapspoint.remove(key);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private static Map<String, GeoPosition> mapspoint;
	private static Set<MyWaypoint> waypoints;
	private static int nombreDeStationDetecte;

	}
