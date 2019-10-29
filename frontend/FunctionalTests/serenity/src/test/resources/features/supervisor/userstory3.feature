@Regression
Feature: BDSO Business Supervisor Home page
  As a Business Supervisor  I can view and update celebrity data

  Scenario: BDSO Business Supervisor features
    Given User is on the BDSO login page
    When enters "test-supervisor" and "test123" values
    Then Bdso Home Page displayed
    Then user clicks logout
