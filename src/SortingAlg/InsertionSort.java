package SortingAlg;

import java.util.Comparator;


public class InsertionSort extends SortBase{

	
	public static <T> void ToInsertSort(T[] data, Comparator <? super T> comp) {
		ToInsertSort(data, 0, data.length, comp);
	}


	public static <T> void ToInsertSort(T[] data, int startIndex, int endIndex, Comparator<? super T> comp) {
		for(int i=startIndex; i<endIndex; i++) {	
			for(int j=i+1; j<=endIndex-1; j++) {
				if(comp.compare(data[i], data[j]) > 0) {		
					swap(data, i, j);
				}
			}
		}	
	}
		
	public <T> void sort(T[] data, Comparator <? super T> comp) {
		ToInsertSort(data, comp);
	}
	
	public String getName() {
		return "Insertion Sort";
	}
}
