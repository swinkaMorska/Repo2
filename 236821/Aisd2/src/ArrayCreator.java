import java.util.Random;

public class ArrayCreator {
	
	
	public int[] createRandomArrray(int n){
		int[] arr = new int[n];
		Random randomGenerator = new Random();
		
	    for (int i = 0; i < n; ++i){
	      arr[i] = randomGenerator.nextInt(10000);
	    }
	    
	    return arr;
	}
	
	
	public int[] makeCopyOf(int[] arr0){
		
		int[] dest = new int[arr0.length];
		System.arraycopy( arr0, 0, dest, 0, arr0.length );
		return dest;
	}

}
