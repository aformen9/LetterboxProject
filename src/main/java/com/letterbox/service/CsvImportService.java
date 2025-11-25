package com.letterbox.service;

import com.letterbox.entity.Movie;
import com.letterbox.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CsvImportService {

    private final MovieRepository movieRepository;

    public CsvImportService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * Deserializa CSV a películas y las guarda en BD
     * Demuestra: Deserialización manual (String → Object)
     */
    public List<Movie> importMoviesFromCsv(String csvContent) {
        if (csvContent == null || csvContent.trim().isEmpty()) {
            throw new IllegalArgumentException("Contenido CSV vacío");
        }

        List<Movie> importedMovies = new ArrayList<>();
        String[] lines = csvContent.split("\n");

        // Validar que haya al menos header + 1 línea de datos
        if (lines.length < 2) {
            throw new IllegalArgumentException("CSV debe tener header y al menos una película");
        }

        // Saltar header (línea 0)
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i].trim();

            if (line.isEmpty()) {
                continue; // Saltar líneas vacías
            }

            try {
                Movie movie = parseCsvLine(line);
                Movie saved = movieRepository.save(movie);
                importedMovies.add(saved);
            } catch (Exception e) {
                throw new IllegalArgumentException("Error en línea " + (i + 1) + ": " + e.getMessage());
            }
        }

        return importedMovies;
    }

    /**
     * Parsea una línea CSV a un objeto Movie
     * Formato esperado: id,title,director,releaseYear,genre,synopsis
     */
    private Movie parseCsvLine(String line) {
        String[] parts = line.split(",");

        if (parts.length < 5) {
            throw new IllegalArgumentException("Línea CSV inválida, faltan columnas");
        }

        Movie movie = new Movie();
        // No seteamos ID (se autogenera)
        movie.setTitle(parts[1].trim());
        movie.setDirector(parts[2].trim());

        try {
            movie.setReleaseYear(Integer.parseInt(parts[3].trim()));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Año inválido: " + parts[3]);
        }

        movie.setGenre(parts[4].trim());

        // Synopsis es opcional (puede no estar o estar vacía)
        if (parts.length > 5) {
            movie.setSynopsis(parts[5].trim());
        }

        return movie;
    }
}