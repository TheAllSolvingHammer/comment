package com.tinqinacademy.comment.persistence.entities;

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

}
