package model.query;

import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;
import java.util.ArrayList;
import java.util.List;

/**
 * Extracts information from results of Google Custom Search query
 *
 * @author  Marczyk Grzegorz
 * @version 1.1.0
 */
public class GoogleResultExtractor {

    /**
     * @return All displayed links obtained by query
     */
    public List<String> getQueryLinks(Search results) {
        List<String> domainList = new ArrayList<String>();

        List<Result> items = results.getItems();
        for(Result result:items)
        {
            domainList.add(result.getDisplayLink());
        }

        if(domainList.isEmpty())
            throw new RuntimeException("Should never happen, something is wrong with google result object returned as result of query execution");

        System.out.println("Domain list: " + domainList);
        return domainList;
    }
}
