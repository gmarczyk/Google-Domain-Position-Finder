package model.query.urlrequest;

import com.google.api.services.customsearch.model.Search;
import model.query.customsearch.GoogleAPIQueryExecutor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author  Marczyk Grzegorz
 * @version 1.0.1
 */
class UrlRequstQueryExecutorTest {

    @Test
    void execute_fail_userCustomization_randomUserAgent() {
        UrlRequstQueryExecutor urqe = new UrlRequstQueryExecutor("123RANDOM;/-USERAGENT",5);
        StringBuffer results = urqe.execute("random keywords");
        assertNotNull(results, "Random user agent should be replaced with default by google");
    }

    @Test
    void execute_fail_userCustomization_inactiveUserAgent() {
        UrlRequstQueryExecutor urqe = new UrlRequstQueryExecutor("Wget/1.9.1",5); // Wget/1.9.1 inactive, last check 26.04.2017
        StringBuffer results = urqe.execute("random keywords");
        assertNull(results, "Inactive user should result in null content");
    }

    @Test
    void execute_pass_defaultOptions_activeAndWorkingAPIAccount() {
        UrlRequstQueryExecutor urqe = new UrlRequstQueryExecutor();
        StringBuffer results = urqe.execute("random keywords");
        assertNotNull(results, "Default URL executor should be up to date and working! Also google can't be blocking requests");
    }

}