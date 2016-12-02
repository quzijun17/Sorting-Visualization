package SortingAlg;

import java.util.*;



abstract public class SortBase {

	abstract public <Type> void sort(Type[] data, Comparator<? super Type> c);
	abstract public String getName();
	

	
	protected static void swap(Object[] x, int a, int b) {
        Object t = x[a];
        x[a] = x[b];
        x[b] = t;
    }
}
