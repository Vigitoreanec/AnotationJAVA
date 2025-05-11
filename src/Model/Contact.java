package Model;

import Anotation.NoNCLI;
import lombok.*;

import java.util.EnumMap;
import java.util.Map;

@Getter
@ToString
@NoNCLI(value = "Контакт в телефонной книге")
public class Contact {
    private final String name;
    private final Map<PhoneType, Number> numbers = new EnumMap<>(PhoneType.class);

    public Contact(String name) {
        this.name = name;
    }

    public void addNumber(PhoneType type, String phoneNumber) {
        numbers.put(type, new Number(phoneNumber, type));
    }

    public boolean removeNumber(PhoneType type) {
        return numbers.remove(type) != null;
    }

    public Map<PhoneType, Number> getNumbers() {
        return new EnumMap<>(numbers);
    }

    @Override
    public String toString() {
        return "Contact => " +
                "name='" + name + '\'' +
                ", numbers=" + numbers +
                '}';
    }
}
