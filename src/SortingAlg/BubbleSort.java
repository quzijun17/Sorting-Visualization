package SortingAlg;

import java.util.Comparator;

public class BubbleSort extends SortBase {

	public static <T> void ToBubbleSort(T[] data, Comparator<? super T> c) {
		for(int i=1;i<data.length;i++){
			for(int j=i;(j>0)&&c.compare(data[j], data[j-1])<0;j--){
				swap(data,j,j-1);
			}
		}        
	}
	
	public <T> void sort(T[] data, Comparator<? super T> c) {
		ToBubbleSort(data, c);
	}
	
	public String getName() {
		String s = "Bubble Sort";
		return s;
	}
}
