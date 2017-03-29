import java.util.*;

public class MST {
	
	static ArrayList<ArrayList<Edge>> adjlist;
	static boolean[] marked;
	static PriorityQueue<Edge> pq;
	
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

	static void process(Edge vertex){
		marked[vertex.u] = true;
		for(Edge adj: adjlist.get(vertex.u)){
			if(!marked[adj.u]){
				pq.add(adj);
			}
		}
	}
	
	static int prim(){ //return the minimum spanning tree as an adjacency list
		process(new Edge(0, 0, 0)); //start at vertex 0, weight is inconsequential
		int mincost = 0;
		while(!pq.isEmpty()){
			Edge first = pq.poll();
			if(!marked[first.u]){
				mincost += first.weight;
				process(first);
				System.out.println(first.u + " -> " + first.v + " with weight " + first.weight);
			}
		}
		return mincost;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//first type number of vertices in the graph
		//for each vertex first type number of adjacent vertices
		//then for each adjacent, type its vertex number first then the edge weight
		Scanner sc = new Scanner(System.in);
		int numvertices = sc.nextInt();
		pq = new PriorityQueue<Edge>();
		marked = new boolean[numvertices];
		adjlist = new ArrayList<ArrayList<Edge>>();
		for(int i = 0; i < numvertices; i++){
			adjlist.add(new ArrayList<Edge>());
			int neighbors = sc.nextInt();
			for(int j = 0; j < neighbors; j++){
				int v = sc.nextInt();
				int weight = sc.nextInt();
				adjlist.get(i).add(new Edge(v, i, weight));
			}
		}
		System.out.println("Edges in MST: ");
		int result = prim();
		System.out.println("Minimum cost: " + result);
		sc.close();
	}

}
