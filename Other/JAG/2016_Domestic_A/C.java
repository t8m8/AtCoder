import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class C {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static String s, t;

	static void rec(int sl, int sr, int tl, int tr) {
		if (sl >= sr) return;
		if (tl >= tr) return;

		int sval = 0, lv = 0;
		int sposl = 0, sposr = 0;

		for (int i=sl; i<=sr; i++) {
			if (s.charAt(i) == '(') {
				lv++;
			} else if (s.charAt(i) == ')') {
				lv--;
			}

			if (lv == 0 && s.charAt(i) == '[') {
				sposl = i;
				int j;
				for (j=i+1; ; j++) {
					if (s.charAt(j) == ']') break;
				}
				sposr = j;
				sval = Integer.parseInt(s.substring(i+1, j));
				break;
			}
		}

		int tval = 0;
		lv = 0;
		int tposl = 0, tposr = 0;

		for (int i=tl; i<=tr; i++) {
			if (t.charAt(i) == '(') {
				lv++;
			} else if (t.charAt(i) == ')') {
				lv--;
			}

			if (lv == 0 && t.charAt(i) == '[') {
				tposl = i;
				int j;
				for (j=i+1; ; j++) {
					if (t.charAt(j) == ']') break;
				}
				tposr = j;
				tval = Integer.parseInt(t.substring(i+1, j));
				break;
			}
		}

		out.print("(");
		rec(sl+1, sposl-2, tl+1, tposl-2);
		out.print(")[");
		out.print((sval+tval));
		out.print("](");
		rec(sposr+2, sr-1, tposr+2, tr-1);
		out.print(")");
	}

	static void solve() {
		s = in.next();
		t = in.next();
		rec(0, s.length()-1, 0, t.length() - 1);
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