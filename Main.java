import javax.swing.*;                  
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;             
import java.util.HashMap; 


public class Main{
    public static void main(String[] args) {
        JFrame f = new JFrame(); // tạo JFrame

        // Label MSSV
        JLabel userNameLabel = new JLabel("UserName:");
        userNameLabel.setBounds(65, 20, 70, 25);
        f.add(userNameLabel);

        // Ô nhập MSSV
        JTextField nameField = new JTextField();
        nameField.setBounds(130, 20, 140, 30);
        f.add(nameField);

        // Label Mật khẩu
        JLabel RoleLabel = new JLabel("Role:");
        RoleLabel.setBounds(65, 60, 70, 25);
        f.add(RoleLabel);

        // Ô nhập mật khẩu
        JTextField RoleField = new JTextField();
        RoleField.setBounds(130, 60, 140, 30);
        f.add(RoleField);

        // Nút đăng nhập
        JButton b = new JButton("Login");
        b.setBounds(130, 110, 90, 40);
        f.add(b);

        // Xử lý sự kiện khi bấm nút
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = nameField.getText();
                String role = RoleField.getText();
                // Kiểm tra
                if (!userName.isEmpty() || !role.isEmpty()) {
                    if(role.equals("Admin")){
                        f.dispose();
                        openAdminMenu(userName);
                    } else if(role.equals("Reader")){
                        f.dispose();
                        openReader(userName);
                    } else if(role.equals("Librarian")){
                        f.dispose();
                        openLibrarian(userName);
                    }
                } else {
                    JOptionPane.showMessageDialog(f, "Vui lòng nhập đầy đủ MSSV và mật khẩu!");
                }
            }
        });

        // Cài đặt JFrame
        f.setTitle("CTU Library");
        f.setSize(400, 220);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    // Giao diện chính sau đăng nhập

    //ADMIN------------------------------------------------------------------------
    public static void openAdminMenu(String useruser) {
        JFrame adminMenu = new JFrame("Admin Menu");
        adminMenu.setSize(650, 500);
        adminMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminMenu.setLayout(null);
    
        JLabel welcomeLabel = new JLabel("Welcome Admin: " + useruser);
        welcomeLabel.setBounds(20, 20, 300, 25);
        adminMenu.add(welcomeLabel);
    
        JButton b1 = new JButton("1. Xem danh sách sách");
        b1.setBounds(150, 60, 300, 27);
        adminMenu.add(b1);
    
        JButton b2 = new JButton("2. Thêm sách mới");
        b2.setBounds(150, 100, 300, 27);
        adminMenu.add(b2);
    
        JButton b3 = new JButton("3. Xóa sách");
        b3.setBounds(150, 140, 300, 27);
        adminMenu.add(b3);
    
        JButton b4 = new JButton("4. Thêm người dùng");
        b4.setBounds(150, 180, 300, 27);
        adminMenu.add(b4);
    
        JButton b5 = new JButton("5. Xóa người dùng");
        b5.setBounds(150, 220, 300, 27);
        adminMenu.add(b5);
    
        JButton b6 = new JButton("6. Xem danh sách người dùng");
        b6.setBounds(150, 260, 300, 27);
        adminMenu.add(b6);
    
        JButton logoutBtn = new JButton("Đăng xuất");
        logoutBtn.setBounds(150, 300, 300, 27);
        adminMenu.add(logoutBtn);
    
        // 1. Xem danh sách sách
        b1.addActionListener(e -> {
            BookManagement bookManagement = new BookManagement();
            bookManagement.listBooks();
            JOptionPane.showMessageDialog(adminMenu, "Danh sách sách đã được hiển thị trên console!");
        });
    
        // 2. Thêm sách mới
        b2.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(adminMenu, "Nhập tên sách:");
            String author = JOptionPane.showInputDialog(adminMenu, "Nhập tác giả:");
            String genre = JOptionPane.showInputDialog(adminMenu, "Nhập thể loại:");
            String ISBN = JOptionPane.showInputDialog(adminMenu, "Nhập ISBN:");
            String status = JOptionPane.showInputDialog(adminMenu, "Nhập trạng thái:");
            String fileFormat = JOptionPane.showInputDialog(adminMenu, "Nhập định dạng file (cho EBook):");
    
            EBook newBook = new EBook(title, author, genre, ISBN, status, fileFormat);
            BookManagement bookManagement = new BookManagement();
            bookManagement.addBook(newBook);
    
            JOptionPane.showMessageDialog(adminMenu, "Sách đã được thêm thành công!");
        });
    
        // 3. Xóa sách
        b3.addActionListener(e -> {
            String ISBN = JOptionPane.showInputDialog(adminMenu, "Nhập ISBN của sách cần xóa:");
            BookManagement bookManagement = new BookManagement();
            Book bookToRemove = bookManagement.findBookById("SomeTitle-" + ISBN); 
            if (bookToRemove != null) {
                bookManagement.removeBook(bookToRemove);
                JOptionPane.showMessageDialog(adminMenu, "Sách đã được xóa!");
            } else {
                JOptionPane.showMessageDialog(adminMenu, "Không tìm thấy sách với ISBN đó.");
            }
        });
    
        // 4. Thêm người dùng
        b4.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(adminMenu, "Nhập ID người dùng:");
            String name = JOptionPane.showInputDialog(adminMenu, "Nhập tên người dùng:");
            String role = JOptionPane.showInputDialog(adminMenu, "Nhập vai trò (Admin/Reader/Librarian):");
            String password = JOptionPane.showInputDialog(adminMenu, "Nhập mật khẩu:");
    
            User newUser = null;
            switch (role.toLowerCase()) {
                case "admin":
                    newUser = new Admin(id, name, password);
                    break;
                case "reader":
                    newUser = new Reader(id, name, password);
                    break;
                case "librarian":
                    newUser = new Librarian(id, name, password);
                    break;
                default:
                    JOptionPane.showMessageDialog(adminMenu, "Vai trò không hợp lệ!");
                    return;
            }
    
            UserManagement userMng = new UserManagement();
            userMng.addUser(newUser);
            JOptionPane.showMessageDialog(adminMenu, "Người dùng đã được thêm!");
        });
    
        // 5. Xóa người dùng
        b5.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(adminMenu, "Nhập ID người dùng cần xóa:");
            UserManagement userMng = new UserManagement();
            User user = userMng.findUserById(id);
            if (user != null) {
                userMng.removeUser(user);
                JOptionPane.showMessageDialog(adminMenu, "Người dùng đã được xóa!");
            } else {
                JOptionPane.showMessageDialog(adminMenu, "Không tìm thấy người dùng với ID này.");
            }
        });
    
        // 6. Xem danh sách người dùng
        b6.addActionListener(e -> {
            UserManagement userMng = new UserManagement();
            userMng.listUsers();
            JOptionPane.showMessageDialog(adminMenu, "Danh sách người dùng đã được hiển thị trên console.");
        });
    
        // Đăng xuất
        logoutBtn.addActionListener(e -> {
            adminMenu.dispose();
            main(null); // trở về màn hình đăng nhập
        });
    
        adminMenu.setVisible(true);
    }
    
    

//READER-----------------------------------------------------------------
    // Trong phương thức openReader
public static void openReader(String user) {
    JFrame mainReader = new JFrame("Reader Menu");
    mainReader.setSize(600, 400);
    mainReader.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    mainReader.setLayout(null);

    JLabel welcomeLabel = new JLabel("Welcome Reader: " + user);
    welcomeLabel.setBounds(20, 20, 300, 25);
    mainReader.add(welcomeLabel);

    JButton viewBooksBtn = new JButton("1. Xem danh sách sách");
    viewBooksBtn.setBounds(150, 50, 300, 27);
    viewBooksBtn.setHorizontalAlignment(SwingConstants.LEFT);
    mainReader.add(viewBooksBtn);

    JButton b2 = new JButton("2. Mượn sách");
    b2.setBounds(150, 90, 300, 27);
    b2.setHorizontalAlignment(SwingConstants.LEFT);
    mainReader.add(b2);

    JButton b3 = new JButton("3. Trả sách");
    b3.setBounds(150, 130, 300, 27);
    b3.setHorizontalAlignment(SwingConstants.LEFT);
    mainReader.add(b3);

    JButton searchBookBtn = new JButton("4. Tìm sách theo tên");
    searchBookBtn.setBounds(150, 170, 300, 27);
    searchBookBtn.setHorizontalAlignment(SwingConstants.LEFT);
    mainReader.add(searchBookBtn);

    searchBookBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String title = JOptionPane.showInputDialog(mainReader, "Nhập tên sách cần tìm:");
            BookManagement bm = new BookManagement();
            ArrayList<Book> result = bm.findBooksByTitle(title);
            if (result != null && !result.isEmpty()) {
                StringBuilder sb = new StringBuilder("Tìm thấy sách:\n");
                for (Book book : result) {
                    sb.append(book.getTitle()).append(" - ").append(book.getAuthor()).append("\n");
                }
                JOptionPane.showMessageDialog(mainReader, sb.toString());
            } else {
                JOptionPane.showMessageDialog(mainReader, "Không tìm thấy sách nào.");
            }
        }
    });
    viewBooksBtn.addActionListener(e -> {
        BookManagement bookManagement = new BookManagement();
        bookManagement.listBooks();
        JOptionPane.showMessageDialog(mainReader, "Danh sách sách đã được hiển thị trên console!");
    });

    b2.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String bookTitle = JOptionPane.showInputDialog(mainReader, "Nhập tên sách bạn muốn mượn:");
            String ISBN = JOptionPane.showInputDialog(mainReader, "Nhập ISBN của sách:");

            BookManagement bookManagement = new BookManagement();
            Book bookToBorrow = bookManagement.findBookById(bookTitle + "-" + ISBN);
            if (bookToBorrow != null && bookToBorrow.getStatus().equals("available")) {
                bookToBorrow.setStatus("borrowed");
                Date now = new Date();
                Transaction transaction = new Transaction("Reader1", (PrintedBook) bookToBorrow, "borrow", now);
                TransManagement transManagement = new TransManagement();
                transManagement.addTransaction(transaction);
                JOptionPane.showMessageDialog(mainReader, "Bạn đã mượn sách thành công!");
            } else {
                JOptionPane.showMessageDialog(mainReader, "Sách không có sẵn để mượn.");
            }
        }
    });

    b3.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String bookTitle = JOptionPane.showInputDialog(mainReader, "Nhập tên sách bạn muốn trả:");
            String ISBN = JOptionPane.showInputDialog(mainReader, "Nhập ISBN của sách:");

            BookManagement bookManagement = new BookManagement();
            Book bookToReturn = bookManagement.findBookById(bookTitle + "-" + ISBN);
            if (bookToReturn != null && bookToReturn.getStatus().equals("borrowed")) {
                bookToReturn.setStatus("available");
                Date now = new Date();
                Transaction transaction = new Transaction("Reader1", (PrintedBook) bookToReturn, "return", now);
                TransManagement transManagement = new TransManagement();
                transManagement.addTransaction(transaction);
                JOptionPane.showMessageDialog(mainReader, "Bạn đã trả sách thành công!");
            } else {
                JOptionPane.showMessageDialog(mainReader, "Sách không có sẵn để trả.");
            }
        }
    });

    JButton logoutBtn = new JButton("5. Đăng xuất");
    logoutBtn.setBounds(150, 210, 300, 27);
    logoutBtn.setHorizontalAlignment(SwingConstants.LEFT);
    mainReader.add(logoutBtn);

    logoutBtn.addActionListener(e -> {
        mainReader.dispose();
        main(null);
    });

    mainReader.setVisible(true);
}




//LIBRARIAN------------------------------------------------------------------------------
    public static void openLibrarian(String user){
    JFrame librarianMenu = new JFrame("Librarian Menu");
    librarianMenu.setSize(600, 400);
    librarianMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    librarianMenu.setLayout(null);

    JLabel welcomeLabel = new JLabel("Welcome Librarian: " + user);
    welcomeLabel.setBounds(20, 20, 300, 25);
    librarianMenu.add(welcomeLabel);

    JButton viewBooksBtn = new JButton("1. Xem danh sách sách");
    viewBooksBtn.setBounds(150, 80, 300, 27);
    librarianMenu.add(viewBooksBtn);

    JButton viewTransactionsBtn = new JButton("2. Xem danh sách mượn");
    viewTransactionsBtn.setBounds(150, 120, 300, 27);
    librarianMenu.add(viewTransactionsBtn);

    JButton searchBookBtn = new JButton("3. Tìm sách theo tên");
    searchBookBtn.setBounds(150, 160, 300, 27);
    librarianMenu.add(searchBookBtn);

    viewBooksBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            BookManagement bm = new BookManagement();
            bm.listBooks();
            JOptionPane.showMessageDialog(librarianMenu, "Danh sách sách đã hiển thị trong console.");
        }
    });

    viewTransactionsBtn.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    sb.append(line).append("\n");
                    sb.append("------------------------------------------------------------\n");
                    firstLine = false;
                } else {
                    sb.append(line).append("\n");
                }
            }

            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            

            JOptionPane.showMessageDialog(librarianMenu, scrollPane, "Danh sách giao dịch", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(librarianMenu, "Không thể đọc file transactions.csv", "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
});
    

    searchBookBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String title = JOptionPane.showInputDialog(librarianMenu, "Nhập tên sách cần tìm:");
            BookManagement bm = new BookManagement();
            ArrayList<Book> result = bm.findBooksByTitle(title);
            if (result != null) {
                JOptionPane.showMessageDialog(librarianMenu, "Tìm thấy sách: " + result);
            } else {
                JOptionPane.showMessageDialog(librarianMenu, "Không tìm thấy sách nào.");
            }
        }
    });
    JButton addBookBtn = new JButton("4. Thêm sách mới");
    addBookBtn.setBounds(150, 200, 300, 27);
    librarianMenu.add(addBookBtn);
    
    JButton deleteBookBtn = new JButton("5. Xóa sách");
    deleteBookBtn.setBounds(150, 240, 300, 27);
    librarianMenu.add(deleteBookBtn);
    
    addBookBtn.addActionListener(e -> {
        String title = JOptionPane.showInputDialog(librarianMenu, "Nhập tên sách:");
        String author = JOptionPane.showInputDialog(librarianMenu, "Nhập tác giả:");
        String genre = JOptionPane.showInputDialog(librarianMenu, "Nhập thể loại:");
        String ISBN = JOptionPane.showInputDialog(librarianMenu, "Nhập ISBN:");
        String status = JOptionPane.showInputDialog(librarianMenu, "Nhập trạng thái:");
        String fileFormat = JOptionPane.showInputDialog(librarianMenu, "Nhập định dạng file (cho EBook):");
    
        EBook newBook = new EBook(title, author, genre, ISBN, status, fileFormat);
        BookManagement bookManagement = new BookManagement();
        bookManagement.addBook(newBook);
    
        JOptionPane.showMessageDialog(librarianMenu, "Sách đã được thêm thành công!");
    });
    
    deleteBookBtn.addActionListener(e -> {
        String ISBN = JOptionPane.showInputDialog(librarianMenu, "Nhập ISBN của sách cần xóa:");
        BookManagement bookManagement = new BookManagement();
        Book bookToRemove = bookManagement.findBookById("SomeTitle-" + ISBN);
        if (bookToRemove != null) {
            bookManagement.removeBook(bookToRemove);
            JOptionPane.showMessageDialog(librarianMenu, "Sách đã được xóa!");
        } else {
            JOptionPane.showMessageDialog(librarianMenu, "Không tìm thấy sách với ISBN đó.");
        }
    });
    
    JButton logoutBtn = new JButton("Đăng xuất");
    logoutBtn.setBounds(150, 280, 300, 27);
    librarianMenu.add(logoutBtn);
    
    logoutBtn.addActionListener(e -> {
        librarianMenu.dispose();
        main(null);
    });
    librarianMenu.setVisible(true);
    }}


