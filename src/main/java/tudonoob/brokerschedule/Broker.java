package tudonoob.brokerschedule;

import java.util.List;

public class Broker {

    private String name;
    private List<Day> constrains;

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
}
