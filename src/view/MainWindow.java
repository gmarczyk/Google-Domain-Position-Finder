package view;

import javax.swing.*;
import javax.swing.table.*;
import model.UserAgentList;

import java.awt.event.ActionListener;

/**
 * Main view of the application.
 *
 * @author  Marczyk Grzegorz
 * @version 2.2.1
 */
public class MainWindow extends JFrame {

    private JPanel panel1;
    private JLabel labelUserAgentComboBox, labelOwnUserAgent, labelDomain, labelKeywords, labelSearchingResult, labelQueryResultValue;
    private JTextField textFieldDomain, textFieldKeywords, textFieldOwnUserAgent;
    private JButton buttonSearch;
    private JComboBox comboBoxUserAgent;

    public MainWindow(ActionListener actionListener) {
        this.setContentPane(panel1);

        initializeComponents();
        addActionListeners(actionListener);
        setActionCommands();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    private void initializeComponents() {
        comboBoxUserAgent.setModel(
                new DefaultComboBoxModel(UserAgentList.userAgents));
    }

    private void addActionListeners(ActionListener ac) {
        buttonSearch.addActionListener(ac);
    }

    private void setActionCommands() {
        buttonSearch.setActionCommand("buttonSearch_Clicked");
    }

    /**
     * @return If textField of "domain" is not empty it returns value of the textField in which user writes his own
     * User-Agent.Otherwise returns selected item of the checkbox, where user can pick one of existing User-Agents
     */
    public String getChosenOrInputDomain() {
        String ownUserAgent = textFieldOwnUserAgent.getText();
        if(ownUserAgent.isEmpty())
            return comboBoxUserAgent.getSelectedItem().toString();
        else
            return ownUserAgent;
    }

    public String getInputKeywords() {
        return textFieldKeywords.getText();
    }

    public String getInputDomain() {
        return textFieldDomain.getText();
    }

    /** Sets text of the label under "Position in query" (According to view v.2.2.1) */
    public void setQueryExecutionResult(String result) {
        this.labelQueryResultValue.setText(result);
    }

}
