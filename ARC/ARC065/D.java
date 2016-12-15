import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class D {

	static final Reader in = new Reader();
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static void solve(){
		int n = in.nextInt();
		int k = in.nextInt();
		int l = in.nextInt();

		DisjointSet clus = new DisjointSet(n);

		for (int i=0; i<k; i++) {
			int p = in.nextInt() - 1;
			int q = in.nextInt() - 1;
			clus.unite(p, q);
		}

		DisjointSet clus2 = new DisjointSet(n);

		HashMap<Integer,Integer> map = new HashMap<>();
		int p = 0;
		for (int i=0; i<n; i++) {
			if (map.containsKey(clus.find(i))) {
			} else {
				map.put(clus.find(i), p++);
			}
		}

		int m = p;

		int[] ss = new int[l];
		int[] tt = new int[l];
		for (int i=0; i<l; i++) {
			ss[i] = in.nextInt() - 1;
			tt[i] = in.nextInt() - 1;
			clus2.unite(ss[i], tt[i]);
		}

		HashMap<Integer,Integer> map2 = new HashMap<>();
		p = 0;
		for (int i=0; i<n; i++) {
			if (map2.containsKey(clus2.find(i))) {
			} else {
				map2.put(clus2.find(i), p++);
			}
		}
		int m2 = p;

		DisjointSet ans = new DisjointSet(n);
		HashMap<Long, ArrayList<Integer>> lists = new HashMap<>();

		for (int i=0; i<l; i++) {
			int s = ss[i];
			int t = tt[i];
			if (clus.same(s, t)) {
				ans.unite(s, t);
			} else {
				long sid = map.get(clus.find(s));
				long tid = map.get(clus.find(t));

				long sid2 = map2.get(clus2.find(s));
				long tid2 = map2.get(clus2.find(t));

				if (lists.containsKey(sid*m2+tid2)) {
					lists.get(sid*m2+tid2).add(s);
				} else {
					ArrayList<Integer> list = new ArrayList<>();
					list.add(s);
					lists.put(sid*m2+tid2, list);
				}

				if (lists.containsKey(tid*m2+sid2)) {
					lists.get(tid*m2+sid2).add(t);
				} else {
					ArrayList<Integer> list = new ArrayList<>();
					list.add(t);
					lists.put(tid*m2+sid2, list);
				}
			}
		}

		for (Long key : lists.keySet()) {
			ArrayList<Integer> list = lists.get(key);
			for (int i=0; i<list.size()-1; i++) {
				ans.unite(list.get(i), list.get(i+1));
			}
		}

		boolean f = false;
		for (int i=0; i<n; i++) {
			out.print((f ? " ": "") + ans.size(i));
			f = true;
		}
		out.println();
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

class DisjointSet {

	private int[] par, cnt;

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