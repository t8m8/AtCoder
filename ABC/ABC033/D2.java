import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class D2 {

	static final Reader in = new Reader();
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static <T extends Comparable<? super T>> int lowerBound(T[] a, T k) {
		int l = -1, r = a.length;
		while (r - l > 1) {
			int m = (l + r)/2;
			if (k.compareTo(a[m]) <= 0) r = m;
			else l = m;
		}
		return r;
	}

	static <T extends Comparable<? super T>> int upperBound(T[] a, T k) {
		int l = -1, r = a.length;
		while (r - l > 1) {
			int m = (l + r)/2;
			if (k.compareTo(a[m]) < 0) r = m;
			else l = m;
		}
		return r;
	}


	static void solve(){
		long n = in.nextInt();

		Point[] ps = new Point[(int)n];
		for (int i=0; i<n; i++) {
			ps[i] = new Point(in.nextInt(), in.nextInt());
		}

		long y = 0, z = 0;

		for (int i=0; i<n; i++) {
			Double[] a = new Double[2*((int)n-1)];
			int pos = 0;
			for (int j=0; j<n; j++) {
				if (i == j) continue;
				a[pos] = Math.atan2(ps[j].getY() - ps[i].getY(), ps[j].getX() - ps[i].getX());
				a[pos+1] = a[pos] + Math.PI*2;
				pos+=2;
			}

			Arrays.sort(a);

			for (int j=0; j<n-1; j++) {
				y += upperBound(a, a[j] + Math.PI/2 + 1e-10) - lowerBound(a, a[j] + Math.PI/2 - 1e-10);
				z += lowerBound(a, a[j] + Math.PI) - upperBound(a, a[j] + Math.PI/2 + 1e-10);
			}
		}

		long x = n*(n-1)*(n-2)/6 - y - z;
		out.println(x+" "+y+" "+z);
	}

	public static void main(String[] args) throws Exception {
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

class Point extends EuclideanSpace implements Comparable, Cloneable {

	private double x, y;
	public Point(double x, double y) {
		this.x = x; this.y = y;
	}

	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}

	public void set(double x, double y) {
		this.x = x; this.y = y;
	}

	public int compareTo(Object another) {
		Point p = (Point)another;
		if (!epsEquals(this.x,p.x)) return this.x < p.x ? -1 : 1;
		if (!epsEquals(this.y,p.y)) return this.y < p.y ? -1 : 1;
		return 0;
	}

	public boolean equals(Point p) {
		return epsEquals(this.x,p.x) && epsEquals(this.y,p.y);
	}

	public Point clone() {
		return new Point(this.x, this.y);
	}

	public String toString() {
		return "("+x+","+y+")";
	}
}

class EuclideanSpace{
	public static final double EPS = 1E-10;
	public static boolean epsEquals(double a, double b) { return Math.abs(a-b) < EPS; };
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
		while (b != '\n' && b != '\r') {
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