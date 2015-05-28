
package ch.hearc.meteo.imp.use.remote.pclocal;

import java.rmi.RemoteException;

import ch.hearc.meteo.imp.afficheur.simulateur.AfficheurSimulateurFactory;
import ch.hearc.meteo.imp.com.simulateur.MeteoServiceSimulatorFactory;
import ch.hearc.meteo.imp.use.remote.PC_I;
import ch.hearc.meteo.spec.afficheur.AffichageOptions;
import ch.hearc.meteo.spec.afficheur.AfficheurService_I;
import ch.hearc.meteo.spec.com.meteo.MeteoServiceOptions;
import ch.hearc.meteo.spec.com.meteo.MeteoService_I;
import ch.hearc.meteo.spec.com.meteo.exception.MeteoServiceException;
import ch.hearc.meteo.spec.com.meteo.listener.MeteoListener_I;
import ch.hearc.meteo.spec.com.meteo.listener.event.MeteoEvent;
import ch.hearc.meteo.spec.reseau.RemoteAfficheurCreator_I;
import ch.hearc.meteo.spec.reseau.rmiwrapper.AfficheurServiceWrapper_I;
import ch.hearc.meteo.spec.reseau.rmiwrapper.MeteoServiceWrapper;

import com.bilat.tools.reseau.rmi.IdTools;
import com.bilat.tools.reseau.rmi.RmiTools;
import com.bilat.tools.reseau.rmi.RmiURL;

public class PCLocal implements PC_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public PCLocal(MeteoServiceOptions meteoServiceOptions, String portCom, AffichageOptions affichageOptions, RmiURL rmiURLafficheurManager)
		{
		this.meteoServiceOptions = meteoServiceOptions;
		this.portCom = portCom;
		this.affichageOptions = affichageOptions;
		this.rmiURLafficheurManager = rmiURLafficheurManager;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override public void run()
		{

		try
			{
			server(); // avant
			}
		catch (Exception e)
			{
			System.err.println("[PCLocal :  run : server : failed");
			e.printStackTrace();
			}

		try
			{
			client(); // aprüs
			}
		catch (RemoteException e)
			{
			System.err.println("[PCLocal :  run : client : failed");
			e.printStackTrace();
			}
		catch (MeteoServiceException e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				server			*|
	\*------------------------------*/

	private void server() throws MeteoServiceException, RemoteException
		{
		// TODO Auto-generated method stub
		/********************** moi ****************************/
		meteoService = (new MeteoServiceSimulatorFactory()).create(portCom);
		meteoServiceWrapper = new MeteoServiceWrapper(meteoService);
		rmiURL = new RmiURL(IdTools.createID(PREFIX));

		RmiTools.shareObject(meteoServiceWrapper, rmiURL);
		/*******************************************************/
		}

	/*------------------------------*\
	|*				client			*|
	\*------------------------------*/

	private void client() throws RemoteException, MeteoServiceException
		{
		// TODO Auto-generated method stub
		RemoteAfficheurCreator_I afficheurRemote = (RemoteAfficheurCreator_I)RmiTools.connectionRemoteObjectBloquant(rmiURLafficheurManager);

		RmiURL rmiURLafficheurServiceWrapper = afficheurRemote.createRemoteAfficheurService(affichageOptions, rmiURL);
		final AfficheurServiceWrapper_I afficheurServiceWrapper = (AfficheurServiceWrapper_I)RmiTools.connectionRemoteObjectBloquant(rmiURLafficheurServiceWrapper);
		final AfficheurService_I afficheurService = (new AfficheurSimulateurFactory()).createOnLocalPC(affichageOptions, meteoServiceWrapper);

		meteoService.connect();
		meteoService.start(meteoServiceOptions);
		meteoService.addMeteoListener(new MeteoListener_I()
			{

				@Override public void temperaturePerformed(MeteoEvent event)
					{
					try
						{
						afficheurService.printTemperature(event);
						afficheurServiceWrapper.printTemperature(event);
						}
					catch (RemoteException e)
						{
						System.out.println("erreur print temperature");
						}
					}

				@Override public void pressionPerformed(MeteoEvent event)
					{
					try
						{
						afficheurServiceWrapper.printPression(event);
						}
					catch (RemoteException e)
						{
						System.out.println("erreur print pression");
						}
					}

				@Override public void altitudePerformed(MeteoEvent event)
					{
					try
						{
						afficheurServiceWrapper.printAltitude(event);
						}
					catch (RemoteException e)
						{
						System.out.println("erreur print altitude");
						}
					}
			});
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Inputs
	private MeteoServiceOptions meteoServiceOptions;
	private String portCom;
	private AffichageOptions affichageOptions;
	private RmiURL rmiURLafficheurManager;
	private MeteoServiceWrapper meteoServiceWrapper;

	// Tools
	private final static String PREFIX = "METEO_SERVICE";
	private RmiURL rmiURL;
	private MeteoService_I meteoService;
	}
