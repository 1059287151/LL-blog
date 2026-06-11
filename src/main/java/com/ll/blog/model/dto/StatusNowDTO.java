package com.ll.blog.model.dto;

import lombok.Data;

@Data
public class StatusNowDTO {
    private Music music;
    private Reading reading;
    private String location;

    @Data
    public static class Reading {
        private String cover;
        private String title;
    }

    @Data
    public static class Music {
        private String artist;
        private String cover;
        private String title;
        private String url;
    }
}




