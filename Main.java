import model.Librarian;

public class Main {
    public static void main(String[] args) {
        // Khởi tạo thủ thư
        Librarian librarian = new Librarian("L001", "Marie", "password123");
        librarian.displayUserInfor();
        System.out.println();
        // Thêm sách in
        System.out.println("➤ Thêm sách in:");
        librarian.addBook("Pride and Prejudice", "Jane Austen", "Classic", "9781234567890", "Available", 300);
        
        // Thêm sách điện tử
        System.out.println("➤ Thêm sách điện tử:");
        librarian.addBook("Digital Fortress", "Dan Brown", "Thriller", "9780987654321", "Available", "PDF");
        
        // Liệt kê tất cả sách
        System.out.println("➤ Danh sách sách:");
        librarian.listBook();
        
        // Tìm sách theo ID
        System.out.println("➤ Tìm sách theo ID (Pride and Prejudice-9781234567890)");
        librarian.findBookById("Digital Fortress-9780987654321");
        
        // Tìm sách theo tiêu đề
        System.out.println("➤ Tìm sách theo tiêu đề (Pride and Prejudice):");
        librarian.findBooksByTitle("Digital Fortress");
        
        // Xoá sách
        System.out.println("➤ Xoá sách với ID Pride and Prejudice-9781234567890");
        librarian.removeBook("Digital Fortress-9780987654321");
        
        // Kiểm tra lại danh sách sách sau khi xoá
        System.out.println("➤ Danh sách sách sau khi xoá:");
        librarian.listBook();
    }
}
