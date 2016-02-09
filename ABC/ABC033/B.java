import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class B {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);

	static void solve() {
		int n = in.nextInt();
		int sum = 0;
		int max = 0;
		String s = "";
		for (int i=0; i<n; i++) {
			String t = in.next();
			int a = in.nextInt();
			sum += a;
			if (max < a) {
				s = t;
				max = a;
			}
		}

		if (sum < max*2) {
			out.println(s);
		} else {
			out.println("atcoder");
		}
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		solve();
		out.flush();

		long end = System.currentTimeMillis();
		//trace(end-start + "ms");
		in.close();
		out.close();
	}

	static void trace(Object... o) { System.out.println(Arrays.deepToString(o));}
}