package com.uiuc.sigsoft.nettest;

/**
 * Created by Pri on 10/11/2014.
 * Edited by Andrew on 10/14/2014
 */
public class MonteCarlo {
    private long runs;
    private long hits;
    
    public MonteCarlo() {
        runs = 0;
        hits = 0;
    }

    public void run(int times) {
        for (int i = 0; i < times; i++) {
            double random_x = Math.random();
            double random_y = Math.random();
            if (random_x * random_x + random_y * random_y <= 1) {
                hits++;
            }
        }
    }

    public long getHits() {
        return hits;
    }
    
    public long getRuns() {
    	return runs;
    }
    
    public double getPortionHits() {
    	return (double)hits/runs;
    }
}
