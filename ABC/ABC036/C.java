import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class C {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static <T extends Comparable<? super T>> int lowerBound(ArrayList<T> a, T k) {
		int n = a.size();
		int l = -1, r = n;
		while (r - l > 1) {
			int m = (l + r)/2;
			if (k.compareTo(a.get(m)) <= 0) r = m;
			else l = m;
		}
		return r;
	}

	static void solve() {
		int n = in.nextInt();
		int[] a = new int[n];
		
		HashSet<Integer> set = new HashSet<>();
		ArrayList<Integer> list = new ArrayList<>();
		for (int i=0; i<n; i++) {
			a[i] = in.nextInt();
			if (!set.contains(a[i])) {
				set.add(a[i]);
				list.add(a[i]);
			}
		}
		Collections.sort(list);

		for (int i=0; i<n; i++) {
			out.println(lowerBound(list, a[i]));
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