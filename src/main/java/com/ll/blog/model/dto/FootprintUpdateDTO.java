package com.ll.blog.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FootprintUpdateDTO {
    private Long id;
    protected String city;
    private String country;
    private Double lat;
    private Double lng;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String note;
    private String photo;
}
