package tudonoob.brokerschedule.domainmodel;

import com.google.common.base.Preconditions;
import lombok.Getter;

@Getter
public class Shift {

    private Boolean availability;

    public Shift(Boolean availability) {
        validateAvailability(availability);
        this.availability = availability;
    }

    private void validateAvailability(Boolean availability) {
        Preconditions.checkNotNull(availability);
    }
}
