package az.edu.turing;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class Family implements HumanCreator, Serializable {
    private Human mother;
    private Human father;
    private List<Human> children;
    private Set<Pet> pets;

    public Family(Human father, Human mother) {
        this.father = father;
        this.mother = mother;
        this.children = new ArrayList<>();
        this.pets = new HashSet<>();
    }

    public Human getMother() {
        return mother;
    }

    public void setMother(Human mother) {
        this.mother = mother;
    }

    public Human getFather() {
        return father;
    }

    public void setFather(Human father) {
        this.father = father;
    }

    public List<Human> getChildren() {
        return children;
    }

    public void setChildren(List<Human> children) {
        this.children = children;
    }

    public Set<Pet> getPet() {
        return pets;
    }

    public void setPet(Set<Pet> pets) {
        this.pets = pets;
    }


    public void addChild(Human child) {
        children.add(child);
        child.setFamily(this);

    }

    public boolean deleteChild(int index) {
        if (index < 0 || index > children.size()) {
            return false;
        }
        children.remove(index);
        return true;
    }

    public int countFamily() {
        return 2 + children.size();
    }
@Override
    public Human bornChild( Family family, String boyName, String girlName, boolean isBoy) {
        int iq = (father.getIq() + mother.getIq()) / 2;

        LocalDate birthDate = LocalDate.now();
        long birthDateMillis = birthDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();

        String name = isBoy ? boyName : girlName;

        if (isBoy) {
            return new Man(name, father.getSurname(), birthDateMillis, iq, null, this, null);
        } else {
            return new Woman(name, father.getSurname(), birthDateMillis, iq, null, this, null);
        }
    }



    public String prettyFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append("Family:\n");
        sb.append("    Mother: ").append(mother.prettyFormat()).append("\n");
        sb.append("    Father: ").append(father.prettyFormat()).append("\n");

        sb.append("    Children:\n");
        if (children.isEmpty()) {
            sb.append("        None\n");
        } else {
            for (Human child : children) {
                sb.append("        ").append(child.prettyFormat()).append("\n");
            }
        }

        sb.append("    Pets:\n");
        if (pets.isEmpty()) {
            sb.append("        None\n");
        } else {
            for (Pet pet : pets) {
                sb.append("        ").append(pet.prettyFormat()).append("\n");
            }
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return "Family{" +
                "mother=" + mother +
                ", father=" + father +
                ", children=" + children +
                ", pets=" + pets +
                '}';
    }
}
