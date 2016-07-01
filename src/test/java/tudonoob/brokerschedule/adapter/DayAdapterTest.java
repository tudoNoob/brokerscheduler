package tudonoob.brokerschedule.adapter;

import org.junit.Before;
import org.junit.Test;
import tudonoob.brokerschedule.domainmodel.Day;
import tudonoob.brokerschedule.model.DayModel;

import static org.junit.Assert.*;

public class DayAdapterTest {


    private DayAdapter adapter;

    @Before
    public void setUp() throws Exception {
        adapter = new DayAdapter();
    }

    @Test(expected = RuntimeException.class)
    public void shouldBeAbleToThrowAnExceptionDueToNullBoolean() throws Exception {
        DayModel model = new DayModel();
        model.setIsAvailableAfternoon(null);
        adapter.adaptDayModelToDay(model);
    }

    @Test(expected = RuntimeException.class)
    public void shouldBeAbleToThrowExceptionForBadDayName() throws Exception {
        DayModel model = new DayModel();
        model.setDayName("frida");
        adapter.adaptDayModelToDay(model);
    }

    @Test
    public void shouldBeAbleToCreateTheDay() throws Exception {
        DayModel model = new DayModel();
        model.setDayName("friday");
        Day day = adapter.adaptDayModelToDay(model);
        assertNotNull(day);
    }


}