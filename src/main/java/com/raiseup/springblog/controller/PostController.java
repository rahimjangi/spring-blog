package com.raiseup.springblog.controller;

import com.raiseup.springblog.dto.PostDto;
import com.raiseup.springblog.exception.PostNotFoundException;
import com.raiseup.springblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createPost(@RequestBody PostDto postDto){
        postService.createPost(postDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>>getPosts(){
        List<PostDto> postDtos = postService.findAll();
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto>getPosts(@PathVariable("id") Long id) throws PostNotFoundException {
        PostDto postDto = postService.getPost(id);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }
}
