package tudonoob.brokerschedule.adapter;

import org.apache.tomcat.util.digester.ArrayStack;
import org.junit.Before;
import org.junit.Test;
import tudonoob.brokerschedule.domainmodel.WeekDays;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class WeekDaysAdapterTest {

    private WeekDaysAdapter adapter;

    @Before
    public void setUp() throws Exception {
        adapter = new WeekDaysAdapter();
    }

    @Test(expected = RuntimeException.class)
    public void shouldBeAbleToThrowRuntimeExceptionDueToListNull() throws Exception {
        adapter.adaptDaysListToWeekDaysList(null);
    }

    @Test
    public void shouldBeAbleToCreateTheWeekDay() throws Exception {
        WeekDays weekDays = adapter.adaptDaysListToWeekDaysList(new ArrayList<>());
        assertNotNull(weekDays);
    }


}