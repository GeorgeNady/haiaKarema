package bee.bee.haiakarema.utiles;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {


    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    static EmailValidator instance;

    public static EmailValidator getInstance() {
        if (instance == null)
            instance = new EmailValidator();
        instance.pattern = Pattern.compile(EMAIL_PATTERN);
        return instance;
    }

    private EmailValidator() {
    }

    /**
     * Validate hex with regular expression
     *
     * @param hex hex for validation
     * @return true valid hex, false invalid hex
     */
    public boolean validate(final String hex) {

        matcher = pattern.matcher(hex);
        return matcher.matches();

    }
}
