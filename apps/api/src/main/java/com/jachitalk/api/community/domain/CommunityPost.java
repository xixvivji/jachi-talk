package com.jachitalk.api.community.domain;

import com.jachitalk.api.common.domain.BaseTimeEntity;
import com.jachitalk.api.region.domain.Region;
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
@Table(name = "community_posts")
public class CommunityPost extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false, length = 30)
    private CommunityPostCategory category;

    @Column(name = "title", nullable = false, length = 120)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "text")
    private String content;

    @Column(name = "hidden", nullable = false)
    private boolean hidden = false;

    protected CommunityPost() {
    }

    public CommunityPost(User author, Region region, CommunityPostCategory category, String title, String content) {
        this.author = author;
        this.region = region;
        this.category = category;
        this.title = title;
        this.content = content;
    }

    public void update(Region region, CommunityPostCategory category, String title, String content) {
        this.region = region;
        this.category = category;
        this.title = title;
        this.content = content;
    }

    public void hide() {
        this.hidden = true;
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public Region getRegion() {
        return region;
    }

    public CommunityPostCategory getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean isHidden() {
        return hidden;
    }
}
