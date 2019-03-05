import java.util.*;
import java.lang.*;

/**
 * Clasa cu ajutorul careia gasim un matching maximal.
 */
public class MaxMatch
{
    private int m; //nr. proiecte
    private int n; //nr. studenti
    private int max; //nr. maxim de perechi
    private Boolean[][] bpGraph; //Graf bipartit reprezentat boolean
    private Problem problem; //Problema primita
    private List<Project> projects; //Lista cu proiecte
    private Integer[] students; //students[i] = proiect

    /**
     * Constructorul problemei
     *
     * @param p - problema.
     */
    MaxMatch(Problem p){
        //Declarari
        problem = p;
        max = 0;
        Integer[] students = new Integer[100];
        List<Project> proj_list = new ArrayList<>();
        Boolean[][] bpGraph = new Boolean[100][100];

        //Adaugarea tuturor proiectelor intr-o lista comuna
        proj_list.addAll(p.getProjects());
        this.projects = proj_list;
        this.students=students;

        //Transformarea in graf bipartit a problemei
        n=p.getStudents().size();
        m=proj_list.size();
        for(int i=0;i< 100;i++) {
            for (int j = 0; j < 100; j++)
                bpGraph[i][j] = false;

        }
        int i=0;
        for(Student stud:p.getStudents()){
            for(Project proj:stud.getListPreferences()){
                    bpGraph[i][proj_list.indexOf(proj)] = true;
            }
            i++;
        }
        this.bpGraph = bpGraph;
    }


    // A DFS based recursive function that
    // returns true if a matching for
    // vertex u is possib
    //

    /**
     * Ruleaza un DFS recursiv ce returneaza numarul maxim de perechi ce se pot realiza.
     *
     * @param u - index student
     * @param seen - salveaza proeictele ce au fost luate
     * @param matchR - vector in care sunt salvate optiuni de match
     * @return
     */
    private boolean bpm(int u, boolean seen[], int matchR[])
    {
        // Incearca fiecare proiect
        for (int v = 0; v < n; v++)
        {

            // Daca studentul este liber iar proiectul nu este luat
            if (bpGraph[u][v] && !seen[v])
            {

                // Viziteaza proiectul v
                seen[v] = true;

                // Daca proiectul v nu a fost luat sau se gaseste o alternativa mai buna
                if (matchR[v] < 0 || bpm(matchR[v], seen, matchR))
                {
                    updateList(u,v); //salvam in students[] pentru afisare
                    matchR[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Refacem vectorul students[]
     *
     * @param u - student
     * @param v - proiect
     */
    public void updateList(int u,int v){
        students[u]=v;
    }

    /**
     * Returneaza numarul maxim de perechi ce se pot realiza intre studenti si proiecte.
     *
     * @return - int
     */
    public int maxBPM()
    {
        // Un array ce tine minte studentii ce au luat un anume proiect.
        int matchR[] = new int[n];

        // Initializare proiecte ca libere
        for(int i = 0; i < n; ++i)
            matchR[i] = -1;

        // Numarare proiecte asignate
        int result = 0;
        for (int u = 0; u < m; u++)
        {
            //Marcare proiecte ca neluate
            boolean seen[] =new boolean[n] ;
            for(int i = 0; i < n; ++i)
                seen[i] = false;

            // Verifica daca un student u poate lua un proiect
            if (bpm(u, seen, matchR))
                result++;
        }
        this.max = result;
        return result;
    }

    /**
     * Returneaza studentul de la pozitia 'u'
     *
     * @param set - lista cu studenti
     * @param u - index
     * @return - student
     */
    private Student getStudentAt(Set<Student> set, int u){
        int i=0;
        for(Student stud:set){
            if(i==u)return stud;
            i++;
        }
        return null;
    }

    /**
     * Afisarea perechilor
     *
     */
    public void Print(){
        for(int i=0;i<max;i++){
            System.out.println("Studentul: "+ getStudentAt(problem.getStudents(),i).getName() +" are proiectul " + projects.get(students[i]).getName() + ".");
        }
    }

}
