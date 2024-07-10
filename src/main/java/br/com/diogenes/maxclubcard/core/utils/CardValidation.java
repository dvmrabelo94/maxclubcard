package br.com.diogenes.maxclubcard.core.utils;

import br.com.diogenes.maxclubcard.dataprovider.brandcard.entity.BrandEntity;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class CardValidation {

    public static boolean isValidCard(String expirationDate, String brandCard, List<BrandEntity> brands) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");

        try {
            YearMonth cardExpiration = YearMonth.parse(expirationDate, formatter);
            YearMonth currentMonth = YearMonth.now();
            var brandsName = brands.stream().map(BrandEntity::getBrandName).toList();
            return !cardExpiration.isBefore(currentMonth) || brandsName.contains(brandCard);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use MM/yy.");
        }
    }
}
