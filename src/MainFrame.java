import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import SortingAlg.SortBase;


public class MainFrame extends JFrame{
	
	private Sorter sorter;
	

	private static final int DEFAULT_WIDTH = 1000;
	private static final int DEFAULT_HEIGHT = 900;

	
	public MainFrame() {
		
		
		this.setTitle("Sorting Visualization");
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		

		final ArrayBars CompPic = new ArrayBars();
		JSlider slider = new JSlider();
		sorter = new Sorter(CompPic);


		add(CompPic, BorderLayout.CENTER);
		
		slider.setMinimum(50);
		slider.setMaximum(500);
		slider.setValue(50);
		
		final JLabel label =  new JLabel("Data Size: n = " + slider.getValue());
		//
		
		JButton runButton = new JButton("Start");
		runButton.setContentAreaFilled(false);

		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sorter.setRun();
			}
		});
		
		JButton stopButton = new JButton("Stop");
		stopButton.setContentAreaFilled(false);
		
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sorter.setStop();
			}
		});

		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				if (!source.getValueIsAdjusting()) {
					label.setText("Data Size: n = " + source.getValue());
					
					sorter.stop();
					sorter.setLength(source.getValue());
					sorter = new Sorter(CompPic);
					new Thread(sorter).start();
				}
			}
		});

		
		JPanel controlButtons = new JPanel();
		controlButtons.add(runButton);
		controlButtons.add(stopButton);
		add(controlButtons, BorderLayout.SOUTH);
		

		final JComboBox<String> combox = new JComboBox<>();
		

		combox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String AlgoName = (String)combox.getSelectedItem();
				
				sorter.stop();
				sorter.setAlgor(AlgoName);
				
				try 
				{
					Thread.sleep(90);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				sorter = new Sorter(CompPic);
				new Thread(sorter).start();
			}
		});

		for(SortBase SB : Sorter.SortAlgos)
			combox.addItem(SB.getName());

		JPanel optionPanel = new JPanel();
		add(optionPanel, BorderLayout.NORTH);
		
		optionPanel.add(combox);
		optionPanel.add(label);
		optionPanel.add(slider);

		new Thread(sorter).start();
	}
		
}
