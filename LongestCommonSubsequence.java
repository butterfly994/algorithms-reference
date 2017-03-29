import java.util.*;

public class LongestCommonSubsequence {
	
	public static String lcg(String a, String b){
		int[][] table = new int[a.length()+1][b.length()+1];
		for(int i = 1; i <= a.length(); i++){ //fill table
			char acurrent = a.charAt(i-1);
			for(int j = 1; j <= b.length(); j++){
				if(acurrent == b.charAt(j-1)){
					table[i][j] = table[i-1][j-1] + 1;
				}
				else{
					table[i][j] = Math.max(table[i][j-1], table[i-1][j]);
				}
				
			}
		}
		
		//trace back
		String sequence = "";
		int r = a.length();
		int c = b.length();
		while(c > 0){
			if(table[r][c] != table[r-1][c] && table[r][c] != table[r][c-1]){
				sequence = a.charAt(r-1) + sequence;
				r--;
				c--;
			}
			else if(table[r][c] == table[r-1][c]){
				r--;
			}
			else{
				c--;
			}
			
		}
		
		return sequence;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String a = sc.nextLine();
		String b = sc.nextLine();
		System.out.println(lcg(a, b));
		sc.close();
	}

}
