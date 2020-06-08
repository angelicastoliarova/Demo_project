package tests;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.testng.ScreenShooter;
import com.epam.reportportal.service.ReportPortal;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Screenshots.takeScreenShot;
import static com.codeborne.selenide.Screenshots.takeScreenShotAsFile;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CustomScreenShooter extends ScreenShooter {
    private static int testCount;

    @Override
    public void onTestStart(ITestResult result) {
        sleep(500);
        super.onTestStart(result);
        System.out.println(++testCount + ". " + result.getMethod().getTestClass().getName() +
                "-" + result.getMethod().getMethodName() + " started at " + new Date());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        super.onTestFailure(result);
        try {
            Method method = result.getMethod().getConstructorOrMethod().getMethod();
           ReportPortal.emitLog(method.getName(), "ERROR", Calendar.getInstance().getTime());
            Allure.addAttachment("Page screenshot with failure", Files.newInputStream(takeScreenShotAsFile().toPath()));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println(result.getMethod().getTestClass().getName() + "-" + result.getMethod().getMethodName() +
                    " failed at " + new Date());
            System.out.println("Execution time: " + millisToMinSec(result.getEndMillis() - result.getStartMillis()));
            result.getThrowable().printStackTrace();
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        super.onTestSuccess(result);
        sleep(500);
        System.out.println(result.getMethod().getTestClass().getName() + "-" + result.getMethod().getMethodName() +
                " PASSED at " + new Date());
        System.out.println("Execution time: " + millisToMinSec(result.getEndMillis() - result.getStartMillis()));
    }

    private String millisToMinSec(long millis){
        return String.format("%02d min : %02d sec",
                TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds((millis) -
                        TimeUnit.MINUTES.toMillis(TimeUnit.MILLISECONDS.toMinutes(millis))));
    }

    private static byte[] getSampleFile(String fileName) {
        try{
            Path path = Paths.get(fileName);
            byte[] bytes = Files.readAllBytes(path);
            return bytes;
        }
        catch (IOException e){e.printStackTrace();}
        return null;
    }

    public static byte[] saveElementScreenshot(SelenideElement element) {
        //Actions.scrollUnderElement(element);
        sleep(500);
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static File saveElementScreenshotAsFile(SelenideElement element) {
        //Actions.scrollUnderElement(element);
        sleep(500);
        return takeScreenShot(element);
    }
}