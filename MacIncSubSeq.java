import java.util.*;

public class MacIncSubSeq {
	
	public static void subsequence(int[] items){
		int[] table = items.clone();
		int[] seqtracker = new int[items.length];
		for(int i = 0; i < seqtracker.length; i++){
			seqtracker[i] = i;
		}
		int best = items[0];
		int index = 0;
		
		for(int i = 1; i < table.length; i++){
			int comp = items[i];
			for(int j = 0; j <= i; j++){
				if(items[j] < comp){
					if(table[j] + items[i] > table[i]){
						seqtracker[i] = j;
						table[i] = table[j] + items[i];
					}		
				}
				if(table[i] > best){
					best = table[i];
					index = i;
				}
				
			}
		}

		int left = best;
		while(left > 0){
			
			System.out.println(items[index]);
			left -= items[index];
			index = seqtracker[index];
			
			
		}
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int numitems = sc.nextInt();
		int[] items = new int[numitems];
		for(int i = 0; i < items.length; i++){
			items[i] = sc.nextInt();
		}
		subsequence(items);
		sc.close();

	}

}
