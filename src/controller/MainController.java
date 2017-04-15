package controller;

import model.GoogleQueryExecutor;
import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import model.DataValidator;

import javax.swing.*;

/**
 * Main controller of the application. Intermediary in communication between view and model.
 * Event handling happens here.
 *
 * @author  Marczyk Grzegorz
 * @version 1.3.2
 */
public class MainController implements ActionListener  {

    private MainWindow view;
    private GoogleQueryExecutor queryExecutor;

    public MainController() {
        this.queryExecutor = new GoogleQueryExecutor("Chrome",20);
        this.view = new MainWindow(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "buttonSearch_Clicked":
                performSearchForDomainPosition();
                break;
            default:
                throw new RuntimeException("[Main controller] Such action command is not supported by action listener in main controller");
        }
    }

    private void performSearchForDomainPosition() {
        String chosenUserAgent = view.getChosenOrInputDomain();
        String inputDomain = view.getInputDomain();
        String inputKeywords = view.getInputKeywords();

        boolean wasExecutionSuccessful = tryExecutingQuery(chosenUserAgent, inputDomain, inputKeywords);
        if(wasExecutionSuccessful) {
            List<String> queryResultRecords = queryExecutor.getQueryResultRecords();
            Integer positionInQuery = findDomainPositionInQueryResult(queryResultRecords,inputDomain);

            if(queryResultRecords.isEmpty() )
                JOptionPane.showMessageDialog(null, "No results found! User-Agent handling may be deprecated or not supported anymore", "Error", JOptionPane.ERROR_MESSAGE);
            else if(positionInQuery != 0)
                view.setQueryExecutionResult(positionInQuery.toString());
            else
                view.setQueryExecutionResult("Such domain not found in query result");
        }
    }

    private boolean tryExecutingQuery(String chosenUserAgent, String inputDomain, String inputKeywords) {
        boolean canBeExecuted = isInputValid(inputDomain,inputKeywords);
        if(canBeExecuted) {
            queryExecutor.userAgentString = chosenUserAgent;
            return queryExecutor.tryExecuting(inputKeywords);
        }
        else {
            JOptionPane.showMessageDialog(null, "Some of input values are incorrect, check the domain syntax and if keywords are not empty",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    private boolean isInputValid(String inputDomain, String inputKeyword) {
        boolean isDomainFormatOk = DataValidator.isInputDomainCorrect(inputDomain);
        boolean areKeywordsNotEmpty = DataValidator.isKeyWordCorrect(inputKeyword);
        return (isDomainFormatOk && areKeywordsNotEmpty);
    }

    /** Searches for record with www.example.com and also for just example.com */
    private int findDomainPositionInQueryResult(List<String> queryResultRecords, String domain) {
        int indexOfDomain = queryResultRecords.indexOf(domain);
        if(indexOfDomain == -1) {
            if(!(domain.matches("(www\\.).*"))) {       // If www.example.com and not found such domain, try with example.com, or do in reverse.
                domain = "www." + domain;
            }
            else {
                domain = domain.substring(4);
            }
        }
        return queryResultRecords.indexOf(domain) + 1;
    }
}
