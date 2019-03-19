import java.time.LocalDate;

/**
 * Clasa ce retine eseurile.
 */
public class Essay extends Project {
    private Topic topic;

    /**
     * Constructor pentru clasa Essay.
     *
     * @param essName - numele eseului.
     * @param deadLine - data limita pana la care se poate prezenta eseul.
     * @param newTopic - topicul eseului.
     */
    Essay(String essName, LocalDate deadLine, Topic newTopic){
        setName(essName);
        setDeadline(deadLine);
        topic=newTopic;
        setAvailable(1);
    }

    /**
     * Returneaza topicul eseului.
     *
     * @return - topic.
     */
    public Topic getTopic() {
        return topic;
    }

    /**
     * Seteaza topicul pentru un eseu.
     *
     * @param topic - topicul ce va fi setat.
     */
    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    /**
     * Converteste intr-o reprezentare String clasa Essay.
     *
     *  @return - String.
     */
    @Override
    public String toString() {
        return "Essay{" +
                "topic=" + topic + ", " + "name='" + name + '\'' +
                ", deadline=" + deadline +
                '}';
    }
}
