
package ch.hearc.meteo.imp.afficheur.real.central.ComSim;


public class SimulationStations
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public SimulationStations()
	{
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/
	public static int getNombreDeStation()
		{
		return NombreDeStation;
		}


	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/
	public static void removeOneStation()
	{
	NombreDeStation--;
	}

	public static void addOneStation()
	{
	NombreDeStation++;
	}



	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private static int NombreDeStation = 6;
	}
