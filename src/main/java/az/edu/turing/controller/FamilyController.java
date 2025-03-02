package az.edu.turing.controller;

import az.edu.turing.Family;
import az.edu.turing.service.FamilyService;
import az.edu.turing.Human;
import az.edu.turing.Pet;

import java.util.List;

public class FamilyController {

    FamilyService familyService;

    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    public void displayAllFamilies() {
        familyService.displayAllFamilies();
    }

    public List<Family> getFamiliesBiggerThan(int number) {
        return familyService.getFamiliesBiggerThan(number);
    }

    public List<Family> getFamiliesLessThan(int number) {
        return familyService.getFamiliesLessThan(number);
    }

    public long countFamiliesWithMemberNumber(int number) {
        return familyService.countFamiliesWithMemberNumber(number);
    }

    public void createNewFamily(Human father, Human mother) {
        familyService.createNewFamily(father, mother);
    }

    public boolean deleteFamilyByIndex(int index) {
        return familyService.deleteFamilyByIndex(index);
    }

    public Family adoptChild(Family family, Human child) {
        return familyService.adoptChild(family, child);
    }

    public void deleteAllChildrenOlderThen(int number) {
        familyService.deleteAllChildrenOlderThen(number);
    }

    public int count() {
        return familyService.count();
    }

    public Family getFamilyById(int id) {
        return familyService.getFamilyById(id);
    }

    public List<Pet> getPets(int index) {
        return familyService.getPets(index);
    }

    public void addPet(int index, Pet pet) {
        familyService.addPet(index, pet);
    }

    public void saveData() {
        familyService.saveData();
    }

    public void loadData() {
        familyService.loadData();
    }
}
