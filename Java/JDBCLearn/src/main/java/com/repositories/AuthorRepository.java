package com.repositories;

import com.entities.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.utils.ConnectionUtils.getConnection;

public class AuthorRepository {

    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        try (Connection connection = getConnection()){
            String query = "SELECT * FROM Author";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Author author = convert(resultSet);
                authors.add(author);
            }
            return authors;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Optional<Author> findById(String isbn) {
        try(Connection connection = getConnection()){
            String query = "SELECT * FROM Author WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, isbn);
            ResultSet resultSet = preparedStatement.executeQuery();
            Author author = null;
            if (resultSet.next()) {
                author =  convert(resultSet);
            }
            return Optional.ofNullable(author);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int save(Author author) {
        try(Connection connection = getConnection()){
            String query = "INSERT INTO Author (firstname, lastname, birthdate) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, author.getFirstname());
            ps.setString(2, author.getLastname());
            ps.setDate(3, Date.valueOf(author.getBirthdate()));

            ps.executeUpdate();

            ResultSet res = ps.getGeneratedKeys();

            if(!res.next()){
                throw new RuntimeException("Insert failed");
            }
            return res.getInt(1);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean update(Integer id, Author author) {
        try(Connection connection = getConnection()){
            String query = "UPDATE Author  SET firstname = ?, lastname = ?, birthdate = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, author.getFirstname());
            ps.setString(2, author.getLastname());
            ps.setDate(3, Date.valueOf(author.getBirthdate()));
            ps.setInt(4, id);
            return ps.executeUpdate() == 1;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean delete(Integer id) {
        try(Connection connection = getConnection()){
            String query = "DELETE FROM Author WHERE id = ?";
            PreparedStatement ps =  connection.prepareStatement(query);
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    private Author convert(ResultSet resultSet) throws SQLException {
        return new Author(resultSet.getInt("id")
                , resultSet.getString("firstname")
                , resultSet.getString("lastname")
                , resultSet.getDate("birthdate").toLocalDate());
    }
}
