package model.query;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.model.Search;
import java.io.IOException;

/**
 * Sends a query to google and stores the results in JSON.
 *
 * @author  Marczyk Grzegorz
 * @version 3.2.0
 */
public class GoogleQueryExecutor {

    private String GOOGLE_API_KEY = "AIzaSyAFhWaUFbYXIgkl9Qu5VxSD181R3g6lbnw";
    private String GOOGLE_SEARCHENGINE_ID = "016249397576237856475:ekuc9da8cse";
    private Customsearch customSearch;

    /**
     * Default constructor which should contain active and working API key and engine ID
     */
    public GoogleQueryExecutor()
    {
        init();
    }

    /**
     * Constructor with own google custom account
     * @param apiKey key required to use Google Custom Search API, given by Google
     * @param searchEngineId id of own google search engine
     */
    public GoogleQueryExecutor(String apiKey, String searchEngineId) {
        this.GOOGLE_API_KEY = apiKey;
        this.GOOGLE_SEARCHENGINE_ID = searchEngineId;
        init();
    }

    private void init() {
        HttpTransport httpTransport = new NetHttpTransport();
        JacksonFactory jsonFactory = new JacksonFactory();
        this.customSearch = new Customsearch.Builder(httpTransport, jsonFactory, null)
                .setApplicationName("Google Query Position Searcher").build();
    }

    public Search execute(String keywords) {
        Search results = null;
        try {
            Customsearch.Cse.List list = customSearch.cse().list(keywords);
            list.setKey(GOOGLE_API_KEY);
            list.setCx(GOOGLE_SEARCHENGINE_ID);
            results = list.execute();

            System.out.println("List executed: " + list);
            System.out.println("Results obtained by execution: " + results);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return results;
    }
}
