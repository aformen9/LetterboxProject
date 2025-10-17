package com.letterbox.config;

import com.letterbox.entity.Movie;
import com.letterbox.entity.Review;
import com.letterbox.repository.MovieRepository;
import com.letterbox.repository.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;

    public DataLoader(MovieRepository movieRepository, ReviewRepository reviewRepository) {
        this.movieRepository = movieRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void run(String... args) {

        if (movieRepository.count() == 0 && reviewRepository.count() == 0) {
            loadSampleData();
        }
    }

    private void loadSampleData() {

        Movie movie1 = new Movie();
        movie1.setTitle("El Padrino");
        movie1.setDirector("Francis Ford Coppola");
        movie1.setReleaseYear(1972);
        movie1.setGenre("Drama");
        movie1.setSynopsis("La saga de una familia mafiosa italiana-americana.");
        movie1 = movieRepository.save(movie1);

        Movie movie2 = new Movie();
        movie2.setTitle("Pulp Fiction");
        movie2.setDirector("Quentin Tarantino");
        movie2.setReleaseYear(1994);
        movie2.setGenre("Crimen");
        movie2.setSynopsis("Historias entrelazadas del mundo criminal.");
        movie2 = movieRepository.save(movie2);

        Movie movie3 = new Movie();
        movie3.setTitle("The Shawshank Redemption");
        movie3.setDirector("Frank Darabont");
        movie3.setReleaseYear(1994);
        movie3.setGenre("Drama");
        movie3.setSynopsis("Dos hombres encarcelados establecen un vínculo a lo largo de varios años.");
        movieRepository.save(movie3);

        Movie movie4 = new Movie();
        movie4.setTitle("The Dark Knight");
        movie4.setDirector("Christopher Nolan");
        movie4.setReleaseYear(2008);
        movie4.setGenre("Acción");
        movie4.setSynopsis("Batman se enfrenta a su mayor desafío, el Joker.");
        movieRepository.save(movie4);

        Movie movie5 = new Movie();
        movie5.setTitle("Forrest Gump");
        movie5.setDirector("Robert Zemeckis");
        movie5.setReleaseYear(1994);
        movie5.setGenre("Drama");
        movie5.setSynopsis("La vida de un hombre de buen corazón que es testigo de eventos históricos.");
        movieRepository.save(movie5);

        Movie movie6 = new Movie();
        movie6.setTitle("Inception");
        movie6.setDirector("Christopher Nolan");
        movie6.setReleaseYear(2010);
        movie6.setGenre("Ciencia Ficción");
        movie6.setSynopsis("Un ladrón que roba secretos corporativos a través de los sueños.");
        movieRepository.save(movie6);

        Movie movie7 = new Movie();
        movie7.setTitle("Fight Club");
        movie7.setDirector("David Fincher");
        movie7.setReleaseYear(1999);
        movie7.setGenre("Drama");
        movie7.setSynopsis("Un oficinista insomne busca una forma de cambiar su vida.");
        movieRepository.save(movie7);

        Movie movie8 = new Movie();
        movie8.setTitle("The Matrix");
        movie8.setDirector("Wachowski Brothers");
        movie8.setReleaseYear(1999);
        movie8.setGenre("Ciencia Ficción");
        movie8.setSynopsis("Un hacker descubre la verdadera naturaleza de su realidad.");
        movieRepository.save(movie8);

        Movie movie9 = new Movie();
        movie9.setTitle("Goodfellas");
        movie9.setDirector("Martin Scorsese");
        movie9.setReleaseYear(1990);
        movie9.setGenre("Crimen");
        movie9.setSynopsis("La historia de Henry Hill y su vida en la mafia.");
        movieRepository.save(movie9);

        Movie movie10 = new Movie();
        movie10.setTitle("Se7en");
        movie10.setDirector("David Fincher");
        movie10.setReleaseYear(1995);
        movie10.setGenre("Suspenso");
        movie10.setSynopsis("Dos detectives persiguen a un asesino en serie que basa sus crímenes en los siete pecados capitales.");
        movieRepository.save(movie10);

        Movie movie11 = new Movie();
        movie11.setTitle("The Silence of the Lambs");
        movie11.setDirector("Jonathan Demme");
        movie11.setReleaseYear(1991);
        movie11.setGenre("Suspenso");
        movie11.setSynopsis("Una joven cadete del FBI debe buscar la ayuda de un asesino manipulador para atrapar a otro.");
        movieRepository.save(movie11);

        Movie movie12 = new Movie();
        movie12.setTitle("Spirited Away");
        movie12.setDirector("Hayao Miyazaki");
        movie12.setReleaseYear(2001);
        movie12.setGenre("Animación");
        movie12.setSynopsis("Una niña de 10 años deambula por un mundo gobernado por dioses, brujas y espíritus.");
        movieRepository.save(movie12);

        Movie movie13 = new Movie();
        movie13.setTitle("Saving Private Ryan");
        movie13.setDirector("Steven Spielberg");
        movie13.setReleaseYear(1998);
        movie13.setGenre("Bélico");
        movie13.setSynopsis("Tras el desembarco de Normandía, un grupo de soldados busca a un paracaidista.");
        movieRepository.save(movie13);

        Movie movie14 = new Movie();
        movie14.setTitle("Interstellar");
        movie14.setDirector("Christopher Nolan");
        movie14.setReleaseYear(2014);
        movie14.setGenre("Ciencia Ficción");
        movie14.setSynopsis("Un equipo de exploradores viaja a través de un agujero de gusano en busca de un nuevo hogar.");
        movieRepository.save(movie14);

        Movie movie15 = new Movie();
        movie15.setTitle("Parasite");
        movie15.setDirector("Bong Joon Ho");
        movie15.setReleaseYear(2019);
        movie15.setGenre("Suspenso");
        movie15.setSynopsis("La codicia y la discriminación de clase amenazan la relación simbiótica recién formada entre dos familias.");
        movieRepository.save(movie15);

        Movie movie16 = new Movie();
        movie16.setTitle("Gladiator");
        movie16.setDirector("Ridley Scott");
        movie16.setReleaseYear(2000);
        movie16.setGenre("Acción");
        movie16.setSynopsis("Un general romano es traicionado y su familia asesinada por el hijo de un emperador.");
        movieRepository.save(movie16);

        Movie movie17 = new Movie();
        movie17.setTitle("The Departed");
        movie17.setDirector("Martin Scorsese");
        movie17.setReleaseYear(2006);
        movie17.setGenre("Crimen");
        movie17.setSynopsis("Un policía encubierto y un topo en la policía intentan identificarse mutuamente.");
        movieRepository.save(movie17);

        Movie movie18 = new Movie();
        movie18.setTitle("Whiplash");
        movie18.setDirector("Damien Chazelle");
        movie18.setReleaseYear(2014);
        movie18.setGenre("Drama");
        movie18.setSynopsis("Un joven y prometedor baterista se inscribe en un conservatorio de música donde sus sueños son puestos a prueba.");
        movieRepository.save(movie18);

        Movie movie19 = new Movie();
        movie19.setTitle("The Prestige");
        movie19.setDirector("Christopher Nolan");
        movie19.setReleaseYear(2006);
        movie19.setGenre("Misterio");
        movie19.setSynopsis("Después de un trágico accidente, dos magos del siglo XIX se involucran en una batalla para crear la ilusión definitiva.");
        movieRepository.save(movie19);

        Movie movie20 = new Movie();
        movie20.setTitle("Alien");
        movie20.setDirector("Ridley Scott");
        movie20.setReleaseYear(1979);
        movie20.setGenre("Terror");
        movie20.setSynopsis("La tripulación de una nave espacial comercial se encuentra con una forma de vida mortal.");
        movieRepository.save(movie20);

        Movie movie21 = new Movie();
        movie21.setTitle("Back to the Future");
        movie21.setDirector("Robert Zemeckis");
        movie21.setReleaseYear(1985);
        movie21.setGenre("Aventura");
        movie21.setSynopsis("Un adolescente es enviado accidentalmente treinta años al pasado en una máquina del tiempo.");
        movieRepository.save(movie21);

        Movie movie22 = new Movie();
        movie22.setTitle("Terminator 2: Judgment Day");
        movie22.setDirector("James Cameron");
        movie22.setReleaseYear(1991);
        movie22.setGenre("Acción");
        movie22.setSynopsis("Un cyborg debe proteger al futuro líder de la resistencia de un cyborg más avanzado.");
        movieRepository.save(movie22);

        Movie movie23 = new Movie();
        movie23.setTitle("Casablanca");
        movie23.setDirector("Michael Curtiz");
        movie23.setReleaseYear(1942);
        movie23.setGenre("Romance");
        movie23.setSynopsis("Un cínico expatriado estadounidense se debate entre el amor y la virtud en Marruecos durante la Segunda Guerra Mundial.");
        movieRepository.save(movie23);

        Movie movie24 = new Movie();
        movie24.setTitle("City of God");
        movie24.setDirector("Fernando Meirelles");
        movie24.setReleaseYear(2002);
        movie24.setGenre("Crimen");
        movie24.setSynopsis("En las favelas de Río de Janeiro, dos niños toman caminos diferentes: uno fotógrafo y otro narcotraficante.");
        movieRepository.save(movie24);

        Movie movie25 = new Movie();
        movie25.setTitle("Psycho");
        movie25.setDirector("Alfred Hitchcock");
        movie25.setReleaseYear(1960);
        movie25.setGenre("Terror");
        movie25.setSynopsis("Una secretaria de Phoenix malversa dinero y se da a la fuga, registrándose en un motel remoto.");
        movieRepository.save(movie25);

        Movie movie26 = new Movie();
        movie26.setTitle("The Lion King");
        movie26.setDirector("Roger Allers");
        movie26.setReleaseYear(1994);
        movie26.setGenre("Animación");
        movie26.setSynopsis("Un joven príncipe león huye de su reino solo para aprender el verdadero significado de la responsabilidad y la valentía.");
        movieRepository.save(movie26);

        Movie movie27 = new Movie();
        movie27.setTitle("Jaws");
        movie27.setDirector("Steven Spielberg");
        movie27.setReleaseYear(1975);
        movie27.setGenre("Aventura");
        movie27.setSynopsis("Un jefe de policía, un biólogo marino y un viejo marinero se unen para cazar un tiburón blanco gigante.");
        movieRepository.save(movie27);

        Movie movie28 = new Movie();
        movie28.setTitle("Star Wars: Episode V - The Empire Strikes Back");
        movie28.setDirector("Irvin Kershner");
        movie28.setReleaseYear(1980);
        movie28.setGenre("Ciencia Ficción");
        movie28.setSynopsis("Después de que los Rebeldes son brutalmente vencidos en Hoth, Luke Skywalker comienza su entrenamiento Jedi con Yoda.");
        movieRepository.save(movie28);

        Movie movie29 = new Movie();
        movie29.setTitle("Blade Runner");
        movie29.setDirector("Ridley Scott");
        movie29.setReleaseYear(1982);
        movie29.setGenre("Ciencia Ficción");
        movie29.setSynopsis("Un blade runner debe perseguir y terminar con cuatro replicantes que robaron una nave en el espacio.");
        movieRepository.save(movie29);

        Movie movie30 = new Movie();
        movie30.setTitle("Amélie");
        movie30.setDirector("Jean-Pierre Jeunet");
        movie30.setReleaseYear(2001);
        movie30.setGenre("Comedia");
        movie30.setSynopsis("Amélie es una camarera inocente e ingenua en París con su propio sentido de la justicia.");
        movieRepository.save(movie30);

        Review review1 = new Review();
        review1.setMovieId(movie1.getId());
        review1.setReviewerName("María González");
        review1.setRating(5.0);
        review1.setComment("Una obra maestra del cine.");
        review1.setIsFavorite(true);
        review1.setReviewDate(LocalDateTime.now());
        reviewRepository.save(review1);

        Review review2 = new Review();
        review2.setMovieId(movie2.getId());
        review2.setReviewerName("Carlos Rodríguez");
        review2.setRating(4.5);
        review2.setComment("Tarantino en su máximo esplendor.");
        review2.setIsFavorite(true);
        review2.setReviewDate(LocalDateTime.now());
        reviewRepository.save(review2);

        System.out.println("✅ 30 películas y 2 reseñas de muestra cargadas");
    }
}
