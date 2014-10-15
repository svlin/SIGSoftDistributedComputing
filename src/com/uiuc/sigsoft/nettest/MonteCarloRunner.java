package com.uiuc.sigsoft.nettest;

public class MonteCarloRunner {
	private static final int STEPS_IN_BLOCK = 1000;
	private Thread calculationThread;
	private double results;
	
	public double getResults() {
		return results;
	}

	private void setResults(double results) {
		this.results = results;
	}
	
	public MonteCarloRunner() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				MonteCarlo accumulator = new MonteCarlo();
				while (!Thread.interrupted()) {
					accumulator.run(STEPS_IN_BLOCK);
				}
				setResults(accumulator.getPortionHits());
			}
		};
		calculationThread = new Thread(runnable);
		calculationThread.setPriority(Thread.MIN_PRIORITY);
		
	}
	
	public void run() {
		calculationThread.start();
	}
	
	public void pause() {
		calculationThread.interrupt();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MonteCarloRunner runner = new MonteCarloRunner();
		runner.run();
	}

}
