package tudonoob.brokerschedule.domainmodel;

import com.google.common.base.Preconditions;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class FullName {

    private String fullName;


    public FullName(String fullName) {
        validateFullName(fullName);
        this.fullName = fullName;
    }

    private void validateFullName(String fullName) {
        Preconditions.checkNotNull(fullName);
        Preconditions.checkArgument(!fullName.isEmpty());
        Preconditions.checkArgument(fullName.length() > 5);
        Preconditions.checkArgument(fullName.length() < 40);
    }
}
