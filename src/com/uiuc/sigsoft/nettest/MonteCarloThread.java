package com.uiuc.sigsoft.nettest;

public class MonteCarloThread extends Thread {
	private final int STEPS_IN_BLOCK;
	private double results;
	
	public MonteCarloThread(int steps) {
		STEPS_IN_BLOCK = steps;
		setPriority(MIN_PRIORITY);
	}
	
	public double getResults() {
		return results;
	}

	private void setResults(double results) {
		this.results = results;
	}
	
	@Override
	public void run() {
		MonteCarlo accumulator = new MonteCarlo();
		while (!Thread.interrupted()) {
			accumulator.run(STEPS_IN_BLOCK);
			yield();
		}
		setResults(accumulator.getPortionHits());
	}
}
