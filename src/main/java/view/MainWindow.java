package view;

import javax.swing.*;

import java.awt.event.ActionListener;

/**
 * Main view of the application.
 *
 * @author  Marczyk Grzegorz
 * @version 2.2.1
 */
public class MainWindow extends JFrame {

    private JPanel panel1;
    private JLabel labelDomain, labelKeywords, labelSearchingResult, labelQueryResultValue, labelSearchingTools;
    private JTextField textFieldDomain, textFieldKeywords;
    private JButton buttonSearch;
    private JComboBox comboBoxSearchingTools;

    public MainWindow(ActionListener actionListener) {
        this.setContentPane(panel1);

        initializeComponents();
        addActionListeners(actionListener);
        setActionCommands();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    private void initializeComponents()
    {
        initComboSearchingTools();
    }

    private void addActionListeners(ActionListener ac) {
        buttonSearch.addActionListener(ac);
    }

    private void setActionCommands() {
        buttonSearch.setActionCommand("buttonSearch_Clicked");
    }

    private void initComboSearchingTools() {
        this.comboBoxSearchingTools.addItem(new ComboBoxItem("GoogleApiSearcher","Google Custom Search API"));
        this.comboBoxSearchingTools.addItem(new ComboBoxItem("UrlRequestSearcher","URL Request"));
    }

    public String getInputKeywords() {
        return textFieldKeywords.getText();
    }

    public String getInputDomain() {
        return textFieldDomain.getText();
    }

    public String getChosenSearchingTool() {
        ComboBoxItem cbi = (ComboBoxItem) comboBoxSearchingTools.getSelectedItem();
        return cbi.getValue();
    }

    /** Sets text of the label under "Position in query" (According to view v.2.2.1) */
    public void setQueryExecutionResult(String result) {
        this.labelQueryResultValue.setText(result);
    }

}
