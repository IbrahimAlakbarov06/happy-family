package az.edu.turing;

import java.util.Map;
import java.util.Set;

public class Man extends Human {
    public Man(String name, String surname, long birthDate, int iq, Set<Pet> pets, Family family, Map<DayOfWeek, String> schedule) {
        super(name, surname, birthDate, iq, pets, family, schedule);
    }

    public void repairCar() {
        System.out.println(getName() + " is repairing his car.");
    }
}
