import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Clasa ce retine problemele.
 */


public class Problem {
    private Set<Student> students=new HashSet<Student>();

    /**
     * Aloca studenti pentru o problema.
     *
     * @param args - lista cu studenti ce vor fi asignati problemei.
     */
    public void setStudents(Student ... args) {
        for (Student arg:args)
            students.add(arg);
    }

    /**
     * Returneaza lista cu studentii asignati problemei.
     *
     * @return - studenti.
     */
    public Set<Student> getStudents() {
          return students;
    }

    /**
     * Returneaza lista cu proiectele alese de studenti.
     *
     * @return - proiecte.
     */
    public Set<Project> getProjects() {
        Set<Project> projects = new HashSet<Project>();
        for (Student arg : students)
            for(Project proj: arg.getPreferences())
                 projects.add(proj);
        return projects;
    }

    /**
     * Afisarea proiectelor.
     *
     * @return - String.
     */
    public String showProjects() {
        return "Projects="+getProjects();
    }

    /**
     * Converteste intr-o reprezentare String clasa Problem.
     *
     *  @return - String.
     */
    @Override
    public String toString() {
        return "Problem{" +
                "students=" + students +
                '}';
    }
}