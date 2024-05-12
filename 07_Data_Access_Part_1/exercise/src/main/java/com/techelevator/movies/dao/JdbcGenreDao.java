package com.techelevator.movies.dao;

import com.techelevator.movies.model.Collection;
import com.techelevator.movies.model.Genre;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcGenreDao implements GenreDao {
    private final String GENRE_SELECT = "SELECT g.genre_id, g.genre_name from genre g ";

    private final JdbcTemplate jdbcTemplate;

    public JdbcGenreDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Genre> getGenres() {
        String sql = GENRE_SELECT;

        List<Genre> genres = new ArrayList<>();

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            Genre singleGenre = mapRowToGenre(results);
            genres.add(singleGenre);
        }

        return genres;
    }

    @Override
    public Genre getGenreById(int id) {
        String sql = GENRE_SELECT + "WHERE g.genre_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            Genre singleGenre = mapRowToGenre(results);
            return singleGenre;
        } else {
            return null;
        }
    }

    @Override
    public List<Genre> getGenresByName(String name, boolean useWildCard) {
        String sql = GENRE_SELECT + "WHERE LOWER(g.genre_name) LIKE ?";

        if (useWildCard) {
            name = "%" + name.toLowerCase() + "%";
        } else {
            name = name.toLowerCase();
        }

        List<Genre> genres = new ArrayList<>();

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);

        while (results.next()){
            Genre singleGenre = mapRowToGenre(results);
            genres.add(singleGenre);
        }
        return genres;
    }

    public Genre mapRowToGenre(SqlRowSet results) {
        Genre genre = new Genre();
        genre.setId(results.getInt("genre_id"));
        genre.setName(results.getString("genre_name"));
        return genre;
    }
}
