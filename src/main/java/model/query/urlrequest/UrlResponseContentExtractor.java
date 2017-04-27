package model.query.urlrequest;

import model.AvailableFormats;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extracts information from response after executing google query by URL Connection.
 *
 * @author  Marczyk Grzegorz
 * @version 1.0.1
 */
public class UrlResponseContentExtractor {

    /**
     * @return All records of the query in ranking order, without URLs to advertisements. May contain youtube links.
     * Should not contain any advertisement links, "recommended", etc.
     */
    public List<String> getQueryLinksNoAds(StringBuffer executionContent) {
        System.out.println(executionContent);
        System.out.println("");
        List<String> hrefUrlStrings = HtmlCodeParser.getHrefUrlStringsWithoutAds(executionContent);
        return HtmlCodeParser.getDomainsFromHrefUrlStrings(hrefUrlStrings);
    }




}
