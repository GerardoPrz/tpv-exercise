package oop.inheritance.ingenico;

public class IngenicoKeyboard {
    private static IngenicoKeyboard keyboard = new IngenicoKeyboard();

    public IngenicoKeyboard() {}

    public static IngenicoKeyboard getInstance() {
        return keyboard;
    }

    /**
     * @return key pressed
     */
    public String get() {
        return "Key pressed";
    }
}
