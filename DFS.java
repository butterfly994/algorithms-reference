import java.util.*;
public class DFS {
	static ArrayList<ArrayList<Integer>> adjlist; //stores our graph
	static boolean[] marked; //have we visited vertex u?
	
	public static void dfs(int u){
		marked[u] = true;
		for(int v: adjlist.get(u)){ //for each neighbor of vertex u,
			if(!marked[v]){ //to avoid cycle
				dfs(v);
			}
		}
	}
	
	public static boolean isReachable(int v){ //is v reachable from u?
		return marked[v];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int numvertices = sc.nextInt();
		sc.nextLine();
		adjlist = new ArrayList<ArrayList<Integer>>();
		for(int i  = 0; i < numvertices; i++){
			adjlist.add(new ArrayList<Integer>());
		}
		marked = new boolean[numvertices];
		
		for(int i = 0; i < numvertices; i++){ //build adjacency list
			String[] line = sc.nextLine().split(" ");
			for(String x: line){
				if(x.length() == 0) continue; //if there are no neighbors to vertex i
				adjlist.get(i).add(Integer.parseInt(x));
			}
		}
		int source = sc.nextInt(); //source vertex
		dfs(source); //run dfs with source as the source vertex
		
		int numqueries = sc.nextInt();
		for(int i = 0; i < numqueries; i++){
			int current = sc.nextInt();
			System.out.println(isReachable(current)); //print is current reachable from source? 
		}
		
		sc.close();
	}

}
