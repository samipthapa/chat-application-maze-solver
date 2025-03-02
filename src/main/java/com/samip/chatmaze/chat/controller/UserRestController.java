package com.samip.chatmaze.chat.controller;

import com.samip.chatmaze.chat.entity.ChatUser;
import com.samip.chatmaze.chat.exception.NotFoundException;
import com.samip.chatmaze.chat.service.ChatUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final ChatUserService userService;

    public UserRestController(ChatUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<ChatUser> getUser(@PathVariable String username) {
        ChatUser user = userService.getUser(username);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<ChatUser> createUser(@RequestBody ChatUser user) {
        ChatUser newUser = userService.saveUser(user);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping
    public ResponseEntity<List<ChatUser>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{username}")
    public ResponseEntity<ChatUser> updateUser(@PathVariable String username,
                                               @RequestBody ChatUser user) {
        ChatUser updatedUser = userService.updateUser(username, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok().build();
    }
}