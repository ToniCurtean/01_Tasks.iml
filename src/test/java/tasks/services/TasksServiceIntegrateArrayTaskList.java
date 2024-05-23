package tasks.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.model.ArrayTaskList;
import tasks.model.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class TasksServiceIntegrateArrayTaskList {

    private Task mockTask;
    private ArrayTaskList atl;
    private TasksService tasksService;
    //private Date date;

    @BeforeEach
    public void setUp() {
        mockTask = mock(Task.class);
        atl = new ArrayTaskList();
        tasksService = new TasksService(atl);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAdd() {
        // arrange
        int preAddLength, postAddLength;
        Task insertedTask;

        // act
        preAddLength = tasksService.getAllTasks().size();
        tasksService.addTask(mockTask);
        postAddLength = tasksService.getAllTasks().size();
        insertedTask = tasksService.getAllTasks().get(postAddLength - 1);

        // assert
        assertEquals(0, preAddLength);
        assertEquals(1, postAddLength);
        assertEquals(mockTask, insertedTask);
    }

    @Test
    public void testRemove() {
        // arrange
        tasksService.addTask(mockTask);
        int preRemoveLength, postRemoveLength;
        boolean result;

        // act
        preRemoveLength = tasksService.getAllTasks().size();
        result = tasksService.removeTask(mockTask);
        postRemoveLength = tasksService.getAllTasks().size();

        // assert
        assertEquals(1, preRemoveLength);
        assertEquals(0, postRemoveLength);
        assertTrue(result);
    }


}
