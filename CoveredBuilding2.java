import java.util.*;

public class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        // Maps to store rows and columns
        Map<Integer, List<Integer>> rowMap = new HashMap<>();
        Map<Integer, List<Integer>> colMap = new HashMap<>();

        // Populate the maps with building coordinates
        for (int[] building : buildings) {
            int x = building[0];
            int y = building[1];

            rowMap.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
            colMap.computeIfAbsent(y, k -> new ArrayList<>()).add(x);
        }

        // Sort the lists for each row and column
        for (List<Integer> row : rowMap.values()) {
            Collections.sort(row);
        }
        for (List<Integer> col : colMap.values()) {
            Collections.sort(col);
        }

        int coveredCount = 0;

        for (int[] building : buildings) {
            int x = building[0];
            int y = building[1];

            // Check in row and column to find neighbors
            boolean hasLeft = getNeighborInList(rowMap.get(x), y, true) != -1;
            boolean hasRight = getNeighborInList(rowMap.get(x), y, false) != -1;
            boolean hasAbove = getNeighborInList(colMap.get(y), x, true) != -1;
            boolean hasBelow = getNeighborInList(colMap.get(y), x, false) != -1;

            if (hasLeft && hasRight && hasAbove && hasBelow) {
                coveredCount++;
            }
        }

        return coveredCount;
    }

    // Helper method to find the neighbor in a sorted list
    private int getNeighborInList(List<Integer> list, int value, boolean findLower) {
        if (list == null) return -1;
        
        int left = 0, right = list.size() - 1;
        int neighbor = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) < value) {
                left = mid + 1;
            } else if (list.get(mid) > value) {
                right = mid - 1;
            } else {
                // If the value exists, find its neighbor
                if (findLower) {
                    return mid > 0 ? list.get(mid - 1) : -1;
                } else {
                    return mid < list.size() - 1 ? list.get(mid + 1) : -1;
                }
            }
        }
        
        // If we did not find the exact value, return the nearest valid neighbor
        if (findLower) {
            return right >= 0 ? list.get(right) : -1;
        } else {
            return left < list.size() ? list.get(left) : -1;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] buildings = {
            {1, 1}, {1, 2}, {1, 3}, {2, 1}, {2, 3}, {3, 2}
        };
        System.out.println(solution.countCoveredBuildings(3, buildings));  // Output will depend on the logic
    }
}
