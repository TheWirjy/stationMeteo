
package ch.hearc.meteo.imp.com.real;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ch.hearc.meteo.imp.com.real.com.ComOption;
import ch.hearc.meteo.imp.com.real.com.ComPortDetection;
import ch.hearc.meteo.spec.com.port.MeteoPortDetectionService_I;

public class MeteoPortDetectionService implements MeteoPortDetectionService_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public MeteoPortDetectionService()
		{
		this.comPortDetection = new ComPortDetection(new ComOption());
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public List<String> findListPortSerie()
		{
		return comPortDetection.listePort();
		}

	@Override
	public boolean isStationMeteoAvailable(String portName, long timeoutMS)
		{
		return comPortDetection.testPort(portName);
		}

	@Override
	public List<String> findListPortMeteo(List<String> listPortExcluded)
		{
		List<String> allPorts = findListPortSerie();
		List<String> meteoListPort = new LinkedList<>();

		for(String portName:allPorts)
			{
			if (listPortExcluded.contains(portName))
				{
				continue;
				}

			if (isStationMeteoAvailable(portName, 1000))
				{
				meteoListPort.add(portName);
				}
			}
		return meteoListPort;
		}

	@Override
	public List<String> findListPortMeteo()
		{
		return findListPortMeteo(new ArrayList<String>(0));
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private ComPortDetection comPortDetection;

	}
