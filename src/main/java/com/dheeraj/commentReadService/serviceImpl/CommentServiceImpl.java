package com.dheeraj.commentReadService.serviceImpl;

import com.dheeraj.commentReadService.model.Comment;
import com.dheeraj.commentReadService.model.PaginatedCommentRequest;
import com.dheeraj.commentReadService.model.PaginatedCommentResponse;
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
                                    .uri("/comment/{id}/{parentId}", id, parentId)
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

    @Override
    public Flux<Comment> findByParentId(String parentId) {
        WebClient client = getWebClientBuiler().build();
        Flux<Comment> response = client.get()
                                    .uri("/comment/parentId/{parentId}", parentId)
                                    .retrieve()
                                    .bodyToFlux(Comment.class);
        return response;
    }

    @Override
    public Flux<PaginatedCommentResponse> findByParentId(String parentId, Integer size, PaginatedCommentRequest pagingState) {
        WebClient client = getWebClientBuiler().build();
        Flux<PaginatedCommentResponse> response = client.post()
                                    .uri("/comment/parentId/{parentId}/pageable/{size}/pageState", parentId, size)
                                    .bodyValue(pagingState)
                                    .retrieve()
                                    .bodyToFlux(PaginatedCommentResponse.class);
        return response;
    }

    @Override
    public Flux<PaginatedCommentResponse> findByParentId(String parentId, Integer size) {
        WebClient client = getWebClientBuiler().build();
        Flux<PaginatedCommentResponse> response = client.get()
                                    .uri("/comment/parentId/{parentId}/pageable/{size}", parentId, size)
                                    .retrieve()
                                    .bodyToFlux(PaginatedCommentResponse.class);
        return response;
    }
    
}
