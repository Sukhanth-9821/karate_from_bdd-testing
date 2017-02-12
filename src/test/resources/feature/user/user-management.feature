Feature: User management
Scenario: List existing users

Given url 'http://localhost:9000/user'
When method GET
Then status 200
And match response == []

Scenario: Create and retreive new user

Given url 'http://localhost:9000/user'
And request {id:'', name:'Fred'}
When method POST
Then status 200
And match response == {id:'#ignore', name:'Fred'}

Given path response.id
When method GET
Then status 200
And match response = {id:response.id, name:'Fred'}
