package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakeScreenShot {
//    public static void main(String[] args) {
//        createFileName();
//    }

public static String createFileName(){
    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    //yyyyMMdd_HHmmss
    //dd.MM.yyyy HH:mm
    System.out.println(System.currentTimeMillis());  // время в мс с 1 января 1970 года
    Date date = new Date(System.currentTimeMillis());
    System.out.println(date);
    String curDate = formater.format(date);
    System.out.println(curDate);
    String fileName = "src/test/resources/screenshots/screen-"
            +curDate+".png";
    System.out.println(fileName);
    return fileName;
    }

    public static void takeScreenShot(TakesScreenshot screenshot){
        String fileName = createFileName();
        File screen = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(screen.toPath(), new File(fileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

//  SimpleDateFormat — встроенный класс для форматирования и парсинга дат в соответствии с заданными шаблонами.
// formater.format(...) — преобразует объект даты и времени (Date) в текстовую строку (String) по заранее заданному шаблону.
// для работы с датами чаще использовать DateTimeFormatter, LocalDateTime,
// однако SimpleDateFormat всё ещё часто встречается в старых проектах и вспомогательных утилитах.

// TakesScreenshot screenshot - чтобы в этот метод можно было передать ваш веб-драйвер и сделать скриншот
// OutputType.FILE - Сохрани этот снимок на жесткий диск
// Files.copy(...) встроенный метод, который копирует файл из источника (screen) в новое назначение (fileName).
// screen.toPath() — берет временный файл скриншота и превращает его в объект пути (Path),