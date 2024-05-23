package tasks.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class ArrayTaskListIsolationTest {
    private Task mockTask;
    private ArrayTaskList atl;

    @BeforeEach
    public void setUp() {
        mockTask = mock(Task.class);
        atl = new ArrayTaskList();
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
        preAddLength = atl.size();
        atl.add(mockTask);
        postAddLength = atl.size();
        insertedTask = atl.getTask(postAddLength - 1);

        // assert
        assertEquals(0, preAddLength);
        assertEquals(1, postAddLength);
        assertEquals(mockTask, insertedTask);
    }

    @Test
    public void testRemove() {
        // arrange
        atl.add(mockTask);
        int preRemoveLength, postRemoveLength;
        boolean result;

        // act
        preRemoveLength = atl.size();
        result = atl.remove(mockTask);
        postRemoveLength = atl.size();

        // assert
        assertEquals(1, preRemoveLength);
        assertEquals(0, postRemoveLength);
        assertTrue(result);
    }
}
