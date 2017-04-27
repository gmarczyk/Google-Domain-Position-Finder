package model.query.urlrequest;

import model.query.PositionSearcherStrategy;

import java.util.List;

/**
 * Uses simple URL Connection to perform a query and find position of given domain in results of the query.
 *
 * @author  Marczyk Grzegorz
 * @version 1.1.0
 */
public class UrlRequestSearcher extends PositionSearcherStrategy {

    UrlRequstQueryExecutor queryExecutor;
    UrlResponseContentExtractor resultExtractor;

    public UrlRequestSearcher() {
        this.queryExecutor = new UrlRequstQueryExecutor();
        this.resultExtractor = new UrlResponseContentExtractor();
    }

    public UrlRequestSearcher(String userAgent, int howManyPositionsToSearchOn) {
        this.queryExecutor = new UrlRequstQueryExecutor(userAgent,howManyPositionsToSearchOn);
        this.resultExtractor = new UrlResponseContentExtractor();
    }

    @Override
    public int searchForDomainPosition(String inputDomain, String inputKeywords) {
        StringBuffer executionContent = queryExecutor.execute(inputKeywords);
        if(executionContent != null) {
            List<String> queryResultRecords = resultExtractor.getQueryLinksNoAds(executionContent);
            return this.findDomainPositionInListOfDomains(queryResultRecords, inputDomain);
        }
        else {
            return -1;
        }
    }


}
