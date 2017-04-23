package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides methods to check if format is correct for specific kinds of data used in this application.
 *
 * @author  Marczyk Grzegorz
 * @version 1.1.1
 */
public final class DataValidator {

    // No instances needed
    private DataValidator()
    {}

    /**
     *  Checks if the format is correct and if string contains only one domain.
     */
    public static boolean isInputDomainCorrect(String inputDomain) {
        Pattern formatToCheck = Pattern.compile(AvailableFormats.DOMAIN.getFormat());
        Matcher matcher = formatToCheck.matcher(inputDomain);

        boolean isValid = false;
        boolean foundOnlyOne = false;
        if(matcher.find()){
            foundOnlyOne = true;
            if((matcher.group().equals(inputDomain)))
                isValid = true;
            if(matcher.find())
                foundOnlyOne = false; // Found second one
        }

        return (foundOnlyOne && isValid);
    }

    public static boolean isKeyWordCorrect(String keyword) {
        boolean notEmpty = (keyword.length() > 0);
        boolean notJustInsignificantSpaces = (keyword.trim().length() > 0);
        return (notEmpty && notJustInsignificantSpaces);
    }

}
