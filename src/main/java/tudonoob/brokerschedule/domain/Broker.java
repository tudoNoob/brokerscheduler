package tudonoob.brokerschedule.domain;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;


public class Broker {

    @NotNull
    @NotEmpty
    private String name;
    private List<Day> constrains;

    public Broker() {
    }

    public Broker(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Broker{" +
                "name='" + name + '\'' +
                ", constrains=" + constrains +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Day> getConstrains() {
        return constrains;
    }

    public void setConstrains(List<Day> constrains) {
        this.constrains = constrains;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Broker broker = (Broker) o;

        if (name != null ? !name.equals(broker.name) : broker.name != null) return false;

        return true;

    }


}
