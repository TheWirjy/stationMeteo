
package ch.hearc.meteo.imp.use.remote.pclocal;

import ch.hearc.meteo.imp.reseau.RemoteAfficheurCreator;
import ch.hearc.meteo.spec.afficheur.AffichageOptions;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;

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
		
		RmiURL rmiUrl = new RmiURL(RemoteAfficheurCreator.RMI_ID);

		MeteoServiceOptions meteoServiceOptions = new MeteoServiceOptions(800, 1000, 1200);
		AffichageOptions affichageOptions = new AffichageOptions(3, "pc_local");

		String portCom = "COM1";
		PCLocal pc = new PCLocal(meteoServiceOptions, portCom, affichageOptions, rmiUrl);
		pc.run();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
