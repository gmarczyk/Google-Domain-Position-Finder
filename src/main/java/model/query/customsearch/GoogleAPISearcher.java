package model.query.customsearch;

import com.google.api.services.customsearch.model.Search;
import model.query.PositionSearcherStrategy;

import java.util.List;


/**
 * Uses Google Custom Search API to perform a query and find position of given domain in results of the query.
 *
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
public class GoogleAPISearcher extends PositionSearcherStrategy {

    GoogleAPIQueryExecutor queryExecutor;
    GoogleApiResultExtractor resultExtractor;

    public GoogleAPISearcher() {
        this.queryExecutor = new GoogleAPIQueryExecutor();
        this.resultExtractor = new GoogleApiResultExtractor();
    }

    public GoogleAPISearcher(String apiKey, String searchEngineId) {
        this.queryExecutor = new GoogleAPIQueryExecutor(apiKey,searchEngineId);
        this.resultExtractor = new GoogleApiResultExtractor();
    }

    @Override
    public int searchForDomainPosition(String inputDomain, String inputKeywords) {
        Search results = queryExecutor.execute(inputKeywords);
        if(results == null)
            return -1;

        List<String> queryResultRecords = resultExtractor.getQueryLinks(results);
        return this.findDomainPositionInListOfDomains(queryResultRecords, inputDomain);
    }
}
