package org.example.demo1.dao;

import org.example.demo1.models.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.sql.*;
import java.util.List;

@Component
public class ActorDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ActorDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Actor> index() {
        return jdbcTemplate.query("SELECT * FROM actor", new BeanPropertyRowMapper<>(Actor.class));
    }

    public Actor show(int id) {
        return jdbcTemplate.query("SELECT * FROM Actor WHERE actor_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Actor.class)).stream().findAny().orElse(null);
    }

    public void update(int id, Actor updatedActor) {
        jdbcTemplate.update("UPDATE Actor SET first_name=?, last_name=?, last_update=? WHERE actor_id=?", updatedActor.getFirst_name(), updatedActor.getLast_name(),new Date(System.currentTimeMillis()), id);
    }

    public void save(Actor actor) {
        jdbcTemplate.update("INSERT INTO Actor VALUES (default,?,?,?)", actor.getFirst_name(),actor.getLast_name(),new Date(System.currentTimeMillis()));
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Actor WHERE actor_id=?", id);
    }
}
