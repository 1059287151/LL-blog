package com.ll.blog.model.dto;

import lombok.Data;

@Data
public class FriendLinkDTO {
    private Long id;
    private String name;
    private String url;
    private String avatar;
    private String city;
    private String lat;
    private String lng;
    private String description;
}
