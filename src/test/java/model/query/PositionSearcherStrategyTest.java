package model.query;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author  Marczyk Grzegorz
 * @version 1.1.0
 */
class PositionSearcherStrategyTest {

    @Test
    void findDomainPositionInListOfDomains_pass_inputDomainNoWWW_resultNoWWW() {
        List<String> domains = new ArrayList<String>(Arrays.asList("www.something.org", "wikipedia.org", "else.org"));
        int position = PositionSearcherStrategy.findDomainPositionInListOfDomains(domains, "wikipedia.org");
        assertTrue((position>0));
    }

    @Test
    void findDomainPositionInListOfDomains_pass_inputDomainNoWWW_resultWithWWW() {
        List<String> domains = new ArrayList<String>(Arrays.asList("www.something.org", "www.wikipedia.org", "else.org"));
        int position = PositionSearcherStrategy.findDomainPositionInListOfDomains(domains, "wikipedia.org");
        assertTrue((position>0));
    }

    @Test
    void findDomainPositionInListOfDomains_pass_inputDomainWithWWW_resultNoWWW() {
        List<String> domains = new ArrayList<String>(Arrays.asList("www.something.org", "wikipedia.org", "else.org"));
        int position = PositionSearcherStrategy.findDomainPositionInListOfDomains(domains, "www.wikipedia.org");
        assertTrue((position>0));
    }

    @Test
    void findDomainPositionInListOfDomains_pass_inputDomainWithWWW_resultWithWWW() {
        List<String> domains = new ArrayList<String>(Arrays.asList("www.something.org", "www.wikipedia.org", "else.org"));
        int position = PositionSearcherStrategy.findDomainPositionInListOfDomains(domains, "www.wikipedia.org");
        assertTrue((position>0));
    }

    @Test
    void findDomainPositionInListOfDomains_pass_positionFoundIsCorrect() {
        List<String> domains = new ArrayList<String>(Arrays.asList("www.some.org", "thing.org", "wikipedia.org"));
        int position = PositionSearcherStrategy.findDomainPositionInListOfDomains(domains, "www.wikipedia.org");
        assertTrue((position == 3));
    }

    @Test
    void findDomainPositionInListOfDomains_pass_findWithSubDomain() {
        List<String> domains = new ArrayList<String>(Arrays.asList("www.something.org", "en.wikipedia.org", "else.org"));
        int position = PositionSearcherStrategy.findDomainPositionInListOfDomains(domains, "wikipedia.org");
        assertTrue((position==2));
    }

    @Test
    void findDomainPositionInListOfDomains_pass_notFound() {
        List<String> domains = new ArrayList<String>(Arrays.asList("www.something.org", "en.wikipedia.org", "else.org"));
        int position = PositionSearcherStrategy.findDomainPositionInListOfDomains(domains, "notInArray.org");
        assertTrue((position == 0));
    }

}