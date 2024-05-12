package com.techelevator.movies.dao;

import com.techelevator.movies.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcMovieDao implements MovieDao {
    private final String MOVIE_SELECT = "SELECT m.movie_id, m.title, m.overview, m.tagline, m.poster_path, m.home_page, m.release_date, m.length_minutes, m.director_id, m.collection_id from movie m ";
    private final JdbcTemplate jdbcTemplate;

    public JdbcMovieDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Movie> getMovies() {
        String sql = MOVIE_SELECT;

        List<Movie> movies = new ArrayList<>();

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            Movie singleMovie = mapRowToMovie(results);
            movies.add(singleMovie);
        }

        return movies;
    }

    @Override
    public Movie getMovieById(int id) {

        String sql = MOVIE_SELECT + "WHERE movie_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);

        if (results.next()) {
            return mapRowToMovie(results);
        } else {
            return null;
        }

    }

    @Override
    public List<Movie> getMoviesByTitle(String title, boolean useWildCard) {

        String sql = MOVIE_SELECT + "WHERE LOWER(m.title) LIKE ?;";

        if (useWildCard) {
            title = "%" + title.toLowerCase() + "%";
        } else {
            title = title.toLowerCase();
        }


        List<Movie> movies = new ArrayList<>();

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, title);

        while (results.next()) {
            Movie movie = mapRowToMovie(results);
            movies.add(movie);
        }

        return movies;

    }

    @Override
    public List<Movie> getMoviesByDirectorNameAndBetweenYears(String directorName, int startYear,
                                                              int endYear, boolean useWildCard) {
        String sql = MOVIE_SELECT +
                "JOIN person p ON p.person_id = m.director_id " +
                "WHERE LOWER(p.person_name) LIKE ? ";


        if (useWildCard) {
            directorName = "%" + directorName.toLowerCase() + "%";
        } else {
            directorName = directorName.toLowerCase();
        }

        sql += "AND (m.release_date BETWEEN TO_DATE(? || '-01-01', 'YYYY-MM-DD') " +
                "AND TO_DATE(? || '-12-31', 'YYYY-MM-DD'))";

        List<Movie> movies = new ArrayList<>();

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, directorName, startYear, endYear);

        while (results.next()) {
            Movie movie = mapRowToMovie(results);
            movies.add(movie);

        }
        return movies;
    }

    private Movie mapRowToMovie(SqlRowSet results) {
        Movie movie = new Movie();
        movie.setId(results.getInt("movie_id"));
        movie.setTitle(results.getString("title"));
        movie.setOverview(results.getString("overview"));
        movie.setTagline(results.getString("tagline"));
        movie.setPosterPath(results.getString("poster_path"));
        movie.setHomePage(results.getString("home_page"));
        movie.setReleaseDate(results.getDate("release_date").toLocalDate());
        movie.setLengthMinutes(results.getInt("length_minutes"));
        movie.setDirectorId(results.getInt("director_id"));
        movie.setCollectionId(results.getInt("collection_id"));
        return movie;
    }
}
