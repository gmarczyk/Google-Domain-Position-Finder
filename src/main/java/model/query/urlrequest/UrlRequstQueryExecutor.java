package model.query.urlrequest;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Performs a request to google search egine using URL Connection. Stores the results obtained in execution,
 * if one was successful.
 *
 * @author  Marczyk Grzegorz
 * @version 1.2.1
 */
public class UrlRequstQueryExecutor {

    public String userAgentString = "Chrome";
    public int howManyPositionsToSearchOn = 10;

    /**
     * 1 if successful, -1 if creating URL fails, -2 if opening connection on created URL fails,
     * -3 if request wasn't performed due to connection error, -4 if response code was not code 200,
     * -5 if downloading execution content fails.
     */
    private int lastExecutionStatusCode;

    public UrlRequstQueryExecutor()
    {}

    public UrlRequstQueryExecutor(String userAgentString, int howManyPositionsToSearchOn) {
        this.userAgentString = userAgentString;
        this.howManyPositionsToSearchOn = howManyPositionsToSearchOn;
    }

    /**
     * @return StringBuffer containing HTML code. Null if not successful, if so, check last execution status code
     * which can be accessed by a getter.
     */
    public StringBuffer execute(String keyword) {
        URL urlToQuery;
        HttpURLConnection connectionToQuery;
        int responseCode;

        keyword = parseToQueryUrlSyntax(keyword);
        try {
            urlToQuery = createUrlToQuery(keyword);
        } catch (Exception e) {
            showError(e,"Failed to create such URL (Url to google query), exception message: " + e.getMessage(), false);
            this.lastExecutionStatusCode = -1;
            return null;
        }

        try {
            connectionToQuery = createConnectionWithGoogleQuery(urlToQuery);
        } catch (Exception e) {
            showError(e,"Failed to open connection between client and given Url, exception message: " + e.getMessage(), false);
            this.lastExecutionStatusCode = -2;
            return null;
        }

        try {
            responseCode = connectionToQuery.getResponseCode();
        } catch (Exception e) {
            showError(e,"Failed to perform such request due to connection error, exception message: " + e.getMessage(), false);
            this.lastExecutionStatusCode = -3;
            return null;
        }

        System.out.println("Sending 'GET' request to URL : " + urlToQuery);
        System.out.println("Response Code : " + responseCode);
        System.out.println("User-Agent  : " + userAgentString);

        if(responseCode != 200) {
            handleResponseCodes(responseCode, false);
            this.lastExecutionStatusCode = -4;
            return null;
        }

        StringBuffer executionContent = null;
        try {
            executionContent = downloadQueryExecutionContent(connectionToQuery);
        } catch (Exception e) {
            showError(e,"Failed to get execution content, exception message: " + e.getMessage(), false);
            this.lastExecutionStatusCode = -5;
            return null;
        }

        System.out.print(executionContent.toString());
        this.lastExecutionStatusCode = 1;
        return executionContent;
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

    private StringBuffer downloadQueryExecutionContent(HttpURLConnection connectionToQuery) throws Exception {
        String inputLine;
        BufferedReader in = new BufferedReader(new InputStreamReader(connectionToQuery.getInputStream()));
        StringBuffer tmpStorage = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            tmpStorage.append(inputLine);
        }
        in.close();

        return tmpStorage;
    }

    private void showError(Exception e, String msg, boolean withMsgDialog) {
        e.printStackTrace();
        if(withMsgDialog)
            JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
        else
            System.out.println(msg);
    }

    private void handleResponseCodes(int responseCode, boolean showMsgDialog) {
        if(responseCode == 403)
            showErrorResponseCode("Service is unavailable, maybe user-agent is not supported anymore: " + responseCode, showMsgDialog);
        else {
            showErrorResponseCode("Executing query failed, google response code: " + responseCode, showMsgDialog);
        }
    }

    private void showErrorResponseCode(String msgToShow, boolean showMsgDialog) {
        if(showMsgDialog)
            JOptionPane.showMessageDialog(null, msgToShow, "Error", JOptionPane.ERROR_MESSAGE);
        else
            System.out.println(msgToShow);
    }

    public int getLastExecutionStatusCode() {
        return this.lastExecutionStatusCode;
    }

}