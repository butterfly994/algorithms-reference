import java.util.*;
// Name: Adabelle Xie
//UserName: adabell1
//Password: 23eh48k
public class APSP {
	//Floyd Warshall DP algorithm, can only be used when V <= 400
	
	static int[][] adjmat;
	static int n;
	
	static void floydwarshall(){
		for(int k = 0; k < n; k++){
			for(int i = 0; i < n; i++){
				for(int j = 0; j < n; j++){
					adjmat[i][j] = Math.min(adjmat[i][j], adjmat[i][k] + adjmat[k][j]);
				}
			}
		}
	}
	
	static int mindist(int u, int v){
		return adjmat[u][v];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		adjmat = new int[n][n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				adjmat[i][j] = 10000;
			}
		}
		
		int e = sc.nextInt();
		for(int i = 0; i < e; i++){
			int u = sc.nextInt();
			int v = sc.nextInt();
			int w = sc.nextInt();
			adjmat[u][v] = w;
		}
		floydwarshall();
		
		int src = sc.nextInt();
		int dest = sc.nextInt();
		System.out.println(mindist(src, dest));
		
		sc.close();
	}

}
