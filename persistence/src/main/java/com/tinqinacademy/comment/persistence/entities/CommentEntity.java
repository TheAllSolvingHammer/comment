package com.tinqinacademy.comment.persistence.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor()
@AllArgsConstructor()
@Builder
@Entity
@Table(name="comments")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="comment_id",updatable = false,nullable = false)
    private UUID id;
    @Column(name="content",nullable = false)
    private String content;
    @JsonBackReference
    @ManyToOne
    private UserEntity user;
    @Column(name="publish_date",nullable = false)
    private LocalDate publishDate;
    @Column(name="last_edit_date",nullable = false)
    private LocalDate lastEditDate;
    @Column(name="room_id",nullable = false)
    private UUID roomID;
    @Column(name="user_last_edit",nullable = false)
    private UUID userLastEdit;
}
