/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.GUI;

import Objects.PersonFilter;
import entities.DtAccounts;
import entities.DtTransactions;
import entities.DtTransactionTypes;
import java.util.List;
import javax.swing.JButton;

/**
 *
 * @author Juan David
 */
public interface TransactionGUI {
    
    void fillTransactionsTable(List<DtTransactions> transactions);
    
    void fillTransactionTypes(List<DtTransactionTypes> types);
    
    void fillAcctNumbers(List<DtAccounts> Acctypes);
    
    DtTransactions getNewTransactionDetails();
    
    List<PersonFilter> getSearchParameters();
    
    void clearTextFields();
      
    void clearTable();
    
    void displayMessageInDialog(String message);
    
    JButton getViewButton();
    
    JButton getAddButton();
    
    JButton getSearchButton();
}
