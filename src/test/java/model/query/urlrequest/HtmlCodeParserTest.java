package model.query.urlrequest;

import org.junit.jupiter.api.Test;

import javax.swing.text.html.HTML;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
class HtmlCodeParserTest {

    @Test
    void getHrefUrlStringsWithoutAds_pass_foundAllCorrectStrings() {
        StringBuffer codetoParse = new StringBuffer();
        codetoParse.append("random things <h3 class=\"r\"><a href=\"/url?q=https://www.pokerstars.eu/pl/&amp; second one here <h3 class=\"r\"><a href=\"/url?q=https://www.pokerstars.eu/pl/&amp; and the last one <h3 class=\"r\"><a href=\"/url?q=https://www.pokerstars.eu/pl/&amp;");
        assertEquals(3,HtmlCodeParser.getHrefUrlStringsWithoutAds(codetoParse).size());
    }

    @Test
    void getHrefUrlStringsWithoutAds_fail_incorrectSyntax_domainNotFound() {
        StringBuffer codetoParse = new StringBuffer();
        codetoParse.append("no advertisement tag <a href=\"/url?q=https://www.pokerstars.eu/pl/&amp; bad advertisement tag ss=\"r\"><a href=\"/url?q=https://www.pokerstars.eu/pl/&amp; bad a href tag f=\"/url?q=https://www.pokerstars.eu/pl/&amp; no &amp; at the end <h3 class=\"r\"><a href=\"/url?q=https://www.pokerstars.eu/pl/&");
        assertEquals(0,HtmlCodeParser.getHrefUrlStringsWithoutAds(codetoParse).size());
    }

    @Test
    void getDomainsFromHrefUrlStrings_pass_foundAllDomains() {
        List<String> listOfHrefs = new ArrayList<String>(Arrays.asList("first is correct <a href=\"/url?q=https://www.pokerstars.eu/pl/&amp;","second is wrong href =\"/url?q=https://www.pokerstars.eu/pl/&amp;, last is correct <a href=\"/url?q=https://www.pokerstars.eu/pl/&amp;"));
        assertEquals(2, HtmlCodeParser.getDomainsFromHrefUrlStrings(listOfHrefs).size());
    }

    @Test
    void getDomainsFromHrefUrlStrings_pass_foundDomainWithWWWPrefix() {
        List<String> listOfHrefs = new ArrayList<String>(Arrays.asList("<a href=\"/url?q=www.pokerstars.eu&amp;"));
        assertFalse(listOfHrefs.isEmpty());
    }

    @Test
    void getDomainsFromHrefUrlStrings_pass_foundDomainWithoutWWWPrefix() {
        List<String> listOfHrefs = new ArrayList<String>(Arrays.asList("<a href=\"/url?q=pokerstars.eu&amp;"));
        assertFalse(listOfHrefs.isEmpty());
    }

    @Test
    void getDomainsFromHrefUrlStrings_pass_foundDomainWithHTTPPrefix() {
        List<String> listOfHrefs = new ArrayList<String>(Arrays.asList("<a href=\"/url?q=https://www.pokerstars.eu&amp;"));
        assertFalse(listOfHrefs.isEmpty());
    }

    @Test
    void getDomainsFromHrefUrlStrings_pass_foundDomainWithSubdomains() {
        List<String> listOfHrefs = new ArrayList<String>(Arrays.asList("<a href=\"/url?q=https://pl.wikipedia.org&amp;"));
        assertFalse(listOfHrefs.isEmpty());
    }

}