/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.GUI;

import entities.DtTransactionTypes;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;

/**
 *
 * @author Juan David
 */
public interface TransactionTypeGUI {
    
    void fillTransactionsTypesTable(List<DtTransactionTypes> types);
    
    void fillMovementTypes();
    
    void clearTable();
    
    void clearTextFields();
    
    int getSelectedTypeId();
    
    void displaySelectedTypeDetails(DtTransactionTypes type);
    
    boolean isTypeSelected();
    
    DtTransactionTypes getNewTransactionTypeDetails();
    
    DtTransactionTypes getCurrentTransactionTypeDetails();
    
    void displayMessageInDialog(String message);
    
    JButton getViewTypeButton();
    
    JButton getAddButton();
    
    JButton getUpdateButton();
    
    JButton getDeleteButton();
    
    JTable getTypeTable();
    
}
