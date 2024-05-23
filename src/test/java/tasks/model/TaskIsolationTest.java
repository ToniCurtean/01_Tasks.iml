package tasks.model;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskIsolationTest {

    @Test
    public void testCreate_Task() {
        // arrange
        Date date = new GregorianCalendar(2023, Calendar.MARCH, 31).getTime();

        // act
        Task task = new Task("task1", date);

        // assert
        assertEquals(date, task.getTime() );
        assertEquals("task1", task.getTitle());
    }

    @Test
    public void testSetTime_Task() {
        // arrange
        Date date = new GregorianCalendar(2023, Calendar.MARCH, 31).getTime();
        Date date2 = new GregorianCalendar(2020, Calendar.MARCH, 30).getTime();
        Task task = new Task("task1", date);

        // act
        task.setTime(date2);

        // assert
        assertEquals(date2, task.getTime());
    }
}
