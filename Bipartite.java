import java.util.*;
public class Bipartite {
	
	/*bipartite: a graph whose nodes can be divided into two disjoint sets, u and v,
	such that every edge in the graph connects a vertex in u to one in v. */
	
	/*color vertices from the source opposite colors, with the colors representing the disjoint sets. 
	If there is an edge that connects two vertices of the same color, the graph is not bipartite. */
	
	public static boolean isBipartite(ArrayList<ArrayList<Integer>> adjlist){
		//modified BFS
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(0);
		
		int[] colors = new int[adjlist.size()];
		for(int i = 0; i < colors.length; i++){
			colors[i] = Integer.MAX_VALUE; //we will only use the colors 0 and 1
		}
		colors[0] = 0;
		
		boolean flag = true;
		while(queue.isEmpty() && flag){
			int u = queue.poll();
			for(int i = 0; i < adjlist.get(u).size(); i++){
				int v = adjlist.get(u).get(i);
				if(colors[v] == Integer.MAX_VALUE){
					colors[v] = 1 - colors[u];
					queue.add(v);
				}
				else if(colors[v] == colors[u]){ //coloring conflict, not bipartite
					flag = false;
				}
			}
		}
		return flag;
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
			System.out.println(isBipartite(adjlist));
		}
		sc.close();
	}

}
