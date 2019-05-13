package app;

import exceptions.*;
import network.ClientThread;

import javax.management.RuntimeErrorException;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SocialNetwork {
    private List<Person> people;
    private List<Friends> friends;
    private List<ClientThread> clients;
    private String name;

    public SocialNetwork(String name){
        this.name = name;
        people = new ArrayList<>();
        friends = new ArrayList<>();
        clients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public List<Friends> getFriends() {
        return friends;
    }

    public void setFriends(List<Friends> friends) {
        this.friends = friends;
    }

    public String register(ClientThread personThread,String name){
        try{
            if(personThread.getIdentity() != null){
                throw new IsOnException();
            }
            for(Person person : people){
                System.out.println(person.getName());
                if(person.getName().equals(name))
                    throw new AlreadyRegisteredException();
            }
        }
        catch (IsOnException e1){
            return "Esti deja logat.";
        }
        catch(AlreadyRegisteredException e2){
            return name + " este deja inregistrat.";
        }
        Person p = new Person(name,this);
        people.add(p);
        p.setLoggedIn(true);
        personThread.setIdentity(p);
        clients.add(personThread);
        return "Inregistrarea a reusit.";
    }

    public String login(ClientThread personThread,String name){
        try{
            if(personThread.getIdentity() != null){
                throw new IsOnException();
            }
            for(Person person : people){
                if(person.getName().equals(name)){
                    if(person.isLoggedIn())
                        throw new IsOnException();
                    person.setLoggedIn(true);
                    personThread.setIdentity(person);
                    clients.add(personThread);
                    return "Salut " + person.getName() + "!";
                }
            }
            throw new NotRegisteredException();
        }catch(NotRegisteredException e1){
            return name + " nu este inregistrat.";
        }
        catch(IsOnException e2){
            return "Esti deja logat.";
        }
    }

    public String addFriend(ClientThread personThread, String[] names){
        String msg="";
        for(String name:names){
            try{
                if(personThread.getIdentity() == null)
                    throw new IsOnException();
                for(Person friend : people){
                    if(friend.getName().equals(name)){
                        if(!friend.getName().equals(personThread.getIdentity().getName())){
                            Friends f = new Friends(personThread.getIdentity(),friend);
                            friends.add(f);
                            throw new RuntimeException();
                        }
                        else{
                            throw new InvalidArgumentsException();
                        }
                    }
                }
                throw new NotRegisteredException();
            }
            catch (IsOnException e02){
                return "Nu esti logat.";
            }
            catch(RuntimeException e0){
                msg = msg + "Acum esti prieten cu "+name + '\n';
            }
            catch(InvalidArgumentsException e1){
                msg = msg + "Nu poti fi prieten cu tine." + '\n';
            }
            catch(NotRegisteredException e2){
                msg = msg + name + " nu este inregistrat." + '\n';
            }
            catch(AlreadyFriendsException e3){
                msg = msg + "Esti deja prieten cu "+name + '\n';
            }

        }
        return msg;
    }

    public String sendMSG(ClientThread personThread, String[] msg){
        String outputData = "From "+personThread.getIdentity().getName();
        outputData = outputData +  " - ";
        for(String message:msg)
            outputData=outputData + message + " ";
        outputData = outputData + "\n";
        try{
            if(personThread.getIdentity() == null)
                throw new IsOnException();
            for(ClientThread client:clients){
                if(client.getIdentity().isLoggedIn() && client.getIdentity() != personThread.getIdentity()){
                    client.addToMessage(outputData);
                }
            }
        }catch (IsOnException e){
            return "Nu esti inregistrat";
        }
    return "Mesajul a fost trimis!";
    }


}
