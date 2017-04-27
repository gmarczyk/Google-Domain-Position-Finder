package model.query.customsearch;

import model.query.PositionSearcherStrategy;
import model.query.customsearch.GoogleAPISearcher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
class GoogleAPISearcherTest {

    @Test
    void searchForDomainPosition_fail_randomConnection_noDomainsFoundInResults_minusOneReturned() {
        PositionSearcherStrategy psf = new GoogleAPISearcher("xyzRandom:/-ApiKey123", "xyzRandom:/-EngineKey123");
        int result = psf.searchForDomainPosition("Insignificantdomain.com","Insignificant keywords");
        assertEquals(-1, result,"Should receive no links and return -1");
    }

    @Test
    void searchForDomainPosition_fail_randomKeywords_domainNotFoundInResults_zeroReturned() {
        PositionSearcherStrategy psf = new GoogleAPISearcher();
        int result = psf.searchForDomainPosition("xyzabcqwe.com","Some keyword not even related to given domain");
        assertEquals(0, result,"There is a small probability that this test won't pass, should not find the domain on result list");
    }

    @Test
    void searchForDomainPosition_pass_domainGotFound_anyIntReturned_defaultApiActiveAndWorking() {
        PositionSearcherStrategy psf = new GoogleAPISearcher();
        int result = psf.searchForDomainPosition("wikipedia.org","wikipedia");
        assertTrue((result>0));
    }



}