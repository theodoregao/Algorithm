package problems.ch1.section1;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 1.1.35 Dice simulation. The following code computes the exact probability
 * distribu- tion for the sum of two dice: 
 * 
        int SIDES = 6;
        double[] dist = new double[2 * SIDES + 1];
        for (int i = 1; i <= SIDES; i++)
            for (int j = 1; j <= SIDES; j++)
                dist[i + j] += 1.0;
        for (int k = 2; k <= 2 * SIDES; k++)
            dist[k] /= 36.0;
            
   The value dist[i] is the probability that the dice sum to k . Run
 * experiments to vali- date this calculation simulating N dice throws, keeping
 * track of the frequencies of oc- currence of each value when you compute the
 * sum of two random integers between 1 and 6. How large does N have to be
 * before your empirical results match the exact results to three decimal
 * places?
 * 
 * @author shun
 *
 */
public class _35_DiceSimulation {
    public static void main(String[] args) {

        int SIDES = 6;
        double[] dist = new double[2 * SIDES + 1];
        for (int i = 1; i <= SIDES; i++)
            for (int j = 1; j <= SIDES; j++)
                dist[i + j] += 1.0;
        for (int k = 2; k <= 2 * SIDES; k++)
            dist[k] /= 36.0;
        
        for (int i = 0; i <= 2 * SIDES; i++) System.out.println(dist[i]);
        
        int[] counts = new int[2 * SIDES + 1];
        final int COUNT = 10000000;
        for (int i = 0; i < COUNT; i++) {
            counts[StdRandom.uniform(1, SIDES + 1) + StdRandom.uniform(1, SIDES + 1)]++;
        }
        
        double maxDelta = 0.0;
        for (int i = 0; i < counts.length; i++) {
            double posibility = 1.0 * counts[i] / COUNT;
            double delta = Math.abs(posibility - dist[i]);
            if (delta > maxDelta) maxDelta = delta;
            System.out.println(counts[i] + "\t" + posibility + "\t" + delta);
        }
        System.out.println(maxDelta);
    }
}
