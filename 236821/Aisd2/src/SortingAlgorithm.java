
public class SortingAlgorithm {
	
	int comparations = 0, moves = 0, fileWritting = 0;

	
	void print(String s){
		if(fileWritting == 0){
			System.out.println(s);
		}
	}
	
	void printArray(int arr[]){
		
		if(arr.length > 50){
			fileWritting = 1;
			return;
		}
			
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println("");
 
    }
	
	private boolean compare(int comp, int a , int b){
		
		print("Im comparing  " + a + " and " + b);
		comparations++;
		
		if(comp == 0){
			if(a<=b)
				return true;
		}else if(comp == 1){
			if(b<=a)
				return true;
		}
		return false;
	}
	
	private boolean checkIfSorted(int arr[], int comp){
		
		
		print("\n\nI'm checking if array is correctly sorted");
		int n = arr.length;
        for (int i=1; i<n; ++i){
        	comparations--;
        	 if(!compare(comp , arr[i-1], arr[i])){
        		 print("not correctly sorted");
        		 return false;
        	 }
        }
  
        print("correctly sorted");
        return true;
	}

//--------------------------------------------------------------------	
	public long[] insertionSort(int arr[], int comp){
		
		int n = arr.length;
		int key, j;
		comparations = 0; 
		moves = 0;
		long startTime = System.nanoTime();    
		
		if(n>99)
			fileWritting = 1;
        
        for (int i=1; i<n; ++i){
        	
            key = arr[i];
            j = i-1;
 
            while (j>=0 && compare(comp, key, arr[j])){
            	
            	arr[j+1] = arr[j];
            	print("Im moving " + arr[j] + " one to the right");
            	moves++;
            	printArray(arr);
                j = j-1;
             
            }
            arr[j+1] = key;
            moves++;
            print("Im moving " + key + " on the right place");
            printArray(arr);
            
        }
        
        long estimatedTime = System.nanoTime() - startTime;
        print("Results:");
        print("time: " + estimatedTime + " comparations: " + comparations + " moves: " + moves);
        checkIfSorted(arr,comp);
        print(String.valueOf(arr.length));
        printArray(arr);
        
        
        
        long[]results = {comparations, moves, estimatedTime};
        return results;
        
        
    }
	
	
	
	
//----------------------------------QS---------------------------------------------------------
	
	int partition(int arr[], int low, int high, int comp)
    {
		int temp;
        int pivot = arr[high];
        int i = (low-1); 
        
        print("\npivot is: " + pivot);
        
        for (int j=low; j<high; j++)
        {
            if(compare(comp, arr[j], pivot))
            {
                i++;
                if(i!=j){
                	print("Im swapping " + arr[i] + " with " + arr[j]);
                	moves++;
	                temp = arr[i];
	                arr[i] = arr[j];
	                arr[j] = temp;
	                printArray(arr);
                }
            }
        }
        
        if(i+1 != high){
        	print("Im swapping "+arr[i+1] + " with pivot: " + arr[high]);
	        moves++;
	        temp = arr[i+1];
	        arr[i+1] = arr[high];
	        arr[high] = temp;
        }
        
        printArray(arr);
        return i+1;
    }
 
 
    
    void quicksort_(int arr[], int low, int high, int comp)
    {
    	
        if (low < high)
        {
            
            int pi = partition(arr, low, high, comp );
            quicksort_(arr, low, pi-1, comp);
            quicksort_(arr, pi+1, high, comp);
        }
        
    }
    
    long[] quicksort(int arr[],  int comp){
    	
    	comparations = 0; moves = 0;
    	if(arr.length>99) fileWritting = 1;
    	long startTime = System.nanoTime();   
 
    	
    	quicksort_(arr, 0, arr.length-1, comp);
    	
    	long estimatedTime = System.nanoTime() - startTime;
    	printArray(arr);
    	print("time: " + estimatedTime + " comparations: " + comparations + " moves: " + moves);
    	checkIfSorted(arr, comp);
    	
    	long[]results = {comparations, moves, estimatedTime};
        return results;
    }
    
    //========================================MS==========================================================================
    
    void merge(int arr[], int l, int m, int r, int comp)
    {
        int i = 0, j = 0, k = l;
        int n1 = m - l + 1;
        int n2 = r - m;
 
      
        int L[] = new int [n1];
        int R[] = new int [n2];
 
        
        for (i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];
        
        print("\n\nIm merging two arrays:");
        printArray(L);
        printArray(R);
 
 
      
        i = 0; j = 0;
        while (i < n1 && j < n2){
        	
            if (compare(comp,L[i], R[j])) {
                arr[k] = L[i];
                i++;
                moves++;
            }
            else{
                arr[k] = R[j];
                j++;
                moves++;
            }
            
            print("adding "+arr[k]+" to the array");
            k++;
        }
 
       
        while (i < n1){
        	print("adding "+arr[k]+" to the array");
            arr[k] = L[i];
            i++;
            k++;
            moves++;
        }
 
        
        while (j < n2){
        	print("adding "+arr[k]+" to the array");
            arr[k] = R[j];
            j++;
            k++;
            moves++;
        }
    }
 
  
    void mergesort_(int arr[], int l, int r, int comp){
    	  
    	if (l < r){
            
            int m = (l+r)/2;
 
            mergesort_(arr, l, m, comp);
            mergesort_(arr , m+1, r, comp );
            merge(arr, l, m, r, comp);
          
        }
        
    }
    
    long[] mergesort(int arr[],  int comp){
    	
    	comparations = 0; moves = 0;
    	if(arr.length>99) fileWritting = 1;
    	long startTime = System.nanoTime();   
    	
    	mergesort_(arr, 0, arr.length-1, comp);
    	
    	long estimatedTime = System.nanoTime() - startTime;
    	
    	print("\n\ntime: " + estimatedTime + " comparations: " + comparations + " moves: " + moves);
    	printArray(arr);
    	checkIfSorted(arr, comp);
    	
    	long[]results = {comparations, moves, estimatedTime};
        return results;
    }
    
    //==================Dual Pivot QS======================================================================================================
    
    int[] partitionDual(int[] arr, int low, int high, int comp )
    {
    	int temp = 0;
        if (compare(comp, arr[high] ,arr[low])){
        	temp = arr[high];
        	arr[high] = arr[low];
        	arr[low] = temp; 
        	moves++;
        }
            
        
        int j = low + 1;
        int g = high - 1, k = low + 1, p = arr[low], q = arr[high];
        while (k <= g) {
     
           
            if (compare(comp, arr[k], p)) {
            	temp = arr[k];
            	arr[k] = arr[j];
            	arr[j] = temp; 
                j++;
                moves++;
            }
     
      
            else if(compare(comp,q,arr[k])){
                while (compare(comp, q,arr[g]) && compare(comp , k , g) && q!=arr[g] && k!=g)
                    g--;

                temp = arr[k];
            	arr[k] = arr[g];
            	arr[g] = temp;
            	moves++;
                
                g--;
                if (compare(comp, arr[k], p)) {
                	temp = arr[k];
                	arr[k] = arr[j];
                	arr[j] = temp; 
                    j++;
                    moves++;
                }
            }
            k++;
        }
        j--;
        g++;
     
     
        temp = arr[j];
    	arr[j] = arr[low];
    	arr[low] = temp;
    	moves++;


    	temp = arr[high];
    	arr[high] = arr[g];
    	arr[g] = temp;
    	moves++;

        
        int[] res = {g,j};
        return res;
    }
    
    
    void dualQuickSort_(int arr[], int low, int high, int comp){
    	
    	if (low < high) {
            
            int lp, rp; 
            int[] res = partitionDual(arr, low, high, comp);
            rp = res[0];
            lp = res[1];
            
            print("Current array: ");
            printArray(arr);
            print("Current pivots: " + arr[lp] + " , " + arr[rp]);
            dualQuickSort_(arr, low, lp - 1, comp);
            dualQuickSort_(arr, lp + 1, rp - 1, comp);
            dualQuickSort_(arr, rp + 1, high,comp);
        }
    }
    
    long[] dualQuicksort(int arr[],  int comp){
    	
    	comparations = 0; moves = 0;
    	if(arr.length>99) fileWritting = 1;
    	long startTime = System.nanoTime();   
    	
    	dualQuickSort_(arr, 0, arr.length-1, comp);
    	
    	long estimatedTime = System.nanoTime() - startTime;
    	printArray(arr);
    	print("time: " + estimatedTime + " comparations: " + comparations + " moves: " + moves);
    	checkIfSorted(arr, comp);
    	
    	
    	printArray(arr);
    	long[]results = {comparations, moves, estimatedTime};
        return results;
    }
}
