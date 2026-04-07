package com;

import com.entities.Author;
import com.entities.Book;
import com.repositories.AuthorRepository;
import com.repositories.BookRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AuthorRepository authorRepo = new AuthorRepository();
        BookRepository bookRepo = new BookRepository();
        while (true) {
            System.out.println("1 Auteur");
            System.out.println("2 Livre");
            System.out.println("0 Sortie");

            int choice = sc.nextInt();
            if (choice == 0) break;
            if (choice == 1) {
                System.out.println("1 Lister");
                System.out.println("2 Ajouter");
                System.out.println("3 Supprimer");
                int c = sc.nextInt();
                sc.nextLine();
                if (c == 1) {
                    List<Author> list = authorRepo.findAll();
                    list.forEach(System.out::println);
                }
                if (c == 2) {
                    System.out.print("Entrez la firstname : ");
                    String fn = sc.nextLine();
                    System.out.print("Entrez la lastname : ");
                    String ln = sc.nextLine();
                    System.out.print("Entrez la date de naissance : ");
                    LocalDate d = LocalDate.parse(sc.nextLine());
                    authorRepo.save(new Author(fn, ln, d));
                }
                if (c == 3) {
                    int id = sc.nextInt();
                    authorRepo.delete(id);
                }
            }
            if (choice == 2) {
                System.out.println("1 Lister");
                System.out.println("2 Ajouter");
                System.out.println("3 Supprimer");
                System.out.println("4 Chercher");
                int c = sc.nextInt();
                sc.nextLine();
                if (c == 1) {
                    System.out.print("Entrez un string pour filtrer les titres des livres : ");
                    String filter = sc.nextLine();
                    System.out.print("Quel page voulez vous voir (5 pas page) : ");
                    int p = sc.nextInt();
                    List<Book> list = bookRepo.findAll(filter, p);
                    list.forEach(System.out::println);
                }
                if (c == 2) {
                    System.out.print("Entrez l'ISBN du livre : ");
                    String isbn = sc.nextLine();
                    System.out.print("Entrez le titre du livre : ");
                    String title = sc.nextLine();
                    System.out.print("Entrez la description du livre : ");
                    String desc = sc.nextLine();
                    System.out.print("Entrez l'id de l'auteur du livre : ");
                    int authorId = sc.nextInt();
                    bookRepo.save(new Book(isbn, title, desc, authorId));
                }
                if (c == 3) {
                    String isbn = sc.nextLine();
                    bookRepo.delete(isbn);
                }
                if (c == 4) {
                    String isbn = sc.nextLine();
                    System.out.println(bookRepo.findByIsbn(isbn).orElse(null));
                }
            }
        }
    }
}