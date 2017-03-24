Feature: User management

Background:
* url baseUrl

Scenario: Login user

Given path '/user/login'
And request {id: '#(id)', password:'#(password)'}
When method POST
Then status 200
And match $ == {authToken:'#notnull'}
And def authToken = $.authToken
