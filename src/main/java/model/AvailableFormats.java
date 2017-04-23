package model;

/**
 * Provides formats (regex) for Pattern class, of specific kinds of data available in this application.
 *
 * @author  Marczyk Grzegorz
 * @version 1.2.2
 */
public enum AvailableFormats {

    /** According to https://www.mkyong.com/regular-expressions/domain-name-regular-expression-example/ */
    DOMAIN,

    /**
     * Diego Perini's perfect URL regex (https://gist.github.com/dperini/729294)
     * @deprecated not used since 23.04.2017 MainController version 2.0.0
     */
    @Deprecated
    URL,

    /**
     * Matches String looking like &lt;h3 class="r" &gt;&lt;a href="/url?q= (HERE URL WITHOUT ,; characters) &amp;amp;
     * @deprecated not used since 23.04.2017 MainController version 2.0.0
     */
    // NOTE: '&lt;' is html entity standing for '<', &gt; = '>', &amp; = '&', javadoc requires this formatting.
    @Deprecated
    QUERY_RESULT_HREF_WITHOUT_ADS;

    public String getFormat(){

        switch (this){
            case DOMAIN:
                return "((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}";
            case URL:
                return "(?:(?:https?|ftp)://)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,}))\\.?)(?::\\d{2,5})?(?:[/?#]\\S*)";
            case QUERY_RESULT_HREF_WITHOUT_ADS:
                return "<h3 class=\"r\"><a href=\"\\/url\\?q=((?:(?!<&amp;)[^,;])*?)&amp;";
            default:
                throw new RuntimeException("[Formats enum] No such field of an enum is avaiable in function");
        }
    }

}
