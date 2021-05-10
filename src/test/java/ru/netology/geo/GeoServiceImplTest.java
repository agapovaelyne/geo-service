package ru.netology.geo;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;
import static ru.netology.geo.GeoServiceImpl.*;


public class GeoServiceImplTest {
    /*
    Написать тесты для проверки определения локации по ip (класс GeoServiceImpl)
Проверить работу метода public Location byIp(String ip)
     */

    @ParameterizedTest
    @ValueSource(strings = {LOCALHOST, MOSCOW_IP, NEW_YORK_IP, "172.45.332.44", "96.332.44.2", "89.55.333.12"})
    public void country_byIp_test(String ip) {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Country expected = null;
        switch (ip) {
            case (MOSCOW_IP):
            case ("172.45.332.44"):
                expected = RUSSIA;
                break;
            case (NEW_YORK_IP):
            case ("96.332.44.2"):
                expected = USA;
                break;
            case (LOCALHOST):
            case ("89.55.333.12"):
                expected = null;
        }
        Country actual = geoService.byIp(ip).getCountry();
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void byIp_nullOrEmptyValue_success(String ip){
        GeoServiceImpl geoService = new GeoServiceImpl();
        Country expected = null;
        Country actual = geoService.byIp(ip).getCountry();
        Assert.assertEquals(actual, expected);
    }
}