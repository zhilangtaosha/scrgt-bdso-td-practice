#Author:Kala Shanmugam
Feature: functionality of BDSO celebrities APIs

@celebrities
Scenario: Validate celebrities API
Given Access to celebrities API
When Response received from Celebrities API
Then View celebrities list


@celebrityId
Scenario: Validate celebrityId API
Given Access to celebrity Id API
When Response received from Celebrity Id API
Then View celebrity Id Passed

@celebrityimdbId
Scenario: Validate celebrity Imdb Id API
Given Access to celebrity Imdb Id API
When Response received from Celebrity Imbd Id API
Then View celebrity Imbd Id Passed

@celebritymoviedbId
Scenario: Validate celebrity Moviedb Id API
Given Access to celebrity Moviedb Id API
When Response received from Celebrity Moviedb Id API
Then View celebrity Moviedb Id Passed

@celebritywikipediaId
Scenario: Validate celebrityId Wikipedia Id API
Given Access to celebrity Wikipedia Id API
When Response received from Celebrity Wikipedia Id API
Then View celebrity Wikipedia Id Passed

@changelogs
Scenario: Validate celebrities Change Logs API
Given Access to celebrities Change Logs API
When Response received from Celebrities Change Logs API
Then View celebrities Change Logs

@changelogId
Scenario: Validate celebrity Change Log Id API
Given Access to celebrity Change Log Id API
When Response received from Celebrity Change Log Id API
Then View celebrity Change Log Id Passed
