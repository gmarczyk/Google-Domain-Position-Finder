package model.query.customsearch;

import com.google.api.services.customsearch.model.Search;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
class GoogleAPIQueryExecutorTest {

    @Test
    void execute_fail_userCustomization_wrongApiKeyAndEngineID() {
        GoogleAPIQueryExecutor gaqe = new GoogleAPIQueryExecutor("123WRONG;/-API_KEYXXzz&","123WRONG;/-ENGINE_IDXXzz&");
        Search search = gaqe.execute("random keywords");
        assertNull(search, "Wrong API account should not be able to perform request");
    }

    @Test
    void execute_pass_defaultOptions_activeAndWorkingAPIAccount() {
        GoogleAPIQueryExecutor gaqe = new GoogleAPIQueryExecutor();
        Search search = gaqe.execute("random keywords");
        assertNotNull(search, "Default API account should be active and working! Also google limits mustn't be exceeded");
    }

}