
package ch.hearc.meteo.imp.use.remote.pclocal;

import java.net.InetAddress;
import java.net.UnknownHostException;

import ch.hearc.meteo.imp.reseau.RemoteAfficheurCreator;
import ch.hearc.meteo.spec.afficheur.AffichageOptions;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;

import com.bilat.tools.reseau.rmi.RmiTools;
import com.bilat.tools.reseau.rmi.RmiURL;

public class UsePCLocal
	{

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static void main(String[] args)
		{
		main();
		}

	public static void main()
		{

		try
			{

			String strIp = System.getProperty("ip", "157.26.110.116");
			InetAddress ip = InetAddress.getByName(strIp);

			RmiURL rmiUrl = new RmiURL(RemoteAfficheurCreator.RMI_ID, ip, RmiTools.PORT_RMI_DEFAUT);

			MeteoServiceOptions meteoServiceOptions = new MeteoServiceOptions(800, 1000, 1200);
			AffichageOptions affichageOptions = new AffichageOptions(3, "");

			String portCom = "COM1";
			PCLocal pc = new PCLocal(meteoServiceOptions, portCom, affichageOptions, rmiUrl);
			pc.run();

			}
		catch (UnknownHostException e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
