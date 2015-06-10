
package ch.hearc.meteo.imp.use.remote.pccentral;

import java.rmi.RemoteException;

import ch.hearc.meteo.imp.afficheur.real.central.JFrameMeteoCentral;
import ch.hearc.meteo.imp.reseau.RemoteAfficheurCreator;
import ch.hearc.meteo.imp.use.remote.PC_I;

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
		try
			{
			JFrameMeteoCentral.getInstance();
			RemoteAfficheurCreator.getInstance();
			}
		catch (RemoteException e)
			{
			// TODO Auto-generated catch block
			System.out.println("erreur getInstance");
			e.printStackTrace();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	}
