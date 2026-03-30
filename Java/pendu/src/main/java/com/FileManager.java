package com;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManager {

    public static List<String> getFilesName() {
        Path path = Paths.get("src/main/resources");
        try (Stream<Path> files = Files.list(path)) {
            return files.filter(p -> p.toString().endsWith(".txt"))
                        .map(p -> p.getFileName().toString().replace(".txt", ""))
                        .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    public static String getRandomWord(String fileName) {
        Path path = getTotalPath(fileName);
        try (Stream<String> lines = Files.lines(path)) {
            List<String> allWords = lines.toList();
            Random random = new Random();
            int randomWordIndex = random.nextInt(allWords.size());
            return allWords.get(randomWordIndex);
        }catch (IllegalArgumentException e) {
            System.out.println("Le fichier est vide ");
            return null;
        }catch(IOException e){
            return null;
        }
    }

    public static String getRandomWord() {
        Random random = new Random();
        try{
            String fileName = getFilesName().get(random.nextInt(getFilesName().size()));
            Path path = getTotalPath(fileName);
            List<String> allWords = Files.readAllLines(path);
            int randomWordIndex = random.nextInt(allWords.size());
            return allWords.get(randomWordIndex);
        }catch (IOException e){
            return null;
        }
    }

    public static void createCategory(String fileName) {
        Path path = getTotalPath(fileName);
        try{
            Files.createFile(path);
        }catch (FileAlreadyExistsException e){
            System.out.println("Un fichier existe déjà avec cette catégorie");
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public static void deleteCategory(String fileName) {
        Path path = getTotalPath(fileName);
        try{
            Files.deleteIfExists(path);
        }catch(DirectoryNotEmptyException e){
            System.out.println("Dossier non vide");
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public static void addWord(Scanner sc, String category) {
        Path path = getTotalPath(category);
        try{
            System.out.print("Entrez le mot à ajouter : ");
            String userWord = sc.nextLine().trim().toLowerCase();
            boolean isEmpty = Files.size(path) == 0;
            if (isEmpty) Files.writeString(path, userWord, Charset.defaultCharset(), StandardOpenOption.APPEND);
            else Files.writeString(path, "\n" + userWord, Charset.defaultCharset(), StandardOpenOption.APPEND);
        }catch(UnsupportedOperationException e){
            System.out.println("Opération pas supportée");
        }
        catch (IOException e){
            System.out.println(e);
        }
    }

    public static void deleteWord(Scanner sc, String category) {
        Path path = getTotalPath(category);
        try {
            System.out.print("Entrez le mot à supprimer : ");
            String userWord = sc.nextLine().trim().toLowerCase();
             List<String> allWords = Files.readAllLines(path);
             List<String> words = allWords.stream()
                     .filter(word -> !word.trim().equals(userWord))
                     .collect(Collectors.toList());
             Files.write(path, words);
        }catch(UnsupportedOperationException e) {
            System.out.println("Opération pas supportée");
        }catch(IOException e){
            System.out.println(e);
        }
    }

    private static Path getTotalPath(String fileName) {
        return Paths.get("src/main/resources/" + fileName + ".txt");
    }
 }