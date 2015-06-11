
package ch.hearc.meteo.imp.afficheur.real.central.panel;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.jxmapviewer.viewer.GeoPosition;

import ch.hearc.meteo.imp.afficheur.real.central.geoloc.TabGeoLoc;
import ch.hearc.meteo.imp.afficheur.real.local.ImageLoader;
import ch.hearc.meteo.imp.afficheur.real.manage.AfficheurServiceMOO;

@SuppressWarnings("serial") public class JPanelDefault extends JPanel
	{

	//OUPS... J'espérais que vous ne tomberiez jamais sur ce code...
	//La gestion avec la map n'est pas catholique du tout :O

	public JPanelDefault()
		{
		this.listPanelMini = new ArrayList<JPanelMini>();
		setLayout(new FlowLayout());
		stationNo = 1;
		map = TabGeoLoc.getMapsPoints();
		iterator = map.entrySet().iterator();

		}

	public void addStation(AfficheurServiceMOO afficheurServiceMOO)
		{
		String stationName = iterator.next().getKey();
		JPanelMini panel = new JPanelMini(afficheurServiceMOO, stationName, stationNo);
		stationNo++;
		listPanelMini.add(panel);
		add(panel);
		revalidate();
		repaint();
		TabGeoLoc.setNbStation(listPanelMini.size());
		}

	public void refresh()
		{
		for(JPanelMini jPanelTestMini:listPanelMini)
			{
			jPanelTestMini.refresh();
			}
		}

	@Override public void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		final ImageIcon bg = ImageLoader.loadSynchroneJar("ressources/backgroundO.png");
		g.drawImage(bg.getImage(), 0, 0, getWidth(), getHeight(), this);
		}


	private ArrayList<JPanelMini> listPanelMini;
	private int stationNo;
	private Iterator<Entry<String, GeoPosition>> iterator;
	private Map<String, GeoPosition> map;

	}
