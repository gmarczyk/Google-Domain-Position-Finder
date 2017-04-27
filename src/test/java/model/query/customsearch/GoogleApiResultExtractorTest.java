package model.query.customsearch;

import com.google.api.services.customsearch.model.Search;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author  Marczyk Grzegorz
 * @version 1.0.1
 */
class GoogleApiResultExtractorTest {

    @Test
    void getQueryLinks_fail_ifNullInput_throw() {
        GoogleAPIQueryExecutor gaqe = new GoogleAPIQueryExecutor("WRONG EXECUTOR API", "WRONG SEARCH ENGINE");
        GoogleApiResultExtractor gare = new GoogleApiResultExtractor();
        try {
            List<String> queryLinks = gare.getQueryLinks(gaqe.execute("random keywords"));
            fail("Exception should be thrown when getting the queryLinks");
        }
        catch(RuntimeException ex)
        {}
    }

    @Test
    void getQueryLinks_fail_ifLinksListIsEmpty_throw() {
        GoogleApiResultExtractor gare = new GoogleApiResultExtractor();
        try {
            List<String> queryLinks = gare.getQueryLinks(new Search());
            fail("Exception should be thrown when no links are extracted from existing Search object");
        }
        catch(RuntimeException ex)
        {}
    }

}