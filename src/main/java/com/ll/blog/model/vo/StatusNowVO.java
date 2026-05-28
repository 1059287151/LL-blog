package com.ll.blog.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusNowVO {
    private Music music;
    private Reading reading;
    private String location;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Music {
        private String title;
        private String artist;
        private String cover;
        private String url;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Reading {
        private String title;
        private String cover;
    }

}
