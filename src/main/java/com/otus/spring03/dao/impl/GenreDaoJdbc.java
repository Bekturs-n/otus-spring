package com.otus.spring03.dao.impl;

import com.otus.spring03.dao.GenreDao;
import com.otus.spring03.domain.Genre;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;

    public GenreDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public long count(){
        return jdbc.getJdbcOperations()
                .queryForObject("select count(*) from genres", Long.class);
    }

    @Override
    public void insert(Genre genre) {
        jdbc.update("INSERT INTO genres (genre) VALUES (:genre)", Map.of("genre", genre.getGenre()));
    }

    @Override
    public void update(Genre genre) {
        jdbc.update("UPDATE genres SET (genre = :genre) WHERE id = :id",
                Map.of("genre", genre.getGenre(), "id", genre.getId()));
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("DELETE FROM genres WHERE id = :id", Map.of("id", id));
    }

    @Override
    public Genre getById(long id) {
        Genre genre = null;
        try {
            genre = jdbc.queryForObject("SELECT * FROM genres WHERE id = :id",
                    Map.of("id", id), new GenreMapper());
        } catch (EmptyResultDataAccessException e){
            //noop
        }
        return genre;
    }

    @Override
    public Genre getByName(String genre) {
        Genre result = null;
        try {
            result = jdbc.queryForObject("SELECT * FROM genres WHERE genre = :genre",
                    Map.of("genre", genre), new GenreMapper());
        } catch (EmptyResultDataAccessException e){
            //noop
        }
        return result;
    }

    static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String genre = rs.getString("genre");
            return new Genre(id, genre);
        }
    }
}
