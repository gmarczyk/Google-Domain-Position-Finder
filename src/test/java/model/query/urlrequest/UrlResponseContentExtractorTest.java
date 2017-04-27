package model.query.urlrequest;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author  Marczyk Grzegorz
 * @version 1.0.1
 */
class UrlResponseContentExtractorTest {

    @Test
    void getQueryLinksNoAds_pass_foundAllNoAdsLinks() {
        UrlResponseContentExtractor urce = new UrlResponseContentExtractor();
        StringBuffer codetoParse = new StringBuffer();
        codetoParse.append("correct no ad link <h3 class=\"r\"><a href=\"/url?q=https://www.pokerstars.eu/pl/&amp; second one here <h3 class=\"r\"><a href=\"/url?q=https://www.pokerstars.eu/pl/&amp; last link is not ad-free <a href=\"/url?q=https://www.pokerstars.eu/pl/&amp;");
        assertEquals(2, urce.getQueryLinksNoAds(codetoParse).size());
    }

    @Test
    void getQueryLinksNoAds_pass_emptyIfNoLinksFound() {
        UrlResponseContentExtractor urce = new UrlResponseContentExtractor();
        StringBuffer codetoParse = new StringBuffer();
        codetoParse.append("incorrect syntax ss=\"r\"><a href=\"/url?q=https://www.pokerstars.eu/pl/&amp; another incorrect syntax rl?q=https://www.pokerstars.eu/pl/&amp; last link is not ad-free <a href=\"/url?q=https://www.pokerstars.eu/pl/&amp;");
        assertEquals(0, urce.getQueryLinksNoAds(codetoParse).size());
    }

}