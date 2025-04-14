package controller;
import java.util.ArrayList;
import java.util.HashMap;
import util.*;
import model.Book;

public class BookManagement{
    private final HashMap<String, ArrayList<Book>> books;
    
    public BookManagement(){
        this.books = new HashMap<>();
        CSVhandler.readBookCSV(books,"books.csv");
    }

    public void addBook(Book book){
        String title = book.getTitle();
        books.computeIfAbsent(title, k -> new ArrayList<Book>());
        ArrayList<Book> list = books.get(title);
        for(Book object : list){
                if(object.getISBN().equals(book.getISBN())){
                    return;
                }
        }
        list.add(book);
        CSVhandler.appendCSV(book,"books.csv");
    }
    
    public void removeBook(Book book){
        String title = book.getTitle();
        String ISBN = book.getISBN();
        
        if(books.containsKey(title)){
            ArrayList<Book> list = books.get(title);
            
            for(Book object : list){
                if(object.getISBN().equals(ISBN)){
                    book = object;
                }
            }
            
            list.remove(book);
            if(list.isEmpty()){
                books.remove(title);
            }
            String header = "Type,Title,Author,Genre,ISBN,Status,Number of pages,Due date,File format";
            CSVhandler.writeCSV(books,"books.csv",header);
        }
    }

    public void listBooks(){
        for(ArrayList<Book> list : books.values()){
            for(Book b : list){
                b.displayBookInfor();
                System.out.println("\n ----\n");
            }
        }
    }
    
    public Book findBookById(String id){
        String data[] = id.split("-");
        if(data.length != 2){
            return null;
        }
        String title = data[0];
        String ISBN = data[1];
        
        if(!(books.containsKey(title))){
            return null;
        }
        
        ArrayList<Book> list = books.get(title);
        for(Book b : list){
            if(b.getISBN().equals(ISBN)){
                return b;
            }
        }
        return null;
    }
    
    public ArrayList<Book> findBooksByTitle(String title){
	    return books.get(title);
    }
}