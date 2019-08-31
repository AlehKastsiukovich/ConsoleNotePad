package by.etc.part6.notepad;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validator {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern patternMail = Pattern.compile(EMAIL_PATTERN);
    private static final String DATE_PATTERN = "\\d{4}-\\d{2}-\\d{2}";
    private static final Pattern patternDate = Pattern.compile(DATE_PATTERN);
    private Matcher matcher;

    public boolean validateMail(String check) {
        matcher = patternMail.matcher(check);
        return matcher.matches();
    }

    public boolean validateDate(String check) {
        matcher = patternDate.matcher(check);
        return matcher.matches();
    }
}
