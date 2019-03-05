import java.time.LocalDate;
import java.time.Month;

/**
 * Clasa de baza.
 */
public class Main {
    /**
     * Inima programului :)
     *
     * @param args - argumente primite la rulare.
     */
    public static void main(String[] args) {
        //Initializare studenti
        Student s1 = new Student("S1", 2);
        Student s2 = new Student("S2", 3);
        Student s3 = new Student("S3", 1);
        Student s4 = new Student("S4", 3);

        System.out.println(s1.getName() + " este in anul " + s1.getYear_of_study());

        //Initializare proiecte
        Application a1 = new Application("A1", LocalDate.of(2019, Month.JUNE, 1), Language.C);
        Application a2 = new Application("A2", LocalDate.of(2019, Month.JANUARY, 1), Language.JAVA);
        Application a3 = new Application("A3", LocalDate.of(2019, Month.FEBRUARY, 1), Language.JAVA);
        Essay e1 = new Essay("E1", LocalDate.parse("2019-06-01"), Topic.ALGORITHMS);
        Essay e2 = new Essay("E2", LocalDate.parse("2019-06-01"), Topic.ALGORITHMS);

        //Setare preferinte
        s1.setPreferences(a1,a2,a3);
        s2.setPreferences(a1,e1);
        s3.setPreferences(a2,a3,e1);
        s4.setPreferences(a3,e2);

        //Initializare problema
        Problem problem = new Problem();
        problem.setStudents(s1,s2,s3,s4);
        problem.setStudents(s1);
        System.out.println(problem.showProjects());
        System.out.println(problem);

        //Matching Optional
        Matching m=new Matching();
        m.setAllocated(problem);
        m.showDistribution();

        //Matching cu Teorema lui Hall
        Bonus new_asign = new Bonus();
        new_asign.check(problem);
        new_asign.Print();

        //Matching maximal
        MaxMatch max = new MaxMatch(problem);
        System.out.println("Nr maxim:" + max.maxBPM());
        max.Print();
    }
}
