import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class C {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int r = in.nextInt();
		int c = in.nextInt();
		int k = in.nextInt();

		int n = in.nextInt();
		int[] rcnt = new int[r];
		int[] ccnt = new int[c];
		int[][] rc = new int[n][2];

		for (int i=0; i<n; i++) {
			rc[i][0] = in.nextInt() - 1;
			rc[i][1] = in.nextInt() - 1;
			rcnt[rc[i][0]]++;
			ccnt[rc[i][1]]++;
		}

		int[] rbucket = new int[100001];
		int[] cbucket = new int[100001];
		for (int i=0; i<r; i++) {
			rbucket[rcnt[i]]++;
		}
		for (int i=0; i<c; i++) {
			cbucket[ccnt[i]]++;
		}

		long cnt = 0;
		for (int i=0; i<k+1; i++) {
			cnt += (long)rbucket[i]*cbucket[k-i];
		}

		for (int i=0; i<n; i++) {
			if (rcnt[rc[i][0]] + ccnt[rc[i][1]] == k) {
				cnt--;
			}
			if (rcnt[rc[i][0]] + ccnt[rc[i][1]] == k+1) {
				cnt++;
			}
		}

		out.println(cnt);
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

class Pair <A extends Comparable<? super A>, B extends Comparable<? super B>> implements Comparable<Pair<A, B>> {
	A fst; B snd;

	public Pair(A fst, B snd) {
		this.fst = fst;
		this.snd = snd;
	}

	public int compareTo(Pair<A, B> p) {
		if (fst.compareTo(p.fst) != 0) return fst.compareTo(p.fst);
		return snd.compareTo(p.snd);
	}

	public boolean equals(Object o) {
		Pair p = (Pair)o;
		return fst.equals(p.fst) && snd.equals(p.snd);
	}

	public int hashCode() {
		return Objects.hash(fst, snd);
	}

	public String toString() {
		return "(" + fst.toString() + "," + snd.toString() + ")";
	}
}