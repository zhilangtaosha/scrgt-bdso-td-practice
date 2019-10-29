@Regression
Feature: BDSO Business user Home page
  As a Business User I want to search for celebrities by
  providing any combination of information so that I can see
  all Biographical and Biometrical data for that celebrity

  Scenario: BDSO Business user select celebrity
    Given User is on the BDSO login page
    When enters "test-user" and "test123" values
    Then Bdso Home Page displayed
    When celebrity "Robin Williams" is selected
    Then celebrity details is displayed for "Robin Williams"
    Then user clicks logout

  Scenario: BDSO Business user search
    Given User is on the BDSO login page
    When enters "test-user" and "test123" values
    Then Bdso Home Page displayed
    Then user clicks logout
