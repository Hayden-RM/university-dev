/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noiseremoving;

/**
 *
 * @author Hayden Richard-Marsters 21152003
 * 
 * Answer following questions in your Comments at the beginning of your CollectionSort Class code.
 * 1. Is quick sort the best way of finding median? Why? (3%)
 *  
 * It depends on the size of the data set, and objective. Quicksort may not be the best when the sole objective is to find the median.
 * While quicksort has an average-case time-complexity of O(n log n) and in particular when applied to unstructured/unsorted larger, growing data sets
 * quicksort time complexity grows at a lower growth rate than sorting algorithims such as bubble, insertion or selection sort.
 * As well as by using quicksort memory usage is minimised due to in-place sorting. However, other algorithims exist that are more efficient that dont 
 * require partitioning the array and recursively sorting both the left and right sub-arrays.
 * Incorrect partitioning leads to worst case time-complexity of O(n^2), making quicksort highly inefficient
 * and exponentially degrading the efficiency of quicksort.
 * 
 * 
 * 2. What is another good way of finding median? Please provide your explanation. (3%)
 * 
 * Another strong and efficient algorithim for finding median is the Median of Medians algorithim. 
 * The most significant advantage to using median of median is the guaranteed worst-case of O(n), meaning it provides a linear time guarantee for finding 
 * the median, and therefore can be faster than quicksort in some cases. Median of medians is highly consistent and predictable, by dividing the array into smaller arrays, finding the median of each group
 * then recursively narrowing down the arrays for the overall median. Even if slightly less efficient on average compared to others,
 * MM is highly consistent and reliable when needing to find median from sorted, highly unbalanced and unsorted data. 
 * 
 * 
 */

public class SortArray <E extends Comparable<E>>{
    
    E[] array;
    
    public SortArray(E[] array)
    {
        this.array = array;
    }
    
    public void setArray(E[] array)
    {
        this.array = array;
    }
    
    public void quickSort() //public method initiate quickSort
    {
        if(array == null || array.length == 0){
            return; 
        }
        quickSort(0,array.length-1);
    }
    
  private void quickSort(int l, int r) //private recursive method to perform quick sort
  {
        if(l >= r){ //if left index is greater than or equal to the right, return
            return; 
        }
        
        E pivot = array[l + (r - l)/2]; //Pivot element
        int i = l;
        int j = r; 
        
        
        while(i <= j){ //Partition array into sub-arrays 
            while(array[i].compareTo(pivot ) < 0){ //Left elements greater, comapred to pivot
                i++;
            }
            while(array[j].compareTo(pivot) > 0){ //Right elements smaller than pivot
                j--;
            }
            
            if( i <= j){ //Swap elements at positions i and j
                
                swap(i,j);
                i++;
                j--;
            }
        }
        
        if(l<j){ //recursively call quick sort subarrays
            quickSort(l,j);
        }
        
        if(r > i){
            quickSort(i, r);
        }
    }
    
    private void swap(int i, int j) //Helper method to swap elements of array
    {
      E temp = array[i];
      array[i] = array[j];
      array[j] = temp; 
    }    
    
}
