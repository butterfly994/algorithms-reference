import java.util.*;
public class TopologicalSort {
	//prerequisite: graph is a DAG(directed acyclic graph)
	
	static ArrayList<Integer> sorted = new ArrayList<Integer>(); //global var to store the toposort for DFS implementation
	
	public static ArrayList<Integer> toposortDFS(ArrayList<ArrayList<Integer>> adjlist, int current, boolean[] marked){
		//DFS version-credit to Robert Tarjan
		marked[current] = true;
		for(int i = 0; i < adjlist.get(current).size(); i++){
			int v = adjlist.get(current).get(i);
			if(!marked[v]){
				toposortDFS(adjlist, v, marked);
			}
		}
		sorted.add(current);
		return sorted;
	}
	
	public static void wrapperDFS(ArrayList<ArrayList<Integer>> adjlist){
		boolean[] marked = new boolean[adjlist.size()];
		for(int i = 0; i < adjlist.size(); i++){
			if(!marked[i]){
				toposortDFS(adjlist, i, marked);
			}
		}
		for(int i = sorted.size()-1; i >= 0; i--){ //topological sort with DFS is returned backwards
			System.out.print(sorted.get(i) + " ");
		}
		System.out.println();//empty line
	}
	
	public static ArrayList<Integer> toposortBFS(ArrayList<ArrayList<Integer>> adjlist){
		//BFS version-credit to Arthur Kahn
		int[] degrees = new int[adjlist.size()];		
		for(int i = 0; i < adjlist.size(); i++){
			ArrayList<Integer> current = adjlist.get(i);
			for(int x: current){
				degrees[x]++;
			}
		}	
		Queue<Integer> standalones = new LinkedList<Integer>();
		for(int i = 0; i < degrees.length; i++){
			if(degrees[i] == 0) standalones.add(i);
		}
		ArrayList<Integer> toposort = new ArrayList<Integer>();
		while(!standalones.isEmpty()){
			int v = standalones.poll(); //dequeue
			toposort.add(v);
			for(int x: adjlist.get(v)){
				if(--degrees[x] == 0){
					standalones.add(x);
				}
			}
		}
		return toposort;
	}
	
	public static void wrapperBFS(ArrayList<ArrayList<Integer>> adjlist){
		ArrayList<Integer> toposort = new ArrayList<Integer>();
		toposort = toposortBFS(adjlist);
		for(int x: toposort){
			System.out.print(x + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int numcases = sc.nextInt();
		for(int i = 1; i <= numcases; i++){
			ArrayList<ArrayList<Integer>> adjlist = new ArrayList<ArrayList<Integer>>(); //build the adjacency list
			int numvertices = sc.nextInt(); //same i/o as dominator
			for(int j = 0; j < numvertices; j++){
				ArrayList<Integer> current = new ArrayList<Integer>();
				for(int k = 0; k < numvertices; k++){
					int connect = sc.nextInt();
					if(connect == 1) current.add(k);
				}
				adjlist.add(current);
			}
			wrapperDFS(adjlist);
			
		
		}
		sc.close();
	}

}
