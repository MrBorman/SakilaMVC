package org.example.demo1.dao;

import org.example.demo1.models.Actor;
import org.example.demo1.models.ActorFilm;
import org.example.demo1.models.Film;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ActorFilmMapper implements RowMapper {
    @Override
    public ActorFilm mapRow(ResultSet resultSet, int i) throws SQLException {

        ActorFilm actorFilm = new ActorFilm();
        actorFilm.setActor_id(resultSet.getInt("actor_id"));
        actorFilm.setFirst_name(resultSet.getString("first_name"));
        actorFilm.setLast_name(resultSet.getString("last_name"));

        actorFilm.setFilm_id(resultSet.getInt("film_id"));
        actorFilm.setTitle(resultSet.getString("title"));


        return actorFilm;
    }
}
