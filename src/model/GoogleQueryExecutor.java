package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Sends a query to google and stores the results.
 *
 * @author  Marczyk Grzegorz
 * @version 2.2.2
 */
public class GoogleQueryExecutor {

    public String userAgentString;
    public int howManyPositionsToSearchOn;

    private StringBuffer executionContent;

    public GoogleQueryExecutor(String userAgentString, int howManyPositionsToSearchOn) {
        this.userAgentString = userAgentString;
        this.howManyPositionsToSearchOn = howManyPositionsToSearchOn;
        this.executionContent = new StringBuffer();
    }

    public boolean tryExecuting(String keyword) {
        URL urlToQuery;
        HttpURLConnection connectionToQuery;
        int responseCode;

        keyword = parseToQueryUrlSyntax(keyword);
        try {
            urlToQuery = createUrlToQuery(keyword);
        } catch (Exception e) {
            showError(e,"Failed to create such URL (Url to google query), exception message: " + e.getMessage());
            return false;
        }

        try {
            connectionToQuery = createConnectionWithGoogleQuery(urlToQuery);
        } catch (Exception e) {
            showError(e,"Failed to open connection between client and given Url, exception message: " + e.getMessage());
            return false;
        }

        try {
            responseCode = connectionToQuery.getResponseCode();
        } catch (Exception e) {
            showError(e,"Failed to perform such request due to connection error, exception message: " + e.getMessage());
            return false;
        }

        System.out.println("\nSending 'GET' request to URL : " + urlToQuery);
        System.out.println("Response Code : " + responseCode);
        System.out.println("User-Agent  : " + userAgentString);

        if(responseCode != 200) {
            handleResponseCodes(responseCode);
            return false;
        }

        try {
            this.executionContent = getQueryExecutionContent(connectionToQuery);
        } catch (Exception e) {
            showError(e,"Failed to get execution content, exception message: " + e.getMessage());
            return false;
        }

        System.out.print(executionContent.toString());
        return true;
    }

    private String parseToQueryUrlSyntax(String keyword) {
        keyword = keyword.replace("+","%2B");
        keyword = keyword.replaceAll(" ", "+"); // In syntax of google query url, all spaces are replaced by '+' sign, in reverse all pluses should be changed to entity %2B which is a space
        return keyword;
    }

    private URL createUrlToQuery(String keyword) throws Exception{
        return new URL("https://www.google.com/search?q="+keyword + "&num="+howManyPositionsToSearchOn);
    }

    private HttpURLConnection createConnectionWithGoogleQuery(URL urlToQuery) throws Exception {
        HttpURLConnection con = (HttpURLConnection) urlToQuery.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", userAgentString);
        return con;
    }

    private StringBuffer getQueryExecutionContent(HttpURLConnection connectionToQuery) throws Exception {
        String inputLine;
        BufferedReader in = new BufferedReader(new InputStreamReader(connectionToQuery.getInputStream()));
        StringBuffer tmpStorage = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            tmpStorage.append(inputLine);
        }
        in.close();

        return tmpStorage;
    }

    private void showError(Exception e, String msg) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void handleResponseCodes(int responseCode) {
        if(responseCode == 403) {
            JOptionPane.showMessageDialog(null, "Service is unavailable, maybe user-agent is not supported anymore: " + responseCode, "Warning", JOptionPane.WARNING_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null, "Executing query failed, google response code: " + responseCode, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @return All records of the query in ranking order, without URLs to advertisements. May contain youtube links.
     * Should not contain any advertisement links, "recommended", etc.
     */
    public List<String> getQueryResultRecords( ) {
        System.out.println(executionContent);
        System.out.println("");
        return getDomainsFromHrefUrlStrings(getHrefUrlStrings());
    }

    private List<String> getDomainsFromHrefUrlStrings(List<String> hrefUrlList) {
        List<String> domainList = new ArrayList<String>();

        Pattern domainPattern = Pattern.compile(AvailableFormats.DOMAIN.getFormat());
        for (int i=0; i< hrefUrlList.size();i++){
            Matcher domainMatcher = domainPattern.matcher(hrefUrlList.get(i));
            if(domainMatcher.find()) domainList.add(domainMatcher.group());
        }

        System.out.println(domainList);
        return domainList;
    }

    private  List<String> getHrefUrlStrings() {
        List<String> hrefUrlStringList = new ArrayList<String>();
        Pattern hrefUrlPattern = Pattern.compile(AvailableFormats.QUERY_RESULT_HREF_WITHOUT_ADS.getFormat());
        Matcher hrefUrlMatcher = hrefUrlPattern.matcher(executionContent);

        while(hrefUrlMatcher.find()) {
            hrefUrlStringList.add(hrefUrlMatcher.group());
        }

        System.out.println(hrefUrlStringList);
        return hrefUrlStringList;
    }
}
