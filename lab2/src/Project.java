import java.time.LocalDate;
import java.util.Objects;

/**
 * Clasa abstracta a proiectelor.
 */
public abstract class Project {
    protected String name;
    protected LocalDate deadline;
    protected int available;
    protected Student lucky;

    /**
     * Verifica daca proiectul a fost ales.
     *
     * @return - bool.
     */
    public boolean isAvailable(){
        if(available==1)return true;
        return false;
    }

    /**
     * Seteaza un student pentru un proiect.
     *
     * @param s - Studentul.
     */
    public void setStudent(Student s){
        lucky = s;
    }

    /**
     * Returneaza studentul ce are proiectul.
     *
     * @return - student.
     */
    public Student getStudent(){
        return lucky;
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
     * Returneaza numele proiectului.
     *
     * @return - String.
     */
    public String getName() {
        return name;
    }

    /**
     * Seteaza numele proiectului.
     *
     * @param newName - numele noului proiect.
     */
    public void setName(String newName){
        this.name=newName;
    }

    /**
     * Seteaza deadline-ul proiectului.
     *
     * @param newDate - noul deadline.
     */
    public void setDeadline(LocalDate newDate){
        this.deadline=newDate;
    }

    /**
     * Rescrierea functiei de egalitate pentru clasa Project.
     *
     * @param obj - un obiect oarecare.
     * @return - bool.
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Conferă un hash instanței obiectului obligatoriu la declararea lui equals in clasa Project.
     *
     * @return - int.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, deadline);
    }

}
