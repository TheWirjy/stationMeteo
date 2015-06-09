
package ch.hearc.meteo.imp.afficheur.real.local.panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class JPanelBackground extends JPanel
	{

	Image bgimage = null;

	public JPanelBackground(String path)
		{
		MediaTracker mt = new MediaTracker(this);
		bgimage = Toolkit.getDefaultToolkit().getImage(path);
		mt.addImage(bgimage, 0);
		try
			{
			mt.waitForAll();
			}
		catch (InterruptedException e)
			{
			e.printStackTrace();
			}
		}

	@Override
	protected void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		g.drawImage(bgimage, 1, 1, getWidth(), getHeight(), this);
		}

	public Image getImg()
	{
	return bgimage;
	}
	}
