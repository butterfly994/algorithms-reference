import java.util.*;

public class SSSP {
	//If the graph is a DAG(directed acyclic graph), use the topological sort algorithm
	static class Edge implements Comparable<Edge> {
		int u, v, weight;
		public Edge(int u, int v, int weight){
			super();
			this.u = u;
			this.v = v;
			this.weight = weight;
		}
		
		public int compareTo(Edge that){
			return this.weight - that.weight;
		}
	}
	
	static ArrayList<ArrayList<Edge>> adjlist;
	static int[] dist; //stores the minimum distance from the source to i
	static int[] parent;
	static int n;
	static int source;
	
	static void dijkstra(int s){
		source = s;
		dist = new int[n];
		for(int i = 0; i < n; i++){
			dist[i] = 10000; //some big number
		}
		dist[s] = 0;
		parent = new int[n];
		
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		pq.add(new Edge(s, s, 0));
		while(!pq.isEmpty()){
			Edge e = pq.poll();
			int w = e.weight;
			int u = e.v;
			if(w > dist[u]) continue;
			else{
				for(Edge x: adjlist.get(u)){
					if(dist[x.v] > dist[u] + x.weight){
						dist[x.v] = dist[u] + x.weight;
						parent[x.v] = u;
						pq.add(new Edge(u, x.v, dist[x.v]));
					}
				}
			}
		}
	}
	
	static void bellmanford(int s){
		source = s;
		dist = new int[n];
		for(int i = 0; i < n; i++){
			dist[i] = 10000; //some big number
		}
		dist[s] = 0;
		parent = new int[n];
		
		for(int i = 0; i < n-1; i++){
			for(int u = 0; u < n; u++){
				for(Edge x: adjlist.get(u)){
					if(dist[x.v] >  dist[u] + x.weight){
						dist[x.v] = dist[u] + x.weight;
						parent[x.v] = u;
					}
				}
			}
		}
	}
	
	static boolean hasNegativeCycle(){
		//prerequisite: have run bellman ford
		for(int u = 0; u < n; u++){
			for(Edge x: adjlist.get(u)){
				if(dist[x.v] > dist[u] + x.weight) return true; //if after running the algorithm, it's possible to get a better distance by relaxing an edge over again, there is a negative cycle
			}
		}
		return false;
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
		adjlist = new ArrayList<ArrayList<Edge>>();
		for(int i = 0; i < n; i++){
			adjlist.add(new ArrayList<Edge>());
		}
		for(int i = 0; i < n; i++){
			int neighbors = sc.nextInt();
			for(int j = 0; j < neighbors; j++){
				int v = sc.nextInt();
				int weight = sc.nextInt();
				adjlist.get(i).add(new Edge(i, v, weight));
			}
		}
		int source = sc.nextInt();
		int dest = sc.nextInt();
//		dijkstra(source);
		bellmanford(source);
		System.out.println("Shortest distance: " + mindistance(dest));
		System.out.print("Shortest path: ");
		path(dest);
		sc.close();

	}

}
