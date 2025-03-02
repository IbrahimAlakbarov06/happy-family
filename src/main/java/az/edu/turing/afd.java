//package az.edu.turing;
//
//import java.time.Instant;
//import java.time.LocalDate;
//import java.time.Period;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class afd {
//}
//
// interface FamilyDao {
//    List<Family> getAllFamilies();
//    Family getFamilyByIndex(int index);
//    boolean deleteFamily(int index);
//    boolean deleteFamily(Family family);
//    void saveFamily(Family family);
//}
//class CollectionFamilyDao implements az.edu.turing.dao.FamilyDao {
//    List<Family> families = new ArrayList<>();
//
//    @Override
//    public List<Family> getAllFamilies() {
//        return families;
//    }
//
//    @Override
//    public Family getFamilyByIndex(int index) {
//        if (index< 0 || index > families.size()) {
//            return null;
//        }
//        return families.get(index);
//    }
//
//    @Override
//    public boolean deleteFamily(int index) {
//        if (index >= 0 && index < families.size()) {
//            families.remove(index);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean deleteFamily(Family family) {
//        return families.remove(family);
//    }
//
//    @Override
//    public void saveFamily(Family family) {
//        if (!families.contains(family)) {
//            families.add(family);
//        }
//    }
//}
//class FamilyService {
//    az.edu.turing.dao.FamilyDao familyDao;
//
//    public FamilyService(az.edu.turing.dao.FamilyDao dao) {
//        this.familyDao=dao;
//    }
//
//    public List<Family> getAllFamilies() {
//        return familyDao.getAllFamilies();
//    }
//
//    public void displayAllFamilies() {
//        familyDao.getAllFamilies().forEach(System.out::println);
//    }
//
//    public List<Family> getFamiliesBiggerThan(int count) {
//        return familyDao.getAllFamilies().stream().
//                filter(family -> family.countFamily() > count).
//                collect(Collectors.toList());
//    }
//
//    public List<Family> getFamiliesLessThan(int count) {
//        return familyDao.getAllFamilies().stream().
//                filter(family -> family.countFamily()< count).
//                collect(Collectors.toList());
//    }
//
//    public long countFamiliesWithMemberNumber(int number){
//        return familyDao.getAllFamilies().stream()
//                .filter(family -> family.countFamily() == number)
//                .count();
//    }
//
//    public void createNewFamily(Human father, Human mother){
//        Family newFamily = new Family(father, mother);
//        familyDao.saveFamily(newFamily);
//    }
//
//    public boolean deleteFamilyByIndex(int index){
//        return familyDao.deleteFamily(index);
//    }
//
//    public Family bornChild(Family family, String maleName, String femaleName) {
//        Human child = family.bornChild();
//        family.addChild(child);
//        familyDao.saveFamily(family);
//        return family;
//    }
//
//    public Family adoptChild(Family family, Human child) {
//        family.addChild(child);
//        familyDao.saveFamily(family);
//        return family;
//    }
//
//    public void deleteAllChildrenOlderThen(int number) {
//        getAllFamilies().forEach(family -> {
//            List<Human> updatedChildren = family.getChildren().stream()
//                    .filter(child -> child.getAge() <= number)
//                    .collect(Collectors.toList());
//            family.setChildren(updatedChildren);
//            familyDao.saveFamily(family);
//        });
//    }
//
//    public int count() {
//        return familyDao.getAllFamilies().size();
//    }
//
//    public Family getFamilyById(int id) {
//        return familyDao.getFamilyByIndex(id);
//    }
//
//    public List<Pet> getPets(int index) {
//        Family family = getFamilyById(index);
//        return new ArrayList<>(family.getPet());
//    }
//
//    public void addPet(int index, Pet pet) {
//        Family family = getFamilyById(index);
//        family.getPet().add(pet);
//        familyDao.saveFamily(family);
//    }
//}
//class FamilyController {
//
//    az.edu.turing.service.FamilyService familyService;
//
//    public FamilyController(az.edu.turing.service.FamilyService familyService) {
//        this.familyService = familyService;
//    }
//
//    public void displayAllFamilies() {
//        familyService.displayAllFamilies();
//    }
//
//    public List<Family> getFamiliesBiggerThan(int number) {
//        return familyService.getFamiliesBiggerThan(number);
//    }
//
//    public List<Family> getFamiliesLessThan(int number) {
//        return familyService.getFamiliesLessThan(number);
//    }
//
//    public long countFamiliesWithMemberNumber(int number) {
//        return familyService.countFamiliesWithMemberNumber(number);
//    }
//
//    public void createNewFamily(Human father, Human mother) {
//        familyService.createNewFamily(father, mother);
//    }
//
//    public boolean deleteFamilyByIndex(int index) {
//        return familyService.deleteFamilyByIndex(index);
//    }
//
//    public Family adoptChild(Family family, Human child) {
//        return familyService.adoptChild(family, child);
//    }
//
//    public void deleteAllChildrenOlderThen(int number) {
//        familyService.deleteAllChildrenOlderThen(number);
//    }
//
//    public int count() {
//        return familyService.count();
//    }
//
//    public Family getFamilyById(int id) {
//        return familyService.getFamilyById(id);
//    }
//
//    public List<Pet> getPets(int index) {
//        return familyService.getPets(index);
//    }
//
//    public void addPet(int index, Pet pet) {
//        familyService.addPet(index, pet);
//    }
//}
//class Family implements HumanCreator{
//    private Human mother;
//    private Human father;
//    private List<Human>  children;
//    private Set<Pet> pets;
//
//    public Family(Human father, Human mother){
//        this.father= father;
//        this.mother=mother;
//        this.children= new ArrayList<>();
//        this.pets=new HashSet<>();
//    }
//
//    public Human getMother() {
//        return mother;
//    }
//
//    public void setMother(Human mother) {
//        this.mother = mother;
//    }
//
//    public Human getFather() {
//        return father;
//    }
//
//    public void setFather(Human father) {
//        this.father = father;
//    }
//
//    public List<Human> getChildren() {
//        return children;
//    }
//
//    public void setChildren(List<Human> children) {
//        this.children = children;
//    }
//
//    public Set<Pet> getPet() {
//        return pets;
//    }
//
//    public void setPet(Set<Pet> pets) {
//        this.pets = pets;
//    }
//
//
//    public void addChild(Human child){
//        children.add(child);
//        child.setFamily(this);
//
//    }
//
//    public boolean deleteChild(int index){
//        if (index<0 || index> children.size()){
//            return false;
//        }
//        children.remove(index);
//        return true;
//    }
//
//    public int countFamily(){
//        return 2+ children.size();
//    }
//
//    @Override
//    public Human bornChild(){
//        Random random= new Random();
//        String[] randomNames = {"Alex", "John", "Maria", "Antonella"};
//        String name = randomNames[random.nextInt(randomNames.length)];
//        int iq = (father.getIq()+mother.getIq())/2;
//        return random.nextBoolean() ? new Man(name, father.getSurname(), 2025, iq, null, this, null) :new Woman(name, father.getSurname(), 2025, iq, null, this, null);
//    }
//
//    public String prettyFormat() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Family:\n");
//        sb.append("    Mother: ").append(mother.prettyFormat()).append("\n");
//        sb.append("    Father: ").append(father.prettyFormat()).append("\n");
//
//        sb.append("    Children:\n");
//        if (children.isEmpty()) {
//            sb.append("        None\n");
//        } else {
//            for (Human child : children) {
//                sb.append("        ").append(child.prettyFormat()).append("\n");
//            }
//        }
//
//        sb.append("    Pets:\n");
//        if (pets.isEmpty()) {
//            sb.append("        None\n");
//        } else {
//            for (Pet pet : pets) {
//                sb.append("        ").append(pet.prettyFormat()).append("\n");
//            }
//        }
//
//        return sb.toString();
//    }
//
//
//
//
//    @Override
//    public String toString() {
//        return "Family{" +
//                "mother=" + mother +
//                ", father=" + father +
//                ", children=" + children +
//                ", pets=" + pets +
//                '}';
//    }
//}
//class Human {
//    private String name;
//    private String surname;
//    private int age;
//    private long birthDate;
//    private int iq;
//    private Set<Pet> pets;
//    private Family family;
//    private Map<DayOfWeek, String> schedule;
//
//    public Human() {
//
//    }
//
//    public Human(String name, String surname, long birthDate, int iq ) {
//        this.name = name;
//        this.surname = surname;
//        this.birthDate = birthDate;
//        this.iq=iq;
//    }
//
//    public Human(String name, String surname, long birthDate, Family family) {
//        this.name = name;
//        this.surname = surname;
//        this.birthDate = birthDate;
//        this.family = family;
//    }
//
//    public Human(String name, String surname, long birthDate, int iq, Set<Pet> pets, Family family, Map<DayOfWeek, String> schedule) {
//        this.name = name;
//        this.surname = surname;
//        this.birthDate = birthDate;
//        this.iq = iq;
//        this.pets = pets;
//        this.family = family;
//        this.schedule = schedule;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getSurname() {
//        return surname;
//    }
//
//    public void setSurname(String surname) {
//        this.surname = surname;
//    }
//    public int getAge() {
//        return Period.between(Instant.ofEpochMilli(birthDate).atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears();
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public long getBirthDate() {
//        return birthDate;
//    }
//
//    public void setBirthDate(long birthDate) {
//        this.birthDate = birthDate;
//    }
//
//    public int getIq() {
//        return iq;
//    }
//
//    public void setIq(int iq) {
//        this.iq = iq;
//    }
//
//    public Set<Pet> getPets() {
//        return pets;
//    }
//
//    public void setPets(Set<Pet> pets) {
//        this.pets = pets;
//    }
//
//    public Family getFamily() {
//        return family;
//    }
//
//    public void setFamily(Family family) {
//        this.family = family;
//    }
//
//    public Map<DayOfWeek, String> getSchedule() {
//        return schedule;
//    }
//
//    public void setSchedule(Map<DayOfWeek, String> schedule) {
//        this.schedule = schedule;
//    }
//
//    public void greetPet() {
//        for (Pet pet : pets) {
//            System.out.println("Hello, " + pet.getNickname());
//        }
//    }
//
//    public void describePet() {
//        for (Pet pet : pets) {
//            System.out.println("I have an " + pet.getSpecies() + " is " + pet.getAge() + " years old, he is " + ((pet.getTrickLevel() > 50) ? "very sly" : "almost not sly" + "."));
//        }
//    }
//
//    public boolean feedPet(boolean isTime) {
//        Random random = new Random();
//        for (Pet pet : pets) {
//            if (isTime || pet.getTrickLevel() > random.nextInt(100)) {
//                System.out.println("Hm... I will feed " + name + "'s " + pet.toString());
//                return true;
//            } else {
//                System.out.println("I think " + name + "  is not hungry.");
//                return false;
//            }
//        }
//        return false;
//    }
//
//    public String prettyFormat() {
//        String formattedDate = Instant.ofEpochMilli(birthDate)
//                .atZone(ZoneId.systemDefault()).toLocalDate()
//                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//        return String.format("{name='%s', surname='%s', birthDate='%s', iq=%d}",
//                name, surname, formattedDate, iq);
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Human human = (Human) o;
//        return birthDate == human.birthDate && iq == human.iq && Objects.equals(name, human.name) && Objects.equals(surname, human.surname) && Objects.equals(pets, human.pets) && Objects.equals(family, human.family) && Objects.deepEquals(schedule, human.schedule);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name, surname, birthDate, iq, pets, family, schedule);
//    }
//
//    @Override
//    public String toString() {
//        String formattedDate = Instant.ofEpochMilli(birthDate).atZone(ZoneId.systemDefault()).toLocalDate()
//                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//        return String.format("Human{name='%s', surname='%s', birthDate='%s', iq=%d}", name, surname, formattedDate, iq);
//    }
//
//
//}
//abstract class Pet {
//    private Species species;
//    private String nickname;
//    private int age;
//    private int trickLevel;
//    private Set<String> habits;
//
//    public Pet(){
//
//    }
//
//    public Pet(Species species, String nickname){
//        this.species=species;
//        this.nickname=nickname;
//    }
//
//    public Pet(Species species, String nickname, int age, int trickLevel, String[] habits) {
//        this.species = species;
//        this.nickname = nickname;
//        this.age = age;
//        this.trickLevel = trickLevel;
//        this.habits = new HashSet<>();
//    }
//
//    public Species getSpecies() {
//        return species;
//    }
//
//    public void setSpecies(Species species) {
//        this.species = species;
//    }
//
//    public String getNickname() {
//        return nickname;
//    }
//
//    public void setNickname(String nickname) {
//        this.nickname = nickname;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public int getTrickLevel() {
//        return trickLevel;
//    }
//
//    public void setTrickLevel(int trickLevel) {
//        this.trickLevel = trickLevel;
//    }
//
//    public Set<String> getHabits() {
//        return habits;
//    }
//
//    public void setHabits(Set<String> habits) {
//        this.habits = habits;
//    }
//
//    public void eat(){
//        System.out.println("I am eating");
//    }
//
//    public abstract void respond();
//
//    public String prettyFormat() {
//        return String.format("{species=%s, nickname='%s', age=%d, trickLevel=%d, habits=%s}",
//                species, nickname, age, trickLevel, habits);
//    }
//
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Pet pet = (Pet) o;
//        return age == pet.age && trickLevel == pet.trickLevel && Objects.equals(species, pet.species) && Objects.equals(nickname, pet.nickname) && Objects.deepEquals(habits, pet.habits);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(species, nickname, age, trickLevel, habits);
//    }
//
//    @Override
//    public String toString() {
//        return "Pet{" +
//                "species=" + species +
//                ", nickname='" + nickname + '\'' +
//                ", age=" + age +
//                ", trickLevel=" + trickLevel +
//                ", habits=" + habits +
//                '}';
//    }
//}
//interface HumanCreator {
//    Human bornChild();
//}
//interface Foul {
//    void foul();
//}
//class FamilyOverflowException extends RuntimeException{
//    public FamilyOverflowException(String message){
//        super(message);
//    }
//}
//class Man extends Human{
//    public Man(String name, String surname, long birthDate, int iq, Set<Pet> pets, Family family, Map<DayOfWeek, String> schedule) {
//        super(name, surname, birthDate, iq, pets, family, schedule);
//    }
//
//    public void repairCar(){
//        System.out.println(getName() + " is repairing his car.");
//    }
//}
//class Woman extends Human{
//    public Woman(String name, String surname, long birthDate, int iq, Set<Pet> pets, Family family, Map<DayOfWeek, String> schedule) {
//        super(name, surname, birthDate, iq, pets, family, schedule);
//    }
//
//    public void makeUp(){
//        System.out.println(getName()+" is doing make up");
//    }
//}
//
//class ConsoleApp {
//    az.edu.turing.service.FamilyService familyService;
//    Scanner scanner;
//
//    public ConsoleApp(az.edu.turing.service.FamilyService familyService) {
//        this.familyService = familyService;
//        this.scanner = new Scanner(System.in);
//    }
//
//    public void displayMenu() {
//        System.out.println("""
//                1. Fill with test data
//                2. Display all families
//                3. Display families bigger than specified number
//                4. Display families smaller than specified number
//                5. Count families with a certain number of members
//                6. Create a new family
//                7. Delete a family by index
//                8. Edit a family
//                9. Remove children older than specified age
//                0. Exit
//                """);
//    }
//
//    public void run() {
//        while (true) {
//            displayMenu();
//            System.out.println("Choose an option: ");
//            int choice = scanner.nextInt();
//            switch (choice) {
//                case 1 -> fillWithTestData();
//                case 2 -> familyService.displayAllFamilies();
//                case 3 -> displayFamiliesBiggerThan();
//                case 4 -> displayFamiliesLessThan();
//                case 5 -> countFamiliesWithMembers();
//                case 6 -> createNewFamily();
//                case 7 -> deleteFamilyByIndex();
//                case 8 -> editFamily();
//                case 9 -> removeChildrenOlderThan();
//                case 0 -> {
//                    System.out.println("Exiting...");
//                    return;
//                }
//                default -> System.out.println("Invalid choice. Try again.");
//            }
//        }
//    }
//
//    public void fillWithTestData() {
//        Human father = new Human("Ali", "Muhammed", 1997, 90);
//        Human mother = new Human("Sara", "Muhammed", 1999, 50);
//        Family family = new Family(mother, father);
//        familyService.createNewFamily(mother, father);
//        System.out.println("Test data added.");
//    }
//
//    public void displayFamiliesBiggerThan() {
//        System.out.println("Enter the number: ");
//        int count = scanner.nextInt();
//        familyService.getFamiliesBiggerThan(count).forEach(family -> System.out.println(family.prettyFormat()));
//    }
//
//    public void displayFamiliesLessThan() {
//        System.out.println("Enter the number: ");
//        int count = scanner.nextInt();
//        familyService.getFamiliesLessThan(count).forEach(family -> System.out.println(family.prettyFormat()));
//    }
//
//
//    public void countFamiliesWithMembers() {
//        System.out.print("Enter the number: ");
//        int count = scanner.nextInt();
//        long families = familyService.countFamiliesWithMemberNumber(count);
//        System.out.println("Number of families: " + families);
//    }
//
//    public Human createHuman() {
//        System.out.print("Enter name: ");
//        String name = scanner.next();
//        System.out.print("Enter surname: ");
//        String surname = scanner.next();
//        System.out.print("Enter birthday year: ");
//        int year = scanner.nextInt();
//        System.out.print("Enter IQ: ");
//        int iq = scanner.nextInt();
//        return new Human(name, surname, year, iq);
//    }
//
//    public void createNewFamily() {
//        System.out.println("Enter Mother's details: ");
//        Human mother = createHuman();
//        System.out.println("Enter father's details:");
//        Human father = createHuman();
//        familyService.createNewFamily(mother, father);
//        System.out.println("New family created.");
//    }
//
//    public void deleteFamilyByIndex() {
//        System.out.println("Enter family index to delete: ");
//        int index = scanner.nextInt();
//        if (familyService.deleteFamilyByIndex(index)) {
//            System.out.println("Family deleted.");
//        } else {
//            System.out.println("Invalid index.");
//        }
//    }
//
//    public void editFamily() {
//        System.out.println("Enter family ID: ");
//        int id = scanner.nextInt();
//        System.out.println("""
//                1. Give birth to a baby
//                2. Adopt a child
//                3. Return to main menu
//                """);
//        System.out.println("Enter a choice: ");
//        int choice = scanner.nextInt();
//
//        Family family = familyService.getAllFamilies().get(id);
//        if (family == null) {
//            System.out.println("Invalid family ID");
//        }
//
//        switch (choice) {
//            case 1->{
//                System.out.print("Enter a boy name: ");
//                String boyName = scanner.next();
//                System.out.print("Enter a girl name: ");
//                String girlName = scanner.next();
//                try {
//                    familyService.bornChild(family, boyName, girlName);
//                    System.out.println("Child added.");
//                } catch (FamilyOverflowException e) {
//                    System.out.println(e.getMessage());
//                }
//
//            }case 2-> {
//                System.out.println("Enter child's details: ");
//                Human child = createHuman();
//                family.getChildren().add(child);
//                System.out.println("Child adopted");
//
//            }case 3-> {
//                return;
//
//            }default-> {
//                System.out.println("Invalid choice.");
//            }
//        }
//    }
//
//    public void removeChildrenOlderThan(){
//        System.out.println("Enter an age: ");
//        int age = scanner.nextInt();
//        familyService.deleteAllChildrenOlderThen(age);
//        System.out.println("Childolder than " + age + " removed.");
//    }
//    public static void main(String[] args) {
//        az.edu.turing.dao.FamilyDao dao = new az.edu.turing.dao.CollectionFamilyDao();
//        az.edu.turing.service.FamilyService service = new az.edu.turing.service.FamilyService(dao);
//        new ConsoleApp(service).run();
//    }
//}
//
//
//
//
//
//
