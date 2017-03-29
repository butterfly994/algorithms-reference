import java.util.*;

public class EditDistance {

	public static void edit(String a, String b){ //turn b into a
		int[][] table = new int[a.length()+1][b.length()+1];
		
		//pre-fill
		for(int i = 1; i <= a.length(); i++){
				table[i][0] = i;
		}
		for(int i = 0; i <= b.length(); i++){
			table[0][i] = i;
		}
		
		//fill the table
		for(int i = 1; i <= a.length(); i++){
			for(int j = 1; j <= b.length(); j++){
				if(a.charAt(i-1) == b.charAt(j-1)){
					table[i][j] = table[i-1][j-1];
				}
				else{
					table[i][j] = Math.min(Math.min(table[i-1][j], table[i][j-1]), table[i-1][j-1])+1;
				}
			}
		}
		
		//backtrace
		int c = b.length();
		int r = a.length();
		while(r > 0 || c > 0){
			if(table[r][c] == table[r-1][c]+1){
				System.out.println("Add " + a.charAt(r-1));
				r--;
			}
			else if(table[r][c] == table[r][c-1]+1){
				System.out.println("Delete " + b.charAt(c-1));
				c--;
			}
			else if(table[r][c] == table[r-1][c-1]+1){
				System.out.println(b.charAt(c-1) + " becomes " + a.charAt(r-1));
				r--;
				c--;
			}
			else if(table[r][c] == table[r-1][c-1]){
				r--;
				c--;
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String a = sc.nextLine();
		String b = sc.nextLine();
		edit(a, b);
		sc.close();
	}

}
