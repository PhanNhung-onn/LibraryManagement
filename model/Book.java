package model;

public abstract class Book{
    protected String id;
    protected String title;
    protected String author;
    protected String genre;
    protected String ISBN;
    protected String status;

    public Book(String title, String author,String genre, String ISBN, String status){
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.ISBN = ISBN;
        this.status = status;
        this.id = title + "-" + ISBN;
    }
    
    public Book(String data[]){
        this.title = data[1];
        this.author = data[2];
        this.genre = data[3];
        this.ISBN = data[4];
        this.status = data[5];
    }

    //Getter
    public String getId(){
        return id;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getAuthor(){
        return author;
    }
    
    public String getGenre(){
        return genre;
    }
    
    public String getISBN(){
        return ISBN;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }

    @Override
    public abstract String toString();

    public void displayBookInfor(){
        System.out.println(title);
        System.out.println("Author: " + author);
        System.out.println("Genre: " + genre);
        System.out.println("ISBN: " + ISBN);
        System.out.println("Status: " + status);
    }
}