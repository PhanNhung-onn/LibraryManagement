package util;

import java.io.*;
import java.util.*;
import model.*;

public class CSVhandler {

    public static void readBookCSV(HashMap<String, ArrayList<Book>> map, String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            reader.readLine(); // bỏ qua dòng tiêu đề

            while ((line = reader.readLine()) != null) {
                
                String data[] = line.split(",");
                if(data.length == 1){
                    continue;
                }
                map.computeIfAbsent(data[1], k -> new ArrayList<>());

                if ("P".equals(data[0])) {
                    map.get(data[1]).add(new PrintedBook(data));
                }else if ("E".equals(data[0])) {
                    map.get(data[1]).add(new EBook(data));
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file sách: " + e.getMessage());
        }
    }

    public static void readUserCSV(HashMap<String, User> map, String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            reader.readLine(); // bỏ qua dòng tiêu đề

            while ((line = reader.readLine()) != null) {
                String data[] = line.split(",");

                if (!map.containsKey(data[0])) {
                    User user = null;
                    switch (data[2]) {
                        case "admin" -> user = new Admin(data[0], data[1], data[3]);
                        case "librarian" -> user = new Librarian(data[0], data[1], data[3]);
                        case "reader" -> user = new model.Reader(data[0], data[1], data[3]);
                    }
                    if (user != null) {
                        map.put(data[0], user);
                    }
                } else {
                    System.out.println("Người dùng đã tồn tại:");
                    map.get(data[0]).displayUserInfor();
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file người dùng: " + e.getMessage());
        }
    }


    public static void readTransactionCSV(HashMap<String, Transaction> map, String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            reader.readLine(); // bỏ qua dòng tiêu đề

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String id = data[0] + "-" + data[3];

                if (!map.containsKey(id)) {
                    map.put(id, new Transaction(data));
                } else {
                    System.out.println("Giao dịch đã tồn tại:");
                    map.get(id).displayTransaction();
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file giao dịch: " + e.getMessage());
        }
    }
    
    public static void writeCSV(HashMap<String, ?> map, String filename, String header){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            // Ghi dòng tiêu đề trước
            writer.write(header);
            writer.newLine();

            // Ghi từng dòng dữ liệu
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                writer.write(entry.getValue().toString());
                writer.newLine();
            }
        }catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }
    
    public static void appendCSV(Object value, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            System.out.println("📄 Đang ghi dòng: " + value.toString());
            writer.write(value.toString());
            System.out.println("✔ Ghi thành công vào file: " + new java.io.File(filename).getAbsolutePath());
            writer.newLine();
        }catch (IOException e) {
            System.out.println("Lỗi khi ghi thêm dòng: " + e.getMessage());
        }
    }
}
