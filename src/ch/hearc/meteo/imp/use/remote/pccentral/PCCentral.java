
package ch.hearc.meteo.imp.use.remote.pccentral;

import ch.hearc.meteo.imp.reseau.RemoteAfficheurCreator;
import ch.hearc.meteo.imp.use.remote.PC_I;
import ch.hearc.meteo.spec.reseau.RemoteAfficheurCreator_I;

public class PCCentral implements PC_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public PCCentral()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override public void run()
		{
		server();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void server()
		{
		// TODO Auto-generated method stub
		//RemoteAfficheurCreator_I remoteAfficheur = RemoteAfficheurCreator.getInstance();
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	}
