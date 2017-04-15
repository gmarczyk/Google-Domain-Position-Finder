package model;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
class GoogleQueryExecutorTest {

    GoogleQueryExecutor gqe;

    @BeforeEach
    void initGoogleQueryExecutor() {
        this.gqe = new GoogleQueryExecutor("Chrome",10);
    }

    @Test
    void tryExecuting() {
        /* No idea how to test something, that will fail only when specific exception occures,
           like IO exception, server not responds, etc. */
    }

    @Test
    void getQueryResultRecords_pass_checkIfContainsOnlyDomains() {
        gqe.howManyPositionsToSearchOn = 100;
        gqe.tryExecuting("games");
        List<String> queryResultRecords = gqe.getQueryResultRecords();

        Pattern domainPattern = Pattern.compile(AvailableFormats.DOMAIN.getFormat());
        for (int i=0; i< queryResultRecords.size(); i++) {
            Matcher domainMatcher = domainPattern.matcher(queryResultRecords.get(i));
            if(!domainMatcher.find())
                fail("Should contain domain for each record");
        }
    }

}