package model;

import com.google.api.services.customsearch.model.Search;
import model.query.GoogleQueryExecutor;
import model.query.GoogleResultExtractor;

import java.util.List;

/**
 * Provides methods to
 *
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
public class PositionSearcherFacade {

    GoogleQueryExecutor queryExecutor;
    GoogleResultExtractor resultExtractor;

    public PositionSearcherFacade() {
        this.queryExecutor = new GoogleQueryExecutor();
        this.resultExtractor = new GoogleResultExtractor();
    }

    public PositionSearcherFacade(String apiKey, String searchEngineId) {
        this.queryExecutor = new GoogleQueryExecutor(apiKey,searchEngineId);
        this.resultExtractor = new GoogleResultExtractor();
    }


    /**
     * @param inputDomain domain whose position is sought in the query results
     * @param inputKeywords basically the question to the google search
     * @return -1 if execution of query gave no links, perhaps some error occured. Otherwise position in query results
     * in ranking order, 0 means domain wasn't found there.
     */
    public int searchForDomainPosition(String inputDomain, String inputKeywords) {
        Search results = queryExecutor.execute(inputKeywords);
        if(results == null)
            return -1;

        List<String> queryResultRecords = resultExtractor.getQueryLinks(results);
        return findDomainPositionInQueryResult(queryResultRecords, inputDomain);
    }

    /** Searches for record with www.example.com and also for just example.com */
    private int findDomainPositionInQueryResult(List<String> queryResultRecords, String domain) {
        int indexOfDomain = queryResultRecords.indexOf(domain);
        if(indexOfDomain == -1) {
            if(!(domain.matches("(www\\.).*"))) {       // If domain is not found with syntax "www.abc.com" try just "abc.com"
                domain = "www." + domain;
            }
            else {
                domain = domain.substring(4);
            }
        }
        return queryResultRecords.indexOf(domain) + 1;
    }
}
