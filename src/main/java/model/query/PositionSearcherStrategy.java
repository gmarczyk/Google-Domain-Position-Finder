package model.query;

import model.AvailableFormats;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides methods to search for position of a domain in google query. Implementations may use different
 * ways to execute query or parse received results which may have different structure.
 *
 * @author  Marczyk Grzegorz
 * @version 1.0.1
 */
public abstract class PositionSearcherStrategy {

    /**
     * @param inputDomain domain whose position is sought in the query results
     * @param inputKeywords basically the question to the google search
     * @return -1 if execution of query gave no links, perhaps some error occured. Otherwise position in query results
     * in ranking order, 0 means domain wasn't found there.
     */
    public abstract int searchForDomainPosition(String inputDomain, String inputKeywords);


    /**
     * Searches for record with www.example.com or example.com or subdomains.example.com
     * @return nearest position in list, where the domain is found. 0 if no domains were found.
     */
    public static int findDomainPositionInListOfDomains(List<String> queryResultRecords, String domain) {
        if((domain.matches("(www\\.).*")))   // Cut the "www." prefix if exists
            domain = domain.substring(4);

        Pattern domainPattern = Pattern.compile(domain);
        for (int i =0; i<queryResultRecords.size(); i++) {
            Matcher domainMatcher = domainPattern.matcher(queryResultRecords.get(i));
            if(domainMatcher.find())
                return i+1; // Position, not index
        }

        return 0;
    }
}
