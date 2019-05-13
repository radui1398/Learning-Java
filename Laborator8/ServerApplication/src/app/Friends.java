package app;

import exceptions.AlreadyFriendsException;
import exceptions.InvalidArgumentsException;

public class Friends {
    private Person friend1;
    private Person friend2;

    Friends(Person person1,Person person2) throws AlreadyFriendsException{
        if(!person2.isFriend(person1) && !person1.isFriend(person2)) {
            friend1 = person1;
            friend2 = person2;
        }
        else
            throw new AlreadyFriendsException();
    }

    public Person getFriend1() {
        return friend1;
    }

    public Person getFriend2() {
        return friend2;
    }
}
