package com.dheeraj.commentReadService.service;

import com.dheeraj.commentReadService.model.Comment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentService {

    public Flux<Comment> findAllComment();
    
    public Mono<Comment> findCommentByIdAndParentId(String id, String parentId);
}
