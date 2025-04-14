package model;

public class EBook extends Book{
    private String fileFormat;

    public EBook(String title, String author, String genre, String ISBN, String status, String fileFormat){
        super(title, author, genre, ISBN, status);
        this.fileFormat = fileFormat;
    }
    
    public EBook(String data[]){
        super(data);
        this.fileFormat = data[8];
    }

    public String getFileFormat(){
        return fileFormat;
    }
    
    public void setFileFormat(String fileFormat){
        this.fileFormat = fileFormat;
    }
    
    @Override
    public String toString(){
        return ("E," + title + "," + author + "," + genre + "," + ISBN + "," + status + ",-,-," + fileFormat);
    }

    @Override
    public void displayBookInfor(){
       System.out.print("E-book: ");
       super.displayBookInfor();
       System.out.println("File format: " + fileFormat);
       System.out.println("State: " + status);
    }
}