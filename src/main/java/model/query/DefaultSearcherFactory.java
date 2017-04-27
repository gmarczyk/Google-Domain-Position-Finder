package model.query;

import model.query.customsearch.GoogleAPISearcher;
import model.query.urlrequest.UrlRequestSearcher;

/**
 * Constructs default versions of implementations of abstract class that allows to perform query to google.
 *
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
public class DefaultSearcherFactory {

    public static PositionSearcherStrategy makeDefaultVersionOfStrategy(String toConstruct) {
        PositionSearcherStrategy objToReturn;
        switch(toConstruct) {
            case "GoogleApiSearcher":
                objToReturn = new GoogleAPISearcher();
                break;
            case "UrlRequestSearcher":
                objToReturn = new UrlRequestSearcher();
                break;
            default:
                throw new RuntimeException("No such value in switch-case for creating default strategy. Check if string passed is correct or if fabric implements such case");
        }
        return objToReturn;
    }
}
