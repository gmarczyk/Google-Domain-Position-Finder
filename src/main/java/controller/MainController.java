package controller;

import model.DataValidator;
import model.query.DefaultSearcherFactory;
import model.query.PositionSearcherStrategy;
import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Main controller of the application. Intermediary in communication between view and model.
 * Event handling happens here.
 *
 * @author  Marczyk Grzegorz
 * @version 2.1.0
 */
public class MainController implements ActionListener  {

    private MainWindow view;
    private PositionSearcherStrategy positionSearcherStrategy;

    public MainController() {
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
        String inputDomain = view.getInputDomain();
        String inputKeywords = view.getInputKeywords();
        if(isInputValid(inputDomain,inputKeywords)) {
            positionSearcherStrategy = DefaultSearcherFactory.makeDefaultVersionOfStrategy(view.getChosenSearchingTool());
            Integer positionInQuery = positionSearcherStrategy.searchForDomainPosition(inputDomain,inputKeywords);

            if(positionInQuery == -1)
                JOptionPane.showMessageDialog(null,"Results contain no links, execution must have been blocked", "Error", JOptionPane.ERROR_MESSAGE);
            else if(positionInQuery != 0)
                view.setQueryExecutionResult(positionInQuery.toString());
            else
                view.setQueryExecutionResult("Such domain not found in query result");
        }
        else {
            JOptionPane.showMessageDialog(null,"Input domain or keywords are not correct!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private boolean isInputValid(String inputDomain, String inputKeyword) {
        boolean isDomainFormatOk = DataValidator.isInputDomainCorrect(inputDomain);
        boolean areKeywordsNotEmpty = DataValidator.isKeyWordCorrect(inputKeyword);
        return (isDomainFormatOk && areKeywordsNotEmpty);
    }
}
