import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class C {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		int q = in.nextInt();

		FenwickTree ft = new FenwickTree(n);
		for (int i=0; i<q; i++) {
			ft.add(in.nextInt()-1, in.nextInt(), 1);
		}

		for (int i=0; i<n; i++) {
			out.print(ft.get(i)%2);
		}
		out.println();
	}

	public static void main(String[] args) {
		debug = args.length > 0;
		long start = System.currentTimeMillis();

		solve();
		out.flush();

		long end = System.currentTimeMillis();
		dump((end-start) + "ms");
		in.close();
		out.close();
	}

	static void dump(Object... o) { if (debug) System.err.println(Arrays.deepToString(o)); }
}

class FenwickTree {

	public static final long MOD = 1_000_000_007;
	public final int length;
	private long[] data;

	public FenwickTree(int length) {
		this.length = length;
		this.data = new long[length+2];
	}

	// [begin, end)
	public void add(int begin, int end, long n) {
		add(begin, n);
		add(end, (MOD - n)%MOD);
	}

	private void add(int idx, long n) {
		idx++;
		while (idx <= length) {
			data[idx] = (data[idx] + n)%MOD;
			idx += idx&-idx;
		}
	}

	public long get(int idx) {
		idx++;
		long ret = 0;
		while (idx > 0) {
			ret = (ret + data[idx])%MOD;
			idx -= idx&-idx;
		}
		return ret;
	}

	public String toString() {
		long[] val = new long[length];
		for (int i=0; i<length; i++) val[i] = get(i);
		return Arrays.toString(val);
	}
}