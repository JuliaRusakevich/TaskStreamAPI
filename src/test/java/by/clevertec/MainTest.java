package by.clevertec;


import by.clevertec.model.Animal;
import by.clevertec.model.Person;
import by.clevertec.util.Util;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void task1() {
        List<Animal> animals = Util.getAnimals();
        int countAnimalsInZoo = 7;

        List<Animal> expectedResult = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal.getAge() >= 10 && animal.getAge() <= 20) {
                expectedResult.add(animal);
            }
        }
        expectedResult.sort(Comparator.comparing(Animal::getAge));
        expectedResult = expectedResult.subList(2 * countAnimalsInZoo, 3 * countAnimalsInZoo);
        assertEquals(expectedResult, Main.task1(animals));
    }

    @Test
    void task2() {
        List<Animal> animals = Util.getAnimals();

        List<String> expectedResult = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal.getOrigin().equals("Japanese") && animal.getGender().equals("Female"))
                expectedResult.add(animal.getBread());
        }
        expectedResult.replaceAll(String::toUpperCase);
        assertEquals(expectedResult, Main.task2(animals));
    }

    @Test
    void task3() {
        List<Animal> animals = Util.getAnimals();
        List<String> expectedList = List.of("Azeri", "Aymara", "Afrikaans",
                "Arabic", "Armenian", "Amharic", "Assamese", "Albanian");
        assertEquals(expectedList, Main.task3(animals));
    }

    @Test
    void task4() {
        List<Animal> animals = Util.getAnimals();
        long expectedResult = 476;
        assertEquals(expectedResult, Main.task4(animals));
    }

    @Test
    void task5() {
        List<Animal> animals = Util.getAnimals();
        //Animal(id=19, bread=Mudskipper (unidentified), age=25, origin=Hungarian, gender=Female)
        Animal animal = new Animal(19, "Mudskipper (unidentified)", 25, "Hungarian", "Female");
        assertEquals(animal, Main.task5(animals));

    }

    @Test
    void task6() {
        List<Animal> animals = Util.getAnimals();
        boolean expectedResult = animals.stream()
                .map(Animal::getGender)
                .distinct()
                .allMatch(gender -> gender.equals("Male")
                                    || gender.equals("Female"));
        assertEquals(expectedResult, Main.task6(animals));
    }

    @Test
    void task7False() {
        List<Animal> animalsTest = List.of(
                new Animal(6, "Javan gold-spotted mongoose", 33, "Kurdish", "Male"),
                new Animal(7, "Southern boubou", 45, "Sotho", "Female"),
                new Animal(8, "Starling, red-shouldered glossy", 17, "Oceania", "Male")
        );
        assertFalse(Main.task7(animalsTest));
    }

    @Test
    void task7True() {
        List<Animal> animalsTest = List.of(
                new Animal(6, "Javan gold-spotted mongoose", 33, "Kurdish", "Male"),
                new Animal(7, "Southern boubou", 45, "Sotho", "Female"),
                new Animal(8, "Starling, red-shouldered glossy", 17, "Pashto", "Male")
        );
        assertTrue(Main.task7(animalsTest));
    }

    @Test
    void task8() {
        List<Animal> animalsTest = List.of(
                new Animal(6, "Javan gold-spotted mongoose", 33, "Kurdish", "Male"),
                new Animal(7, "Southern boubou", 45, "Sotho", "Female"),
                new Animal(8, "Starling, red-shouldered glossy", 17, "Pashto", "Male")
        );
        assertEquals(45, Main.task8(animalsTest));
    }

    @Test
    void task9() {
        List<Animal> animalsTest = List.of(
                new Animal(6, "Javan gold-spotted mongoose", 33, "Kurdish", "Male"),
                new Animal(7, "Southern boubou", 45, "Sotho", "Female"),
                new Animal(8, "Starling, red-shouldered glossy", 17, "Pashto", "Male")
        );
        int expectedResult = animalsTest.stream()
                .map(Animal::getBread)
                .map(String::length)
                .min(Integer::compareTo)
                .orElseThrow();
        assertEquals(expectedResult, Main.task9(animalsTest));
    }

    @Test
    void task10() {

        List<Animal> animalsTest = List.of(
                new Animal(6, "Javan gold-spotted mongoose", 33, "Kurdish", "Male"),
                new Animal(7, "Southern boubou", 45, "Sotho", "Female"),
                new Animal(8, "Starling, red-shouldered glossy", 17, "Pashto", "Male")
        );

        var expectedResult = animalsTest.stream()
                .mapToInt(Animal::getAge)
                .sum();

        assertEquals(expectedResult, Main.task10(animalsTest));
    }

    @Test
    void task11() {

        List<Animal> animalsTest = List.of(
                new Animal(6, "Javan gold-spotted mongoose", 33, "Indonesian", "Male"),
                new Animal(7, "Southern boubou", 45, "Sotho", "Female"),
                new Animal(8, "Starling, red-shouldered glossy", 17, "Indonesian", "Male")
        );

        var expectedResult = animalsTest.stream()
                .filter(animal -> animal.getOrigin().equals("Indonesian"))
                .mapToInt(Animal::getAge)
                .average()
                .orElseThrow();

        assertEquals(expectedResult, Main.task11(animalsTest));

    }

    @Test
    void task12() {

        List<Person> persons = Util.getPersons();
        List<Person> expectedResult = new ArrayList<>();

        for (Person person : persons) {
            int age = Period.between(person.getDateOfBirth(), LocalDate.now()).getYears();
            if (person.getGender().equals("Male") &&
                (age >= 18 && age <= 27)) {
                expectedResult.add(person);
            }
        }

        expectedResult.sort(Comparator.comparing(Person::getRecruitmentGroup));

        if (expectedResult.size() > 200)
            expectedResult = expectedResult.subList(0, 200);

        assertEquals(expectedResult, Main.task12(persons));

    }
}