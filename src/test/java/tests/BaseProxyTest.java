package tests;

import io.netty.handler.codec.http.HttpMethod;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.HarLog;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class BaseProxyTest {
    public static BrowserMobProxy proxy = null;
    private static WebDriver driver;

    public BaseProxyTest() {
    }

    public void setUp() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            BrowserMobProxy proxy = getProxyServer();
            System.out.println("BrowserMob Proxy running on port: " + proxy.getPort());
            Proxy seleniumProxy = getSeleniumProxy(proxy);
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
            WebDriver driver = new ChromeDriver(capabilities);
            proxy.newHar("kinopoisk.ru");
            driver.get("https://www.kinopoisk.ru");
        }
    }

    public Proxy getSeleniumProxy(BrowserMobProxy proxyServer) {
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxyServer);
        try {
            String hostIp = Inet4Address.getLocalHost().getHostAddress();
            seleniumProxy.setHttpProxy(hostIp + ":" + proxyServer.getPort());
            seleniumProxy.setSslProxy(hostIp + ":" + proxyServer.getPort());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            Assert.fail("invalid Host Address");
        }
        return seleniumProxy;
    }

    public BrowserMobProxy getProxyServer() {
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.setTrustAllServers(true);
        proxy.setHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
      proxy.addRequestFilter((request, content, info) -> {
           request.setMethod(HttpMethod.POST);
           System.out.println(request.headers().entries().toString());
            return null;
        });
        proxy.start();
        return proxy;
    }

    public void close() {
        proxy.stop();
        driver.quit();
    }
}

