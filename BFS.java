import java.util.*;
public class BFS {
	static ArrayList<ArrayList<Integer>> adjlist;
	static boolean[] marked;
	
	public static void bfs(int u){
		Queue<Integer> x = new LinkedList<Integer>();
		x.add(u);
		while(!x.isEmpty()){
			int current = x.poll();
			marked[current] = true;
			for(int v : adjlist.get(current)){
				if(!marked[v]){ //prevent cycle
					x.add(v);
				}
			}
		}
	}
	
	public static boolean isReachable(int v)
	{
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
		bfs(source); //run dfs with source as the source vertex
		
		int numqueries = sc.nextInt();
		for(int i = 0; i < numqueries; i++){
			int current = sc.nextInt();
			System.out.println(isReachable(current)); //print is current reachable from source? 
		}
		
		sc.close();

	}

}
