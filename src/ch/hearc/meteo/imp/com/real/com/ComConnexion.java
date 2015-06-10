
package ch.hearc.meteo.imp.com.real.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import ch.hearc.meteo.imp.com.logique.MeteoServiceCallback_I;
import ch.hearc.meteo.imp.com.real.com.trame.TrameDecoder;
import ch.hearc.meteo.imp.com.real.com.trame.TrameEncoder;
import ch.hearc.meteo.spec.com.meteo.exception.MeteoServiceException;

// TODO student
//  Implémenter cette classe
//  Updater l'implémentation de MeteoServiceFactory.create()

/**
 * <pre>
 * Aucune connaissance des autres aspects du projet ici
 *
 * Ouvrer les flux vers le port com
 * ecouter les trames arrivantes (pas boucle, mais listener)
 * decoder trame
 * avertir meteoServiceCallback
 *
 *</pre>
 */
public class ComConnexion implements ComConnexions_I
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public ComConnexion(MeteoServiceCallback_I meteoServiceCallback, String portName, ComOption comOption)
		{
		assert portName != null : "Le nom du port ne doit pas être nul !";

		this.comOption = comOption;
		this.portName = portName;
		this.meteoServiceCallback = meteoServiceCallback;
		}

	/**
	 * <pre>
	 * Problem :
	 * 		MeteoService est un MeteoServiceCallback_I
	 * 		ComConnexions_I utilise MeteoServiceCallback_I
	 * 		MeteoService utilise ComConnexions_I
	 *
	 * On est dans la situation
	 * 		A(B)
	 * 		B(A)
	 *
	 * Solution
	 * 		 B
	 * 		 A(B)
	 *  	 B.setA(A)
	 *
	 *  Autrement dit:
	 *
	 *		ComConnexions_I comConnexion=new ComConnexion( portName,  comOption);
	 *      MeteoService_I meteoService=new MeteoService(comConnexion);
	 *      comConnexion.setMeteoServiceCallback(meteoService);
	 *
	 *      Ce travail doit se faire dans la factory
	 *
	 *  Warning : call next
	 *  	setMeteoServiceCallback(MeteoServiceCallback_I meteoServiceCallback)
	 *
	 *  </pre>
	 */
	public ComConnexion(String portName, ComOption comOption)
		{
		this(null, portName, comOption);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void start() throws Exception
		{
		bufferedReader = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));

		serialPort.addEventListener(new SerialPortEventListener()
			{

				@Override
				public void serialEvent(SerialPortEvent event)
					{
					try
						{
						switch(event.getEventType())
							{
							case SerialPortEvent.DATA_AVAILABLE:
								String line = bufferedReader.readLine();
								float value = TrameDecoder.valeur(line);
								switch(TrameDecoder.dataType(line))
									{
									case ALTITUDE:
										{
										meteoServiceCallback.altitudePerformed(value);
										break;
										}
									case PRESSION:
										{
										meteoServiceCallback.pressionPerformed(value);
										break;
										}
									case TEMPERATURE:
										{
										meteoServiceCallback.temperaturePerformed(value);
										break;
										}
									default:
										System.err.println("Panic error");
										break;
									}
							}
						}
					catch (IOException e)
						{
						// TODO Auto-generated catch block
						e.printStackTrace();
						}
					catch (MeteoServiceException e)
						{
						// TODO Auto-generated catch block
						e.printStackTrace();
						}
					}
			});
		}

	@Override
	public void stop() throws Exception
		{
		//serialPort.removeEventListener();
		serialPort.notifyOnDataAvailable(false);
		}

	@Override
	public void connect() throws Exception
		{

		CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier(this.portName);
		serialPort = (SerialPort)portId.open(portName, 1000);
		serialPort.setSerialPortParams(comOption.getSpeed(), comOption.getDataBit(), comOption.getStopBit(), comOption.getParity());

		comOption = new ComOption(57600, SerialPort.DATABITS_8, SerialPort.PARITY_NONE, SerialPort.STOPBITS_1);
		serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);

		serialPort.notifyOnDataAvailable(true);

		outputStream = serialPort.getOutputStream();

		}

	@Override
	public void disconnect() throws Exception
		{
		serialPort.removeEventListener();
		serialPort.close();
		}

	@Override
	public void askAltitudeAsync() throws Exception
		{
		String trame = "010200";
		outputStream.write(TrameEncoder.coder(trame));
		}

	@Override
	public void askPressionAsync() throws Exception
		{
		String trame = "010000";
		outputStream.write(TrameEncoder.coder(trame));
		}

	@Override
	public void askTemperatureAsync() throws Exception
		{
		String trame = "010100";
		outputStream.write(TrameEncoder.coder(trame));
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	@Override
	public String getNamePort()
		{
		return portName;
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/**
	 * For post building
	 */
	public void setMeteoServiceCallback(MeteoServiceCallback_I meteoServiceCallback)
		{
		this.meteoServiceCallback = meteoServiceCallback;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Input
	private ComOption comOption;
	private String portName;
	private MeteoServiceCallback_I meteoServiceCallback;

	// Tools
	private SerialPort serialPort;
	private OutputStream outputStream;
	private BufferedReader bufferedReader;
	}
