import java.util.*;

public class GraphCC {
	//find connected components to a particular vertex
	
	public static ArrayList<Integer> connected(ArrayList<ArrayList<Integer>> adjlist, boolean[] marked, int current, ArrayList<Integer> conn){
		marked[current] = true;
		for(int adj: adjlist.get(current)){
			if(adj != current && !marked[adj]){ //avoid cycle
				marked[adj] = true;
				conn.add(adj);
				connected(adjlist, marked, adj, conn);
			}
		}
		return conn;
	}
	
	public static void connectedWrapper(ArrayList<ArrayList<Integer>> adjlist){
		for(int i = 0; i < adjlist.size(); i++){
			boolean[] marked = new boolean[adjlist.size()];
			ArrayList<Integer> conn = new ArrayList<Integer>();
			conn = connected(adjlist, marked, i, conn);
			System.out.print("CC " + i + ": ");
			for(int j: conn){
				System.out.print(j + " ");
			}
			System.out.println();
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int numcases = sc.nextInt();
		for(int i = 1; i <= numcases; i++){
			ArrayList<ArrayList<Integer>> adjlist = new ArrayList<ArrayList<Integer>>(); //build the adjacency list
			int numvertices = sc.nextInt();
			for(int j = 0; j < numvertices; j++){
				ArrayList<Integer> current = new ArrayList<Integer>();
				for(int k = 0; k < numvertices; k++){
					int connect = sc.nextInt();
					if(connect == 1) current.add(k);
				}
				adjlist.add(current);
				
			}
			connectedWrapper(adjlist);
		}
		sc.close();

	}

}
