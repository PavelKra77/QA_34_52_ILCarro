package utils.enums;

public enum HeaderMenu {
    LOGO("//img[@alt='logo']"),
    SEARCH("//a[text()=' Search ']"),
    LET_THE_CAR_WORK("//div[@class='header']/a[3]"),
    TERMS_OF_USE("//a[@href='/terms-of-use']"),
    SIGN_UP("//a[text()=' Sign up ']"),
    LOGIN("//a[text()=' Log in ']"),
    LOGOUT("//a[@href='/logout?url=%2Fsearch']"),
    DELETE_ACCOUNT("//div[@class='header']//a[text()='Delete account']");

    private final String locator;

    HeaderMenu(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }
}
// (enum) — это независимые списки констант (например, типы топлива, роли пользователей, статусы). они доступны всему проекту
// enum могут понадобиться где угодно: в классах dto (Car), в генераторах данных (CarFactory), в тестах или провайдерах данных.
// Написали private final String locator - делаем Конструктои и Геттер через Generate
// У LOGO есть locator - ("//img[@alt='logo']"). Данные запрашивают так  HeaderMenu.LOGO.getLocator()).