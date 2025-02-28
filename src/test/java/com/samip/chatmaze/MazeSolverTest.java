package com.samip.chatmaze;

import com.samip.chatmaze.maze.service.MazeSolverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MazeSolverTest {

    private MazeSolverService mazeSolverService;

    @BeforeEach
    void setUp() {
        mazeSolverService = new MazeSolverService();
    }

    @Test
    void testValidPathExists() {
        int[][] maze = {
                {0, 0, 1, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0}
        };
        int[] start = {0, 0};
        int[] end = {4, 4};

        int steps = mazeSolverService.findMinimumSteps(maze, start, end);
        assertThat(steps).isEqualTo(8);
    }

    @Test
    void testNoValidPath() {
        int[][] maze = {
                {0, 1, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        int[] start = {0, 0};
        int[] end = {2, 2};

        int steps = mazeSolverService.findMinimumSteps(maze, start, end);
        assertThat(steps).isEqualTo(-1);
    }

    @Test
    void testStartEqualsEnd() {
        int[][] maze = {
                {0, 1, 0},
                {1, 0, 0},
                {0, 0, 0}
        };
        int[] start = {2, 2};
        int[] end = {2, 2};

        int steps = mazeSolverService.findMinimumSteps(maze, start, end);
        assertThat(steps).isEqualTo(0);
    }
}
