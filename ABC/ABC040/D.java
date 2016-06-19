import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class D {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static int[] ans;

	static int kruskal(int[] s, int[] t, int[] cost, int n, int[][] h, int q) {
		DisjointSet ds = new DisjointSet(n);
		int e = s.length;
		int[][] edge = new int[e][2];
		for (int i=0; i<e; i++) {
			edge[i][0] = cost[i];
			edge[i][1] = i;
		}

		int res = 0, pos = 0;

		Arrays.sort(edge, new Comparator<int[]>(){
			public int compare(int[] a, int[] b) {
				return b[0] - a[0];
			}
		});
		for (int i=0; i<e; i++) {
			int cur = edge[i][1];
			if (!ds.same(s[cur],t[cur])) {
				while (pos < q && h[pos][1] >= cost[cur]) {
					ans[h[pos][2]] = ds.cnt[ds.find(h[pos][0])];
					pos++;
				}

				ds.unite(s[cur],t[cur]);
				res += cost[cur];
			}
		}

		while (pos < q) {
			ans[h[pos][2]] = ds.cnt[ds.find(h[pos][0])];
			pos++;
		}

		return res;
	}

	static void solve() {
		int n = in.nextInt();
		int m = in.nextInt();

		int[] a = new int[m];
		int[] b = new int[m];
		int[] y = new int[m];
		for (int i=0; i<m; i++) {
			a[i] = in.nextInt() - 1;
			b[i] = in.nextInt() - 1;
			y[i] = in.nextInt();
		}

		int q = in.nextInt();

		int[][] h = new int[q][3];
		for (int i=0; i<q; i++) {
			h[i][0] = in.nextInt() - 1;
			h[i][1] = in.nextInt();
			h[i][2] = i;
		}

		Arrays.sort(h, new Comparator<int[]>(){
			public int compare(int[] a, int[] b) {
				if (a[1] != b[1]) return b[1] - a[1];
				return a[0] - b[0];
			}
		});


		ans = new int[q];
		kruskal(a, b, y, n, h, q);

		for (int i=0; i<q; i++) {
			out.println(ans[i]);
		}
	}

	public static void main(String[] args) {
		debug = args.length > 0;
		long start = System.nanoTime();

		solve();
		out.flush();

		long end = System.nanoTime();
		dump((end - start) / 1000000 + " ms");
		in.close();
		out.close();
	}

	static void dump(Object... o) { if (debug) System.err.println(Arrays.deepToString(o)); }
}

class DisjointSet {
	int[] data;
	int[] cnt;

	public DisjointSet(int n){
		data = new int[n];
		cnt = new int[n];
		for (int i=0; i<n; i++) {
			data[i] = i;
			cnt[i] = 1;
		}
	}

	public int find(int x){
		if(data[x] == x) return x;
		int t = find(data[x]);
		data[x] = t;
		cnt[x] = Math.max(cnt[x], cnt[t]);
		cnt[t] = Math.max(cnt[x], cnt[t]);
		return data[x];
	}

	public boolean same(int x,int y){
		return find(x) == find(y);
	}

	public void unite(int x,int y){
		int a = cnt[x];
		int b = cnt[y];
		cnt[x] += b;
		cnt[y] += a;

		int t = find(x);
		int s = find(y);
		data[t] = s;
		cnt[t] = Math.max(cnt[t], cnt[s]);
		cnt[s] = Math.max(cnt[t], cnt[s]);
	}

	public String toString() {
		return Arrays.toString(data);
	}
}