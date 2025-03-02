package com.samip.chatmaze.chat.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chat_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChatUser {

    @Id
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}
