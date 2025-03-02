package az.edu.turing.service;

import az.edu.turing.Family;
import az.edu.turing.Human;
import az.edu.turing.Pet;
import az.edu.turing.dao.FamilyDao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FamilyService {
    FamilyDao familyDao;

    public FamilyService(FamilyDao dao) {
        this.familyDao = dao;
    }

    public List<Family> getAllFamilies() {
        return familyDao.getAllFamilies();
    }

    public void displayAllFamilies() {
        familyDao.getAllFamilies().forEach(System.out::println);
    }

    public List<Family> getFamiliesBiggerThan(int count) {
        return familyDao.getAllFamilies().stream().
                filter(family -> family.countFamily() > count).
                collect(Collectors.toList());
    }

    public List<Family> getFamiliesLessThan(int count) {
        return familyDao.getAllFamilies().stream().
                filter(family -> family.countFamily() < count).
                collect(Collectors.toList());
    }

    public long countFamiliesWithMemberNumber(int number) {
        return familyDao.getAllFamilies().stream()
                .filter(family -> family.countFamily() == number)
                .count();
    }

    public void createNewFamily(Human father, Human mother) {
        Family newFamily = new Family(father, mother);
        familyDao.saveFamily(newFamily);
    }

    public boolean deleteFamilyByIndex(int index) {
        return familyDao.deleteFamily(index);
    }

    public Family bornChild(Family family, String boyName, String girlName, boolean isBoy) {
        Human child = family.bornChild(family, boyName, girlName, isBoy);
        family.addChild(child);
        familyDao.saveFamily(family);
        return family;
    }


    public Family adoptChild(Family family, Human child) {
        family.addChild(child);
        familyDao.saveFamily(family);
        return family;
    }

    public void deleteAllChildrenOlderThen(int number) {
        getAllFamilies().forEach(family -> {
            List<Human> updatedChildren = family.getChildren().stream()
                    .filter(child -> child.getAge() <= number)
                    .collect(Collectors.toList());
            family.setChildren(updatedChildren);
            familyDao.saveFamily(family);
        });
    }

    public int count() {
        return familyDao.getAllFamilies().size();
    }

    public Family getFamilyById(int id) {
        return familyDao.getFamilyByIndex(id);
    }

    public List<Pet> getPets(int index) {
        Family family = getFamilyById(index);
        return new ArrayList<>(family.getPet());
    }

    public void addPet(int index, Pet pet) {
        Family family = getFamilyById(index);
        family.getPet().add(pet);
        familyDao.saveFamily(family);
    }

    public void saveData() {
        familyDao.saveData();
    }

    public void loadData() {
        familyDao.loadData();
    }
}
