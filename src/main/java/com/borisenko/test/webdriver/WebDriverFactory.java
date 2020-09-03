package com.borisenko.test.webdriver;

import com.borisenko.test.util.Browser;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Set;

/*
 * Factory to instantiate a WebDriver object. It returns an instance of the driver (local invocation) or an instance of RemoteWebDriver
 * 
 * @author Sebastiano Armeli-Battana
 */
public class WebDriverFactory {

	/* Browsers constants */
	public static final String CHROME = "chrome";
	public static final String INTERNET_EXPLORER = "ie";
    public static final String SAFARI = "safari";

	/* Platform constants */
	public static final String WINDOWS = "windows";
	public static final String ANDROID = "android";
	public static final String XP = "xp";
	public static final String VISTA = "vista";
	public static final String MAC = "mac";
	public static final String LINUX = "linux";
        
        private WebDriverFactory(){}

	/*
	 * Factory method to return a RemoteWebDriver instance given the url of the
	 * Grid hub and a Browser instance.
	 * 
	 * @param gridHubUrl : grid hub URI
	 * 
	 * @param browser : Browser object containing info around the browser to hit
	 * 
	 * @param username : username for BASIC authentication on the page to test
	 * 
	 * @param password : password for BASIC authentication on the page to test
	 * 
	 * @return RemoteWebDriver
	 */
	public static WebDriver getInstance( Browser browser,
			String username, String password) {


		DesiredCapabilities capability = new DesiredCapabilities();
		String browserName = browser.getName();
		capability.setJavascriptEnabled(true);
    	return getInstance(browserName, username, password);
	}

	/*
	 * Factory method to return a WebDriver instance given the browser to hit
	 * 
	 * @param browser : String representing the local browser to hit
	 * 
	 * @param username : username for BASIC authentication on the page to test
	 * 
	 * @param password : password for BASIC authentication on the page to test
	 * 
	 * @return WebDriver instance
	 */
	public static WebDriver getInstance(String browser, String username,
			String password) {

		WebDriver webDriver = null;

		if (CHROME.equals(browser)) {
			setChromeDriver();

			 webDriver = new ChromeDriver();
			Set<Cookie> allCookies = webDriver.manage().getCookies();
			for (Cookie cookie : allCookies) {
				webDriver.manage().deleteCookieNamed(cookie.getName());
			}
		}
		return webDriver;
	}

	/*
	 * Helper method to set version and platform for a specific browser
	 * 
	 * @param capability : DesiredCapabilities object coming from the selected
	 * browser
	 * 
	 * @param version : browser version
	 * 
	 * @param platform : browser platform
	 * 
	 * @return DesiredCapabilities
	 */
	private static DesiredCapabilities setVersionAndPlatform(
			DesiredCapabilities capability, String version, String platform) {
		if (MAC.equalsIgnoreCase(platform)) {
			capability.setPlatform(Platform.MAC);
		} else if (LINUX.equalsIgnoreCase(platform)) {
			capability.setPlatform(Platform.LINUX);
		} else if (XP.equalsIgnoreCase(platform)) {
			capability.setPlatform(Platform.XP);
		} else if (VISTA.equalsIgnoreCase(platform)) {
			capability.setPlatform(Platform.VISTA);
		} else if (WINDOWS.equalsIgnoreCase(platform)) {
			capability.setPlatform(Platform.WINDOWS);
		} else if (ANDROID.equalsIgnoreCase(platform)) {
			capability.setPlatform(Platform.ANDROID);
		} else {
			capability.setPlatform(Platform.ANY);
		}

		if (version != null) {
			capability.setVersion(version);
		}
		return capability;
	}

	/*
	 * Helper method to set ChromeDriver location into the right ststem property
	 */
	private static void setChromeDriver() {
		String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
		String chromeBinary = "src/main/resources/drivers/chrome/chromedriver"
				+ (os.equals("win") ? ".exe" : "");
		System.setProperty("webdriver.chrome.driver", chromeBinary);
	}

    private static void isSupportedPlatform(String browser) {
        boolean is_supported = true;
        Platform current = Platform.getCurrent();
        if (INTERNET_EXPLORER.equals(browser)) {
            is_supported = Platform.WINDOWS.is(current);
        } else if (SAFARI.equals(browser)) {
            is_supported = Platform.MAC.is(current) || Platform.WINDOWS.is(current);
        }
        assert is_supported : "Platform is not supported by " + browser.toUpperCase() + " browser";
    }
}
