package org.example.demo1.dao;

import org.example.demo1.models.Actor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActorMapper implements RowMapper<Actor> {
    @Override
    public Actor mapRow(ResultSet resultSet, int i) throws SQLException {
        Actor actor = new Actor();
        actor.setActor_id(resultSet.getInt("actor_id"));
        actor.setFirst_name(resultSet.getString("first_name"));
        actor.setLast_name(resultSet.getString("last_name"));
        actor.setLast_update((resultSet.getDate("last_update")));
        return actor;
    }
}
