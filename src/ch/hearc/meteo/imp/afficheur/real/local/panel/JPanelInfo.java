
package ch.hearc.meteo.imp.afficheur.real.local.panel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
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
import ch.hearc.meteo.imp.afficheur.real.manage.AfficheurServiceMOO;
import ch.hearc.meteo.imp.afficheur.real.manage.Stat;
import ch.hearc.meteo.imp.afficheur.simulateur.vue.atome.MathTools;

public class JPanelInfo extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	JPanelInfo(String titre, String unite, Stat stat, AfficheurServiceMOO afficheurServiceMOO)
		{
		this.afficheurServiceMOO = afficheurServiceMOO;
		this.stat = stat;
		this.titre = titre;
		this.unite = unite;
		geometry();
		control();
		apparance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void update()
		{
		this.labelValue.setText(MathTools.arrondir(stat.getLast()) + " " + unite);
		this.labelMaxValue.setText("maximum : " + MathTools.arrondir(stat.getMax()) + " " + unite);
		this.labelMinValue.setText("minimum : " + MathTools.arrondir(stat.getMin()) + " " + unite);
		this.labelMeanValue.setText("moyenne : " + MathTools.arrondir(stat.getMoy()) + " " + unite);

		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setEnableSlider(boolean enable)
		{
		this.sliderTime.setEnabled(enable);
		}

	public void setSliderValue(int value)
		{
		this.sliderTime.setValue(value);
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public int getSliderValue()
		{
		return this.sliderTime.getValue();
		}

	public JSlider getSlider()
		{
		return this.sliderTime;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void control()
		{
		this.sliderTime.addChangeListener(new ChangeListener()
			{

				@Override
				public void stateChanged(ChangeEvent e)
					{
					labelEchant.setText("dt = " + decimalFormat.format(sliderTime.getValue() / 1000.0) + " s");

					}
			});

		}

	private void apparance()
		{
		setPreferredSize(new Dimension(180, 10));
		labelValue.setFont(font);
		sliderTime.setPreferredSize(new Dimension(100, 30));
		sliderTime.setMinimum(100);
		sliderTime.setMaximum(10000);

		}

	@Override
	public void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		final ImageIcon bg = ImageLoader.loadSynchroneJar("ressources/backgroundcarree.png");
		g.drawImage(bg.getImage(), 0, 0, getWidth(), getHeight(), this);
		}

	private void geometry()
		{
		font = new Font("Arial", Font.PLAIN, 20);

		sliderTime = new JSlider();
		boxV = Box.createVerticalBox();
		boxH = Box.createHorizontalBox();

		decimalFormat = new DecimalFormat();
		decimalFormat.setMaximumFractionDigits(1);
		decimalFormat.setMinimumFractionDigits(1);
		labelValue = new JLabel("-- " + unite);
		labelMinValue = new JLabel("minimum : -- " + unite);
		labelMaxValue = new JLabel("maximum : -- " + unite);
		labelMeanValue = new JLabel("moyenne : -- " + unite);
		labelEchant = new JLabel("dt = " + decimalFormat.format(sliderTime.getValue() / 1000.0) + " s");

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
		boxH.add(sliderTime);
		boxH.add(Box.createHorizontalGlue());
		boxV.add(boxH);

		this.add(boxV);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// input
	private String unite;
	@SuppressWarnings("unused")
	private String titre;
	// tools
	private JLabel labelValue;
	private JLabel labelMinValue;
	private JLabel labelMaxValue;
	private JLabel labelMeanValue;
	private JLabel labelEchant;
	private JSlider sliderTime;

	private NumberFormat decimalFormat;
	private Stat stat;
	private AfficheurServiceMOO afficheurServiceMOO;

	private Box boxV;
	private Box boxH;

	private Font font;
	private Image bg;
	}
