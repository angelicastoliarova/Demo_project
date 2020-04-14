package tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class ProxyFilterTraficTest extends BaseProxyTest {

    @BeforeClass
    public void createNewHar() {
        super.setUp();
    }

    @Test
    public void testChromeDriver() {
        int expectedPageCount = 5;
        List entries = proxy.getHar().getLog().getEntries().stream()
               .filter(entry -> entry.getRequest().getMethod().equals("POST"))
                .filter(entry -> entry.getRequest().getPostData()
                        .getText().contains("png")).collect(Collectors.toList());
        Assert.assertTrue(entries.size() >= expectedPageCount);
    }

    @AfterClass
    public void close() {
        super.close();
    }
}

