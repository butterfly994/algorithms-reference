import java.util.*;

public class PalindromicSubsequence {
	
	public static void palindrome(String a){
		int[][] table = new int[a.length()][a.length()];
		
		for(int i = 0; i < a.length(); i++){ //fill table
			for(int j = 0; j < a.length()-i; j++){
				if(i == 0){ //pre-fill base cases(strings of lengths 1)
					table[j][j] = 1;
				}
				else if(i == 1){
					if(a.charAt(j) == a.charAt(j+1)){
						table[j][j+i] = 2;
					}
					else{
						table[j][j+i] = 1;
					}
				}
				else{
					if(a.charAt(j) == a.charAt(j+i)){
						table[j][j+i] = table[j+1][j+i-1] + 2;
					}
					else{
						table[j][j+i] = Math.max(table[j][j+i-1], table[j+1][j+i]);
					}
				}
			}
		}
		
		//back trace
		String f = "";
		int r = 0;
		int c = a.length() - 1;
		while(table[r][c] > 0){
			if(table[r][c] == table[r+1][c]){
				r++;
			}
			else if(table[r][c] == table[r][c-1]){
				c--;
			}
			else{
				f = f.substring(0, f.length()/2) + f.charAt(r) + f.charAt(c) + f.substring(f.length());
				r++;
				c--;
			}
		}
		System.out.println(f);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String a = sc.nextLine();
		palindrome(a);
		sc.close();
	}

}
