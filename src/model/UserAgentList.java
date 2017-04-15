package model;

/**
 * List of few User-Agent's and robots available to public.
 * Sources:
 * https://pl.wikipedia.org/wiki/User_agent
 * https://support.google.com/webmasters/answer/1061943?hl=en
 *
 * @author  Marczyk Grzegorz
 * @version 1.0.0 12.04.2017
 */
public final class UserAgentList {

    // No instances needed
    private UserAgentList()
    {}

    public static final String[] userAgents =
            {
                    "Chrome",
                    "Mozilla",
                    "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)",
                    "Googlebot/2.1 (+http://www.google.com/bot.html)",
                    "Mozilla/4.78 [en] (Win98; U)",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)",
                    "Mozilla/5.0 (X11; U; Linux i686; pl-PL; rv:1.7.10) Gecko/20050717 Firefox/1.0.6",
                    "Mozilla/4.0 (compatible; MSIE 6.0; X11; Linux i686; en) Opera 8.01",
                    "Mozilla/5.0 (Macintosh; U; PPC; ja-JP; rv:1.0.1) Gecko/20020823 Netscape/7.0",
                    "Mozilla/5.0 (compatible; Konqueror/3.3; Linux) (KHTML, like Gecko)",
                    "Wget/1.9.1",
                    "Mozilla/5.0 (compatible; Yahoo! Slurp; http://help.yahoo.com/help/us/ysearch/slurp)",
                    "W3C_Validator/1.305.2.148 libwww-perl/5.803, Jigsaw/2.2.3 W3C_CSS_Validator_JFouffa/2.0",
                    "Mediapartners-Google/2.1 (+http://www.googlebot.com/bot.html)",
                    "Java1.8.0"
            };
}
