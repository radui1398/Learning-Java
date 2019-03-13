import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

/**
 * Clasa pentru modelarea studentilor.
 */
public class Student {
    private String name;
    private int year_of_study;
    private Set<Project> preferences=new HashSet<Project>();;
    private int available;
    /**
     * Constructorul clasei Student.
     *
     * @param stud_name - numele studentului.
     * @param year - anul de studiu al studentului.
     */
    Student(String stud_name, int year) {
        this.name = stud_name;
        this.year_of_study = year;
        this.available = 1;
    }

    /**
     * Verifica daca studentul are un proiect ales.
     *
     * @return - bool.
     */
    public boolean isAvailable(){
        if(available==1)return true;
        return false;
    }

    /**
     * Schimba valoarea variabilei 'available'.
     *
     * @param b - valoare noua available.
     */
    public void setAvailable(int b){
        available=b;
    }

    /**
     * Seteaza numele studentului.
     *
     * @param new_name - noul nume.
     */
    public void setName(String new_name){
        this.name=new_name;
    }

    /**
     * Returneaza numele studentului.
     *
     * @return - nume.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Returneaza anul de studiu.
     *
     * @return - an.
     */
    public int getYear_of_study(){
        return this.year_of_study;
    }

    /**
     * Seteaza anul de studiu.
     *
     * @param year_of_study - an.
     */
    public void setYear_of_study(int year_of_study) {
        this.year_of_study = year_of_study;
    }

    /**
     * Adauga o preferinta de proiect.
     *
     * @param p - proiect.
     */
    public void addPreference(Project p) {
        preferences.add(p);
    }

    /**
     * Seteaza preferintele.
     *
     * @param args - lista cu proiecte.
     */
    public void setPreferences(Project ... args) { ;
        for(Project arg:args)
            preferences.add(arg);
    }

    /**
     * Returneaza preferintele.
     *
     * @return - preferinte.
     */
    public Set<Project> getPreferences() {
        return preferences;
    }

    /**
     * Returneaza preferintele ca si List.
     *
     * @return - preferinte.
     */
    public List<Project> getListPreferences() {
        List<Project> preferences1=new ArrayList<Project>(preferences);
        return preferences1;
    }

    /**
     * Converteste intr-o reprezentare String clasa Student.
     *
     *  @return - String.
     */
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", year_of_study=" + year_of_study +
                ", preferences=" + preferences +
                '}';
    }

    /**
     * Rescrie egalitatea pentru functionarea corecta impreuna cu clasa Student.
     *
     * @param o - Un obiect oarecare.
     * @return - bool.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return year_of_study == student.year_of_study &&
                Objects.equals(name, student.name) &&
                Objects.equals(preferences, student.preferences);
    }

    /**
     * Conferă un hash instanței obiectului obligatoriu la declararea lui equals in clasa Student.
     *
     * @return - int.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, year_of_study, preferences);
    }
}

