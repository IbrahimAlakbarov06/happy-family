package az.edu.turing;

import az.edu.turing.dao.CollectionFamilyDao;
import az.edu.turing.dao.FamilyDao;
import az.edu.turing.service.FamilyService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ConsoleApp {
    FamilyService familyService;
    Scanner scanner;

    public ConsoleApp(FamilyService familyService) {
        this.familyService = familyService;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("""
                1. Load saved data
                2. Save data
                3. Fill with test data
                4. Display all families
                5. Display families bigger than specified number
                6. Display families smaller than specified number
                7. Count families with a certain number of members
                8. Create a new family
                9. Delete a family by index
                10. Edit a family
                11. Remove children older than specified age
                0. Exit
                """);
    }

    public void run() {
        while (true) {
            displayMenu();
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> familyService.loadData();
                case 2 -> familyService.saveData();
                case 3 -> fillWithTestData();
                case 4 -> familyService.getAllFamilies().forEach(System.out::println);
                case 5 -> displayFamiliesBiggerThan();
                case 6 -> displayFamiliesLessThan();
                case 7 -> countFamiliesWithMembers();
                case 8 -> createNewFamily();
                case 9 -> deleteFamilyByIndex();
                case 10 -> editFamily();
                case 11 -> removeChildrenOlderThan();
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public void fillWithTestData() {
        long fatherBirthDate = LocalDate.of(1997, 1, 1)
                .atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long motherBirthDate = LocalDate.of(1999, 1, 1)
                .atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();

        Human father = new Human("Ali", "Muhammed", fatherBirthDate, 90);
        Human mother = new Human("Sara", "Muhammed", motherBirthDate, 50);

        Family family = new Family(mother, father);
        familyService.createNewFamily(mother, father);
        System.out.println("Test data added.");
    }


    public void displayFamiliesBiggerThan() {
        System.out.print("Enter the number: ");
        int count = scanner.nextInt();
        familyService.getFamiliesBiggerThan(count).forEach(family -> System.out.println(family.prettyFormat()));
    }

    public void displayFamiliesLessThan() {
        System.out.print("Enter the number: ");
        int count = scanner.nextInt();
        familyService.getFamiliesLessThan(count).forEach(family -> System.out.println(family.prettyFormat()));
    }

    public void countFamiliesWithMembers() {
        System.out.print("Enter the number: ");
        int count = scanner.nextInt();
        long families = familyService.countFamiliesWithMemberNumber(count);
        System.out.println("Number of families: " + families);
    }

    public Human createHuman() {
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter surname: ");
        String surname = scanner.next();
        System.out.print("Enter birth date (dd/MM/yyyy): ");
        String birthDateString = scanner.next();

        long birthDate = LocalDate.parse(birthDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                .atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();

        System.out.print("Enter IQ: ");
        int iq = scanner.nextInt();

        return new Human(name, surname, birthDate, iq);
    }

    public void createNewFamily() {
        System.out.println("Enter Mother's details: ");
        Human mother = createHuman();
        System.out.println("Enter father's details:");
        Human father = createHuman();
        familyService.createNewFamily(mother, father);
        System.out.println("New family created.");
    }

    public void deleteFamilyByIndex() {
        System.out.print("Enter family index to delete: ");
        int index = scanner.nextInt();
        if (familyService.deleteFamilyByIndex(index)) {
            System.out.println("Family deleted.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void editFamily() {
        System.out.print("Enter family ID: ");
        int id = scanner.nextInt();
        System.out.println("""
                1. Give birth to a baby
                2. Adopt a child
                3. Return to main menu
                """);
        System.out.print("Enter a choice: ");
        int choice = scanner.nextInt();

        Family family = familyService.getAllFamilies().get(id);
        if (family == null) {
            System.out.println("Invalid family ID");
        }

        switch (choice) {
            case 1 -> {
                System.out.print("Enter a boy name: ");
                String boyName = scanner.next();
                System.out.print("Enter a girl name: ");
                String girlName = scanner.next();
                System.out.print("Enter gender (true for boy, false for girl): ");
                boolean isBoy = scanner.nextBoolean();

                try {
                    familyService.bornChild(family, boyName, girlName, isBoy);
                    System.out.println("Child added.");
                } catch (FamilyOverflowException e) {
                    System.out.println(e.getMessage());
                }
            }
            case 2 -> {
                System.out.println("Enter child's details: ");
                Human child = createHuman();
                family.getChildren().add(child);
                System.out.println("Child adopted");

            }
            case 3 -> {
                return;

            }
            default -> {
                System.out.println("Invalid choice.");
            }
        }
    }

    public void removeChildrenOlderThan() {
        System.out.print("Enter an age: ");
        int age = scanner.nextInt();
        familyService.deleteAllChildrenOlderThen(age);
        System.out.println("Child older than " + age + " removed.");
    }

    public static void main(String[] args) {
        FamilyDao dao = new CollectionFamilyDao();
        FamilyService service = new FamilyService(dao);
        new ConsoleApp(service).run();
    }
}
