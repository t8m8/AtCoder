import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class D {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int n = in.nextInt();
		char[] c = in.next().toCharArray();

		SegmentTreeInt l = new SegmentTreeInt(n);
		SegmentTreeInt r = new SegmentTreeInt(n);

		int[] hl = new int[n+1];
		int[] hr = new int[n+1];

		for (int i=1; i<=n; i++) {
			hl[i] = hl[i-1];
			hr[i] = hr[i-1];
			if (c[i-1] == '(') {
				hl[i]++;
				hr[i]++;
			} else if (c[i-1] == ')') {
				hl[i]--;
				hr[i]--;
			} else {
				hl[i]++;
				hr[i]--;
			}
			l.set(i-1, hl[i]);
			r.set(i-1, hr[i]);
		}

		int q = in.nextInt();
		while (q-- > 0) {
			int s = in.nextInt()-1;
			int t = in.nextInt()-1;

			if ((t - s + 1)%2 == 1) {
				out.println("No");
				continue;
			}

			if (hl[s] <= l.get(s, t+1) && hr[t+1] <= r.get(s, t+1)) {
				out.println("Yes");
			} else {
				out.println("No");
			}
		}
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

class SegmentTreeInt {

	public static final int INF = Integer.MAX_VALUE/2;
	public final int length;
	private final int reviceLen;
	private final int internalLen;
	private int[] data;

	public SegmentTreeInt(int n) {
		this.length = n;
		this.reviceLen = Integer.highestOneBit(Math.max(length-1,1))<<1;
		this.internalLen = 2*reviceLen - 1;
		this.data = new int[internalLen];
		for (int i=0; i<internalLen; i++) data[i] = INF;
	}

	public void set(int idx, int val) {
		idx += reviceLen-1;
		data[idx] = val;
		while (idx > 0) {
			idx = (idx - 1) / 2;
			data[idx] = Math.min(data[idx*2+1], data[idx*2+2]);
		}
	}

	public int get(int idx) {
		return data[idx + reviceLen - 1];
	}

	public int get(int begin, int end) {
		return get(begin, end, 0, 0, reviceLen);
	}

	private int get(int begin, int end, int idx, int l, int r) {
		if (r <= begin || end <= l) return INF;
		if (begin <= l && r <= end) return data[idx];

		int vl = get(begin, end, idx*2+1, l, (l+r)/2);
		int vr = get(begin, end, idx*2+2, (l+r)/2, r);
		return Math.min(vl, vr);
	}

	public String toString() {
		int[] val = new int[length];
		for (int i=0; i<length; i++) val[i] = data[i + reviceLen - 1];
		return Arrays.toString(val);
	}
}