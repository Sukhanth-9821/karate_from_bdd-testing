Feature: User management

Background:
* url baseUrl

Scenario: List existing users

Given path '/user'
When method GET
Then status 200
And match $ == []
And assert responseTime < 1000

Scenario: Create and receive new user

Given path '/user'
And request {id:'', name:'Fred', age:22, password:'abc123'}
When method POST
Then status 200
And match response == {id:'#uuid', name:'Fred', age:22, password:'abc123'}
And assert responseTime < 1000
And def user = response

Given path '/user/'+user.id
When method GET
Then status 200
And match $ == {id:'#(user.id)', name:'#(user.name)', age:#(user.age), password:'#(user.password)'}
And assert responseTime < 1000

Scenario Outline: Create multiple users and verify their id, name and age

Given path '/user'
And request {id:'', name:'<name>', age: <age>}
When method POST
Then status 200
And match $ == {id:'#uuid', name: '<name>', age: <age>}
And assert responseTime < 1000
And def user = response

Given path '/user/'+user.id
When method GET
And status 200
And assert responseTime < 1000
And match $ == {id:'#(user.id)', name:'#(user.name)', age:#(user.age)}

Examples:
| name  | age |
| Tim   | 42  |
| Liz   | 16  |
| Selma | 65  |
| Ted   | 12  |
| Luise | 19  |

Scenario: Create and login user

Given path '/user'
And request {id:'', name:'Eleonore', age:31, password:'foobar'}
When method POST
Then status 200
And def user = response

Given path '/user/secured/date'
When method GET
Then status 403


Given path '/user/secured/date'
And def userLogin = call read('classpath:feature/security/user-login.feature') {id:'#(user.id)', password:'#(user.password)'}
And header Auth-Token = userLogin.authToken
When method GET
Then status 200
And match $ == {date:'#notnull'}

Scenario: Remove all users
Given path '/user'
When method DELETE
Then status 200
And assert responseTime < 1000

Given path '/user'
When method GET
Then status 200
And match $ == []
And assert responseTime < 1000