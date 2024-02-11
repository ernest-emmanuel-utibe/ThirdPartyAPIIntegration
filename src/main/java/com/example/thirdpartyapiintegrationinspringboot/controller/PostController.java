package com.example.thirdpartyapiintegrationinspringboot.controller;

import com.example.thirdpartyapiintegrationinspringboot.postservice.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/getPosts")
    List<Map<String, Object>> getAllPosts() {
        return postService.getPosts();
    }

    @GetMapping("/getPostsById/{id}")
    Map<String, Object> getPostsByID(@PathVariable int id) {
        return postService.getPostById(id);
    }

    @PostMapping("/insertPosts")
    Map<String, Object> insertAPost(@RequestBody Map<String, Object> payload) {
        return postService.insertPost(payload);
    }

    @PutMapping("/updatePosts/{id}")
    Map<String, Object> updateAPost(Map<String, Object> payload,@PathVariable int id) {
        return postService.updatePost(payload, id);
    }

    @DeleteMapping("/deletePost/{id}")
    Map<String, Object> deletePost(@PathVariable int id) {
        return postService.deletePost(id);
    }


}
