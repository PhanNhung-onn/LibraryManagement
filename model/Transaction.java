package model;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import controller.*;

public class Transaction{
    String id;
    String readerId;
    PrintedBook book;
    String action;
    Date time;
    
    public Transaction(String readerId, PrintedBook book, String action, Date time){
        this.readerId = readerId;
        this.book = book;
        this.action = action;
        this.time = time;
        this.id = readerId + "-" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(time);
    }
    
    public Transaction(String data[]){
        this.readerId = data[0];
        
        Book b = new BookManagement().findBookById(data[1]);
        if( b instanceof PrintedBook){
            this.book = (PrintedBook) b;
        }
        
        this.action = data[2];
        try {
            this.time = (new SimpleDateFormat("hh:mm:ss-dd/MM/yyyy")).parse(data[3]);
        } catch (ParseException e) {
            System.out.println("Lỗi định dạng thời gian: " + data[3]);
            e.printStackTrace(); // hoặc xử lý theo ý tiểu thư
        }

        this.id = readerId + "-" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(time);
    }
    
    public String getId(){
        return id;
    }
    
    public String getReaderId(){
        return readerId;
    }
    
    public PrintedBook getBook(){
        return book;
    }
    
    public String getAction(){
        return action;
    }
    
    public Date getTime(){
        return time;
    }
    
    public String toString(){
        return readerId + "," + book.getId() + "," + action + "," + new SimpleDateFormat("hh:mm:ss-dd/MM/yyyy").format(time);
    }
    
    public void displayTransaction(){
        SimpleDateFormat formater = new SimpleDateFormat("hh:mm:ss - dd/MM/yyyy");
        System.out.println("Reader " + readerId + " has " + action + "ed " + book.getTitle() + " at " + formater.format(time));
    }
}