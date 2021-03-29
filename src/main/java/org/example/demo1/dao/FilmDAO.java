package org.example.demo1.dao;

import org.example.demo1.models.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class FilmDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FilmDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Film> index() {
        return jdbcTemplate.query("SELECT * FROM film", new BeanPropertyRowMapper<>(Film.class));
    }

    public Film show(int id) {
        return jdbcTemplate.query("SELECT * FROM film WHERE film_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Film.class)).stream().findAny().orElse(null);
    }

    public void update(int id, Film updatedFilm) {
        jdbcTemplate.update("UPDATE Film SET title=?, description=?,  language_id=?, original_language_id=?, rental_duration=?, rental_rate=?, length=?, replacement_cost=?, rating=?, special_features=?,last_update=? WHERE film_id=?",
                updatedFilm.getTitle(), updatedFilm.getDescription(),updatedFilm.getLanguage_id(),
                updatedFilm.getOriginal_language_id(),updatedFilm.getRental_duration(),updatedFilm.getRental_rate(),updatedFilm.getLength(),
                updatedFilm.getReplacement_cost(), updatedFilm.getRating(), updatedFilm.getSpecial_features(), new Date(System.currentTimeMillis()), id);
    }

    public void save(Film film) {
        jdbcTemplate.update("INSERT INTO Film VALUES (default,?,?,default,?,?,?,?,?,?,?,?,?)",film.getTitle(), film.getDescription(),film.getLanguage_id(),
                film.getOriginal_language_id(),film.getRental_duration(),film.getRental_rate(),film.getLength(),
                film.getReplacement_cost(), film.getRating(), film.getSpecial_features(), new Date(System.currentTimeMillis()));
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Film WHERE film_id=?", id);
    }
}
