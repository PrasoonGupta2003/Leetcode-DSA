class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
         // Maps to store rows and columns
        Map<Integer, TreeSet<Integer>> rowMap = new HashMap<>();
        Map<Integer, TreeSet<Integer>> colMap = new HashMap<>();

        for (int[] building : buildings) {
            int x = building[0];
            int y = building[1];
            rowMap.computeIfAbsent(x, k -> new TreeSet<>()).add(y);
            colMap.computeIfAbsent(y, k -> new TreeSet<>()).add(x);
        }

        int coveredCount = 0;

        for (int[] building : buildings) {
            int x = building[0];
            int y = building[1];

            // Check in row and column using TreeSet's lower() and higher()
            boolean hasLeft = rowMap.get(x).lower(y) != null;
            boolean hasRight = rowMap.get(x).higher(y) != null;
            boolean hasAbove = colMap.get(y).lower(x) != null;
            boolean hasBelow = colMap.get(y).higher(x) != null;

            if (hasLeft && hasRight && hasAbove && hasBelow) {
                coveredCount++;
            }
        }

        return coveredCount;
    }
}