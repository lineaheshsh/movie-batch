package com.zzangho.moviebatch.movie.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class MovieRequest {
    private String key;
    private String curPage;
    private String itemPerPage;
}
