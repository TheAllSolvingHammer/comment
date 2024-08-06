package com.tinqinacademy.comment.persistence.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor()
@AllArgsConstructor()
@Builder
@Entity
@Table(name="users")
@EqualsAndHashCode()
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="user_id",updatable = false,nullable = false)
    private UUID id;
    @Column(name="first_name",nullable = false)
    private String firstName;
    @Column(name="last_name",nullable = false)
    private String lastName;
    @Column(name="birthday",nullable = false)
    private LocalDate birthDate;
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<CommentEntity> commentEntityList;
}
