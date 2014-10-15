import java.math.BigDecimal;
import java.security.SecureRandom;


public class Demo {

	private static double getDouble(byte[] bArr) {
		double raw = getRawDouble(bArr);
		raw = Math.abs(raw);
		raw /= Long.MAX_VALUE;
		return raw;
	}
	private static double getRawDouble(byte[] bArr) {
		long result = 0;
		for (int i = 0; i < 8 * bArr.length; i += 8) {
			result = result << i;
			result = result | bArr[i/8];
		}
		double generated = (double)result;
		return generated;
	}
	
	public static final long RUNS = 100_000_000L;
	public static void main(String[] args) {
		long succeeded = 0;
		SecureRandom rand = new SecureRandom();
		long start = System.currentTimeMillis();
		for (long i = 0; i < RUNS; i++) {
			byte[] xArr = new byte[8];
			byte[] yArr = new byte[8];
			rand.nextBytes(xArr);
			rand.nextBytes(yArr);
			double x = getDouble(xArr);
			double y = getDouble(yArr);
			if (Math.pow(x, 2) + Math.pow(y,  2) <= 1)
				succeeded++;
		}
		long now = System.currentTimeMillis();
		long time = now - start;
		BigDecimal calculatedPI = new BigDecimal(succeeded);
		calculatedPI = calculatedPI.divide(new BigDecimal(RUNS));
		calculatedPI = calculatedPI.multiply(new BigDecimal(4.0));
		System.out.printf("Process tested %,d runs in: %,d milliseconds (%f runs/second)\n", RUNS, time, (double)RUNS/time);
		System.out.printf("Process output: pi=%s %f percent error", calculatedPI.toString(), 100.0*Math.abs(calculatedPI.doubleValue() - Math.PI)/Math.PI);
	}
}
