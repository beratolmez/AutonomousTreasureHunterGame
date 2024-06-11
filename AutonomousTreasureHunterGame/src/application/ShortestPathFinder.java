package application;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ShortestPathFinder {

    private static class Node {
        int x;
        int y;
        List<int[]> path;

        Node(int x, int y, List<int[]> path) {
            this.x = x;
            this.y = y;
            this.path = new ArrayList<>(path);
            this.path.add(new int[]{x, y});
        }
    }
    public static List<int[]> findShortestPath(int[][] grid, int startX, int startY, int targetX, int targetY) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(startX, startY, new ArrayList<>()));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.x;
            int y = node.y;
            List<int[]> path = node.path;

            if (x == targetX && y == targetY) {
                return path;
            }

            visited[y][x] = true;

            
            int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (newX >= 0 && newX < n && newY >= 0 && newY < m && grid[newY][newX] != 1 && !visited[newY][newX]) {
                    queue.offer(new Node(newX, newY, path));
                    visited[newY][newX] = true;
                }
            }
        }

  
        return new ArrayList<>();
    }

    
    }