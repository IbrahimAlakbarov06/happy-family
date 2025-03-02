package az.edu.turing;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Human implements Serializable {
    private String name;
    private String surname;
    private int age;
    private long birthDate;
    private int iq;
    private Set<Pet> pets;
    private Family family;
    private Map<DayOfWeek, String> schedule;

    public Human() {

    }

    public Human(String name, String surname, long birthDate, int iq) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.iq = iq;
    }

    public Human(String name, String surname, long birthDate, Family family) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.family = family;
    }

    public Human(String name, String surname, long birthDate, int iq, Set<Pet> pets, Family family, Map<DayOfWeek, String> schedule) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.iq = iq;
        this.pets = pets;
        this.family = family;
        this.schedule = schedule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return Period.between(Instant.ofEpochMilli(birthDate).atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(long birthDate) {
        this.birthDate = birthDate;
    }

    public int getIq() {
        return iq;
    }

    public void setIq(int iq) {
        this.iq = iq;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public Map<DayOfWeek, String> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<DayOfWeek, String> schedule) {
        this.schedule = schedule;
    }

    public void greetPet() {
        for (Pet pet : pets) {
            System.out.println("Hello, " + pet.getNickname());
        }
    }

    public void describePet() {
        for (Pet pet : pets) {
            System.out.println("I have an " + pet.getSpecies() + " is " + pet.getAge() + " years old, he is " + ((pet.getTrickLevel() > 50) ? "very sly" : "almost not sly" + "."));
        }
    }

    public boolean feedPet(boolean isTime) {
        Random random = new Random();
        for (Pet pet : pets) {
            if (isTime || pet.getTrickLevel() > random.nextInt(100)) {
                System.out.println("Hm... I will feed " + name + "'s " + pet.toString());
                return true;
            } else {
                System.out.println("I think " + name + "  is not hungry.");
                return false;
            }
        }
        return false;
    }

    public String prettyFormat() {
        String formattedDate = Instant.ofEpochMilli(birthDate)
                .atZone(ZoneId.systemDefault()).toLocalDate()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return String.format("{name='%s', surname='%s', birthDate='%s', iq=%d}",
                name, surname, formattedDate, iq);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return birthDate == human.birthDate && iq == human.iq && Objects.equals(name, human.name) && Objects.equals(surname, human.surname) && Objects.equals(pets, human.pets) && Objects.equals(family, human.family) && Objects.deepEquals(schedule, human.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, birthDate, iq, pets, family, schedule);
    }

    @Override
    public String toString() {
        String formattedDate = Instant.ofEpochMilli(birthDate).atZone(ZoneId.systemDefault()).toLocalDate()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return String.format("Human{name='%s', surname='%s', birthDate='%s', iq=%d}", name, surname, formattedDate, iq);
    }
}
