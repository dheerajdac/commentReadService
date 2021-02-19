package com.dheeraj.commentReadService.controller;

import com.dheeraj.commentReadService.model.Comment;
import com.dheeraj.commentReadService.model.PaginatedCommentRequest;
import com.dheeraj.commentReadService.model.PaginatedCommentResponse;
import com.dheeraj.commentReadService.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;
    
    @GetMapping("/test/")
    public String getTest(){
        return "Hello World";
    }

    @GetMapping("/all")
    public Flux<Comment> getAllComment(){
        return this.commentService.findAllComment();
    }

    @GetMapping(value="/parentId/{parentId}")
    public Flux<Comment> getCommentFromParentId(@PathVariable String parentId) {
        return this.commentService.findByParentId(parentId);
    }

    @GetMapping("/{id}/{parentId}")
    public Mono<Comment> getCommentFromIdAndParentId(@PathVariable String id, @PathVariable String parentId){
        return this.commentService.findCommentByIdAndParentId(id, parentId);
    }

    @GetMapping(value="/parentId/{parentId}/pageable/{size}/pageState")
    public Flux<PaginatedCommentResponse> getCommentFromParentIdPagianated(@PathVariable String parentId, @PathVariable Integer size, @RequestBody PaginatedCommentRequest pagingState) {
        return this.commentService.findByParentId(parentId, size, pagingState);
    }

    @GetMapping(value="/parentId/{parentId}/pageable/{size}")
    public Flux<PaginatedCommentResponse> getCommentFromParentIdPagianated(@PathVariable String parentId, @PathVariable Integer size){
        return this.commentService.findByParentId(parentId, size);
    }

}
