public class UFDS {
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
