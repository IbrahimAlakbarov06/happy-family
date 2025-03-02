package az.edu.turing;

import java.util.Map;
import java.util.Set;

public class Woman extends Human {
    public Woman(String name, String surname, long birthDate, int iq, Set<Pet> pets, Family family, Map<DayOfWeek, String> schedule) {
        super(name, surname, birthDate, iq, pets, family, schedule);
    }

    public void makeUp() {
        System.out.println(getName() + " is doing make up");
    }
}
