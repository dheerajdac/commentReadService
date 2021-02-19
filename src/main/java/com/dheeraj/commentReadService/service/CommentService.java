package com.dheeraj.commentReadService.service;

import com.dheeraj.commentReadService.model.Comment;
import com.dheeraj.commentReadService.model.PaginatedCommentRequest;
import com.dheeraj.commentReadService.model.PaginatedCommentResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentService {

    public Flux<Comment> findAllComment();
    
    public Mono<Comment> findCommentByIdAndParentId(String id, String parentId);

	public Flux<Comment> findByParentId(String parentId);

	public Flux<PaginatedCommentResponse> findByParentId(String parentId, Integer page, PaginatedCommentRequest pagingState);

	public Flux<PaginatedCommentResponse> findByParentId(String parentId, Integer size);
}
