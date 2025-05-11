package Book;

import Anotation.CLI;
import Model.Contact;
import Model.PhoneBook;
import Model.PhoneType;

import java.util.Map;
import java.util.Scanner;

@CLI(value = "Главное меню")
public class PhoneBookCLI {
    private final Scanner scanner;
    private final PhoneBook phoneBook;

    public PhoneBookCLI() {
        this.phoneBook = new PhoneBook();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            printMainMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addNewContact();
                case "2" -> allContacts();
                case "3" -> searchContact();
                //case "4" -> addNumberToExistingContact();
                case "5" -> { System.out.println("Выход..."); return; }
                default -> System.out.println("❌ Неверный ввод!");
            }
        }
    }

    private void allContacts() {
        Map<String, Contact> contacts = phoneBook.getAllContacts();

        if (contacts.isEmpty()) {
            System.out.println("\n📭 Телефонная книга пуста!");
            return;
        }

        System.out.println("\n📋 Все контакты:");
        contacts.forEach((name, contact) -> System.out.println(contact));
    }

    private void searchContact() {
        System.out.println("\nПоиск по:");
        System.out.println("1. Имени");
        System.out.println("2. Номеру телефона");
        System.out.print("> ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> {
                System.out.print("Введите имя: ");
                Contact contact = phoneBook.findContactByName(scanner.nextLine().toLowerCase());
                System.out.println(contact != null ? contact : "❌ Контакт не найден!");
            }
            case "2" -> {
                System.out.print("Введите номер: ");
                Contact contact = phoneBook.findContactByPhoneNumber(scanner.nextLine());
                System.out.println(contact != null ? contact : "❌ Контакт не найден!");
            }
            default -> System.out.println("❌ Неверный выбор!");
        }
    }

    private void printMainMenu() {
        System.out.println("\n\uD83D\uDCDE Телефонная книга");
        System.out.println("1. Добавить новый контакт");
        System.out.println("2. Просмотреть все контакты");
        System.out.println("3. Поиск контакта");
        System.out.println("4. Добавить номер к существующему контакту");
        System.out.println("5. Выход");
        System.out.print("Выберите действие: ");
    }

    @CLI
    private void addNewContact() {
        System.out.print("\nВведите имя контакта: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("❌ Имя не может быть пустым!");
            return;
        }

        if (phoneBook.addContact(name)) {
            System.out.println("✅ Контакт добавлен!");
            addNumbersToContact(name);
        } else {
            System.out.println("❌ Контакт уже существует!");
        }
    }

    @CLI
    private void addNumbersToContact(String contactName) {
        while (true) {
            printPhoneTypeMenu();
            String choice = scanner.nextLine();

            if (choice.equals("0")) break;

            try {
                int typeIndex = Integer.parseInt(choice) - 1;
                PhoneType type = PhoneType.values()[typeIndex];

                System.out.print("Введите номер телефона: ");
                String number = scanner.nextLine().trim();

                if (number.isEmpty()) {
                    System.out.println("❌ Номер не может быть пустым!");
                    continue;
                }

                if (phoneBook.addNumberToContact(contactName, type, number)) {
                    System.out.println("✅ Номер добавлен!");
                } else {
                    System.out.println("❌ Ошибка добавления номера!");
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("❌ Неверный выбор типа номера!");
            }
        }
    }

    private void printPhoneTypeMenu() {
        System.out.println("\nВыберите тип номера:");
        PhoneType[] types = PhoneType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.printf("%d. %s%n", i + 1, types[i]);
        }
        System.out.println("0. Назад");
        System.out.print("> ");
    }
}
