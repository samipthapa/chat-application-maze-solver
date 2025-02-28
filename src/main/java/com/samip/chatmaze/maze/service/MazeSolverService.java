package com.samip.chatmaze.maze.service;

import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Queue;

@Service
public class MazeSolverService {

    private static final int[][] DIRECTIONS = {
            {-1, 0}, // Up
            {1, 0},  // Down
            {0, -1}, // Left
            {0, 1}   // Right
    };

//    Stores the current position (row, col) and the number of steps taken
    record Node(int[] point, int steps) {}

    /**
     * @param maze 2D array where 0 denotes a walkable cell and 1 denotes a blocked cell
     * @param start starting position
     * @param end target position
     * @return minimum number of steps to reach the target or -1 if no path exists
     */
    public int findMinimumSteps(int[][] maze, int[] start, int[] end) {
        int rows = maze.length, columns = maze[0].length;
        boolean[][] visited = new boolean[rows][columns];   // Keeps track of visited cells
        Queue<Node> queue = new ArrayDeque<>();


        queue.add(new Node(start, 0));
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

//            If the current node is the destination, return the step count
            if (node.point[0] == end[0] && node.point[1] == end[1]) {
                return node.steps;
            }

//            Explore all possible moves
            for (int[] dir : DIRECTIONS) {
                int newRow = node.point[0] + dir[0];
                int newCol = node.point[1] + dir[1];

                if (isValidMove(newRow, newCol, maze, visited)) {
                    visited[newRow][newCol] = true;
                    queue.add(new Node(
                            new int[]{newRow, newCol},
                            node.steps + 1
                    ));
                }
            }
        }

        return -1;  // No valid path found
    }

    private boolean isValidMove(int r, int c, int[][] maze, boolean[][] visited) {
        return (r >= 0 && r < maze.length) &&
                (c >= 0 && c < maze[0].length) &&
                !visited[r][c] &&
                maze[r][c] == 0;
    }
}