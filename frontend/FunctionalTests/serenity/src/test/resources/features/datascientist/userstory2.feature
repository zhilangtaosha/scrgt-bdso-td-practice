@Regression
Feature: BDSO Data Science Features
  As a data scientist, I need to be able to have
  an infinity scroll infographic to view the raw data.

  Scenario: BDSO Data Scientist features
    Given User is on the BDSO login page
    When enters "test-ds" and "test123" values
    Then display data science page
    Then user clicks logout
