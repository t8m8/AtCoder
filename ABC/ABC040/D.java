import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class D2 {

	static final Reader in = new Reader();
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

		static void solve() {
		int n = in.nextInt();
		int m = in.nextInt();

		int[] a = new int[m];
		int[] b = new int[m];
		int[] y = new int[m];
		int[][] edge = new int[m][2];
		for (int i=0; i<m; i++) {
			a[i] = in.nextInt() - 1;
			b[i] = in.nextInt() - 1;
			y[i] = in.nextInt();
			edge[i][0] = y[i];
			edge[i][1] = i;
		}

		Arrays.sort(edge, new Comparator<int[]>(){
			public int compare(int[] a, int[] b) {
				return b[0] - a[0];
			}
		});

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


		int[] ans = new int[q];
		int res = 0, pos = 0;

		DisjointSet ds = new DisjointSet(n);

		for (int i=0; i<m; i++) {
			int cur = edge[i][1];
			if (!ds.same(a[cur],b[cur])) {
				while (pos < q && h[pos][1] >= y[cur]) {
					ans[h[pos][2]] = ds.size(h[pos][0]);
					pos++;
				}

				ds.unite(a[cur],b[cur]);
				res += y[cur];
			}
		}

		while (pos < q) {
			ans[h[pos][2]] = ds.size(h[pos][0]);
			pos++;
		}

		dump(ds.cnt);

		for (int i=0; i<q; i++) {
			out.println(ans[i]);
		}
	}

	public static void main(String[] args) throws Exception {
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

class Reader {
	private final InputStream in;
	private final byte[] buf = new byte[1024];
	private int ptr = 0;
	private int buflen = 0;

	public Reader() { this(System.in);}
	public Reader(InputStream source) { this.in = source;}

	private boolean hasNextByte() {
		if (ptr < buflen) return true;
		ptr = 0;
		try{
			buflen = in.read(buf);
		}catch (IOException e) {e.printStackTrace();}
		if (buflen <= 0) return false;
		return true;
	}

	private int readByte() { if (hasNextByte()) return buf[ptr++]; else return -1;}

	private boolean isPrintableChar(int c) { return 33 <= c && c <= 126;}

	private void skip() { while(hasNextByte() && !isPrintableChar(buf[ptr])) ptr++;}

	public boolean hasNext() {skip(); return hasNextByte();}

	public String next() {
		if (!hasNext()) throw new NoSuchElementException();
		StringBuilder sb = new StringBuilder();
		int b = readByte();
		while (isPrintableChar(b)) {
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	public long nextLong() {
		if (!hasNext()) throw new NoSuchElementException();
		boolean minus = false;
		long num = readByte();

		if(num == '-'){
			num = 0;
			minus = true;
		}else if (num < '0' || '9' < num){
			throw new NumberFormatException();
		}else{
			num -= '0';
		}

		while(true){
			int b = readByte();
			if('0' <= b && b <= '9')
				num = num * 10 + (b - '0');
			else if(b == -1 || !isPrintableChar(b))
				return minus ? -num : num;
			else
				throw new NoSuchElementException();
		}
	}

	public int nextInt() {
		long num = nextLong();
		if (num < Integer.MIN_VALUE || Integer.MAX_VALUE < num)
			throw new NumberFormatException();
		return (int)num;
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}

	public char nextChar() {
		if (!hasNext()) throw new NoSuchElementException();
		return (char)readByte();
	}

	public String nextLine() {
		while (hasNextByte() && (buf[ptr] == '\n' || buf[ptr] == '\r')) ptr++;
		if (!hasNextByte()) throw new NoSuchElementException();

		StringBuilder sb = new StringBuilder();
		int b = readByte();
		while (b != '\n' && b != '\r' && b != -1) {
			sb.appendCodePoint(b);
			b = readByte();
		}

		return sb.toString();
	}

	public int[] nextIntArray(int n) {
		int[] res = new int[n];
		for (int i=0; i<n; i++) res[i] = nextInt();
		return res;
	}

	public char[] nextCharArray(int n) {
		char[] res = new char[n];
		for (int i=0; i<n; i++) res[i] = nextChar();
		return res;
	}

	public void close() {try{ in.close();}catch(IOException e){ e.printStackTrace();}};
}

class DisjointSet {
	int[] par, cnt;

	public DisjointSet(int n){
		par = new int[n];
		cnt = new int[n];
		for (int i=0; i<n; i++) {
			par[i] = i;
			cnt[i] = 1;
		}
	}

	public int find(int x){
		return par[x] == x ? x : (par[x] = find(par[x]));
	}

	public boolean same(int x, int y){
		return find(x) == find(y);
	}

	public void unite(int x, int y){
		x = find(x); y = find(y);
		if (x == y) return;
		cnt[x] = cnt[y] = cnt[x] + cnt[y];
		par[x] = y;
	}

	public int size(int x) {
		return cnt[find(x)];
	}

	public String toString() {
		return Arrays.toString(par);
	}
}