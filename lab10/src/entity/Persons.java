package entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NamedQuery(name="Person.findByName",
        query="SELECT c FROM Persons c WHERE c.name = :name")
public class Persons {
    private int id;
    private String name;
    private String type;
    private List<MovieActors> movieActorsById;
    private List<Movies> moviesById;

    public Persons(String name) {
        this.name = name;
    }

    public Persons(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Persons{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public Persons() {
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

    @Basic
    @Column(name = "type", nullable = true, length = 100)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persons persons = (Persons) o;
        return id == persons.id &&
                Objects.equals(name, persons.name) &&
                Objects.equals(type, persons.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
    }

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "personsByActorId")
    public List<MovieActors> getMovieActorsById() {
        return movieActorsById;
    }

    public void setMovieActorsById(List<MovieActors> movieActorsById) {
        this.movieActorsById = movieActorsById;
    }

    @OneToMany(mappedBy = "personsByDirectorId")
    public List<Movies> getMoviesById() {
        return moviesById;
    }

    public void setMoviesById(List<Movies> moviesById) {
        this.moviesById = moviesById;
    }
}
