package learn.welcome.ui;

import java.awt.*;

public enum MenuOption {
    EXIT (0, "Exit"),
    VIEW_RESERVATIONS (1, "View Reservations"),
    ADD_RESERVATION (2, "Add a Reservation"),
    CANCEL_RESERVATION (3, "Cancel a Reservation"),
    EDIT_RESERVATION (4, "Edit a Reservation"),
    VIEW_GUEST(5, "View All Guests"),
    VIEW_HOST(6, "View All Hosts");

    private int value;
    private String message;

    private MenuOption(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public static MenuOption fromValue(int value) {
        for (MenuOption option: MenuOption.values()) {
            if (option.getValue() == value) {
                return option;
            }
        }
        return EXIT;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

}
