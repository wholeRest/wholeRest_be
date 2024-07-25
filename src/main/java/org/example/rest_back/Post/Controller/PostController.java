package org.example.rest_back.Post.Controller;

import org.example.rest_back.Post.Domain.Post;
import org.example.rest_back.Post.Dto.PostDto;
import org.example.rest_back.Post.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Create, 게시물 작성
    // 유저 아이디 확인하는 로직 작성해야 함
    @PostMapping
    public ResponseEntity<PostDto> registerPost(@RequestBody PostDto postDto) {
        Post post = postService.registerPost(postService.convertToEntity(postDto));

        return ResponseEntity.ok(postService.convertToDto(post));
    }

    // Read All, 모든 게시물 조회
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        List<PostDto> postDtos = new ArrayList<>();

        for (Post post : posts) {
            postDtos.add(postService.convertToDto(post));
        }

        return ResponseEntity.ok(postDtos);
    }

    // Read Specific Category, 특정 카테고리의 게시물들만 조회, 카테고리 값으로 조회
    @GetMapping("/category/{category}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable(name = "category") String category){
        List<Post> posts = postService.getPostsByCategory(category);
        List<PostDto> postDtos = new ArrayList<>();

        for (Post post : posts) {
            postDtos.add((postService.convertToDto(post)));
        }

        return ResponseEntity.ok(postDtos);
    }

    // Read Only One, 단 하나의 게시물만 조회, id값으로 조회
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id){
        Optional<Post> postOptional = postService.getPostById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();

            return ResponseEntity.ok(postService.convertToDto(post));
            }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update, 게시물 수정
    @PatchMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable(name = "id") Long id, @RequestBody PostDto postDto) {
        try {
            Post post = postService.updatePost(id, postDto);
            return ResponseEntity.ok(postService.convertToDto(post));
        }
        catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete, 게시물 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable(name = "id") Long id){
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
