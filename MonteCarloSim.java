/**
 * Created by Pri on 10/11/2014.
 */

public class MonteCarloSim {

    long totalRuns;
    long hits;
    long timesRun;
    long r;

    public MonteCarloSim(int runs)
    {
        totalRuns = runs;
        hits = 0;
        timesRun = 0;
        r = 1;
    }

    public void runAll()
    {
        while(timesRun < totalRuns)
        {
            double random_x = Math.random()*(r * 2);
            double random_y = Math.random()*(r * 2);

            if ((Math.pow((random_x - r), 2) + Math.pow((random_y - r), 2)) <= Math.pow(r, 2))
            {
                hits++;
            }
            timesRun++;
        }
    }

    public long getHits()
    {
        return hits;
    }
}
