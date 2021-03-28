package org.example.demo1.dao;

import org.example.demo1.models.Actor;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;


import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class ActorDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/sakila";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345";

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public List<Actor> index() throws SQLException {
        List<Actor> actors = new ArrayList<>();

        Statement statement = null;
        statement = connection.createStatement();

        String SQL = "SELECT * FROM actor";
        ResultSet resultSet = null;
        resultSet = statement.executeQuery(SQL);

        while(resultSet.next()) {
            Actor actor = new Actor();
            actor.setActor_id(resultSet.getInt("actor_id"));
            actor.setFirst_name(resultSet.getString("first_name"));
            actor.setLast_name(resultSet.getString("last_name"));
            actor.setLast_update((resultSet.getDate("last_update")));
            actors.add(actor);
        }
        return actors;
    }

    public Actor show(int id) {
        Actor actor = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM Actor WHERE actor_id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            actor = new Actor();
            actor.setActor_id(resultSet.getInt("actor_id"));
            actor.setFirst_name(resultSet.getString("first_name"));
            actor.setLast_name(resultSet.getString("last_name"));
            actor.setLast_update(resultSet.getDate("last_update"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return actor;
    }

    public void update(int id, Actor updatedActor) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Actor SET first_name=?, last_name=?, last_update=? WHERE actor_id=?");
            preparedStatement.setString(1,updatedActor.getFirst_name());
            preparedStatement.setString(2,updatedActor.getLast_name());
            preparedStatement.setDate(3,new Date(System.currentTimeMillis()));
            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void save(Actor actor) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Actor VALUES (default,?,?,?)");
            preparedStatement.setString(1,actor.getFirst_name());
            preparedStatement.setString(2,actor.getLast_name());
            preparedStatement.setDate(3,new Date(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM Actor WHERE actor_id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
