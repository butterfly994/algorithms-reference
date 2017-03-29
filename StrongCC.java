import java.util.*;
public class StrongCC {
	//Thank you Robert Tarjan!
	static int[] dfs_num;
	static int[] dfs_low;
	static int dfsNumberCounter;
	static Stack<Integer> s;
	static boolean[] marked;
	static ArrayList<ArrayList<Integer>> adjlist;
	
	public static void sCC(int u) {
		dfs_low[u] = dfs_num[u] = dfsNumberCounter++;
		s.add(u); //store based on order of visitation
		marked[u] = true;
		
		for(int x : adjlist.get(u)){
			if(!marked[x]){
				sCC(x);
			}
			if(marked[x]){
				dfs_low[u] = Math.min(dfs_low[u], dfs_low[x]);
			}
		}
		
		if(dfs_low[u] == dfs_num[u]){
			System.out.println("SCC");
			while(true){
				int next = s.pop();
				System.out.print(" " + next);
				if(u == next) break;
			}
			System.out.println();
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
		dfs_low = new int[numvertices];
		dfs_num = new int[numvertices];
		dfsNumberCounter = 0;
		s = new Stack<Integer>();
		marked = new boolean[numvertices];
		
		for(int i = 0; i < numvertices; i++){
			if(!marked[i]){
				sCC(i);
			}
		}

	}

}
