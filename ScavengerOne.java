import java.util.*;


public class ScavengerOne {
	
	
	public static void subsetsum(int capacity, int[] items){
		boolean[][] table = new boolean[items.length][capacity+1];
		//set all items at 0 weight to be true
		for(int i = 0; i < table.length; i++){
			table[i][0] = true;
		}
		
		for(int i = 0; i < table.length; i++){//fill the table
			if(i == 0){
				for(int j = 1; j < capacity+1; j++){
					if(items[i] != j) table[i][j] = false;
				}
			}
			else{
				for(int j = 1; j < capacity+1; j++){
					if(j < items[i]){
						table[i][j] = table[i-1][j];
					}
					else if(table[i-1][j]){
						table[i][j] = true;
					}
					else{
						if(table[i-1][j-items[i]]) table[i][j] = true;
						else table[i][j] = false;
					}
				}
			}
		}
		
		if(!table[items.length-1][capacity]){
			System.out.println("Cannot fill to capacity");
			return;
		}
		
		//trace back
		System.out.println("This run is:");
		int c = capacity;
		int r = items.length-1;
		while(c > 0){
			if(table[r-1][c]){
				r--; //true is coming from previous element
			}
			else{
				System.out.println(" " + items[r]);//element is in solution, print
				c -= items[r];
				r--;
			}
		}
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int numcases = sc.nextInt();
		for(int i = 0; i < numcases; i++){
			int cap = sc.nextInt();
			int numvalues = sc.nextInt();
			int[] vals = new int[numvalues];
			for(int j = 0; j < numvalues; j++){
				vals[j] = sc.nextInt();
			}
			
			subsetsum(cap, vals);
		}
		
		sc.close();

	}

}
