package model;
import java.util.ArrayList;
import controller.BookManagement;

public class Librarian extends User {
    
    public Librarian(String id, String name, String password) {
        super(id, name, password);
        this.role = "librarian";
    }
    
    public void displayUserInfor(){
        System.out.print("Librarian: ");
        super.displayUserInfor();
    }
    
    public void addBook(String title, String author, String genre, String ISBN, String status, int numberOfPages){
        BookManagement manager = new BookManagement();
        PrintedBook book = new PrintedBook(title, author, genre, ISBN, status, numberOfPages);
        manager.addBook(book);
    }
    
    public void addBook(String title, String author, String genre, String ISBN, String status, String fileFormat){
        BookManagement manager = new BookManagement();
        EBook book = new EBook(title, author, genre, ISBN, status, fileFormat);
        manager.addBook(book);
    }
    
    public void removeBook(String id){
        BookManagement manager = new BookManagement();
        Book book = manager.findBookById(id);
        
	    if(book != null){
            manager.removeBook(book);
	    }
    }
    
    public void listBook(){
        BookManagement manager = new BookManagement();
        manager.listBooks();
    }
    
    public void findBookById(String id){
        BookManagement manager = new BookManagement();
	    Book book = manager.findBookById(id);
	    if(book == null){
	        System.out.println("Không tìm thấy sách");
	        return;
	    }
	    book.displayBookInfor();
    }
    
    public void findBooksByTitle(String title){
        BookManagement manager = new BookManagement();
	    ArrayList<Book> books = manager.findBooksByTitle(title);
	    if(books == null){
	        System.out.println("Không tìm thấy sách");
	        return;
	    }
	    for(Book b : books){
	        b.displayBookInfor();
	    }
    }
}