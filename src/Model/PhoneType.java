package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PhoneType {
    HOME("Домашний"),
    WORK("Рабочий"),
    MOBILE("Мобильный"),
    CITY("Городской");

    private String displayName;

    PhoneType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
