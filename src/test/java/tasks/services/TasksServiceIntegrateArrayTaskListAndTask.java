package tasks.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.model.ArrayTaskList;
import tasks.model.Task;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class TasksServiceIntegrateArrayTaskListAndTask {

    private TasksService taskService;
    private ArrayTaskList arrayTaskList;
    private Date date;

    @BeforeEach
    public void setUp() {
        date = new GregorianCalendar(2023, Calendar.MARCH, 31).getTime();
        arrayTaskList = new ArrayTaskList();
        taskService = new TasksService(arrayTaskList);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void test_addTask_addsInRepo() {
        // arrange
        Task task = new Task("task1", date);

        // act
        int lenBeforeAdd = arrayTaskList.size();
        taskService.addTask(task);
        int lenAfterAdd = arrayTaskList.size();

        // assert
        assertEquals(1, lenAfterAdd - lenBeforeAdd);            // one task added
        assertEquals(task, arrayTaskList.getTask(lenAfterAdd - 1));     // task was added
    }

    @Test
    public void test_removeTask_removesFromRepo() {
        // arrange
        Task task = new Task("task1", date);
        arrayTaskList.add(task);
        arrayTaskList.add(new Task("task2", date));
        arrayTaskList.add(new Task("task3", date));

        // act
        int lenBeforeAdd = arrayTaskList.size();
        boolean result = taskService.removeTask(task);
        int lenAfterAdd = arrayTaskList.size();

        // assert
        assertTrue(result);
        assertEquals(1, lenBeforeAdd - lenAfterAdd);            // one task removed
        for (Task t: arrayTaskList.getAll()) {
            assertNotEquals(task, t);
        }
    }

    @Test
    public void test_removeTask_taskNotFound() {
        // arrange
        Task task = new Task("task1", date);
        arrayTaskList.add(new Task("task2", date));
        arrayTaskList.add(new Task("task3", date));

        // act
        int lenBeforeAdd = arrayTaskList.size();
        boolean result = taskService.removeTask(task);
        int lenAfterAdd = arrayTaskList.size();

        // assert
        assertFalse(result);
        assertEquals(0, lenBeforeAdd - lenAfterAdd);            // no task removed
        for (Task t: arrayTaskList.getAll()) {
            assertNotEquals(task, t);
        }
    }

    @Test
    public void test_removeTask_emptyRepo() {
        // arrange
        Task task = new Task("task1", date);

        // act
        boolean result = taskService.removeTask(task);

        // assert
        assertFalse(result);
        assertEquals(0, arrayTaskList.size());
    }


}
