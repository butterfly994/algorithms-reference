import java.util.*;
public class SSSPUnweighted {
	//a variation on breadth first search
	static int[] dist; //stores the minimum distance from the source to i
	static int[] parent;
	static int n;
	static int source;
	static ArrayList<ArrayList<Integer>> adjlist;
	
	static void sssp(int s){ //build shortest path spanning tree
		source = s;
		dist = new int[n];
		for(int i = 0; i < n; i++){
			dist[i] = 10000; //some big number
		}
		dist[s] = 0;
		parent = new int[n];
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(s);
		while(!q.isEmpty()){
			int u = q.poll();
			for(Integer adj : adjlist.get(u)){
				if(dist[adj] == 10000){
					dist[adj] = dist[u] + 1;
					parent[adj] = u;
					q.add(adj);
				}
			}
		}
	}
	
	static int mindistance(int d){
		return dist[d];
	}
	
	static void path(int d){
		if(d == source){
			System.out.print(d + " ");
			return;
		}
		else{
			path(parent[d]);
			System.out.print(d + " ");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		adjlist = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < n; i++){
			adjlist.add(new ArrayList<Integer>());
		}
		for(int i = 0; i < n; i++){
			int neighbors = sc.nextInt();
			for(int j = 0; j < neighbors; j++){
				int v = sc.nextInt();
				adjlist.get(i).add(v);
				adjlist.get(v).add(i);
			}
		}
		int source = sc.nextInt();
		int dest = sc.nextInt();
		sssp(source);
		System.out.println("Shortest distance: " + mindistance(dest));
		System.out.print("Shortest path: ");
		path(dest);
		sc.close();
	}

}
