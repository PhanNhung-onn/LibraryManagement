package controller;
import java.util.HashMap;
import util.*;
import model.Transaction;

public class TransManagement{
    private HashMap<String, Transaction> transactions;
    
    public TransManagement(){
        this.transactions = new HashMap<String, Transaction>();
        CSVhandler.readTransactionCSV(transactions,"transactions.csv");
    }

    public void addTransaction(Transaction t){
        String id = t.getId();
        if(!(transactions.containsKey(id))){
            transactions.put(id, t);
            CSVhandler.appendCSV(t,"transactions.csv");
        }
    }
    
    public void removeTransaction(Transaction t){
        String id = t.getId();
        
        if(transactions.containsKey(id)){
            transactions.remove(id);
            
            String header = "Reader ID,Book ID,Action,Time";
            CSVhandler.writeCSV(transactions,"transactions.csv",header);
        }
    }

    public void listTransactions(){
        for(Transaction t : transactions.values()){
            t.displayTransaction();
        }
    }
    
    public Transaction findTransactionById(String id){
        return transactions.get(id);
    }
}