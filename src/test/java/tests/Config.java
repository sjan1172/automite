package tests;

public interface Config {
    final String baseUrl = System.getProperty("baseUrl", "http://the-internet.herokuapp.com");
    final String browserName = System.getProperty("browserName", "safari");

    //cloud configs
    final String host = System.getProperty("host", "localhost"); //cloud provider
    final String browserVersion = System.getProperty("browser.version", "33");
    final String platform = System.getProperty("platform", "Windows XP");

    //Saucelab configs
    final String sauseUser = System.getenv("SAUCE_USERNAME");
    final String sauseKey = System.getenv("SAUCE_ACCESS_KEY");


}
