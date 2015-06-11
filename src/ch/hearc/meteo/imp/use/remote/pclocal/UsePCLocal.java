
package ch.hearc.meteo.imp.use.remote.pclocal;

import javax.swing.UIManager;

import ch.hearc.meteo.imp.afficheur.real.JFramePort;

public class UsePCLocal
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args)
		{
		try
			{
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			}
		catch (Throwable e)
			{
			e.printStackTrace();
			}
		main();
		}

	public static void main()
		{
		new JFramePort();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
