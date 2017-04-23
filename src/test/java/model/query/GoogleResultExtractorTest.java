package model.query;

import com.google.api.services.customsearch.model.Search;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author  Marczyk Grzegorz
 * @version 3.2.0
 */
class GoogleResultExtractorTest {

    @Test
    void getQueryLinks_fail_emptyResultList_shouldNeverHappen() {
        GoogleResultExtractor gre = new GoogleResultExtractor();
        GoogleQueryExecutor gqe = new GoogleQueryExecutor();
        try {
            Search results = gqe.execute("Some keyword");
        }
        catch (RuntimeException rte){
            System.out.println(rte.getMessage());
            System.out.println("Should never happen, not null Search object always contains links");
            rte.printStackTrace();
        }
    }

    @Test
    void getQueryLinks_pass_anyResults() {
        GoogleResultExtractor gre = new GoogleResultExtractor();
        GoogleQueryExecutor gqe = new GoogleQueryExecutor();
        Search results = gqe.execute("Some keyword");
        assertEquals(false,results.isEmpty(), "If the service, api key and engine id are active, should always return links");
    }

}