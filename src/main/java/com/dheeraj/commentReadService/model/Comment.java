package com.dheeraj.commentReadService.model;

import java.util.Date;

import lombok.Data;

@Data
public class Comment {

    private String id;

    private String text;

    private String parentId;

    private String commentParentId;

    private String imageURL;

    private boolean isImage = false;

    private boolean isDeleted = false;

    private String createdBy;

    private Date createdOn;

    private boolean isEdited = false;

    private Date lastModified;
    
}
