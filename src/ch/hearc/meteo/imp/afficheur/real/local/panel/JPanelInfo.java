
package ch.hearc.meteo.imp.afficheur.real.local.panel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.hearc.meteo.imp.afficheur.real.local.ImageLoader;
import ch.hearc.meteo.imp.afficheur.real.manage.Stat;
import ch.hearc.meteo.imp.afficheur.simulateur.vue.atome.MathTools;

@SuppressWarnings("serial") public class JPanelInfo extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	JPanelInfo(String titre, String unite, Stat stat)
		{
		this.stat = stat;
		this.titre = titre;
		this.unite = unite;
		geometry();
		control();
		apparance();
		updateStat();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void updateStat()
		{
		Thread t1 = new Thread(new Runnable()
			{

				@Override public void run()
					{
					while(true)
						{
						labelValue.setText(MathTools.arrondir(stat.getLast()) + " " + unite);
						labelMaxValue.setText("maximum : " + MathTools.arrondir(stat.getMax()) + " " + unite);
						labelMinValue.setText("minimum : " + MathTools.arrondir(stat.getMin()) + " " + unite);
						labelMeanValue.setText("moyenne : " + MathTools.arrondir(stat.getMoy()) + " " + unite);
						try
							{
							Thread.sleep(100);
							}
						catch (InterruptedException e)
							{
							// TODO Auto-generated catch block
							e.printStackTrace();
							}
						}
					}
			});
		t1.start();
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setEnableSlider(boolean enable)
		{
		this.sliderDT.setEnabled(enable);
		}

	public void setSliderValue(int value)
		{
		this.sliderDT.setValue(value);
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public int getSliderValue()
		{
		return this.sliderDT.getValue();
		}

	public JSlider getSlider()
		{
		return this.sliderDT;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void control()
		{
		this.sliderDT.addChangeListener(new ChangeListener()
			{

				@Override public void stateChanged(ChangeEvent e)
					{
					labelEchant.setText("dt = " + decimalFormat.format(sliderDT.getValue() / 1000.0) + " s");

					}
			});

		}

	private void apparance()
		{
		setPreferredSize(new Dimension(180, 10));
		labelValue.setFont(font);
		sliderDT.setPreferredSize(new Dimension(100, 30));
		sliderDT.setMinimum(500);
		sliderDT.setMaximum(10000);

		}

	@Override public void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		final ImageIcon bg = ImageLoader.loadSynchroneJar("ressources/backgroundcarree.png");
		g.drawImage(bg.getImage(), 0, 0, getWidth(), getHeight(), this);
		}

	private void geometry()
		{
		font = new Font("Arial", Font.PLAIN, 20);

		sliderDT = new JSlider();
		boxV = Box.createVerticalBox();
		boxH = Box.createHorizontalBox();

		decimalFormat = new DecimalFormat();
		decimalFormat.setMaximumFractionDigits(1);
		decimalFormat.setMinimumFractionDigits(1);
		labelValue = new JLabel("-- " + unite);
		labelMinValue = new JLabel("minimum : -- " + unite);
		labelMaxValue = new JLabel("maximum : -- " + unite);
		labelMeanValue = new JLabel("moyenne : -- " + unite);
		labelEchant = new JLabel("dt = " + decimalFormat.format(sliderDT.getValue() / 1000.0) + " s");

		setLayout(new FlowLayout());

		boxV.add(Box.createVerticalGlue());
		boxV.add(labelValue);
		boxV.add(Box.createVerticalStrut(6));
		boxV.add(labelMaxValue);
		boxV.add(Box.createVerticalStrut(3));
		boxV.add(labelMeanValue);
		boxV.add(Box.createVerticalStrut(3));
		boxV.add(labelMinValue);
		boxV.add(Box.createVerticalStrut(3));
		boxV.add(labelEchant);
		boxH.add(Box.createHorizontalGlue());
		boxH.add(sliderDT);
		boxH.add(Box.createHorizontalGlue());
		boxV.add(boxH);

		this.add(boxV);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// input
	private String unite;
	@SuppressWarnings("unused") private String titre;
	// tools
	private JLabel labelValue;
	private JLabel labelMinValue;
	private JLabel labelMaxValue;
	private JLabel labelMeanValue;
	private JLabel labelEchant;
	private JSlider sliderDT;

	private NumberFormat decimalFormat;
	private Stat stat;

	private Box boxV;
	private Box boxH;

	private Font font;
	}
