package com.repositories;


import com.entities.Author;
import com.entities.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.utils.ConnectionUtils.getConnection;

public class BookRepository {
    public List<Book> findAll(String titleChoose, int page ) {
        List<Book> books = new ArrayList<>();
        int limit = 5;
        try (Connection connection = getConnection()){
            String query = "SELECT * FROM Book WHERE title LIKE ? LIMIT ? OFFSET ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + titleChoose + "%");
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, limit * (page -1));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = convert(resultSet);
                books.add(book);
            }
            return books;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Optional<Book> findByIsbn(String isbn) {
        try(Connection connection = getConnection()){
            String query = "SELECT * FROM Book INNER JOIN author ON author.id = book.author_id WHERE book.isbn  = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, isbn);
            ResultSet resultSet = preparedStatement.executeQuery();
            Book book = null;
            Author author = null;
            if (resultSet.next()) {
                book = new Book(resultSet.getString("isbn")
                        , resultSet.getString("title")
                        , resultSet.getString("description")
                        , resultSet.getInt("author_id")
                );
                author = new Author(resultSet.getInt("author_id")
                                    , resultSet.getString("firstname")
                                    , resultSet.getString("lastname")
                                    , resultSet.getDate("birthdate").toLocalDate()

                );
                book.setAuthor(author);
            }
            return Optional.ofNullable(book);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean save(Book book) {
        try(Connection connection = getConnection()){
            String query = "INSERT INTO Book (isbn, title, description, author_id) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getDescription());
            ps.setInt(4, book.getAuthorId());
            return ps.executeUpdate() == 1;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean update(Integer isbn, Book book) {
        try(Connection connection = getConnection()){
            String query = "UPDATE Book  SET isbn = ?, title = ?, description = ?, author_id = ? WHERE isbn = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, isbn);
            ps.setString(2, book.getIsbn());
            ps.setString(3, book.getTitle());
            ps.setString(4, book.getDescription());
            ps.setInt(5, isbn);
            return ps.executeUpdate() == 1;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean delete(String isbn) {
        try(Connection connection = getConnection()){
            String query = "DELETE FROM Book WHERE isbn = ?";
            PreparedStatement ps =  connection.prepareStatement(query);
            ps.setString(1, isbn);
            return ps.executeUpdate() == 1;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private Book convert(ResultSet resultSet) throws SQLException {
        return new Book(resultSet.getString("isbn")
                , resultSet.getString("title")
                , resultSet.getString("description")
                , resultSet.getInt("author_id"));
    }
}
