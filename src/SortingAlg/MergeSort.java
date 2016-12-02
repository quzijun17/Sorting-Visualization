package SortingAlg;

import java.util.Comparator;

public class MergeSort extends SortBase {

	
	public static <T> void ToMergeSort(T[] data, Comparator<? super T> c) {

		Object[] temp = new Object[data.length];
		mergeSort(data,temp,0,data.length-1, c);
	}
	    
    private static <T> void mergeSort(T[] data,Object[] temp,int l,int r, Comparator<? super T> comp){
        int mid=(l+r)/2;
        if(l==r) return ;
        mergeSort(data,temp,l,mid, comp);
        mergeSort(data,temp,mid+1,r, comp);
        for(int i=l;i<=r;i++){
            temp[i]=data[i];
        }
        int i1=l;
        int i2=mid+1;
        for(int cur=l;cur<=r;cur++){
            if(i1==mid+1)
                data[cur]=(T)temp[i2++];
            else if(i2>r)
                data[cur]=(T)temp[i1++];
            else if(comp.compare((T) temp[i1], (T) temp[i2]) < 0) 
                data[cur]=(T)temp[i1++];
            else
                data[cur]=(T)temp[i2++];            
        }
    }

	
	@Override
	public <T> void sort(T[] data, Comparator<? super T> c) {
		ToMergeSort(data, c);
	}

	@Override
	public String getName() {
		return "Merge Sort";
	}

}
