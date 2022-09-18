package com.raiseup.springblog.service;

import com.raiseup.springblog.dto.PostDto;
import com.raiseup.springblog.exception.PostNotFoundException;
import com.raiseup.springblog.model.AppUser;
import com.raiseup.springblog.model.Post;
import com.raiseup.springblog.repository.PostRepository;
import com.raiseup.springblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    public void createPost(PostDto postDto){
        String username=authService.getCurrentUser();
        log.info("createPost - > username {}",username);
        AppUser appUser = userRepository.findByUsername(username).get();
        Post post = Post.builder()
                .content(postDto.getContent())
                .title(postDto.getTitle())
                .username(appUser.getUsername())
                .appUser(appUser)
                .build();
        log.info("Requested User: {}",appUser);
        Post savedPost= postRepository.save(post);
        log.info("Post saved! {}",post);
    }

    public List<PostDto> findAll() {
        List<Post> postList = postRepository.findAll();
        return postList.stream()
                .map(post -> postMapToPostDto(post))
                .toList();
    }

    private PostDto postMapToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .content(post.getContent())
                .title(post.getTitle())
                .username(post.getUsername())
                .build();
    }

    public PostDto getPost(Long id) throws PostNotFoundException {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post does not exist"));
        return postMapToPostDto(post);
    }
}
