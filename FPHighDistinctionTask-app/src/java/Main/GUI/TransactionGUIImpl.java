/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.GUI;

import entities.DtAccounts;
import entities.DtTransactionTypes;
import entities.DtTransactions;
import entities.DtUsers;
import Objects.PersonFilter;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
public class TransactionGUIImpl extends JFrame implements TransactionGUI {

    private static final String[] TABLE_COLUMNS = {"ID", "Trans Name", "Trans. Type", "Acct. No", "Location", "Amount", "DateTime"};

    private final JButton addButton;
    private final JButton viewButton;
    private final JButton searchButton;

    private final JPanel inputPanel;
    private final JPanel buttonPanel;

    private final JLabel transactionIdLabel;
    private final JLabel transactionNameLabel;
    private final JLabel transactionTypeLabel;
    private final JLabel accountNumberLabel;
    private final JLabel locationLabel;
    private final JLabel amountLabel;
    private final JLabel transactionDescrLabel;

    private final JTextField transactionIdTextField;
    private final JTextField transactionNameTextField;
    private final JTextField locationTextField;
    private final JTextField amountTextField;
    private final JTextField transactionDescrTextField;

    private final JTable transactionsTable;

    private final JComboBox transactionTypeComboBox;
    private final JComboBox AccountNumbersComboBox;

    public TransactionGUIImpl(ActionListener actionListener, ListSelectionListener listSelectionListener) {
        super("Transaction Manager Withdrawal/Deposits");

        // create buttons
        this.viewButton = new JButton("View");
        this.searchButton = new JButton("Search");
        this.addButton = new JButton("Add");

        // create container
        Container container = this.getContentPane();

        // create labels
        this.transactionIdLabel = new JLabel("Transaction ID:");
        this.transactionNameLabel = new JLabel("Transaction Name:");
        this.transactionTypeLabel = new JLabel("Transaction Type:");
        this.accountNumberLabel = new JLabel("Account Number:");
        this.locationLabel = new JLabel("Location:");
        this.amountLabel = new JLabel("Amount:");
        this.transactionDescrLabel = new JLabel("Transaction Description:");

        // create text fields
        this.transactionIdTextField = new JTextField();
        this.transactionNameTextField = new JTextField();
        this.locationTextField = new JTextField();
        this.amountTextField = new JTextField();
        this.transactionDescrTextField = new JTextField();

        // create table
        this.transactionsTable = new JTable(new DefaultTableModel(TABLE_COLUMNS, 0));
        this.transactionsTable.getSelectionModel().addListSelectionListener(listSelectionListener);
        this.transactionsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        TableColumnModel propertyTableColumnModel = this.transactionsTable.getColumnModel();
        propertyTableColumnModel.getColumn(0).setPreferredWidth(50);
        propertyTableColumnModel.getColumn(1).setPreferredWidth(120);
        propertyTableColumnModel.getColumn(2).setPreferredWidth(100);
        propertyTableColumnModel.getColumn(3).setPreferredWidth(100);
        propertyTableColumnModel.getColumn(4).setPreferredWidth(150);
        propertyTableColumnModel.getColumn(5).setPreferredWidth(100);
        propertyTableColumnModel.getColumn(6).setPreferredWidth(200);

        //create combobox
        this.transactionTypeComboBox = new JComboBox();
        this.AccountNumbersComboBox = new JComboBox();

        // create panels
        this.inputPanel = new JPanel();
        this.buttonPanel = new JPanel();

        // set layout manager
        container.setLayout(new BorderLayout());
        this.inputPanel.setLayout(new GridLayout(7, 2));
        this.buttonPanel.setLayout(new GridLayout(1, 4));

        // add action listeners
        this.addButton.addActionListener(actionListener);
        this.viewButton.addActionListener(actionListener);
        this.searchButton.addActionListener(actionListener);

        // add components
        this.inputPanel.add(this.transactionIdLabel);
        this.inputPanel.add(this.transactionIdTextField);

        this.inputPanel.add(this.transactionNameLabel);
        this.inputPanel.add(this.transactionNameTextField);

        this.inputPanel.add(this.transactionTypeLabel);
        this.inputPanel.add(this.transactionTypeComboBox);

        this.inputPanel.add(this.accountNumberLabel);
        this.inputPanel.add(this.AccountNumbersComboBox);

        this.inputPanel.add(this.locationLabel);
        this.inputPanel.add(this.locationTextField);

        this.inputPanel.add(this.amountLabel);
        this.inputPanel.add(this.amountTextField);

        this.inputPanel.add(this.transactionDescrLabel);
        this.inputPanel.add(this.transactionDescrTextField);

        // add buttons to panel
        this.buttonPanel.add(this.addButton);
        this.buttonPanel.add(this.searchButton);
        this.buttonPanel.add(this.viewButton);

        // add panels to content pane
        container.add(inputPanel, BorderLayout.NORTH);
        container.add(new JScrollPane(this.transactionsTable), BorderLayout.CENTER);
        container.add(buttonPanel, BorderLayout.SOUTH);

        this.setSize(850, 550);
        this.setVisible(true);
    }

    @Override
    public void fillTransactionsTable(List<DtTransactions> transactions) {
        this.clearTable();
        this.clearTextFields();

        for (DtTransactions DtTransactionsIn : transactions) {
            ((DefaultTableModel) this.transactionsTable.getModel()).addRow(new Object[]{DtTransactionsIn.getTransactionId(),
                DtTransactionsIn.getTransactionName(),
                DtTransactionsIn.getTrtypeId().getTypeName(),
                DtTransactionsIn.getAccountNumber().getAccountNumber(),
                DtTransactionsIn.getLocation(),
                DtTransactionsIn.getAmount(),
                DtTransactionsIn.getDatetimeTrans()});
        }
    }

    @Override
    public void fillTransactionTypes(List<DtTransactionTypes> types) {
        this.transactionTypeComboBox.removeAllItems();

        this.transactionTypeComboBox.addItem("");

        for (DtTransactionTypes DtTransactionTypesIn : types) {
            this.transactionTypeComboBox.addItem(DtTransactionTypesIn);
        }
    }

    @Override
    public void fillAcctNumbers(List<DtAccounts> AcctNos) {
        this.AccountNumbersComboBox.removeAllItems();

        this.AccountNumbersComboBox.addItem("");

        for (DtAccounts DtAccountsIn : AcctNos) {
            this.AccountNumbersComboBox.addItem(DtAccountsIn);
        }
    }

    public void clearTable() {
        int numberOfRow = this.transactionsTable.getModel().getRowCount();

        if (numberOfRow > 0) {
            DefaultTableModel tableModel = (DefaultTableModel) this.transactionsTable.getModel();
            for (int index = (numberOfRow - 1); index >= 0; index--) {
                tableModel.removeRow(index);
            }
        }
    }

    @Override
    public void clearTextFields() {
        this.transactionIdTextField.setText("");
        this.transactionNameTextField.setText("");
        this.locationTextField.setText("");
        this.amountTextField.setText("");
        this.transactionDescrTextField.setText("");
        if (this.transactionTypeComboBox.getItemCount() > 0) {
            this.transactionTypeComboBox.setSelectedIndex(0);
        }
        if (this.AccountNumbersComboBox.getItemCount() > 0) {
            this.AccountNumbersComboBox.setSelectedIndex(0);
        }
    }

    @Override
    public DtTransactions getNewTransactionDetails() {
        DtTransactions NewTransaction = new DtTransactions();
        boolean error = false;

        if (transactionNameTextField.getText().isEmpty()) {
            displayMessageInDialog("Transaction Name is required.");
            error = true;
        } else if (transactionTypeComboBox.getSelectedIndex() == 0) {
            displayMessageInDialog("Transaction type required.");
            error = true;
        } else if (AccountNumbersComboBox.getSelectedIndex() == 0) {
            displayMessageInDialog("Transaction type required.");
            error = true;
        } else if (locationTextField.getText().isEmpty()) {
            displayMessageInDialog("Location is required.");
            error = true;
        } else if (amountTextField.getText().isEmpty()) {
            displayMessageInDialog("Amount is required.");
            error = true;
        } else if (transactionDescrTextField.getText().isEmpty()) {
            displayMessageInDialog("Descripttion is required.");
            error = true;
        }

        if (!error) {
            try {
                NewTransaction.setTransactionName(transactionNameTextField.getText());
                NewTransaction.setTrtypeId((DtTransactionTypes) this.transactionTypeComboBox.getSelectedItem());
                NewTransaction.setDescription(transactionDescrTextField.getText());
                NewTransaction.setAccountNumber((DtAccounts) this.AccountNumbersComboBox.getSelectedItem());
                NewTransaction.setLocation(locationTextField.getText());
                BigDecimal Amount = new BigDecimal(amountTextField.getText());
                NewTransaction.setAmount(Amount);
                Date CurrentTime = new Date();
                NewTransaction.setDatetimeTrans(CurrentTime);

                DtUsers TransactionUser = new DtUsers();
                TransactionUser.setIdUser("JORTEGA");
                NewTransaction.setIdUser(TransactionUser);

            } catch (Exception e) {
                displayMessageInDialog("Failed to create the Transaction Object: " + e.getMessage());
            }
        }

        return NewTransaction;
    }
    
    @Override
    public List<PersonFilter> getSearchParameters(){
         List<PersonFilter> resultList = new ArrayList<>();
         PersonFilter Filter = new PersonFilter();
         
         if (!transactionIdTextField.getText().isEmpty()){
            Filter.setFieldName("TransactionID");
            Filter.setFieldvalue(transactionIdTextField.getText());
            resultList.add(Filter);
            Filter = new PersonFilter();
         }
         if (!transactionNameTextField.getText().isEmpty()){
            Filter.setFieldName("TransactionName");
            Filter.setFieldvalue(transactionNameTextField.getText());
            resultList.add(Filter);
            Filter = new PersonFilter();
         }
         if (transactionTypeComboBox.getSelectedIndex() != 0){
            Filter.setFieldName("TransactionType");
            String bridge = transactionTypeComboBox.getSelectedItem().toString();
            Filter.setFieldvalue(bridge.substring(0,1));
            resultList.add(Filter);
            Filter = new PersonFilter();
         }       
         
         return resultList;
    }

    @Override
    public void displayMessageInDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public JButton getViewButton() {
        return viewButton;
    }

    @Override
    public JButton getAddButton() {
        return addButton;
    }

    @Override
    public JButton getSearchButton() {
        return searchButton;
    }

}
