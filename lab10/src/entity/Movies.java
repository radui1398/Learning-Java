package entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Movies {
    private int id;
    private String name;
    private List<MovieActors> movieActorsById;
    private Persons personsByDirectorId;

    public Movies(String name, Persons personsByDirectorId) {
        this.name = name;
        this.personsByDirectorId = personsByDirectorId;
    }

    public Movies() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movies movies = (Movies) o;
        return id == movies.id &&
                Objects.equals(name, movies.name) &&
                Objects.equals(movieActorsById, movies.movieActorsById) &&
                Objects.equals(personsByDirectorId, movies.personsByDirectorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, movieActorsById, personsByDirectorId);
    }

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "moviesByMovieId")
    public List<MovieActors> getMovieActorsById() {
        return movieActorsById;
    }

    public void setMovieActorsById(List<MovieActors> movieActorsById) {
        this.movieActorsById = movieActorsById;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "director_id", referencedColumnName = "id")
    public Persons getPersonsByDirectorId() {
        return personsByDirectorId;
    }

    public void setPersonsByDirectorId(Persons personsByDirectorId) {
        this.personsByDirectorId = personsByDirectorId;
    }

    public Movies(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
