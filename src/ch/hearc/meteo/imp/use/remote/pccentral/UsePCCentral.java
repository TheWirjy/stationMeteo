
package ch.hearc.meteo.imp.use.remote.pccentral;

import javax.swing.UIManager;

public class UsePCCentral
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

		new PCCentral().run();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
