
package ch.hearc.meteo.imp.com.real.com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import ch.hearc.meteo.imp.com.real.com.trame.TrameDecoder;
import ch.hearc.meteo.imp.com.real.com.trame.TrameEncoder;

public class ComPortDetection
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public ComPortDetection(ComOption comOption)
		{
		assert (comOption != null);
		this.comOption = comOption;
		}
	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	public List<String> listePort()
	{
	@SuppressWarnings("rawtypes")
	Enumeration portIdentifiers = CommPortIdentifier.getPortIdentifiers();
	List<String> list = new LinkedList<>();
	while(portIdentifiers.hasMoreElements())
		{
		CommPortIdentifier port = (CommPortIdentifier)portIdentifiers.nextElement();
		list.add(port.getName());
		}
	return list;
	}

	public boolean testPort(final String portName)
		{
		try
			{
			SerialPort port = (SerialPort)CommPortIdentifier.getPortIdentifier(portName).open(getClass().getSimpleName(), 1000);
			port.setSerialPortParams(comOption.getSpeed(), comOption.getDataBit(), comOption.getStopBit(), comOption.getParity());

			final BufferedReader reader = new BufferedReader(new InputStreamReader(port.getInputStream()));
			OutputStream outputStream = port.getOutputStream();

			port.notifyOnDataAvailable(true);
			SerialPortEventListener serialPortEventListener = new SerialPortEventListener()
				{

					@Override
					public void serialEvent(final SerialPortEvent event)
						{
						try
							{
							if (event.getEventType() != SerialPortEvent.DATA_AVAILABLE) { testPortResult=false; }

							String trame = reader.readLine();

							TrameDecoder.valeur(trame);
							testPortResult = false;
							}
						catch (Exception e)
							{
							testPortResult = true;
							}
						}
				};
			port.addEventListener(serialPortEventListener);

			String trame = "010200";
			outputStream.write(TrameEncoder.coder(trame));
			port.close();
			return testPortResult;
			}
		catch (Exception e)
			{
			return true;
			}
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

	// Input
	private ComOption comOption;
	// Tools
	// R�sultat du test du port
	private boolean testPortResult;

	}
