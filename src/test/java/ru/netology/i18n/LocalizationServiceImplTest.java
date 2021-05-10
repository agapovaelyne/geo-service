package ru.netology.i18n;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullSource;
import ru.netology.entity.Country;

public class LocalizationServiceImplTest {
    //Проверить работу метода public String locale(Country country)

    @ParameterizedTest
    @EnumSource(Country.class)
    public void locale_countries_success(Country country){
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String expected;
        switch (country) {
            case RUSSIA:
                expected = "Добро пожаловать";
                break;
            default:
                expected = "Welcome";
        }
        String actual = localizationService.locale(country);
        Assertions.assertEquals(actual, expected);
    }

    @ParameterizedTest
    @NullSource
    public void locale_nullCountry_success(Country country){
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String expected  = "Welcome";
        String actual = localizationService.locale(country);
        Assert.assertEquals(actual,expected);
    }
}
