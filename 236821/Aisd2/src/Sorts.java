import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Sorts {
	static int k;
	
	private static String getFileName(String params) throws Exception{
		
		
		char sortType = params.charAt(7);
		int index = 33;
		String name = "";
		String kString = "";
		
		if(sortType == 'q' || sortType == 'm' || sortType == 'd' ){
			index = 32;
		}else if(sortType != 'i'){
			throw new Exception();
		}
		
		
		while(params.charAt(index) != ' '){
			name+=params.charAt(index);
			index++;
			if(index == params.length())
				throw new Exception();
		}
		
		if(!name.substring(name.length()-4, name.length()-1).equals(".txt"))
			name += ".txt";
		
		index++;
		while(index < params.length()){
			kString += params.charAt(index);
			index++;
		}
		
		k = Integer.valueOf(kString);
		
		return name;
	}
	
	
	public static long[] sort(char sortType, char compChar, int[] arr) throws Exception{
		
		SortingAlgorithm d = new SortingAlgorithm();
		int comp = 1;
		
		if(sortType == 'i'){
			//System.out.println("insertionsort + " + comp );
			if(compChar == '<')
				comp = 0;
			else if(compChar != '>')
				throw new Exception();
			
			return d.insertionSort(arr, comp);
			
		}else if(sortType == 'm'){
			System.out.println("mergesort + " + comp);
			if(compChar == '<')
				comp = 0;
			else if(compChar != '>')
				throw new Exception();
			
			return d.mergesort(arr, comp);
		}
		else if(sortType == 'q'){
			System.out.println("quicksort + " + comp);
			if(compChar == '<')
				comp = 0;
			else if(compChar != '>')
				throw new Exception();
			
			return d.quicksort(arr, comp);
		}else if(sortType == 'd'){
			System.out.println("dual quicksort + " + comp);
			if(compChar == '<')
				comp = 0;
			else if(compChar != '>')
				throw new Exception();
			
			return d.dualQuicksort(arr, comp) ;
		}else{
			throw new Exception();
		}
		
	}
	
	
	

	public static void main(String[] args) {
		
		int n, i;
		int[] arr;
		char sortType, compChar;
		String params, fileName;
		Scanner scanner = new Scanner(System.in);
		FileWriter out = null;
		ArrayCreator creator = new ArrayCreator();
		
		/*SortingAlgorithm a2 = new SortingAlgorithm();
		int[] f = {1, 8,3,6,2,4,2,4,55,1};
		a2.dualQuicksort(f, 0);*/
		
		
		
		

		System.out.println("Enter parameters: ");
		try{
			
			params = scanner.nextLine();
			sortType = params.charAt(7);
			if(sortType == 'i')
				compChar = params.charAt(22);
			else compChar = params.charAt(21);
			
			
			
			
//====================zadanie 1.=============================================
			System.out.print(params.length());
			if(params.length()<26){ 
				
				n = scanner.nextInt();
				arr = new int[n];
				i = 0;
				
				while (i<n) {
			        if (scanner.hasNextInt()){
			            arr[i] = (scanner.nextInt());
			            i++;
			        }
			        else 
			        	scanner.next();
			    }
				
				sort(sortType, compChar, arr);
					
			}
//====================zadanie 2============================================			
			else{ 
				fileName = getFileName(params);
				out = new FileWriter(fileName);
				
				
				PrintWriter writer = new PrintWriter(fileName, "UTF-8");
				
				for( int h = 100; h <= 10000; h+=100){
					for(i = 0; i<k; i++){
						arr = creator.createRandomArrray(h);
						long[] a = sort(sortType, compChar, creator.makeCopyOf(arr));
						
						String c = (h + "; " + String.valueOf(a[0]) + "; " 
								+String.valueOf(a[1]) + "; "
								+String.valueOf(a[2]) );
						writer.println(c);
						
						//System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n written: " + c);
						
					}
					System.out.println(h);
				}
				writer.close();
			}
			
			
			
		}catch(Exception e){
			System.out.println("There is something wrong with given parameters");
		}
		
		scanner.close();
	}

}
