/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.GUI;

import entities.DtTransactionTypes;
import Objects.movementType;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Juan David
 */
public class TransactionTypeGUIImpl extends JFrame implements TransactionTypeGUI {

    private static final String[] TABLE_COLUMNS = {"Type ID", "Type Name", "Movement Type"};

    private final JButton addButton;
    private final JButton viewTypeButton;
    private final JButton updateButton;
    private final JButton deleteButton;

    private final JPanel inputPanel;
    private final JPanel buttonPanel;

    private final JLabel transactionTypeIdLabel;
    private final JLabel transactionTypeIdSelectedLabel;
    private final JLabel transactionTypeDescrLabel;
    private final JLabel transactionTypeMovementLabel;

    private final JTextField transactionTypeDescrTextField;

    private final JTable transactionsTypeTable;

    private final JComboBox transactionTypeMovementComboBox;

    public TransactionTypeGUIImpl(ActionListener actionListener, ListSelectionListener listSelectionListener) {
        super("Transaction Types");

        // create buttons
        this.viewTypeButton = new JButton("ViewTypes");
        this.addButton = new JButton("Add");
        this.updateButton = new JButton("Update");
        this.deleteButton = new JButton("Delete");

        // create container
        Container container = this.getContentPane();

        // create labels
        this.transactionTypeIdLabel = new JLabel("Type ID:");
        this.transactionTypeIdSelectedLabel = new JLabel("");
        this.transactionTypeDescrLabel = new JLabel("Transaction Type Name:");
        this.transactionTypeMovementLabel = new JLabel("Movement Type (Credit/Debit):");

        // create text fields
        this.transactionTypeDescrTextField = new JTextField();

        // create table
        this.transactionsTypeTable = new JTable(new DefaultTableModel(TABLE_COLUMNS, 0));
        this.transactionsTypeTable.getSelectionModel().addListSelectionListener(listSelectionListener);
        this.transactionsTypeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        TableColumnModel transactionsTypeTableColumnModel = this.transactionsTypeTable.getColumnModel();
        transactionsTypeTableColumnModel.getColumn(0).setPreferredWidth(100);
        transactionsTypeTableColumnModel.getColumn(1).setPreferredWidth(150);
        transactionsTypeTableColumnModel.getColumn(2).setPreferredWidth(100);

        //create combobox
        this.transactionTypeMovementComboBox = new JComboBox();

        // create panels
        this.inputPanel = new JPanel();
        this.buttonPanel = new JPanel();

        // set layout manager
        container.setLayout(new BorderLayout());
        this.inputPanel.setLayout(new GridLayout(3, 2));
        this.buttonPanel.setLayout(new GridLayout(1, 4));

        // add action listeners   
        this.addButton.addActionListener(actionListener);
        this.viewTypeButton.addActionListener(actionListener);
        this.updateButton.addActionListener(actionListener);
        this.deleteButton.addActionListener(actionListener);

        // add components
        this.inputPanel.add(transactionTypeIdLabel);
        this.inputPanel.add(transactionTypeIdSelectedLabel);

        this.inputPanel.add(this.transactionTypeDescrLabel);
        this.inputPanel.add(this.transactionTypeDescrTextField);

        this.inputPanel.add(this.transactionTypeMovementLabel);
        this.inputPanel.add(this.transactionTypeMovementComboBox);

        // add buttons to panel
        this.buttonPanel.add(this.addButton);
        this.buttonPanel.add(this.viewTypeButton);
        this.buttonPanel.add(this.updateButton);
        this.buttonPanel.add(this.deleteButton);

        // add panels to content pane
        container.add(inputPanel, BorderLayout.NORTH);
        container.add(new JScrollPane(this.transactionsTypeTable), BorderLayout.CENTER);
        container.add(buttonPanel, BorderLayout.SOUTH);

        this.setSize(450, 450);
        this.setVisible(true);
    }

    @Override
    public void fillTransactionsTypesTable(List<DtTransactionTypes> types) {
        this.clearTable();
        this.clearTextFields();

        for (DtTransactionTypes DtTransactionsTypesIn : types) {
            ((DefaultTableModel) this.transactionsTypeTable.getModel()).addRow(new Object[]{DtTransactionsTypesIn.getTrtypeId(),
                DtTransactionsTypesIn.getTypeName(),
                DtTransactionsTypesIn.getMovementTypeId()});
        }
    }

    @Override
    public void fillMovementTypes() {
        movementType MovCredit = new movementType();
        movementType MovDebit = new movementType();
         
        MovCredit.setMovType("C");
        MovCredit.setMovDescr("Credit");
        MovDebit.setMovType("D");
        MovDebit.setMovDescr("Debit");
    
        this.transactionTypeMovementComboBox.addItem("");
        this.transactionTypeMovementComboBox.addItem(MovCredit);
        this.transactionTypeMovementComboBox.addItem(MovDebit);
    }

    @Override
    public int getSelectedTypeId() {
        int selectedRowIndex = this.transactionsTypeTable.getSelectedRow();

        String transactionId = this.transactionsTypeTable.getValueAt(selectedRowIndex, 0).toString();
        return Integer.parseInt(transactionId);
    }

    public boolean isTypeSelected() {
        return (this.transactionsTypeTable.getSelectedRow() >= 0);
    }

    @Override
    public void displaySelectedTypeDetails(DtTransactionTypes type) {
        this.transactionTypeIdSelectedLabel.setText(type.getTrtypeId().toString());
        this.transactionTypeDescrTextField.setText(type.getTypeName());
        if (type.getMovementTypeId().equals("C")) {
            transactionTypeMovementComboBox.setSelectedIndex(1);
        } else {
            transactionTypeMovementComboBox.setSelectedIndex(2);
        }
    }

    @Override
    public DtTransactionTypes getCurrentTransactionTypeDetails() {
        DtTransactionTypes NewType = new DtTransactionTypes();
        boolean error = false;

        if (transactionTypeIdSelectedLabel.getText().isEmpty()) {
            this.displayMessageInDialog("Select a transaction to update.");
            error = true;
        } else if (transactionTypeDescrTextField.getText().isEmpty()) {
            this.displayMessageInDialog("Type Description is required.");
            error = true;
        } else if (transactionTypeMovementComboBox.getSelectedIndex() == 0) {
            displayMessageInDialog("Transaction type required.");
            error = true;
        }

        if (!error) {
            try {
                NewType.setTrtypeId(Short.parseShort(transactionTypeIdSelectedLabel.getText()));
                NewType.setTypeName(transactionTypeDescrTextField.getText());
                if (transactionTypeMovementComboBox.getSelectedItem().toString().equals("Credit")) {
                    NewType.setMovementTypeId("C");
                } else {
                    NewType.setMovementTypeId("D");
                }
            } catch (Exception e) {
                displayMessageInDialog("Failed to create the Transaction Object: " + e.getMessage());
            }
        }

        return NewType;
    }

    @Override
    public DtTransactionTypes getNewTransactionTypeDetails() {
        DtTransactionTypes NewType = new DtTransactionTypes();
        boolean error = false;

        if (transactionTypeDescrTextField.getText().isEmpty()) {
            this.displayMessageInDialog("Type Description is required.");
            error = true;
        } else if (transactionTypeMovementComboBox.getSelectedIndex() == 0) {
            displayMessageInDialog("Transaction type required.");
            error = true;
        }

        if (!error) {
            try {
                NewType.setTypeName(transactionTypeDescrTextField.getText());
                if (transactionTypeMovementComboBox.getSelectedItem().toString().equals("Credit")) {
                    NewType.setMovementTypeId("C");
                } else {
                    NewType.setMovementTypeId("D");
                }
            } catch (Exception e) {
                displayMessageInDialog("Failed to create the Transaction Object: " + e.getMessage());
            }
        }

        return NewType;
    }

    @Override
    public void clearTable() {
        int numberOfRow = this.transactionsTypeTable.getModel().getRowCount();

        if (numberOfRow > 0) {
            DefaultTableModel tableModel = (DefaultTableModel) this.transactionsTypeTable.getModel();
            for (int index = (numberOfRow - 1); index >= 0; index--) {
                tableModel.removeRow(index);
            }
        }
    }

    @Override
    public void clearTextFields() {
        this.transactionTypeDescrTextField.setText("");
        this.transactionTypeIdSelectedLabel.setText("");
        if (this.transactionTypeMovementComboBox.getItemCount() > 0) {
            this.transactionTypeMovementComboBox.setSelectedIndex(0);
        }
    }

    @Override
    public void displayMessageInDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public JButton getViewTypeButton() {
        return viewTypeButton;
    }

    @Override
    public JTable getTypeTable() {
        return transactionsTypeTable;
    }

    @Override
    public JButton getAddButton() {
        return addButton;
    }

    @Override
    public JButton getUpdateButton() {
        return updateButton;
    }
    
    @Override
    public JButton getDeleteButton() {
        return deleteButton;
    }
}
