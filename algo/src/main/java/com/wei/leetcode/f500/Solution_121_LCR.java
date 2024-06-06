package com.wei.leetcode.f500;

class Solution_121_LCR {
    public boolean findTargetIn2DPlants(int[][] plants, int target) {
        boolean res = false;
        if (plants == null || plants.length == 0 || plants[0].length == 0) {
            return res;
        }
        int row = 0;
        int col = plants[0].length - 1;
        while (row < plants.length && col >= 0) {
            if (plants[row][col] == target) {
                return true;
            } else if (plants[row][col] > target) {
                col--;
            } else {
                row++;
            }

        }
        return res;
    }
}