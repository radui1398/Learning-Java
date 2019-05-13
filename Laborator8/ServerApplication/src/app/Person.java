package app;

public class Person {
    private String name;
    private SocialNetwork network;
    private boolean loggedIn = false;

    public Person(String name, SocialNetwork network){
        this.name = name;
        this.network = network;
    }

    public boolean isFriend(Person person){
        for(Friends friends:network.getFriends()){
            if(friends.getFriend1() == this && friends.getFriend2() == person)
                return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public SocialNetwork getNetwork() {
        return network;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
