import java.util.*;

/**
 * Clasa cu ajutorul careia gasim perechile folosind teorema lui Hall.
 */
public class Bonus
{

    private Problem p; //Problema P
    private int fail; //Verifica daca programul a functionat

    /**
     * Functia ce verifica perechile.
     *
     * @param p - problema.
     */
    public void check(Problem p){
        //Declarari
        fail=0; //initial problema are sanse de rezolvare.
        Student m;
        Project w;
        List<Student> students = new ArrayList<Student>(p.getStudents());
        this.p = p;

        //Aplicam algoritmul de matching
        m=getFreeMan(students); //se ia primul student liber
        while(m!=null){
            w=getBest(m); //Se ia cel mai potrivit proiect
            while(w!=null){
                //Verificam daca proiectul este in lista de preferinte si daca este liber
                if(m.getListPreferences().contains(w) && w.isAvailable()==true){
                    //Realizam asignarea
                    m.setAvailable(0);
                    w.setAvailable(0);
                    w.setStudent(m);
                    break;
                }
                else{
                    //Daca proiectul a fost ales dar acesta ar fi mai bun impreuna cu studentul m schimbam
                    if(isBetter(w,m)==true) {
                        w.getStudent().setAvailable(1);
                        w.setStudent(m);
                        m.setAvailable(0);
                        break;
                    }
                    else{
                        w=getBest(m);
                    }
                }

            }
            //Daca un student nu si-a gasit proiect iesim cu fail
            if(getFreeMan(students)==m){
                fail=1;
                break;
            }
            m=getFreeMan(students);
        }
    }

    /**
     * Verificam daca exista o pereche mai buna decat cea actuala
     *
     * @param w - proiect
     * @param m - student
     * @return - bool
     */
    private boolean isBetter(Project w,Student m){
        if(m.getListPreferences().indexOf(w) < w.getStudent().getListPreferences().indexOf(w)){
            return true;
        }
        return false;
    }

    /**
     * Ia cel mai bun proiect pentru studentul m.
     *
     * @param m - student
     * @return - proiect
     */
    public Project getBest(Student m){
        for(Project w:m.getListPreferences()){
            if(w.isAvailable()==true)
                return w;
        }
        return null;
    }

    /**
     * Ia un student ce nu are proiect
     *
     * @param students - lista cu studenti
     * @return - student.
     */
    public Student getFreeMan(List <Student> students){
        for(Student stud:students){
            if(stud.isAvailable() == true)return stud;
        }
        return null;
    }

    /**
     * Afisarea solutiei
     */
    public void Print(){
        if(fail==0) {
            for (Project proj : p.getProjects()) {
                if (proj.getStudent() != null)
                    System.out.println("Studentul - " + proj.getStudent().getName() + " are proiectul - " + proj.getName());
            }
        }
        else{
            System.out.println("Nu s-a gasit nici o solutie posibila.");
        }
    }
}

