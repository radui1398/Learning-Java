import java.time.LocalDate;
import java.util.Objects;

/**
 * Clasa ce retine aplicatiile.
 */
public class Application extends Project {
    private Language language;

    /**
     * Acesta este constructorul pentru clasa Application.
     *
     * @param appName este numele aplicatiei.
     * @param deadLine este data pana la care poate fi prezentata aplicatia.
     * @param newLang limbajul de programare pe care se bazeaza aplicatia.
     */
    Application(String appName, LocalDate deadLine, Language newLang){
        setName(appName);
        setDeadline(deadLine);
        language = newLang;
        setAvailable(1);
    }

    /**
     * Returneaza limbajul aplicatiei pe care se bazeaza aplicatia.
     *
     * @return - limbaj.
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * Seteaza limbajul pentru aplicatie.
     *
     * @param language - limbajul setat.
     */
    public void setLanguage(Language language) {
        this.language = language;
    }

    /**
     * Converteste intr-o reprezentare String clasa Application.
     *
     *  @return - String.
     */
    @Override
    public String toString() {
        return "Application{" +
                "language=" + language + ", " + "name='" + name + '\'' +
                ", deadline=" + deadline +
                '}';
    }

    /**
     * Rescrie egalitatea pentru functionarea corecta impreuna cu clasa Application.
     *
     * @param o - Un obiect oarecare.
     * @return - bool.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Application that = (Application) o;
        return language == that.language;
    }

    /**
     * Conferă un hash instanței obiectului obligatoriu la declararea lui equals in clasa Application.
     *
     * @return - int.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), language);
    }
}
