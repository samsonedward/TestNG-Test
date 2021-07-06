Feature: login functionality of Leaftaps

Scenario: Login with valid credentials

Given Enter username as Demosalesmanager
And Enter password as crmsfa
When Click login button
Then Homepage should be displayed 
