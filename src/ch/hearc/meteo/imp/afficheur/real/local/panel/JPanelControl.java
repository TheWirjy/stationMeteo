
package ch.hearc.meteo.imp.afficheur.real.local.panel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ch.hearc.meteo.imp.afficheur.real.manage.AfficheurServiceMOO;

public class JPanelControl extends JPanel
	{

	public JPanelControl(AfficheurServiceMOO afficheurServiceMOO)
		{
		this.afficheurServiceMOO = afficheurServiceMOO;
		geometry();
		control();
		apparance();
		}

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	private void apparance()
		{
		// RIEN

		}

	private void control()
		{
		buttonStart.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					afficheurServiceMOO.setPause(false);

					}
			});

		buttonStop.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					afficheurServiceMOO.setPause(true);
					}
			});

		}


	private void geometry()
		{
		setLayout(new FlowLayout());
		buttonStart = new JButton("Start");
		buttonStop = new JButton("Stop");
		add(buttonStart);
		add(buttonStop);

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

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private JButton buttonStop;
	private JButton buttonStart;
	private AfficheurServiceMOO afficheurServiceMOO;
	}
