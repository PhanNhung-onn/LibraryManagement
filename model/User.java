package model;

public abstract class User {
    protected String id;
    protected String name;
    protected String role;
    protected String password;

    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public String getRole(){
        return role;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    @Override
    public String toString(){
        return (id + "," + name + "," + role + "," + password);
    }
    
    public void displayUserInfor(){
        System.out.println(name + "|| ID: " + id);
    }
}