package com.tinqinacademy.comment.persistence.repositories;

import com.tinqinacademy.comment.persistence.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {

    String queryForRoomID= """
            select cs.comment_id,cs.content,cs.last_edit_date,cs.publish_date,cs.room_id,cs.user_user_id,cs.user_last_edit
            from comments cs
            where room_id = :roomID
            """;
    @Query(value = queryForRoomID, nativeQuery = true)
    List<CommentEntity> findAllByRoomID (UUID roomID);
}
