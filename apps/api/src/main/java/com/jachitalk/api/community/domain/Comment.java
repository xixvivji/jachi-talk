package com.jachitalk.api.community.domain;

import com.jachitalk.api.common.domain.BaseTimeEntity;
import com.jachitalk.api.user.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Enumerated(EnumType.STRING)
    @Column(name = "post_type", nullable = false, length = 30)
    private PostType postType;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "content", nullable = false, columnDefinition = "text")
    private String content;

    protected Comment() {
    }

    public Comment(User author, PostType postType, Long postId, String content) {
        this.author = author;
        this.postType = postType;
        this.postId = postId;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public PostType getPostType() {
        return postType;
    }

    public Long getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }
}

