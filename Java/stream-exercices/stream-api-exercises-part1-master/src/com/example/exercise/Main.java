package com.example.exercise;

import com.example.dao.InMemoryWorldDao;
import com.example.domain.City;
import com.example.domain.Country;
import com.example.domain.Director;
import com.example.domain.Movie;
import com.example.service.InMemoryMovieService;
import com.example.service.MovieService;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Main {
    static void main(String[] args){
        InMemoryWorldDao world =  InMemoryWorldDao.getInstance();
        List<City> cities = world.findAllCities();
        List<Country> countries = world.findAllCountries();
        List<String> continents = world.getAllContinents().stream().toList();

        MovieService moviesService = InMemoryMovieService.getInstance();
        List<Movie> movies = moviesService.findAllMovies().stream().toList();



        // Exercise 1
//        System.out.println("EXERCISE 1");
//        Map<String, String> countryToContinent = countries.stream()
//                .collect(Collectors.toMap(
//                        Country::getCode,
//                        Country::getContinent
//                ));
//        Map<String, Optional<City>> citiesResult = cities.stream()
//                .collect(Collectors.groupingBy(
//                        city -> countryToContinent.get(city.getCountryCode()),
//                        Collectors.maxBy(Comparator.comparingLong(City::getPopulation))
//                ));
//        System.out.println(citiesResult);

        // Exercise 2
//        System.out.println("EXERCISE 2");
//
//        Map<Director, Long> filmsByDirector = movies.stream()
//                .flatMap(movie -> movie.getDirectors().stream())
//                .collect(Collectors.groupingBy(
//                        director -> director,
//                        Collectors.counting()
//                ));
//
//        System.out.println(filmsByDirector);

        // Exercise 3
        System.out.println("EXERCISE 3");


        // Exercise 4
//        System.out.println("EXERCISE 4 ");
//
//        Optional<City> mostPopulatedCity = cities.stream()
//                .max(Comparator.comparing(City::getPopulation));
//
//        System.out.println(mostPopulatedCity.get().getName());

        // Exercise 5
        System.out.println("EXERCISE 5 ");

        

    }
}
