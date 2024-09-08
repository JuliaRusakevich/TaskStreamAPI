package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.model.Car;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.Student;
import by.clevertec.util.Constants;
import by.clevertec.util.Util;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static by.clevertec.util.Constants.ADULT_AGE;
import static by.clevertec.util.Constants.COUNT_ANIMALS_IN_ZOO;
import static by.clevertec.util.Constants.COUNT_DAYS_IN_YEAR;
import static by.clevertec.util.Constants.COUNT_OF_PEOPLE_IN_FRENCH_LEGION;
import static by.clevertec.util.Constants.COUNT_SKIPPED_ZOO;
import static by.clevertec.util.Constants.COUNT_YEARS_FOR_TASK_15;
import static by.clevertec.util.Constants.FEMALE;
import static by.clevertec.util.Constants.GROUP_C_2_FOR_TASK_19;
import static by.clevertec.util.Constants.HUNGARIAN;
import static by.clevertec.util.Constants.INDONESIAN;
import static by.clevertec.util.Constants.JAPANESE;
import static by.clevertec.util.Constants.MALE;
import static by.clevertec.util.Constants.MAX_AGE_TASK_2;
import static by.clevertec.util.Constants.MAX_AGE_TASK_3;
import static by.clevertec.util.Constants.MAX_AGE_TASK_5;
import static by.clevertec.util.Constants.MAX_MILITARY_AGE;
import static by.clevertec.util.Constants.MIN_AGE_TASK_1;
import static by.clevertec.util.Constants.MIN_AGE_TASK_5;
import static by.clevertec.util.Constants.MIN_EXAMINATION_MARK;
import static by.clevertec.util.Constants.MIN_MILITARY_AGE;
import static by.clevertec.util.Constants.OCEANIA;
import static by.clevertec.util.Constants.PREFIX_A;
import static by.clevertec.util.Constants.PRICE_OF_ONE_CUBIC_METER_OF_WATER;
import static by.clevertec.util.Constants.REGEX_S_C_TASK_15;
import static by.clevertec.util.Constants.RETIREMENT_AGE;
import static by.clevertec.util.Constants.VASE_MATERIAL_ALUMINUM;
import static by.clevertec.util.Constants.VASE_MATERIAL_GLASS;
import static by.clevertec.util.Constants.VASE_MATERIAL_STEEL;

public class Main {

    public static void main(String[] args) {

        List<Animal> animals = Util.getAnimals();
        List<Person> persons = Util.getPersons();
        List<Flower> flowers = Util.getFlowers();
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        List<House> houses = Util.getHouses();

        task1(animals);
        task2(animals);
        task3(animals);
        task4(animals);
        task5(animals);
        task6(animals);
        task7(animals);
        task8(animals);
        task9(animals);
        task10(animals);
        task10_1(animals);
        task11(animals);
        task12(persons);
        task13(houses);
        // task14();
        task15(flowers);
        task16(students);
        task17(students);
        task18(students);
        task18_1(students);
        task19(students, examinations);
        task20(students, examinations);
        task21(students);
        task22(students);

    }

    public static List<Animal> task1(List<Animal> animals) {
        return animals.stream()
                .filter(animal -> isAnimalInAgeInterval(animal, MIN_AGE_TASK_1, MAX_AGE_TASK_2))
                .sorted(Comparator.comparing(Animal::getAge))
                .skip(COUNT_SKIPPED_ZOO * COUNT_ANIMALS_IN_ZOO)
                .limit(COUNT_ANIMALS_IN_ZOO)
                .peek(animal -> System.out.println(animal.getBread() + " : age - " + animal.getAge()))
                .toList();
    }

    public static List<String> task2(List<Animal> animals) {
        return animals.stream()
                .filter(animal -> isAnimalOrigin(animal, JAPANESE)
                                  && isGender(animal, FEMALE))
                .peek(System.out::println)
                .map(animal -> animal.getBread().toUpperCase())
                .peek(System.out::println)
                .toList();

    }

    public static List<String> task3(List<Animal> animals) {
        return animals.stream()
                .filter(animal -> animal.getAge() > MAX_AGE_TASK_3)
                .map(Animal::getOrigin)
                .distinct()
                .filter(origin -> origin.startsWith(PREFIX_A))
                .peek(System.out::println)
                .toList();
    }

    public static long task4(List<Animal> animals) {
        return animals.stream()
                .filter(animal -> isGender(animal, FEMALE))
                .count();
    }

    public static Animal task5(List<Animal> animals) {
        return animals.stream()
                .filter(animal -> isAnimalInAgeInterval(animal, MIN_AGE_TASK_5, MAX_AGE_TASK_5)
                                  && isAnimalOrigin(animal, HUNGARIAN))
                .findFirst()
                .orElseThrow();
    }

    public static boolean task6(List<Animal> animals) {
        var isAllGenderMaleAndFemale = animals.stream()
                .allMatch(animal -> isGender(animal, FEMALE)
                                    && isGender(animal, MALE));
        System.out.println("Все ли они пола Male и Female ?\n" + isAllGenderMaleAndFemale);
        return isAllGenderMaleAndFemale;
    }

    public static boolean task7(List<Animal> animals) {
        var isOriginOfAnimalOceania = animals.stream()
                .noneMatch(animal -> isAnimalOrigin(animal, OCEANIA));
        System.out.println("Действительно ли, что ни одно из животных не имеет страну происхождения Oceania? \n" + isOriginOfAnimalOceania);
        return isOriginOfAnimalOceania;
    }

    public static int task8(List<Animal> animals) {
        var animalWithMaxAge = animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .peek(animal -> System.out.println(animal.getBread() + " : age " + animal.getAge()))
                .mapToInt(Animal::getAge)
                .max()
                .orElseThrow(NoSuchElementException::new);
        System.out.println("Возраст самого старого животного : " + animalWithMaxAge);
        return animalWithMaxAge;
    }

    public static int task9(List<Animal> animals) {
        var minBreadLength = animals.stream()
                .map(Animal::getBread)
                .peek(System.out::println)
                .map(String::toCharArray)
                .map(bread -> bread.length)
                .min(Integer::compareTo)
                .orElseThrow(NoSuchElementException::new);
        System.out.println("Длина самого короткого массива (=породы) : " + minBreadLength);
        return minBreadLength;
    }

    public static int task10(List<Animal> animals) {
        var result = animals.stream()
                .mapToInt(Animal::getAge)
                .sum();
        System.out.println("Суммарный возраст всех животных : " + result);
        return result;
    }

    public static int task10_1(List<Animal> animals) {
        var result = animals.stream()
                .reduce(0, (partialAgeResult, animal) ->
                        partialAgeResult + animal.getAge(), Integer::sum);
        System.out.println("Суммарный возраст всех животных : " + result);
        return result;
    }

    public static double task11(List<Animal> animals) {
        var averageAgeIndonesianAnimals = animals.stream()
                .filter(animal -> isAnimalOrigin(animal, INDONESIAN))
                .mapToInt(Animal::getAge)
                .average()
                .orElseThrow();
        System.out.println("Средний возраст всех животных из Индонезии " + averageAgeIndonesianAnimals);
        return averageAgeIndonesianAnimals;
    }

    public static List<Person> task12(List<Person> persons) {
        return persons.stream()
                .filter(person -> person.getGender().equals(MALE)
                                  && (Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() >= MIN_MILITARY_AGE
                                      && Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() <= MAX_MILITARY_AGE))
                .sorted(Comparator.comparing(Person::getRecruitmentGroup))
                .limit(COUNT_OF_PEOPLE_IN_FRENCH_LEGION)
                .peek(System.out::println)
                .toList();
    }

    public static List<Person> task13(List<House> houses) {
        return houses.stream()

                .flatMap(house -> {
                    // в первую очередь обойти дома и эвакуировать больных и раненых (из Hospital)
                    if (house.getBuildingType().equals("Hospital")) {
                        return house.getPersonList().stream();
                    } else
                        //во вторую очередь детей и стариков (до 18 и пенсионного возраста)
                        return house.getPersonList().stream()
                                .filter(person -> Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() <= ADULT_AGE ||
                                                  Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() >= RETIREMENT_AGE);
                })
                .limit(500)
                .peek(System.out::println)
                .toList();
    }

    public static void task14() {
        List<Car> cars = Util.getCars();
        //cars.stream()
    }

    public static Double task15(List<Flower> flowers) {
        int daysInFiveYears = COUNT_YEARS_FOR_TASK_15 * COUNT_DAYS_IN_YEAR + 1;
        var totalPrice = flowers.stream()
                .sorted(Comparator.comparing(Flower::getOrigin).reversed()
                        .thenComparing(Comparator.comparing(Flower::getPrice).reversed())
                        .thenComparing(Comparator.comparing(Flower::getWaterConsumptionPerDay).reversed()))
                .filter(flower -> Pattern.matches(REGEX_S_C_TASK_15, flower.getCommonName()))
                .filter(flower -> flower.getFlowerVaseMaterial().stream()
                        .anyMatch(vase -> vase.equalsIgnoreCase(VASE_MATERIAL_GLASS)
                                          || vase.equalsIgnoreCase(VASE_MATERIAL_ALUMINUM)
                                          || vase.equalsIgnoreCase(VASE_MATERIAL_STEEL)))
                // price + priceWaterConsumptionPerDay + PRICE_OF_ONE_CUBIC_METER_OF_WATER (price/1L) / 1000 * days
                .map(flower -> flower.getPrice() + flower.getWaterConsumptionPerDay() * PRICE_OF_ONE_CUBIC_METER_OF_WATER / 1000 * daysInFiveYears)
                .reduce(0.0, Double::sum);
        System.out.println("Общая стоимость обслуживания всех растений - " + String.format("%1$,.2f", totalPrice));
        return totalPrice;

    }

    public static List<Student> task16(List<Student> students) {
        return students.stream()
                .filter(student -> student.getAge() <= Constants.ADULT_AGE)
                .sorted(Comparator.comparing(Student::getSurname))
                .peek(student -> System.out.println(student.getSurname() + " : " + student.getAge()))
                .toList();
    }

    public static List<String> task17(List<Student> students) {
        return students.stream()
                .map(Student::getGroup)
                .distinct()
                .peek(System.out::println)
                .toList();
    }

    public static void task18(List<Student> students) {
        students.stream()
                .collect(Collectors.groupingBy(
                        Student::getFaculty,
                        Collectors.summarizingInt(Student::getAge)// объект IntSummaryStatistics
                ))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.comparingDouble(IntSummaryStatistics::getAverage).reversed()))
                .forEach(element ->
                        System.out.println("Факультет " + element.getKey() +
                                           " : средний возраст студента - " + element.getValue().getAverage()));
    }

    public static void task18_1(List<Student> students) {
        students.stream()
                .collect(Collectors.groupingBy(
                        Student::getFaculty,
                        Collectors.averagingDouble(Student::getAge)))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(element ->
                        System.out.println("Факультет " + element.getKey() +
                                           " : средний возраст студента - " + element.getValue()));
    }

    public static List<Student> task19(List<Student> students, List<Examination> examinations) {
        return students.stream()
                .filter(student -> student.getGroup().equals(GROUP_C_2_FOR_TASK_19))
                .map(student -> {
                            if (examinations.stream()
                                    .filter(examination ->
                                            examination.getStudentId() == student.getId())
                                    .findFirst()
                                    .filter(examination -> examination.getExam3() > MIN_EXAMINATION_MARK)
                                    .isEmpty())
                                return null;
                            return student;
                        }
                )
                .peek(System.out::println)
                .toList();
    }

    public static void task20(List<Student> students, List<Examination> examinations) {
        students.stream()
                .collect(Collectors.toMap(Function.identity(),
                        student -> examinations.stream()
                                .filter(examination ->
                                        examination.getStudentId() == student.getId())
                                .map(Examination::getExam1)
                                .findFirst().orElse(0)
                ))

                .entrySet().stream()
                .filter(element -> element.getValue() != 0)
                .collect(Collectors.groupingBy(element -> element.getKey().getFaculty(),
                        Collectors.averagingDouble(Map.Entry::getValue)
                ))

                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(element -> System.out.println("Факультет " + element.getKey() +
                                                         " с максимальной средней оценкой по первому экзамену " + element.getValue()));

    }

    public static Map<String, Long> task21(List<Student> students) {
        var groupCountStudents = students.stream()
                .collect(Collectors.groupingBy(Student::getGroup,
                        Collectors.counting()));

        System.out.println("Общее количество студентов : " + students.size());
        System.out.println("Группа" + "Количество студентов");
        for (Map.Entry<String, Long> element : groupCountStudents.entrySet()) {
            System.out.println(element.getKey() + "        " + element.getValue());
        }
        return groupCountStudents;
    }

    public static Map<String, Integer> task22(List<Student> students) {
        var facultyMinStudentAge = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getFaculty,
                        Collectors.minBy(Comparator.comparing(Student::getAge))))
                .entrySet().stream()
                .map(element -> Map.entry(element.getKey(),
                        element.getValue().orElseThrow().getAge()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        for (Map.Entry<String, Integer> element : facultyMinStudentAge.entrySet()) {
            System.out.println("Факультет : " + element.getKey()
                               + "\nМинимальный возраст студента : " + element.getValue() + "\n");
        }
        return facultyMinStudentAge;
    }

    private static boolean isAnimalInAgeInterval(Animal animal, int minAge, int maxAge) {
        return animal.getAge() >= minAge && animal.getAge() <= maxAge;
    }

    private static boolean isGender(Animal animal, String gender) {
        return animal.getGender().equals(gender);
    }

    private static boolean isAnimalOrigin(Animal animal, String origin) {
        return animal.getOrigin().equals(origin);
    }


}
