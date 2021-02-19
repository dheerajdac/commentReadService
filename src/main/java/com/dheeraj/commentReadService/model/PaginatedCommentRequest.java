package com.dheeraj.commentReadService.model;

import java.nio.ByteBuffer;

import lombok.Data;

@Data
public class PaginatedCommentRequest {
    ByteBuffer pagingState;    
}
