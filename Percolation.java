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
        p.isFull(2, 1);
        System.out.println("a");
    }

    int[][] grid;
    WeightedQuickUnionUF unionFindElements;

    // Creates n-by-n grid with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("N must be greater than 0");
        }

        // This grid will keep track of open/closed sites
        // grid[i][j] = 0 will represent a blocked site | 1 = open site
        this.grid = new int[n][n];

        // Store the total number of nodes + 2 (Virtual top/bottom site) in the union find data structure
        this.unionFindElements = new WeightedQuickUnionUF(n * n);
    }

    // Opens the site (row,col) if it is not opened already
    // Connect site to all its adjacent open sites
    // 0-4 calls to union()
    public void open(int row, int col) {
        int ABOVE_ROW = row - 1;
        int BELOW_ROW = row + 1;
        int COLUMN_TO_THE_LEFT = col - 1;
        int COLUMN_TO_THE_RIGHT = col + 1;

        // Open the site
        this.grid[row - 1][col - 1] = 1;

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
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        this.validateRowAndCol(row, col);
        return this.grid[row - 1][col - 1] != 0;
    }

    // A full site is an open site that can be connected to an open site in the top row via a chain of neighboring
    public boolean isFull(int row, int col) {
        // If the site is open on the top row its a full site.
        if (row - 1 == 0 && this.grid[row - 1][col - 1] == 1) {
            return true;
        }

        // Otherwise we want to check if the root of the 1D Arr index is on the top row
        int pIndex = getOneDimensionalArrayIndex(row, col);
        int rootIndex = this.unionFindElements.find(pIndex);

        // Convert 1D rootIndex to 2D grid index to see if its open and on the top row
        int rowIndex = rootIndex / 3;
        int colIndex = rootIndex % 3;


        return false;
    }

    // returns number of open sites
    public int numberOfOpenSites() {
        return 1;
    }

    // We say the system percolates if there is a full site in the bottom row (connection to the top row)
    public boolean percolates() {
        return true;
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
