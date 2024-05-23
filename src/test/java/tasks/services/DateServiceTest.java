package tasks.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
class DateServiceTest {

    private DateService dateService;
    private TasksService mockTasksService;


    @BeforeEach
    void setUp() {
        mockTasksService=mock(TasksService.class);
        dateService=new DateService(mockTasksService);
    }

    @AfterEach
    void tearDown() {
    }

    private static Stream<Arguments> getDateMergedWithTimeProvideValidParams() {
        return Stream.of(
                Arguments.of("8:00",
                        new GregorianCalendar(2024, Calendar.MARCH, 31).getTime(),
                        new GregorianCalendar(2024, Calendar.MARCH, 31, 8, 0).getTime()),
                Arguments.of("0:0",
                        new GregorianCalendar(1970, Calendar.JANUARY, 1, 1, 1).getTime(),
                        new GregorianCalendar(1970, Calendar.JANUARY, 1, 0, 0).getTime()),
                Arguments.of("1:1",
                        new GregorianCalendar(1970, Calendar.JANUARY, 2).getTime(),
                        new GregorianCalendar(1970, Calendar.JANUARY, 2, 1, 1).getTime())
        );
    }

    @Order(1)
    @ParameterizedTest
    @MethodSource("getDateMergedWithTimeProvideValidParams")
    @DisplayName("getDateMergedWithTimeValid valid parameters")
    @Tag("Valid")
    public void getDateMergedWithTimeValid(String time, Date noTimeDate, Date expectedDate) {
        //act
        Date date = dateService.getDateMergedWithTime(time, noTimeDate);

        // assert
        assertEquals(expectedDate, date);
    }

    @Order(2)
    @Test
    @DisplayName("getDateMergedWithTimeValid valid time and date representatives")
    @Tag("Valid")
    @Tag("ECP")
    public void getDateMergedWithTimeECPValidInputReturns() {
        // arrange
        String time = "8:00";
        Date noTimeDate = new GregorianCalendar(2023, Calendar.MARCH, 31).getTime();
        Date expectedDate = new GregorianCalendar(2023, Calendar.MARCH, 31, 8, 0).getTime();

        // act
        Date date = dateService.getDateMergedWithTime(time, noTimeDate);

        // assert
        assertEquals(expectedDate, date);
    }

    @Order(3)
    @Test
    @DisplayName("getDateMergedWithTimeValid valid hour, minute and date lower boundary")
    @Tag("Valid")
    @Tag("BVA")
    public void getDateMergedWithTimeBVAValidReturns1() {
        // arrange
        String time = "0:0";
        Date noTimeDate = new GregorianCalendar(1970, Calendar.JANUARY, 1, 1, 1).getTime();
        Date expectedDate = new GregorianCalendar(1970, Calendar.JANUARY, 1, 0, 0).getTime();

        // act
        Date date = dateService.getDateMergedWithTime(time, noTimeDate);

        // assert
        assertEquals(expectedDate, date);
    }

    @Order(4)
    @Test
    @DisplayName("getDateMergedWithTimeValid valid hour, minute and date lower boundary")
    @Tag("Valid")
    @Tag("BVA")
    public void getDateMergedWithTimeBVAValidReturns2() {
        // arrange
        String time = "1:1";
        Date noTimeDate = new GregorianCalendar(1970, Calendar.JANUARY, 2).getTime();
        Date expectedDate = new GregorianCalendar(1970, Calendar.JANUARY, 2, 1, 1).getTime();

        // act
        Date date = dateService.getDateMergedWithTime(time, noTimeDate);

        // assert
        assertEquals(expectedDate, date);
    }

    @Order(5)
    @Test
    @DisplayName("getDateMergedWithTimeValid invalid hour, minute and date representatives")
    @Tag("Invalid")
    @Tag("ECP")
    public void getDateMergedWithTimeECPInvalidHourMinuteAndDateThrows() {
        // arrange
        String time = "54:79";
        Date noTimeDate = new GregorianCalendar(1900, Calendar.JANUARY, 1).getTime();

        // act
        // assert
        Exception ex = assertThrows(IllegalArgumentException.class, () -> dateService.getDateMergedWithTime(time, noTimeDate));
    }

    @Order(6)
    @Test
    @DisplayName("getDateMergedWithTimeValid valid time format")
    @Tag("Invalid")
    @Tag("ECP")
    public void getDateMergedWithTimeECPInvalidTimeThrows() {
        // arrange
        String time = "asd";
        Date noTimeDate = new GregorianCalendar(2023, Calendar.MARCH, 31).getTime();

        // act
        // assert
        Exception ex = assertThrows(IllegalArgumentException.class, () -> dateService.getDateMergedWithTime(time, noTimeDate));
    }

    @Order(7)
    @Test
    @DisplayName("getDateMergedWithTimeValid invalid hour lower boundary")
    @Tag("Invalid")
    @Tag("BVA")
    public void getDateMergedWithTimeBVAInvalidHourThrows1() {
        // arrange
        String time = "-1:15";
        Date noTimeDate = new GregorianCalendar(2023, Calendar.APRIL, 4).getTime();

        // act
        // assert
        Exception ex = assertThrows(IllegalArgumentException.class, () -> dateService.getDateMergedWithTime(time, noTimeDate));
    }

    @Order(8)
    @Test
    @DisplayName("getDateMergedWithTimeValid invalid hour upper boundary")
    @Tag("Invalid")
    @Tag("BVA")
    public void getDateMergedWithTimeBVAInvalidHourThrows2() {
        // arrange
        String time = "24:12";
        Date noTimeDate = new GregorianCalendar(2023, Calendar.APRIL, 4).getTime();

        // act
        // assert
        Exception ex = assertThrows(IllegalArgumentException.class, () -> dateService.getDateMergedWithTime(time, noTimeDate));
    }

    @Order(9)
    @Test
    @DisplayName("getDateMergedWithTimeValid invalid minute upper boundary")
    @Tag("Invalid")
    @Tag("BVA")
    public void getDateMergedWithTimeBVAInvalidMinutesThrows1() {
        // arrange
        String time = "18:60";
        Date noTimeDate = new GregorianCalendar(2023, Calendar.APRIL, 4).getTime();

        // act
        // assert
        Exception ex = assertThrows(IllegalArgumentException.class, () -> dateService.getDateMergedWithTime(time, noTimeDate));
    }

    @Order(10)
    @Test
    @DisplayName("getDateMergedWithTimeValid invalid minute lower boundary")
    @Tag("Invalid")
    @Tag("BVA")
    public void getDateMergedWithTimeBVAInvalidMinutesThrows2() {
        // arrange
        String time = "19:-1";
        Date noTimeDate = new GregorianCalendar(2023, Calendar.APRIL, 4).getTime();

        // act
        // assert
        Exception ex = assertThrows(IllegalArgumentException.class, () -> dateService.getDateMergedWithTime(time, noTimeDate));
    }

    @Order(11)
    @Test
    @DisplayName("getDateMergedWithTimeValid invalid date lower boundary")
    @Tag("Invalid")
    @Tag("BVA")
    public void getDateMergedWithTimeBVAInvalidDateThrows() {
        // arrange
        String time = "02:12";
        Date noTimeDate = new GregorianCalendar(1969, Calendar.DECEMBER, 31).getTime();

        // act
        // assert
        Exception ex = assertThrows(IllegalArgumentException.class, () -> dateService.getDateMergedWithTime(time, noTimeDate));
    }

    @Order(12)
    @Test
    @DisplayName("getDateMergedWithTimeValid valid hour, minute and date upper boundary")
    @Tag("Valid")
    @Tag("BVA")
    public void getDateMergedWithTimeBVAValidReturns3() {
        // arrange
        String time = "23:59";

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date(Long.MAX_VALUE));
        calendar.add(Calendar.DAY_OF_MONTH, -2);
        Date noTimeDate = calendar.getTime();

        calendar.setTime(new Date(Long.MAX_VALUE));
        calendar.add(Calendar.DAY_OF_MONTH, -2);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        Date expectedDate = calendar.getTime();

        // act
        Date date = dateService.getDateMergedWithTime(time, noTimeDate);

        // assert
        assertEquals(expectedDate, date);
    }

    @Order(13)
    @Test
    @DisplayName("getDateMergedWithTimeValid valid date upper boundary")
    @Tag("Valid")
    @Tag("BVA")
    public void getDateMergedWithTimeBVAValidReturns4() {
        // arrange
        String time = "19:35";

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(new Date(Long.MAX_VALUE));
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date noTimeDate = calendar.getTime();

        calendar.setTime(new Date(Long.MAX_VALUE));
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 35);
        Date expectedDate = calendar.getTime();

        // act
        Date date = dateService.getDateMergedWithTime(time, noTimeDate);

        // assert
        assertEquals(expectedDate, date);
    }
}
