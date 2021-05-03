package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;


import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;
import static ru.netology.geo.GeoServiceImpl.*;


public class GeoServiceImplTest {
    /*
    Написать тесты для проверки определения локации по ip (класс GeoServiceImpl)
Проверить работу метода public Location byIp(String ip)
     */
    @ParameterizedTest
    @ValueSource(strings = {LOCALHOST, MOSCOW_IP, NEW_YORK_IP, "172.45.332.44" , "96.332.44.2", "89.55.333.12"})
    public void byIp_test1(String ip) {
        GeoServiceImpl geoServiceMock = Mockito.mock(GeoServiceImpl.class);
        GeoServiceImpl geoServiceSpy = Mockito.spy(new GeoServiceImpl());

        Mockito.when(geoServiceMock.byIp(MOSCOW_IP))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        Mockito.when(geoServiceMock.byIp("172.45.332.44"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        Mockito.when(geoServiceMock.byIp(NEW_YORK_IP))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        Mockito.when(geoServiceMock.byIp("96.332.44.2"))
                .thenReturn(new Location("New York", Country.USA, null,  0));

        Mockito.when(geoServiceMock.byIp(LOCALHOST))
                .thenReturn(new Location(null, null, null, 0));

        Mockito.when(geoServiceMock.byIp("89.55.333.12"))
               .thenReturn(new Location(null, null, null, 0));

        Country expected = geoServiceMock.byIp(ip).getCountry();
        Country actual = geoServiceSpy.byIp(ip).getCountry();

        Assertions.assertEquals(expected,actual);
    }



    @ParameterizedTest
    @ValueSource(strings = {LOCALHOST, MOSCOW_IP, NEW_YORK_IP, "172.45.332.44" , "96.332.44.2", "89.55.333.12"})
    public void byIp_customAndDefaultMatchersPractice_test(String ip) {
        GeoServiceImpl geoService = Mockito.spy(new GeoServiceImpl());
        GeoServiceImpl geoServiceMock = Mockito.mock(GeoServiceImpl.class);

        when(geoServiceMock.byIp(argThat(new ArgumentMatcher <String>() {
            @Override
            public boolean matches(Object o) {
                if (o instanceof String) {
                    String ip = (String) o;
                    return ip.startsWith("172") || ip.equals(MOSCOW_IP);
                }
                return false;
            }
        })))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));


        when(geoServiceMock.byIp(argThat(new ArgumentMatcher <String>() {
            @Override
            public boolean matches(Object o) {
                if (o instanceof String) {
                    String ip = (String) o;
                    return ip.startsWith("96") || ip.equals(NEW_YORK_IP);
                }
                return false;
            }
        })))
                .thenReturn(new Location("New York", Country.USA, null,  0));

        when(geoServiceMock.byIp(eq(LOCALHOST)))
                .thenReturn(new Location(null, null, null, 0));

        when(geoServiceMock.byIp("89.55.333.12"))
            .thenReturn(new Location(null, null, null, 0));

        Country expected = geoServiceMock.byIp(ip).getCountry();
        Country actual = geoService.byIp(ip).getCountry();

        Assertions.assertEquals(actual, expected);
    }

}
