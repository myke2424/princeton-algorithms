/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import java.lang.IllegalArgumentException;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// Constraints:
// Row and column indices are integers between 1 and n
// E.g. (1,1) is the top left site
// Length of grid = N^2 - 1

// Time complexity requirements:
// The constructor must take time proportional to n^2
// ALl instance methods must take constant time plus a constant number of calls to union() and find().

public class Percolation {
    // Test client
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(1, 1);
        p.open(2, 1);
        p.open(2, 2);
        p.open(1, 2);

        boolean siteIsFull = p.isFull(2, 1);
        System.out.println("Is Full: " + siteIsFull + " - Expected true");

        System.out.println("System percolates: " + p.percolates() + " - Expected false");

        p.open(3, 1);
        System.out.println("System percolates: " + p.percolates() + " - Expected true");

    }

    int[][] grid;
    WeightedQuickUnionUF unionFindElements;
    int virtualTopSiteIndex;
    int virtualBottomSiteIndex;
    int totalOpenSites;

    // Creates n-by-n grid with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("N must be greater than 0");
        }

        // This grid will keep track of open/closed sites
        // grid[i][j] = 0 = blocked site | 1 = open site
        this.grid = new int[n][n];

        // Store the total number of nodes + 2 (Virtual top/bottom site) in the union find data structure
        // Top virtual site index will be n * n
        // Bottom virtual site index will n * n + 1
        this.unionFindElements = new WeightedQuickUnionUF((n * n) + 2);
        this.virtualTopSiteIndex = n * n;
        this.virtualBottomSiteIndex = (n * n) + 1;
        this.totalOpenSites = 0;
    }

    // Opens the site (row,col) if it is not opened already
    // Connect site to all its adjacent open sites
    public void open(int row, int col) {
        this.validateRowAndCol(row, col);

        int ABOVE_ROW = row - 1;
        int BELOW_ROW = row + 1;
        int COLUMN_TO_THE_LEFT = col - 1;
        int COLUMN_TO_THE_RIGHT = col + 1;

        // Open the site
        this.grid[row - 1][col - 1] = 1;
        this.totalOpenSites++;

        // Map the adjacent grid indices to a 1D Array index to map against our UnionFind DS
        int currentSiteArrayIndex = getOneDimensionalArrayIndex(row, col);
        int aboveSiteArrayIndex = getOneDimensionalArrayIndex(ABOVE_ROW, col);
        int belowSiteArrayIndex = getOneDimensionalArrayIndex(BELOW_ROW, col);
        int leftSiteArrayIndex = getOneDimensionalArrayIndex(row, COLUMN_TO_THE_LEFT);
        int rightSiteArrayIndex = getOneDimensionalArrayIndex(row, COLUMN_TO_THE_RIGHT);

        // Is the site above open
        if (isInsideGridBounds(ABOVE_ROW)) {
            if (isOpen(ABOVE_ROW, col)) {
                // Connect the adjacent open nodes with union
                this.unionFindElements.union(currentSiteArrayIndex, aboveSiteArrayIndex);
            }
        }

        // Is the site below open
        if (isInsideGridBounds(BELOW_ROW)) {
            if (isOpen(BELOW_ROW, col)) {
                this.unionFindElements.union(currentSiteArrayIndex, belowSiteArrayIndex);
            }
        }

        // Is the site to the left open
        if (isInsideGridBounds(COLUMN_TO_THE_LEFT)) {
            if (isOpen(row, COLUMN_TO_THE_LEFT)) {
                this.unionFindElements.union(currentSiteArrayIndex, leftSiteArrayIndex);
            }
        }

        // Is the site to the right open
        if (isInsideGridBounds(COLUMN_TO_THE_RIGHT)) {
            if (isOpen(row, COLUMN_TO_THE_RIGHT)) {
                this.unionFindElements.union(currentSiteArrayIndex, rightSiteArrayIndex);
            }
        }

        // if the site is on the first row, it's a child of our a top virtual site (Full site)
        if (row == 1) {
            this.unionFindElements.union(currentSiteArrayIndex, virtualTopSiteIndex);
        }

        // if the site is on the last row, it's a child of our bottom virtual site
        if (row == this.grid.length) {
            this.unionFindElements.union(currentSiteArrayIndex, virtualBottomSiteIndex);
        }
    }

    // If the site isn't 0, it's open
    public boolean isOpen(int row, int col) {
        this.validateRowAndCol(row, col);
        return this.grid[row - 1][col - 1] != 0;
    }

    // A full site is an open site that can be connected to an open site in the top row via a chain of neighboring
    public boolean isFull(int row, int col) {
        this.validateRowAndCol(row, col);

        int pIndex = getOneDimensionalArrayIndex(row, col);
        int rootIndex = this.unionFindElements.find(pIndex);

        // If the root index is the virtual top site index, it's full
        if (rootIndex == this.unionFindElements.find(virtualTopSiteIndex)) {
            return true;
        }


        return false;
    }

    // returns number of open sites
    public int numberOfOpenSites() {
        return this.totalOpenSites;
    }

    // We say the system percolates if there is a full site in the bottom row connected to a site in the top row
    // If the bottom virtual site and top virtual site are connected, then the system percolates
    public boolean percolates() {
        return this.unionFindElements.find(virtualTopSiteIndex) == this.unionFindElements
                .find(virtualBottomSiteIndex);
    }


    private int getOneDimensionalArrayIndex(int row, int col) {
        return ((row - 1) * this.grid.length) + col - 1;
    }


    private void validateRowAndCol(int row, int col) {
        if (row > this.grid.length || row <= 0 || col > this.grid.length || col <= 0) {
            throw new IllegalArgumentException("Outside of grid bounds");
        }
    }

    private boolean isInsideGridBounds(int n) {
        if (n > this.grid.length || n <= 0) {
            return false;
        }
        return true;
    }

}
