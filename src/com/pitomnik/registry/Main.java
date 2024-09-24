package com.pitomnik.registry;

import com.pitomnik.animals.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static AnimalRegistry registry = new AnimalRegistry();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            printMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addAnimal();
                    break;
                case "2":
                    listCommands();
                    break;
                case "3":
                    learnCommand();
                    break;
                case "4":
                    listAnimalsByBirthDate();
                    break;
                case "5":
                    showTotalCount();
                    break;
                case "6":
                    exit = true;
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("=== Реестр домашних животных ===");
        System.out.println("1. Добавить новое животное");
        System.out.println("2. Вывести список команд животного");
        System.out.println("3. Обучить животное новой команде");
        System.out.println("4. Вывести список животных по дате рождения");
        System.out.println("5. Показать общее количество животных");
        System.out.println("6. Выход");
        System.out.print("Выберите опцию: ");
    }

    private static void addAnimal() {
        System.out.print("Введите тип животного (Dog, Cat, Hamster, Horse, Camel, Donkey): ");
        String type = scanner.nextLine();
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();
        System.out.print("Введите дату рождения (YYYY-MM-DD): ");
        String birthDateStr = scanner.nextLine();
        LocalDate birthDate = LocalDate.parse(birthDateStr);
        System.out.print("Введите команды (через запятую): ");
        String commandsStr = scanner.nextLine();
        List<String> commands = Arrays.asList(commandsStr.split(",\\s*"));

        Animal animal = null;
        switch (type.toLowerCase()) {
            case "dog":
                System.out.print("Введите породу: ");
                String breed = scanner.nextLine();
                animal = new Dog(name, birthDate, commands, breed);
                break;
            case "cat":
                System.out.print("Введите цвет: ");
                String color = scanner.nextLine();
                animal = new Cat(name, birthDate, commands, color);
                break;
            case "hamster":
                System.out.print("Введите размер колеса: ");
                double wheelSize = Double.parseDouble(scanner.nextLine());
                animal = new Hamster(name, birthDate, commands, wheelSize);
                break;
            case "horse":
                System.out.print("Введите скорость: ");
                double speed = Double.parseDouble(scanner.nextLine());
                animal = new Horse(name, birthDate, commands, speed);
                break;
            case "camel":
                System.out.print("Введите количество горбов: ");
                int humpCount = Integer.parseInt(scanner.nextLine());
                animal = new Camel(name, birthDate, commands, humpCount);
                break;
            case "donkey":
                System.out.print("Введите силу: ");
                double strength = Double.parseDouble(scanner.nextLine());
                animal = new Donkey(name, birthDate, commands, strength);
                break;
            default:
                System.out.println("Неизвестный тип животного.");
                return;
        }


        registry.addAnimal(animal);
        System.out.println("Животное добавлено успешно.");
    }

    private static void listCommands() {
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();
        List<String> commands = registry.getCommands(name);
        if (commands != null) {
            System.out.println("Команды для " + name + ": " + String.join(", ", commands));
        } else {
            System.out.println("Животное не найдено.");
        }
    }

    private static void learnCommand() {
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();
        System.out.print("Введите новую команду: ");
        String command = scanner.nextLine();
        boolean success = registry.addCommand(name, command);
        if (success) {
            System.out.println("Команда добавлена успешно.");
        } else {
            System.out.println("Животное не найдено.");
        }
    }

    private static void listAnimalsByBirthDate() {
        List<Animal> sortedAnimals = registry.getAnimalsByBirthDate();
        System.out.println("Список животных по дате рождения:");
        for (Animal animal : sortedAnimals) {
            System.out.println(animal.getName() + " - " + animal.getBirthDate() + " - " + animal.getType());
        }
    }

    private static void showTotalCount() {
        int count = registry.getTotalCount();
        System.out.println("Общее количество животных: " + count);
    }
}
