package model;

import com.google.api.services.customsearch.model.Search;
import model.query.GoogleQueryExecutor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
class PositionSearcherFacadeTest {

    @Test
    void searchForDomainPosition_fail_randomConnection_noDomainsFoundInResults() {
        PositionSearcherFacade psf = new PositionSearcherFacade("xyzRandom:/-ApiKey123", "xyzRandom:/-EngineKey123");
        int result = psf.searchForDomainPosition("Insignificantdomain.com","Insignificant keywords");
        assertEquals(-1, result,"Should receive no links and return -1");
    }

    @Test
    void searchForDomainPosition_fail_randomKeywords_domainNotFoundInResults() {
        PositionSearcherFacade psf = new PositionSearcherFacade();
        int result = psf.searchForDomainPosition("xyzabcqwe.com","Some keyword not even related to given domain");
        assertEquals(0, result,"There is a small probability that this test won't pass, should not find the domain on result list");
    }

    @Test
    void searchForDomainPosition_pass_domainGotFound_noWWW() {
        PositionSearcherFacade psf = new PositionSearcherFacade();
        int result = psf.searchForDomainPosition("wikipedia.org","wikipedia");
        assertTrue((result>0));
    }

    @Test
    void searchForDomainPosition_pass_domainGotFound_withWWW() {
        PositionSearcherFacade psf = new PositionSearcherFacade();
        int result = psf.searchForDomainPosition("www.wikipedia.org","wikipedia");
        assertTrue((result>0));
    }

    @Test
    void searchForDomainPosition_pass_domainGotFound_withSubdomain() {
        PositionSearcherFacade psf = new PositionSearcherFacade();
        int result = psf.searchForDomainPosition("en.wikipedia.org","wikipedia");
        assertTrue((result>0));
    }

}