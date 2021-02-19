package com.dheeraj.commentReadService.model;

import java.nio.ByteBuffer;
import java.util.List;

import lombok.Data;

@Data
public class PaginatedCommentResponse {

    List<Comment> comments;

    ByteBuffer pagingState;
    
}