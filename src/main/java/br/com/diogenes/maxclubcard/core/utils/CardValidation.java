package br.com.diogenes.maxclubcard.core.utils;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CardValidation {

    public static final String MAXCLUBCARD = "MAXCLUBCARD";

    public static boolean isCardValid(String expirationDate, String brandCard) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");

        try {
            YearMonth cardExpiration = YearMonth.parse(expirationDate, formatter);
            YearMonth currentMonth = YearMonth.now();
            return cardExpiration.isBefore(currentMonth) && brandCard.equals(MAXCLUBCARD);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use MM/yy.");
        }
    }
}
