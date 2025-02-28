package com.samip.chatmaze.maze.dto;

public class MazeRequest {

    private int[][] maze;
    private int[] start;
    private int[] end;

    public int[][] getMaze() {
        return maze;
    }

    public int[] getStart() {
        return start;
    }

    public int[] getEnd() {
        return end;
    }
}
