import java.util.*;
import java.io.*;
import java.awt.geom.*;
import java.math.*;

public class C {

	static final Scanner in = new Scanner(System.in);
	static final PrintWriter out = new PrintWriter(System.out,false);
	static boolean debug = false;

	static final int MOD = 1_000_000_007;

	static long[] fact(int n, long mod) {
		long[] fact = new long[n+1];
		fact[0] = fact[1] = 1;
		for (int i=2; i<=n; i++) {
			fact[i] = fact[i-1]*i%mod;
		}
		return fact;
	}

	public static long[] extgcd(long a, long b) {
		if (a == 0 || b == 0) return null;
		int asig = Long.signum(a);
		int bsig = Long.signum(b);
		a = Math.abs(a); b = Math.abs(b);
		long x = 1, y = 0;
		long p = 0, q = 1;
		while (b > 0) {
			long c = a/b, d = a;
			a = b; b = d % b;
			d = x; x = p; p = d - c*p;
			d = y; y = q; q = d - c*q;
		}
		return new long[]{x*asig,y*bsig,a};
	}


	static long inv(long a, long mod) {
		long[] gcd = extgcd(a, mod);
		return (mod + gcd[0]%mod)%mod;
	}

	static long[] factInv(long[] fact, long mod) {
		int n = fact.length;
		long[] inv = new long[n];
		inv[n-1] = inv(fact[n-1], mod);
		for (int i=n-1; i>0; i--) {
			inv[i-1] = inv[i]*i%mod;
		}
		return inv;
	}

	static long nCk(int n, int k, long mod, long[] fact, long[] factI) {
		if (k < 0 || n < k) return 0;
		return fact[n] * factI[k] % mod * factI[n-k] % mod;
	}

	static void solve() {
		int w = in.nextInt()-1;
		int h = in.nextInt()-1;

		long[] fact = fact(w+h, MOD);
		long[] factI = factInv(fact, MOD);
		out.println(nCk(w+h, w, MOD, fact, factI));
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