import java.util.*;

public class MaxNonAdjSubseq {
	
	public static void maxSubset(int[] items){		
		int[] table = new int[items.length];//fill the table
		table[0] = items[0];
		table[1] = Math.max(items[1], items[0]);
		
		for(int i = 2; i < items.length; i++){
			table[i] = Math.max(Math.max(table[i-1], table[i-2] + items[i]), items[i]);
		}
		System.out.println(table[table.length-1]);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int numitems = sc.nextInt();
		int[] items = new int[numitems];
		for(int i = 0; i < numitems; i++){
			items[i] = sc.nextInt();
		}
		maxSubset(items);
		sc.close();
	}

}
