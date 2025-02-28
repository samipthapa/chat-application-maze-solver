package com.samip.chatmaze.maze.controller;

import com.samip.chatmaze.maze.dto.MazeRequest;
import com.samip.chatmaze.maze.service.MazeSolverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/maze")
public class MazeSolverController {

    private final MazeSolverService mazeSolverService;

    @Autowired
    public MazeSolverController(MazeSolverService mazeSolverService) {
        this.mazeSolverService = mazeSolverService;
    }

    @PostMapping("/solve")
    public ResponseEntity<String> solveMaze(@RequestBody MazeRequest request) {
        int steps = mazeSolverService.findMinimumSteps(request.getMaze(), request.getStart(), request.getEnd());

        return steps == -1
                ? ResponseEntity.badRequest().body("No valid path found")
                : ResponseEntity.ok("Minimum Steps: " + steps);
    }
}
