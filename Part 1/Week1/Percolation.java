import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
	private boolean[] open;
	private WeightedQuickUnionUF uf;
	private WeightedQuickUnionUF uftop;
	private int N;
	private int cnt;
	private int top;
	private int bottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException();
		}
		N = n;
		cnt = 0;
		open = new boolean[N * N];
		top = N * N;
		bottom = N * N + 1;
		uf = new WeightedQuickUnionUF(N * N + 2);
		uftop = new WeightedQuickUnionUF(N * N + 1);
	}

	// get the 1d, 0-start index from 2d, 1-start index
	private int getPos(int row, int col) {
		return N * (row - 1) + col - 1;
	}

	// check if row & col are between 1 ~ n
	private void checkBound(int row, int col) {
		if (row <= 0 || row > N || col <= 0 || col > N) {
			throw new IllegalArgumentException();
		}
	}

	// override union so it will not do repeat work 
	private void union(int pos1, int pos2, WeightedQuickUnionUF uf) {
		if (uf.find(pos1) != uf.find(pos2)) {
			uf.union(pos1, pos2);
		}
	}

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
		checkBound(row, col);
		if (isOpen(row, col)) {
			return;
		}

		int pos = getPos(row, col);
		open[pos] = true;
		cnt++;

		if (row == 1) {
			union(pos, top, uf);
			union(pos, top, uftop);
		} else if (isOpen(row - 1, col)) {
			union(pos, pos - N, uf);
			union(pos, pos - N, uftop);
		}

		if (row == N) {
			union(pos, bottom, uf);
		} else if (isOpen(row + 1, col)) {
			union(pos, pos + N, uf);
			union(pos, pos + N, uftop);
		}

		if (col != 1 && open[pos - 1]) {
			union(pos, pos - 1, uf);
			union(pos, pos - 1, uftop);
		}

		if (col != N && open[pos + 1]) {
			union(pos, pos + 1, uf);
			union(pos, pos + 1, uftop);
		}
	}

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
		checkBound(row, col);
		return open[getPos(row, col)];
	}

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
		checkBound(row, col);
		return (uftop.find(getPos(row, col)) == uftop.find(top));
	}

    // returns the number of open sites
    public int numberOfOpenSites() {
		return cnt;
	}

    // does the system percolate?
    public boolean percolates() {
		return (uf.find(top) == uf.find(bottom));
	}

    // test client (optional)
    public static void main(String[] args) {
		Percolation p
	}
}