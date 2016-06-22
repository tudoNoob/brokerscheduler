package tudonoob.brokerschedule.domain;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

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
