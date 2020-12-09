package net.jcip.examples.resultcache;

import java.math.BigInteger;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.jcip.annotations.ThreadSafe;

/**
 * 
* @ClassName: Factorizer  
* @Description: Factorizing servlet that caches results using Memoizer
* @author 去恶  
* @date 2020年12月9日
 */
@ThreadSafe
public class Factorizer extends GenericServlet implements Servlet {
	private final Computable<BigInteger, BigInteger[]> c = new Computable<BigInteger, BigInteger[]>() {
		public BigInteger[] compute(BigInteger arg) {
			return factor(arg);
		}
	};
	private final Computable<BigInteger, BigInteger[]> cache = new Memoizer<BigInteger, BigInteger[]>(
			c);

	public void service(ServletRequest req, ServletResponse resp) {
		try {
			BigInteger i = extractFromRequest(req);
			encodeIntoResponse(resp, cache.compute(i));
		} catch (InterruptedException e) {
			encodeError(resp, "factorization interrupted");
		}
	}

	void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
	}

	void encodeError(ServletResponse resp, String errorString) {
	}

	BigInteger extractFromRequest(ServletRequest req) {
		return new BigInteger("7");
	}

	BigInteger[] factor(BigInteger i) {
		// Doesn't really factor
		return new BigInteger[] { i };
	}
}