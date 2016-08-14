import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		String s = in.next();
		int n = s.length();
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		for (int i=0; i<n; i++) {
			switch (s.charAt(i)) {
				case '0': stack.addLast(0); break;
				case '1': stack.addLast(1); break;
				case 'B': stack.pollLast(); break;
			}
		}
		for (int i : stack) {
			out.print(i);
		}
		out.println();
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