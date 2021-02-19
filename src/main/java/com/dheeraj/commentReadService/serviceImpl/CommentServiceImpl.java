package com.dheeraj.commentReadService.serviceImpl;

import com.dheeraj.commentReadService.model.Comment;
import com.dheeraj.commentReadService.service.CommentService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CommentServiceImpl implements CommentService {

    @Value("${repo.uri}")
    String uri;

    @Override
    public Mono<Comment> findCommentByIdAndParentId(String id, String parentId) {
        WebClient client = getWebClientBuiler().build();
        Mono<Comment> respose = client.get()
                                    .uri("/{id}/{parentId}", id, parentId)
                                    .retrieve()
                                    .bodyToMono(Comment.class);
        return respose;
    }

    @Override
    public Flux<Comment> findAllComment(){
        WebClient client = getWebClientBuiler().build();
        return client.get()
                    .uri("/comment/all")
                    .retrieve()
                    .bodyToFlux(Comment.class);
    }

    public WebClient.Builder getWebClientBuiler() {
        return WebClient.builder()
                        .baseUrl(uri);
    }
    
}
