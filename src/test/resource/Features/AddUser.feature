@users
Feature: Adding the users to the List.
Background:
Given User is on reqres URL

@add
Scenario Outline: Add user
When user enters the "<name>" and "<job>"
And users hit the users API
Then users are added to list
Examples:
|name|job|
|Rohan|Developer|
|Ajay|Software|

@update
Scenario: Update the user
When User enters name & job
|Deepa|Consultant|
|Sathish|Analyst|
And user hits the Api
Then user data is updated

