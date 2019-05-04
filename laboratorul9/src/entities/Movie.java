package entities;

public class Movie {
    private int id;
    private String name;
    private int director_id;

    public Movie(int id, String name, int director_id) {
        this.id = id;
        this.name = name;
        this.director_id = director_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDirector_id() {
        return director_id;
    }

    public void setDirector_id(int director_id) {
        this.director_id = director_id;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", director_id='" + director_id + '\'' +
                '}';
    }
}
