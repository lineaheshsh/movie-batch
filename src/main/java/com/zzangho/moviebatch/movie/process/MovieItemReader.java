package com.zzangho.moviebatch.movie.process;

import com.zzangho.moviebatch.movie.model.MovieResponse;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class MovieItemReader implements ItemReader<List<MovieResponse>> {

    @Value("${open.api.key}")
    private String apiKey;

    @Value("${open.api.uri}")
    private String apiUri;

    private int nextMovieIndex;
    private RestTemplate restTemplate;
    private List<MovieResponse> movieList;

    MovieItemReader(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        nextMovieIndex = 0;
    }


    @Override
    public List<MovieResponse> read() throws Exception {
        if (movieDataIsNotInitialized()) {
            movieList = fetchMovieDataFromAPI();
        }

        List<MovieResponse> nextMovie = null;

        if (nextMovieIndex < movieList.size()) {
            nextMovie = movieList.get(nextMovieIndex);
            nextMovieIndex++;
        }
        else {
            nextMovieIndex = 0;
            movieList = null;
        }

        return nextMovie;
    }

    private boolean movieDataIsNotInitialized() {
        return this.movieList == null;
    }

    private List<MovieResponse> fetchMovieDataFromAPI() {
        ResponseEntity<MovieResponse[]> response = restTemplate.getForEntity(apiUri,
                MovieResponse[].class
        );
        MovieResponse[] movieData = response.getBody();
        return Arrays.asList(movieData);
    }
}
