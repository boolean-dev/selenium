package com.onegene.server.selenium.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SeleniumUtils
 * @Descriiption SeleniumUtils
 * @Author yanjiantao
 * @Date 2019/1/14 10:46
 **/
@Slf4j
public class SeleniumUtils {


    private static String downloadFilepath;

    public static String getDownloadFilepath()
    {
        return downloadFilepath;
    }


    //超时时间
    public static void setTimeOut(long millis)
    {
        try
        {
            Thread.sleep(millis);
        } catch (InterruptedException e)
        {
            log.error("页面加载超时，{}", e);
        }
    }


    /*获取浏览器的连接*/
    public static WebDriver openAccess()
    {

        System.setProperty("webdriver.chrome.driver",DriverPathUtils.getPath() + "chromedriver.exe");
        //在idea运行的谷歌驱动路径
        /*System.setProperty("webdriver.chrome.driver",
                "src/main/resources/driver/chromedriver.exe");*/
        //打jar包后的谷歌驱动路径
		/*String driverPath = System.getProperty("user.dir")+File.separator+"driver"+ File.separator+"chromedriver.exe";
		System.setProperty("webdriver.chrome.driver",
				driverPath);*/
//		log.info("谷歌驱动路径，{}", driverPath);
        /*String currentTime = String.valueOf(System.currentTimeMillis());
        log.info("系统当前时间，{}",currentTime);*/
        /*downloadFilepath = PropertiesUtil
                .readProperties("WORKSPACE_PATH")+currentTime;*/
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs
                .put("profile.default_content_settings.popups", 0);//设置为禁止弹出下载窗口
        chromePrefs
                .put("download.default_directory", downloadFilepath);//设置为文件下载路径
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromeOptionsMap = new HashMap<>();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--test-type");
        options.addArguments("disable-infobars");//取消Chrome正在受到自动测试软件的控制
        options.addArguments("user-data-dir=" + DriverPathUtils.getChromePath());
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        WebDriver driver = null;
        boolean flag = true;
        while (flag)
        {
            try
            {
                flag = false;
                driver = new ChromeDriver(cap);
                //响应时间超过8秒，则重新开启浏览器连接
                driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
                driver.manage().window().maximize();
//                driver.get(url);

            } catch (Exception e)
            {
                flag = true;
                if (driver != null)
                {
                    driver.quit();
                }
                log.info("wait for connection browser ");
            }
        }
        return driver;
    }

}
