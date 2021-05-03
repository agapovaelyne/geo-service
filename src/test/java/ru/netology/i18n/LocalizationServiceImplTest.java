package ru.netology.i18n;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;

import static org.mockito.Matchers.any;

public class LocalizationServiceImplTest {
    //Проверить работу метода public String locale(Country country)

    @ParameterizedTest
    @EnumSource(Country.class)
    public void locale_countries_success(Country country){
        LocalizationServiceImpl localizationServiceMock = Mockito.mock(LocalizationServiceImpl.class);
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

        for (Country c: Country.values()){
            switch (c) {
                case RUSSIA:
                    Mockito.when(localizationServiceMock.locale(c))
                            .thenReturn("Добро пожаловать");
                    break;
                default:
                    Mockito.when(localizationServiceMock.locale(c))
                            .thenReturn("Welcome");
            }
        }

        String expected = localizationServiceMock.locale(country);
        String actual = localizationService.locale(country);

        Assertions.assertEquals(actual, expected);
    }

    @ParameterizedTest
    @EnumSource(Country.class)
    public void locale_countriesAnswerPractice_success(Country country){
        LocalizationServiceImpl localizationServiceMock = Mockito.mock(LocalizationServiceImpl.class);
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

        Mockito.when(localizationServiceMock.locale(any(Country.class)))
                .thenAnswer(
                        invocationOnMock -> {
                            Object[] c = invocationOnMock.getArguments();
                            switch ((Country) c[0]) {
                                case RUSSIA:
                                    return "Добро пожаловать";
                                default:
                                    return "Welcome";
                            }
                        }
                );

        String expected = localizationServiceMock.locale(country);
        String actual = localizationService.locale(country);

        Assertions.assertEquals(actual, expected);
    }

    @ParameterizedTest
    @NullSource
    public void locale_nullCountry_success(Country country){
        LocalizationServiceImpl localizationServiceSpy = Mockito.spy(new LocalizationServiceImpl());
        String expected  = "Welcome";
        String actual = localizationServiceSpy.locale(country);
        Assert.assertEquals(actual,expected);
    }
}
