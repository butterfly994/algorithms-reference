import java.util.*;

public class Kruskal {
	//sort the edge list, then add the next edge with minimum weight that doesn't create a cycle(inherent property of mst is trees have no cycle)
	//these two nested classes are prerequisites for this algorithm
	//use for variants minimum spanning subgraph, second best, and minimax/maximin
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
	
	static class UFDS {
		int[] parent;
		int[] rank;
		public UFDS(int n){
			parent = new int[n];
			rank = new int[n];
			for(int i = 0; i < n; i++){
				parent[i] = i;
			}
		}
		
		public int findSet(int i){
			if(parent[i] == i) return i;
			else{
				parent[i] = findSet(parent[i]);
				return parent[i];
			}
		}
		
		public boolean isSameSet(int i, int j){
			return findSet(i) == findSet(j);
		}
		
		public void unionSet(int i, int j) {
			if(!isSameSet(i, j)){
				int parenti = findSet(i);
				int parentj = findSet(j);
				if(rank[parenti] >= rank[parentj]) parent[j] = parenti;
				else{
					parent[i] = parentj;
					if(rank[parenti] == rank[parentj]) rank[parenti]++;
				}
			}
		}

	}
	
	static ArrayList<Edge> edgelist;
	static int n;
	
	static int kruskal(){
		int mincost = 0;
		UFDS ufds = new UFDS(n);
		for(int i = 0; i < edgelist.size(); i++){
			Edge current = edgelist.get(i);
			if(!ufds.isSameSet(current.u, current.v)){
				mincost += current.weight;
				ufds.unionSet(current.u, current.v);
				System.out.println(current.u + " -> " + current.v + " with weight " + current.weight);
			}
		}
		return mincost;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		edgelist = new ArrayList<Edge>();
		for(int i = 0; i < n; i++){
			int neighbors = sc.nextInt();
			for(int j = 0; j < neighbors; j++){
				int v = sc.nextInt();
				int weight = sc.nextInt();
				edgelist.add(new Edge(i, v, weight));
			}
		}
		Collections.sort(edgelist);
		System.out.println("Edges in MST: ");
		System.out.println(kruskal());
		sc.close();
	}

}
