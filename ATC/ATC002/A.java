import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class A {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve() {
		int r = in.nextInt();
		int c = in.nextInt();
		int sy = in.nextInt() - 1;
		int sx = in.nextInt() - 1;
		int gy = in.nextInt() - 1;
		int gx = in.nextInt() - 1;
		char[][] t = new char[r][c];
		for (int i=0; i<r; i++) {
			t[i] = in.next().toCharArray();
		}

		ArrayDeque<int[]> que = new ArrayDeque<>();
		BitSet visited = new BitSet();

		que.add(new int[]{sy,sx,0});
		visited.set(sy*c+sx);

		int[] dx = { 0, 1, 0,-1};
		int[] dy = { 1, 0,-1, 0};
		int[][] dist = new int[r][c];

		while (!que.isEmpty()) {
			int[] p = que.pollFirst();
			int cy = p[0];
			int cx = p[1];
			int d = p[2];
			dist[cy][cx] = d;

			for (int i=0; i<4; i++) {
				int ny = cy + dy[i];
				int nx = cx + dx[i];
				if (ny < 0 || nx < 0 || r <= ny || c <= nx) continue;
				if (visited.get(ny*c+nx) || t[ny][nx] == '#') continue;
				que.add(new int[]{ny, nx, d+1});
				visited.set(ny*c+nx);
			}
		}

		out.println(dist[gy][gx]);
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