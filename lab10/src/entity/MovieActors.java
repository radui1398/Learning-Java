package entity;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "movie_actors", schema = "movies", catalog = "")
public class MovieActors {
    private int id;
    private Movies moviesByMovieId;
    private Persons personsByActorId;

    public MovieActors(Movies moviesByMovieId, Persons personsByActorId) {
        this.moviesByMovieId = moviesByMovieId;
        this.personsByActorId = personsByActorId;
    }

    public MovieActors() {
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieActors that = (MovieActors) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false)
    public Movies getMoviesByMovieId() {
        return moviesByMovieId;
    }

    public void setMoviesByMovieId(Movies moviesByMovieId) {
        this.moviesByMovieId = moviesByMovieId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "actor_id", referencedColumnName = "id", nullable = false)
    public Persons getPersonsByActorId() {
        return personsByActorId;
    }

    public void setPersonsByActorId(Persons personsByActorId) {
        this.personsByActorId = personsByActorId;
    }
}
