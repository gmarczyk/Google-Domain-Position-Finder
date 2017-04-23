package model.query;

import com.google.api.services.customsearch.model.Search;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
class GoogleQueryExecutorTest {

    @Test
    void execute_fail_randomConnection_nullResult() {
        GoogleQueryExecutor gqe = new GoogleQueryExecutor("Random123API:/*KEY", "Random123ENGINE:/*ID");
        Search results = gqe.execute("Insignificant keywords");
        assertNull(results,"Should be null because of invalid api key and search engine ID");
    }

    @Test
    void execute_pass_defaultConstructor_activeAndWorkingAPIAccount() {
        GoogleQueryExecutor gqe = new GoogleQueryExecutor();
        Search results = gqe.execute("Insignificant keywords");
        assertNotNull(results,"Should pass if the actual api and search engine are active and working");
    }
}