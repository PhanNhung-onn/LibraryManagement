package model;
import java.util.Date;
import java.util.ArrayList;
import controller.*;

public class Reader extends User {
    
    public Reader(String id, String name, String password) {
        super(id, name, password);
        this.role = "reader";
    }
    @Override
    public void displayUserInfor(){
        System.out.print("Reader: ");
        super.displayUserInfor();
    }
    
    public void borrowBook(String id, Date dueDate) {
        BookManagement books = new BookManagement();
        TransManagement transactions = new TransManagement();
        
        Book book = books.findBookById(id);

        if (book != null && book instanceof PrintedBook && "available".equals(book.getStatus())) {
            book.setStatus("borrowed");
            ((PrintedBook) book).setDueDate(dueDate);

            books.removeBook(book);
            books.addBook(book);

            transactions.addTransaction(new Transaction(id, (PrintedBook) book, "borrow", new Date()));
        } else {
            System.out.println("Không thể mượn sách: sách không tồn tại hoặc không khả dụng.");
        }
    }

    
    public void returnBook(String id) {
        BookManagement books = new BookManagement();
        TransManagement transactions = new TransManagement();
        
        Book book = books.findBookById(id);

        if (book != null && book instanceof PrintedBook && "borrowed".equals(book.getStatus())) {
            book.setStatus("available");
            ((PrintedBook) book).setDueDate(null);

            books.removeBook(book);
            books.addBook(book);

            transactions.addTransaction(new Transaction(id, (PrintedBook) book, "return", new Date()));
        } else {
            System.out.println("Không thể trả sách: sách không tồn tại hoặc chưa được mượn.");
        }
    }

    
    public void listBooks(){
        BookManagement books = new BookManagement();
        
        books.listBooks();
    }
    
    public void viewBook(String id){
        BookManagement books = new BookManagement();
        
        Book book = books.findBookById(id);
            if (book != null) {
                book.displayBookInfor();
            } else {
                System.out.println("Không tìm thấy sách với mã: " + id);
            }
    }
    
    public void findBookById(String id){
        BookManagement books = new BookManagement();
        
	    Book book = books.findBookById(id);
	    if(book == null){
	        return;
	    }
	    book.displayBookInfor();
    }
    
    public void findBookByTitle(String title){
        BookManagement books = new BookManagement();
        
	    ArrayList<Book> list = books.findBooksByTitle(title);
	    if(list == null){
	        return;
	    }
	    for(Book b : list){
	        b.displayBookInfor();
	    }
    }
}