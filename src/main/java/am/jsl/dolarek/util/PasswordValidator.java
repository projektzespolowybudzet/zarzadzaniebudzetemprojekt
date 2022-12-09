package am.jsl.dolarek.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PasswordValidator weryfikuje hasła za pomocą RegexP.
 */
public class PasswordValidator {
    private Pattern pattern;
    private Matcher matcher;

   /*
    ( # Początek grupy
            (? =.*\ d) # musi zawierać jedną cyfrę od 0-9
            (? =.*[A-Z]) # musi zawierać jeden małe postacie
            (? =.*[A-Z]) # musi zawierać jeden wielkie znaki
            (? =.*[@#$%])#musi zawierać jeden specjalny symbole na liście „@#$%”
            .# Dopasuj wszystko do poprzedniego sprawdzania warunków
    {8,20} # długość co najmniej 8 znaków i maksymalnie 20
            ) # Koniec grupy
    */
    private static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";

    public PasswordValidator(){
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    /**
     * Sprawdź hasło za pomocą wyrażenia regularnego
     * @param password hasło do sprawdzania poprawności
     * @return true prawidłowe hasło, false nieprawidłowe hasło
     */
    public boolean validate(final String password){

        matcher = pattern.matcher(password);
        return matcher.matches();

    }
}
