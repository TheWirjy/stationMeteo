
package ch.hearc.meteo.imp.afficheur.real.central.panel;

import java.awt.BorderLayout;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.LocalResponseCache;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;

import ch.hearc.meteo.imp.afficheur.real.central.geoloc.TabGeoLoc;
import ch.hearc.meteo.imp.afficheur.real.central.panel.jxmap.FancyWaypointRenderer;
import ch.hearc.meteo.imp.afficheur.real.central.panel.jxmap.MyWaypoint;

public class JPanelMap extends JPanel
	{

	public JPanelMap(JPanelDefault panelDefault)
		{
		this.panelDefault = panelDefault;
		geometry();
		control();
		apparance();

		}

	private void geometry()
		{
		setLayout(new BorderLayout());
		// Create a TileFactoryInfo for Virtual Earth
		TileFactoryInfo info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);
		DefaultTileFactory tileFactory = new DefaultTileFactory(info);
		tileFactory.setThreadPoolSize(8);

		// Setup local file cache
		File cacheDir = new File(System.getProperty("user.home") + File.separator + ".jxmapviewer2");
		LocalResponseCache.installResponseCache(info.getBaseURL(), cacheDir, false);

		// Setup JXMapViewer
		mapViewer = new JXMapViewer();
		mapViewer.setTileFactory(tileFactory);

		updateAll();

		Iterator<Entry<String, GeoPosition>> it = MapsPoints.entrySet().iterator();
		Entry<String, GeoPosition> firstItem = it.next();
		// Set the focus
		mapViewer.setZoom(10);
		mapViewer.setAddressLocation(firstItem.getValue());

		}

	private void control()
		{
		// Add interactions
		MouseInputListener mia = new PanMouseInputListener(mapViewer);
		mapViewer.addMouseListener(mia);
		mapViewer.addMouseMotionListener(mia);
		mapViewer.addMouseListener(new CenterMapListener(mapViewer));
		mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
		mapViewer.addKeyListener(new PanKeyListener(mapViewer));
		add(mapViewer, BorderLayout.CENTER);

		}

	private void apparance()
		{
		// TODO Auto-generated method stub

		}

	public void updateAll()
		{
		waypoints = TabGeoLoc.peupler();
		MapsPoints = TabGeoLoc.getMapsPoints();
		panelDefault.update();
		// Create a waypoint painter that takes all the waypoints
		WaypointPainter<MyWaypoint> waypointPainter = new WaypointPainter<MyWaypoint>();
		waypointPainter.setWaypoints(waypoints);
		waypointPainter.setRenderer(new FancyWaypointRenderer());
		mapViewer.setOverlayPainter(waypointPainter);
		}

	//tools
	private JXMapViewer mapViewer;
	private Map<String, GeoPosition> MapsPoints;
	private Set<MyWaypoint> waypoints;

	//inputs
	private JPanelDefault panelDefault;

	}
