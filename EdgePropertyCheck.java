import java.util.*;
public class EdgePropertyCheck {
	//uses DFS spanning tree
	static ArrayList<ArrayList<Integer>> adjlist = new ArrayList<ArrayList<Integer>>(); //build the adjacency list
	static int[] graphstate = new int[adjlist.size()];
	static int[] dfsparent = new int[adjlist.size()]; //needed to distinguish bidirectional edges from true cycles
	
	public static void graphCheck(int u){
		//0 = unvisited, 1 = explored, 2 = visited
		graphstate[u] = 1;
		for(int i = 0; i < adjlist.get(u).size(); i++){
			int v = adjlist.get(u).get(i);
			if(graphstate[v] == 0){ //tree edge, continue recursion
				dfsparent[v] = u;
				graphCheck(v);
			}
			else if(graphstate[v] == 1){
				if(v == dfsparent[u]){ //not a true cycle
					System.out.println("Bi-directional Edge:" + u + "-->" + v + ", " + v + "-->" + u);
				}
				else{ //a true cycle
					System.out.println("Back Edge (" + u + ", " + v + ") (Part of a Cycle)"); //typical use
					//explored to explored
				}
			}
			else if(graphstate[v] == 2){
				System.out.println("Forward/Cross Edge (" + u + ", " + v + ")"); //typically not tested
				//explored to visited
			}
		}
		graphstate[u] = 2;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int numcases = sc.nextInt();
		for(int i = 1; i <= numcases; i++){
			int numvertices = sc.nextInt(); //same i/o as dominator
			for(int j = 0; j < numvertices; j++){
				ArrayList<Integer> current = new ArrayList<Integer>();
				for(int k = 0; k < numvertices; k++){
					int connect = sc.nextInt();
					if(connect == 1) current.add(k);
				}
				adjlist.add(current);	
			}
			for(int k = 0; k < numvertices; k++){
				if(graphstate[k] == 0){
					System.out.println("Component " + k + ": ");
					graphCheck(k);
				}
			}
		}
		sc.close();

	}

}
