package com.ll.blog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimeRecordDTO {
    /**
     * 番剧 ID
     */
    private long animeId;
    /**
     * 0-想看,1-在看,2-已看完,3-弃了
     */
    private long watchStatus;
    private Long episodesWatched;
    private Double rating;
    private String comment;
}
