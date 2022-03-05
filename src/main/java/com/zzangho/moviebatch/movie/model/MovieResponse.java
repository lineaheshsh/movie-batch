package com.zzangho.moviebatch.movie.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class MovieResponse {
    private String movieCd;
    private String movieNm;
    private String movieNmEn;
    private String prdtYear;
    private String openDt;
    private String typeNm;
    private String prdtStatNm;
    private String nationAlt;
    private String genreAlt;
    private String repNationNm;
    private String repGenreNm;
    private String directors;
    private String peopleNm;
    private String companys;
    private String companyCd;
    private String companyNm;
}
