package model;
import controller.UserManagement;
import java.util.ArrayList;

public class Admin extends User {
    public Admin(String id, String name, String password) {
        super(id, name, password);
        this.role = "admin";
    }
    
    public void displayUserInfor(){
        System.out.print("Admin: ");
        super.displayUserInfor();
    }
    
    public void addUser(String id, String name, String role, String password){
        UserManagement manager = new UserManagement();
        
        switch(role){
            case "admin":
                Admin admin = new Admin(id, name, password);
                manager.addUser(admin);
                break;
            case "librarian":
                Librarian librarian = new Librarian(id, name, password);
                manager.addUser(librarian);
                break;
            case "reader":
                Reader reader = new Reader(id, name, password);
                manager.addUser(reader);
                break;
        }
        
    }
    
    public void removeUser(String id){
        UserManagement manager = new UserManagement();
        
        User user = manager.findUserById(id);
	    if(user != null){
            manager.removeUser(user);
	    }
    }
    
    public void listUsers(){
        UserManagement manager = new UserManagement();
        manager.listUsers();
    }
    
    public void findUserByName(String name){
        UserManagement manager = new UserManagement();
        ArrayList<User> list = manager.findUserByName(name);
        if(list == null){
            return;
        }
        for(User user : list){
            user.displayUserInfor();
        }
    }
    
}