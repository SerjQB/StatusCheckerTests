UI-tests for Status Checker app.

CONFIGURING ENVIRONMENT FOR STARTING TESTS

1. Clone project from github
2. Run command gradle init from project directory

Installing SafariDriver
For running tests with Safari browser - you should download SafariDriver 
extension(http://www.seleniumhq.org/download/) and add it to the your local Safari browser as extension.

RUNNING TESTS

For running tests use command gradle clean test from project directory 

PROJECT STRUCTURE

/test/java/com/:
StatusCheckerTests directory - package with test-classes with test-methods inside
StatusCheckerPages directory - package with page-objects files, all page-methods and locators inside
NonSeleniumMethods directory - package with non-selenium methods required for tests inside
TestIntializtions directory - package with only one file - Wrappers.java, which is used for wrapping Selenium methods
/test:
Resources directory - resources with ChromeDriver for different OSes and test-suite file TestNG.xml

TESTS STRUCTURE

All tests presented in /test/Resources/TestNG.xml test-suite file
There are some required parameters in the test-suite file:
browser - for running browser verion
domain - for testing domain link(prod, dev, local, etc.)
appName - for testing instance

Classes runs all tests for described test-class from StatusCheckerTests package if you don't add manually
needful methods to suite's xml structure.
