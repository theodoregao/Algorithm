package problems.dp;

import java.util.Arrays;

class Job implements Comparable<Job>{
    int start;
    int end;
    int profit;
    Job(int start,int end,int profit){
        this.start= start;
        this.end = end;
        this.profit= profit;
    }
    boolean canPanarrel(Job other) {
        return start >= other.end || end <= other.start;
    }
    @Override
    public int compareTo(Job other) {
        return end - other.end;
    }
}

/**
 * http://www.cs.princeton.edu/courses/archive/spr05/cos423/lectures/06dynamic-programming.pdf
 * Given set of jobs with start and end interval and profit, how to maximize profit such that 
 * jobs in subset do not overlap.
 */
public class WeightedJobSchedulingMaximumProfit {

    /**
     * Sort the jobs by finish time.
     * For every job find the first job which does not overlap with this job
     * and see if this job profit plus profit till last non overlapping job is greater
     * than profit till last job.
     * @param jobs
     * @return
     */
    public int maxProfits(Job[] jobs){
        Arrays.sort(jobs);
        int[] maxProfits = new int[jobs.length];
        int temp;
        int maxProfit = jobs[0].profit;
        for (int i = 0; i < jobs.length; i++) maxProfits[i] = jobs[i].profit;
        for (int i = 1; i < maxProfits.length; i++) {
            temp = maxProfits[i];
            for (int j = 0; j < i; j++) {
                if (jobs[i].canPanarrel(jobs[j])) maxProfits[i] = Math.max(maxProfits[i], temp + jobs[j].profit);
                if (maxProfits[i] > maxProfit) maxProfit = maxProfits[i];
            }
        }
        return maxProfit;
    }
    
    public static void main(String args[]){
        Job jobs[] = new Job[6];
        jobs[0] = new Job(1,3,5);
        jobs[1] = new Job(2,5,6);
        jobs[2] = new Job(4,6,5);
        jobs[3] = new Job(6,7,4);
        jobs[4] = new Job(5,8,11);
        jobs[5] = new Job(7,9,2);
        WeightedJobSchedulingMaximumProfit mp = new WeightedJobSchedulingMaximumProfit();
        System.out.println(mp.maxProfits(jobs));
    }
}
