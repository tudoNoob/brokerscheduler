package tudonoob.brokerschedule.model;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class Broker {

    @NotNull
    @NotEmpty
    private String name;
    private List<Day> constrains;

    public Broker(String name) {
        this.name = name;
    }

}
