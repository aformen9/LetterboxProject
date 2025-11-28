package com.letterbox.service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.letterbox.entity.Movie;
import com.letterbox.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CsvService {

    private final MovieRepository movieRepository;

    public CsvService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Serialización
    public String exportMoviesToCsv(List<Movie> movies) {
        if (movies == null || movies.isEmpty()) {
            return "";
        }
        try {
            CsvMapper mapper = new CsvMapper();
            CsvSchema schema = mapper.schemaFor(Movie.class).withHeader();
            return mapper.writer(schema).writeValueAsString(movies);
        } catch (Exception e) {
            throw new RuntimeException("Error al exportar CSV", e);
        }
    }

    // Deserialización
    @Transactional
    public List<Movie> importMoviesFromCsv(String csvContent) {
        if (csvContent == null || csvContent.trim().isEmpty()) {
            throw new IllegalArgumentException("El archivo CSV está vacío");
        }

        try {
            CsvMapper mapper = new CsvMapper();

            CsvSchema schema = mapper.schemaFor(Movie.class).withHeader();

            MappingIterator<Movie> it = mapper.readerFor(Movie.class)
                    .with(schema)
                    .readValues(csvContent);

            List<Movie> movies = it.readAll();

            return movieRepository.saveAll(movies);

        } catch (Exception e) {
            throw new RuntimeException("Error al procesar e importar el CSV: " + e.getMessage(), e);
        }
    }
}