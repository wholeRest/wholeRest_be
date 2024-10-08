package org.example.rest_back.Post.Repository;

import org.example.rest_back.Post.Domain.Likes;
import org.example.rest_back.Post.Domain.Post;
import org.example.rest_back.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByUserAndPost(User user, Post post);
}
