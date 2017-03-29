import java.util.*;

public class Knapsack {
	public static void knapsack(int[] weights, int[] vals, int capacity){
		int[][] table = new int[weights.length+1][capacity+1];
		for(int i = 1; i <= weights.length; i++){//construct table
			int currentw = weights[i-1];
			for(int j = 1; j <= capacity; j++){
				if(currentw > j){
					table[i][j] = table[i-1][j];
				}
				else{
					table[i][j] = Math.max(vals[i-1]+table[i-1][j-currentw], table[i-1][j]);
				}
			}
		}
		
		//trace back
		int r = weights.length;
		int c = capacity;
		while(c > 0){
			int currentv = table[r][c];
			if(currentv == table[r-1][c]){
				r--;
			}
			else{
				System.out.println(weights[r-1] + " (" + vals[r-1]+ ")");
				r--;
				c -= weights[r];
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int capacity = sc.nextInt();
		int numitems = sc.nextInt();
		int[] weights = new int[numitems];
		int[] values = new int[numitems];
		for(int i = 0; i < numitems; i++){
			weights[i] = sc.nextInt();
		}
		for(int i = 0; i < numitems; i++){
			values[i] = sc.nextInt();
		}
		knapsack(weights, values, capacity);
		sc.close();

	}

}
