import java.util.*;

public class Interweaving {
	
	public static boolean isInterweaving(String a, String b, String c){
		boolean[][] table = new boolean[b.length()+1][a.length()+1];
		table[0][0] = true;
		for(int i = 1; i <= a.length(); i++){ //fill first row
			if(a.charAt(i-1) == c.charAt(i-1)){
				if(table[0][i-1]) table[0][i] = true;
			}
		}
		
		for(int i = 1; i <= b.length(); i++){ //fill first column
			if(b.charAt(i-1) == c.charAt(i-1)){
				if(table[i-1][0]) table[i][0] = true;
			}
		}
		
		for(int i = 1; i <= b.length(); i++){
			for(int j = 1; j <= a.length(); j++){
				if(c.charAt(i+j-1) == a.charAt(j-1)){
					if(table[i][j-1]){
						table[i][j] = true;
					}
				}
				else if(c.charAt(i+j-1) == b.charAt(i-1)){
					if(table[i-1][j]){
						table[i][j] = true;
					}
				}
				
			}
		}
		
		return table[a.length()][b.length()];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String a = sc.nextLine();
		String b = sc.nextLine();
		String c = sc.nextLine();
		System.out.println(isInterweaving(a, b, c));
		sc.close();
	}

}
