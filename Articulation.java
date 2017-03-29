import java.util.*;
public class Articulation {
	//given a graph, find a vertex that, when removed from the graph, disconnects it(articulation point)
	//given a graph, find an edge that, when removed from the graph, disconnects it(bridge)
	//algorithm courtesy of John Hopcroft and Robert Tarjan, a modification of DFS
	
	static ArrayList<ArrayList<Integer>> adjlist;
	static int[] dfs_low; //the lowest index vertex that can be reached from vertex u
	static int[] dfs_num; //the iteration count when vertex u was first visited
	static int[] dfs_parent; //similar to edge classification, identifies whether this is a true cycle, or just a back edge
	static int dfs_counter; //the current number of vertices traversed by DFS
	static int root_children; //to handle trivial case. The root of DFS can only be an articulation point if it has more than one child
	static int dfs_root;
	//if dfs_low[u] >= dfs_num, there is NO cycle present, which means that disconnecting vertex u will disconnect the graph
	//if dfs_low[u] > dfs_num[v], there is a bridge present(no equals intentional, present an example graph)
	//if there is a cycle present, dfs_low[u] < dfs_num[u], that means disconnecting u won't do anything, as there are alternate ways to reach vertices
	
	static ArrayList<Integer> points; //for tracking purposes
	static ArrayList<String> bridges;
	
	public static void articulationBridge(int u){
		dfs_low[u] = dfs_num[u] = dfs_counter++;
		for(int v: adjlist.get(u)){
			if(dfs_num[v] == -1){ //if v is unvisited
				dfs_parent[v] = u;
				if(u == dfs_root) root_children++; //to handle trivial case
				
				articulationBridge(v); //dfs recursive traversal step
				
				if(dfs_low[v] >= dfs_num[u]){
					points.add(v);
				}
				if(dfs_low[v] > dfs_num[u]){
					bridges.add(u + " -> " + v);
				}
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
			}
			else if(v != dfs_parent[u]){ //back edge, part of a cycle
				dfs_low[u] = Math.min(dfs_low[u], dfs_num[v]);
			}
		}
		
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
		
		for(int i = 0; i < numvertices; i++){ //build adjacency list
			String[] line = sc.nextLine().split(" ");
			for(String x: line){
				if(x.length() == 0) continue; //if there are no neighbors to vertex i
				adjlist.get(i).add(Integer.parseInt(x));
			}
		}
		
		dfs_counter = 0;
		dfs_num = new int[numvertices];
		dfs_parent = new int[numvertices];
		points = new ArrayList<Integer>();
		bridges = new ArrayList<String>();
		for(int i = 0; i < numvertices; i++){
			dfs_num[i] = -1;
		}
		dfs_low = new int[numvertices];
		for(int i = 0; i < numvertices; i++){
			if(dfs_num[i] == -1){
				dfs_root = i;
				root_children = 0;
				articulationBridge(i);
				if(root_children > 1) points.add(i);
			}
		}
		
		System.out.println("Articulation Points:");
		for(int x: points){
			System.out.println(x);
		}
		System.out.println("Bridges:");
		for(String x: bridges){
			System.out.println(x);
		}
		
		sc.close();
	}
}
	
	
	
	
	





