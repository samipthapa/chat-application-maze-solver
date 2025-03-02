package com.samip.chatmaze.chat.controller;

import com.samip.chatmaze.chat.entity.ChatMessage;
import com.samip.chatmaze.chat.entity.ChatUser;
import com.samip.chatmaze.chat.service.ChatUserService;
import com.samip.chatmaze.chat.service.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@Slf4j
public class ChatWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;
    private final ChatUserService userService;

    @Autowired
    public ChatWebSocketController(SimpMessagingTemplate messagingTemplate,
                                   MessageService messageService,
                                   ChatUserService userService) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
        this.userService = userService;
    }

//   One to one chat messages are sent to the {user}/queue/messages destination
    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage message) {
        message.setTimestamp(LocalDateTime.now());
        ChatMessage dbMsg = messageService.saveMessage(message);

        messagingTemplate.convertAndSendToUser(
                message.getReceiver(),
                "/queue/messages",
                ChatMessage.builder()
                    .id(dbMsg.getId())
                    .sender(dbMsg.getSender())
                    .receiver(dbMsg.getReceiver())
                    .content(dbMsg.getContent())
                    .timestamp(dbMsg.getTimestamp())
                    .build()
        );

        messagingTemplate.convertAndSendToUser(
                message.getSender(),
                "/queue/messages",
                ChatMessage.builder()
                    .id(dbMsg.getId())
                    .sender(dbMsg.getSender())
                    .receiver(dbMsg.getReceiver())
                    .content(dbMsg.getContent())
                    .timestamp(dbMsg.getTimestamp())
                    .build()
        );
    }


//    Thymeleaf template engine is used to render the chat interface
    @RequestMapping("/chat")
    public String chatWithUser(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        ChatUser user = userService.getUser(username);
        List<ChatUser> users = userService.getAllUsersExcept(username);

        model.addAttribute("user", user);
        model.addAttribute("users", users);
        return "index";
    }
}
