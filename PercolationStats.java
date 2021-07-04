/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import java.lang.IllegalArgumentException;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    // perform independent trials on an n-by-n grid

    int n; // nxn grid
    int trials; // number of trials to perform
    double[] thresholds;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("N must be greater than 0");
        }

        this.n = n;
        this.trials = trials;

        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);

            System.out.println("Trial #: " + i);

            boolean systemPercolates = perc.percolates();

            while (!systemPercolates) {
                int randomRow = StdRandom.uniform(n + 1);
                int randomCol = StdRandom.uniform(n + 1);

                if (randomRow == 0) {
                    randomRow += 1;
                }
                if (randomCol == 0) {
                    randomCol += 1;
                }
                perc.open(randomRow, randomCol);
                System.out.println("Total opened sites: " + perc.totalOpenSites);
            }

            double percolationThreshold = perc.totalOpenSites / (n * n);
            this.thresholds[i] = percolationThreshold;

        }

    }


    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(trials));
    }

    // test client
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, t);

        System.out.println("mean = " + stats.mean());
        System.out.println("stddev = " + stats.stddev());
        System.out.println("95% confidence interval = " + "[" + stats.confidenceLo() + "," + stats
                .confidenceHi() + "]");
    }
}
