
package ch.hearc.meteo.imp.afficheur.real.central.panel;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import ch.hearc.meteo.imp.afficheur.real.manage.AfficheurServiceMOO;

public class JPanelDefault extends JPanel
	{

	public JPanelDefault()
		{
		this.listPanelMini = new ArrayList<JPanelTestMini>();
		//this.afficheurServiceMOO = afficheurServiceMOO;
		//tabPanelMini = new JPanelMini[SimulationStations.getNombreDeStation()];
		setLayout(new FlowLayout());

		update();

		}

	public void addStation(AfficheurServiceMOO afficheurServiceMOO)
		{
		JPanelTestMini panel = new JPanelTestMini(afficheurServiceMOO);
		listPanelMini.add(panel);
		add(panel);
		revalidate();
		repaint();
		}

	public void update()
		{
		/*Map<String, GeoPosition> map = TabGeoLoc.getMapsPoints();
		Iterator<Entry<String, GeoPosition>> iterator = map.entrySet().iterator();
		removeAll();
		revalidate();
		repaint();*/

		//		for(int i = 1; i <= SimulationStations.getNombreDeStation(); i++)
		//			{
		//			//JPanelMini panelMini= new JPanelMini(i);
		//			String stationName = iterator.next().getKey();
		//			tabPanelMini[i - 1] = new JPanelMini(i, stationName);
		//			add(tabPanelMini[i - 1]);
		//			}
		}

	public void refresh()
		{
		for(JPanelTestMini jPanelTestMini:listPanelMini)
			{
			jPanelTestMini.refresh();
			}
		}

	public JPanelMini[] getTabPanelMini()
		{
		return tabPanelMini;
		}

	private JPanelMini[] tabPanelMini;
	private ArrayList<JPanelTestMini> listPanelMini;
	//private AfficheurServiceMOO afficheurServiceMOO;

	}
