import java.util.*;

public class Jackpot {
	
	public static int winnings(int[] bets){//Kadane's algorithm
		int maxending = 0;
		int maxsofar = 0;
		
		for(int x: bets){
			maxending = Math.max(0, maxending+x);
			maxsofar = Math.max(maxsofar, maxending);
		}
		
		return maxsofar;
	}
	
	public static int win(int[] bets){ //bottom up dynamic programming solution
		int[] memo = new int[bets.length];
		memo[0] = bets[0];
		boolean flag = true;
		for(int i = 1; i < memo.length; i++){
			memo[i] = memo[i-1];
			int r = memo[i-1];
			if(memo[i-1] < memo[i-1] + bets[i] && flag){
				memo[i] = memo[i-1] + bets[i];
			}
			if(memo[i-1] <= bets[i]){
				memo[i] = bets[i];
				flag = true;
				continue;
			}
			if(memo[i] == r) flag = false;
		}
		return memo[bets.length-1];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		while(true){
			int numbets = sc.nextInt();
			if(numbets == 0) break;
			int[] bets = new int[numbets];
			for(int i = 0; i < numbets; i++){
				bets[i] = sc.nextInt();
			}
			int streak = winnings(bets);
			if(streak <= 0) System.out.println("Losing streak.");
			else System.out.println("The maximum winning streak is " + streak + ".");
		}
		sc.close();
	}

}
