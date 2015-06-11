
package ch.hearc.meteo.imp.afficheur.real.manage;

import ch.hearc.meteo.imp.afficheur.real.local.JFrameStationMeteo;
import ch.hearc.meteo.spec.afficheur.AffichageOptions;
import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper_I;

public class AfficheurServiceSimulateur implements AfficheurService_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/**
	 * n = #data to print
	 */
	public AfficheurServiceSimulateur(AffichageOptions affichageOptions, MeteoServiceWrapper_I meteoServiceRemote)
		{
		afficheurServiceMOO = new AfficheurServiceMOO(affichageOptions, meteoServiceRemote);
		new JFrameStationMeteo(afficheurServiceMOO);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override public void printAltitude(MeteoEvent event)
		{
		afficheurServiceMOO.printAltitude(event);
		}

	@Override public void printTemperature(MeteoEvent event)
		{
		afficheurServiceMOO.printTemperature(event);
		}

	@Override public void printPression(MeteoEvent event)
		{
		afficheurServiceMOO.printPression(event);
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private AfficheurServiceMOO afficheurServiceMOO;

	@Override public void updateMeteoServiceOptions(MeteoServiceOptions meteoServiceOptions)
		{
		// RIEN

		}

	}
