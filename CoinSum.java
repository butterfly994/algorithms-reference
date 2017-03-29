import java.util.*;

public class CoinSum {
	
	public static void coinchange(int sum, int[] coins){//can be modified to count the number of ways
		int[] numcoins = new int[sum+1];
		int[] typecoins = new int[sum+1];
		
		//prefill
		for(int i = 1; i < numcoins.length; i++){ //with basically infinity
			numcoins[i] = Integer.MAX_VALUE-1;
		}
		
		for(int i = 0; i < typecoins.length; i++){ //with negatives
			typecoins[i] = -1;
		}
		
		//fill tables
		for(int i = 0; i < coins.length; i++){
			int currentcoin = coins[i];
			for(int j = 0; j <= sum; j++){
				if(j >= currentcoin){
					int temp = numcoins[j];
					numcoins[j] = Math.min(numcoins[j], 1 + numcoins[j-currentcoin]);
					if(numcoins[j] != temp){
						typecoins[j] = i;
					}
				}
			}
		}
		
		//back trace
		int c = sum;
		while(c > 0){
			System.out.println(coins[typecoins[c]]);
			c -= coins[typecoins[c]];
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int sum = sc.nextInt();
		int numcoins = sc.nextInt();
		int[] coins = new int[numcoins];
		for(int i = 0; i < numcoins; i++){
			coins[i] = sc.nextInt();
		}
		coinchange(sum, coins);
		sc.close();
	}

}
