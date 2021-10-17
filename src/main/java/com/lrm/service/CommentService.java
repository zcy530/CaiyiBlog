package com.lrm.service;

import com.lrm.po.Comment;

import java.util.List;

/**
 * Created by ZhangCaiyi on 2021/5/30.
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
