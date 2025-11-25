package com.letterbox.service;

import com.letterbox.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CsvExportService {

    /**
     * Serializa una lista de películas a formato CSV
     * Demuestra: Serialización manual (Object → String)
     */
    public String exportMoviesToCsv(List<Movie> movies) {
        if (movies == null || movies.isEmpty()) {
            return "";
        }

        StringBuilder csv = new StringBuilder();

        // Header (primera línea con nombres de columnas)
        csv.append("id,title,director,releaseYear,genre,synopsis\n");

        // Datos (una línea por película)
        for (Movie movie : movies) {
            csv.append(movie.getId()).append(",")
                    .append(escapeCsv(movie.getTitle())).append(",")
                    .append(escapeCsv(movie.getDirector())).append(",")
                    .append(movie.getReleaseYear()).append(",")
                    .append(escapeCsv(movie.getGenre())).append(",")
                    .append(escapeCsv(movie.getSynopsis()))
                    .append("\n");
        }

        return csv.toString();
    }

    /**
     * Escapa caracteres especiales para CSV
     * Si el texto contiene comas o comillas, lo envuelve en comillas
     */
    private String escapeCsv(String value) {
        if (value == null) {
            return "";
        }

        // Si contiene coma, comilla o salto de línea, envolver en comillas
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            // Duplicar comillas internas y envolver en comillas
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }

        return value;
    }
}