public class QuickFind {
	private int[] id;

// set each object to iteself (N array access)
	public QuickFind(int N) {
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}

// check whether p and q are in the same component (O(1))
	public boolean connected(int p, int q) {
		return id[p] == id[q];
	}

// change all entries same as id[p] to id[q] (O(N))
	public void union(int p, int q) {
		int pid = id[p];
		int qid = id[q];
		for (int i = 0; i < id.length; i++) {
			if (id[i] == pid) {
				id[i] = qid;
			}
		}
	}

}
