package tudonoob.brokerschedule.model;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrokerModel {

    @NotNull
    @NotEmpty
    private String name;
    private List<DayModel> constrains;

    public BrokerModel(String name) {
        this.name = name;
    }

}
