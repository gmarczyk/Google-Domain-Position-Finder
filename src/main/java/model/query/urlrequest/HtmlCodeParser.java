package model.query.urlrequest;

import model.AvailableFormats;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides methods that find specific informations, records, in given HTML code.
 *
 * @author  Marczyk Grzegorz
 * @version 1.0.0
 */
public class HtmlCodeParser {

    /**
     * @param executionContent HTML code
     * @return list of strings matching the syntax &lt;h3 class="r" &gt;&lt;a href="/url?q= (HERE URL WITHOUT ,; characters) &amp;amp;
     * which stand for links that are not set as advertisements.
     */
    public static List<String> getHrefUrlStringsWithoutAds(StringBuffer executionContent) {
        List<String> hrefUrlStringList = new ArrayList<String>();
        Pattern hrefUrlPattern = Pattern.compile(AvailableFormats.QUERY_RESULT_HREF_WITHOUT_ADS.getFormat());
        Matcher hrefUrlMatcher = hrefUrlPattern.matcher(executionContent);

        while(hrefUrlMatcher.find()) {
            hrefUrlStringList.add(hrefUrlMatcher.group());
        }

        System.out.println(hrefUrlStringList);
        return hrefUrlStringList;
    }

    /**
     * @param hrefUrlList strings matching syntax &lt;a href="/url?q= (HERE URL WITHOUT ,; characters)
     * @return list of domains with subdomains, with or without "www." prefix
     */
    public static List<String> getDomainsFromHrefUrlStrings(List<String> hrefUrlList) {
        List<String> domainList = new ArrayList<String>();

        Pattern domainPattern = Pattern.compile(AvailableFormats.DOMAIN.getFormat());
        for (int i=0; i< hrefUrlList.size();i++){
            Matcher domainMatcher = domainPattern.matcher(hrefUrlList.get(i));
            if(domainMatcher.find()) domainList.add(domainMatcher.group());
        }

        System.out.println(domainList);
        return domainList;
    }
}
