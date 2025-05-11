package Model;
import Anotation.NoNCLI;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoNCLI(value = "Представление телефонного номера")
public class Number {
    private String phoneNumber;
    private PhoneType type;

    public Number(String phoneNumber, PhoneType type) {
        this.phoneNumber = phoneNumber;
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return phoneNumber;
    }
}


