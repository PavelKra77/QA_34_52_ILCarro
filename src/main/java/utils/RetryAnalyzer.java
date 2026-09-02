package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private int maxTryCount = 3;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(retryCount < maxTryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}

// Класс реализует автоматический повторный перезапуск упавших тестов
// implements IRetryAnalyzer -стандартный интерфейс TestNG, позволяет перехватить момент падения теста и решить, нужно ли запускать его повторно.
// ITestResult (тип данных): Интерфейс из библиотеки TestNG, который содержит данные о только что завершившемся тесте
// Когда какой-то тест падает, TestNG автоматически обращается к этому методу и передает в него данные упавшего теста в аргументе iTestResult.