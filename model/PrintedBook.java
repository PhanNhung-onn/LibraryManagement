package model;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.lang.Integer;

public class PrintedBook extends Book{
    private int numberOfPages;
    private Date dueDate;

    public PrintedBook(String title, String author, String genre, String ISBN, String status, int numberOfPages, Date dueDate){
        super(title, author, genre, ISBN, status);
        this.numberOfPages = numberOfPages;
        Date today = new Date();
        if(dueDate.before(today) || dueDate == null || status != "borrowed"){
            this.dueDate = null;
        }else{
            this.dueDate = dueDate;
        }
    }
    
    public PrintedBook(String title, String author, String genre, String ISBN, String status, int numberOfPages){
        super(title, author, genre, ISBN, status);
        this.numberOfPages = numberOfPages;
        this.dueDate = null;
    }
    
    public PrintedBook(String data[]){
        super(data);
        if(data[6] != "-"){
            this.numberOfPages = Integer.parseInt(data[6]);
        }else{
            this.numberOfPages = 0;
        }
        if(data[7].equals("-")){
            this.dueDate = null;
        }else{
            try {
                this.dueDate = (new SimpleDateFormat("dd/MM/yyyy")).parse(data[7]);
            } catch (ParseException e) {
                System.out.println("Lỗi định dạng thời gian: " + data[7]);
                e.printStackTrace();
            }
        }
    }

    public int getNumberOfPages(){
        return numberOfPages;
    }
    
    public Date getDueDate(){
        return dueDate;
    }
    
    public void setDueDate(Date dueDate){
        this.dueDate = dueDate;
    }
    
    @Override
    public String toString(){
	    if(dueDate != null){
            return ("P," + title + "," + author + "," + genre + "," + ISBN + "," + status + "," + numberOfPages + "," + new SimpleDateFormat("dd/MM/yyyy").format(dueDate)) + ",-";
	    }
	    return ("P," + title + "," + author + "," + genre + "," + ISBN + "," + status + "," + numberOfPages + ",-,-");
    }
    
    @Override
    public void displayBookInfor(){
       System.out.print("Printed book: ");
       super.displayBookInfor();
       System.out.println("Number of pages: " + numberOfPages);
       if(dueDate != null){
            System.out.println("Due date: " + new SimpleDateFormat("dd/MM/yy").format(dueDate));
       }
    }
}