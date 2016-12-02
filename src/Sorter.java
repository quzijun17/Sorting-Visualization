import java.awt.*;

import java.util.*;
import java.util.concurrent.Semaphore;

import javax.swing.*;

import SortingAlg.*;


public class Sorter implements Runnable{
	
	private Double[] values;
	private ArrayBars component;
	private boolean run,end;
	private Semaphore gate;
	private static SortBase sortAlgorithm;

	private static int length = 50;


	private static final int DELAYTIME = 5 ;
	

	public static HashMap<String, Integer> HashMapAlgo = new HashMap<String, Integer>(); 

	
	public static SortBase[] SortAlgos;
	
	public Sorter(ArrayBars comp) {
		
		values = new Double[this.length];

		for (int i=0; i<values.length; i++)
			values[i] = new Double(Math.random());

		this.component = comp;
		this.run = false;
		this.gate = new Semaphore(1);
		this.end = false;
	}
	
	public boolean setAlgo(int index) {
		if(index > SortAlgos.length) 
			return false;
		else {
			sortAlgorithm = SortAlgos[index];
			return true;
		}
	}

	public boolean setAlgor(String algorithmName) {
		Integer index = (this.HashMapAlgo).get(algorithmName);
		if (index == null) 
			return false;
		else 
			return setAlgo(index);
	}
	
	public void stop() {
		run = false;
		end = true;
		gate.release();
	}


	public void setRun() {
		run = true;
		gate.release();
	}
	
	public void setStop() {
		run = false;
		gate.release();
	}
	
	public void setLength(int length) 
	{
		this.length = length;
	}
	
	public int getLength()
	{
		return length;
	}
	
	public synchronized void run() {
		Comparator<Double> comp = new Comparator<Double>() {
			public int compare(final Double num1, final Double num2) {
				
				EventQueue.invokeLater(new Runnable() {
					
					public void run() {
						component.setValues(values, num1, num2);
					}
				});
								
				try{
					if(end) 
						Thread.interrupted();
					else if(run) 
						Thread.sleep(DELAYTIME);
					else 
						gate.acquire();
				} 
				catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				
				return num1.compareTo(num2);
			}
		};
		

		this.sortAlgorithm.sort(values, comp);
		System.out.println(sortAlgorithm.getName());
		
		component.setValues(values, null, null);
		
		if(!end) 
			JOptionPane.showMessageDialog(null, "Sorting Complete!");
	}
	
	



	static{

		SortAlgos = new SortBase[] {
				new BubbleSort(),
				new MergeSort(),
				new QuickSort(),
				new InsertionSort(),
				new SelectionSort(),
		};
		
		
		for (int i=0; i<SortAlgos.length; i++) {
			HashMapAlgo.put(SortAlgos[i].getName(), i);

			
		}

		sortAlgorithm = SortAlgos[0];
	}
	
}
