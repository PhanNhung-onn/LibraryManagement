package controller;
import java.util.HashMap;
import java.util.ArrayList;
import util.CSVhandler;
import model.User;

public class UserManagement{
    private HashMap<String, User> users;
    
    public UserManagement(){
        this.users = new HashMap<String, User>();
        CSVhandler.readUserCSV(users,"users.csv");
    }

    public void addUser(User u){
        String id = u.getId();
        if(!(users.containsKey(id))){
        users.put(id, u);
        }
        CSVhandler.appendCSV(u,"users.csv");
    }
    
    public void removeUser(User u){
        String id = u.getId();
        
        if(users.containsKey(id)){
            users.remove(id);
            
            String header = "User ID,User name,Role,Password";
            CSVhandler.writeCSV(users,"users.csv",header);
        }
    }

    public void listUsers(){
        for(User u : users.values()){
            u.displayUserInfor();
        }
    }
    
    public User findUserById(String id){
        return users.get(id);
    }
    
    public ArrayList<User> findUserByName(String name){
        ArrayList<User> list = new ArrayList<User>();
	    for(User u : users.values()){
	        if(u.getName().equals(name)){
	            list.add(u);
	       }
	    }
	    if(list.isEmpty()){
	        return null;
	    }
	    return list;
    }
}