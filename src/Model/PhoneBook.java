package Model;

import Anotation.NoNCLI;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@ToString
@NoNCLI(value = "Телефонная книга")
public class PhoneBook {
    private final Map<String, Contact> contacts = new HashMap<>();

    public boolean addContact(String name) {
        if (contacts.containsKey(name)) return false;
        contacts.put(name, new Contact(name));
        return true;
    }

    public boolean removeContact(String name) {
        return contacts.remove(name) != null;
    }

    public boolean addNumberToContact(String contactName, PhoneType type, String phoneNumber) {
        Contact contact = contacts.get(contactName);
        if (contact == null) return false;
        contact.addNumber(type, phoneNumber);
        return true;
    }

    public Contact findContactByName(String name) {
        return contacts.get(name);
    }

    public Contact findContactByPhoneNumber(String phoneNumber) {
        return contacts.values().stream()
                .filter(c -> c.getNumbers().values().stream()
                        .anyMatch(n -> n.getPhoneNumber().equals(phoneNumber)))
                .findFirst()
                .orElse(null);
    }

    public Map<String, Contact> getAllContacts() {
        return new HashMap<>(contacts);
    }

}
