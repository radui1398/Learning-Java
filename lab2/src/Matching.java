import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Clasa ce se ocupa de asignarea tuturor proiectelor.
 */
public class Matching{
    /* List of allocating projects to students */
    private ArrayList <Project> allocated= new ArrayList <Project>();
    private List <Student> students;
    private int check[];

    /**
     * Aloca studenților câte un proiect in funcție de preferința.
     *
     * @param p - o problema primita.
     */
    public void setAllocated(Problem p) {
        students = new ArrayList<Student>(p.getStudents());
        check=new int[students.size()];
        Arrays.fill(check,0);
        int level=0;
        int isOK=0;
        /* Backtracking algorithm for allocating students to projects */
        while(level>=0){
            if(Successor(level)==true)
                if(Validation(level)==true)
                    if(Solution(level,students)==true){
                        isOK=1;
                        break;
                    }
                    else level++;
                 else ;
            else level--;
        }
        if(isOK==0)
            allocated.clear();
    }

    /**
     * Verifica daca s-a gasit o solutie.
     *
     * @param level - al n-lea student unde n=level.
     * @param students - lista cu studenti.
     * @return - bool.
     */
    public boolean Solution(int level,List<Student> students){
        if(level==students.size()-1)
            return true;
        return false;
    }

    /**
     * Verifica dacă toate proiectele alocate pana la acel moment(level) sunt diferite.
     *
     * @param level - al n-lea student unde n=level.
     * @return - bool.
     */
    public boolean Validation(int level){
        for(int i=0;i<level;i++)
            if(allocated.get(i).equals(allocated.get(level))==true)
                return false;
         return true;
    }

    /**
     * Determina următorul proiect din lista de preferințe pentru un student.
     *
     * @param level - al n-lea student unde n=level.
     * @return - bool.
     */
    public boolean Successor(int level){
        if(check[level]==0) {
            allocated.add(level,students.get(level).getListPreferences().get(0));
            check[level]=1;
            return true;
        }
        else if(check[level]==-1) {
            allocated.set(level,students.get(level).getListPreferences().get(0));
            check[level]=1;
            return true;
        }
        int i;
        for(i=0;i<students.get(level).getListPreferences().size();i++)
            if(allocated.get(level).equals(students.get(level).getListPreferences().get(i))){
                break;
            }
        if(i==students.get(level).getListPreferences().size()-1) {
            check[level]=-1;
            return false;
        }
        allocated.set(level,students.get(level).getListPreferences().get(i+1));
        return true;
    }

    /**
     * Afiseaza distributia realizata.
     */
    public void showDistribution(){
        for(int i=0;i<allocated.size();i++)
            System.out.println(students.get(i).getName()+' '+allocated.get(i).getName());
    }

    /**
     * Converteste intr-o reprezentare String clasa Matching.
     *
     *  @return - String.
     */
    @Override
    public String toString() {
        return "Matching{" +
                "allocated=" + allocated +
                ", students=" + students +
                '}';
    }
}