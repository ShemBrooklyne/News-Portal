# NEWS-PORTAL API

## Author
* Shem Brooklyne Mwangi

### Description
This is an Organizational Rest API that queries and retrieves scoped news and information within an organization.

## Features
A user visiting the news portal Api site will be able to:

1. Add New Departments & and also view local departments present.
2. Add new User/s' to andy department desired.
3. Add news in a particular department and view the news under the directed department.

## Prerequisites & Application Setup
- Use of version control. ie. Git
- Java SDK & Environment.
- Java IntelliJIDEA Community.
- Gradle.
- PostgreSQL

### Clone Repository

- Depending on your preffereable method of acquiring the Application to your local machine, you might as well try:
1. Forking the repository to your repositories.
2. Cloning the forked repository or cloning directly from my repository.
3. Finally run the project on your IntelliJIDEA `$ gradle build`, `$ gradle run` in your projects terminal.

## Expected Behaviour. (USERS' GUIDE)
### View Main Landing Page.
* Method: GET
* Route: /

{
    message: "Hello there Netizen! WELCOME to NEWS-PORTAL-API mainpage."
}

- The returned Json is as seen above.

### View Departments
* Method: GET
* Route: `/departments`

### View Departments by ID
* Method: GET
* Route: `/departments/:id`

### View Departments via Sorted News
* Method: GET
* Route: `/departments/:id/sortedNews`

- Sort news in accordance to time passed since it was posted.

### View Users by ID
* Method: GET
* Route: `/users/:id`

## Technologies Used
- Java
- Java Spark*
- PostgreSQL
- Postman

## Bugs
~ At the moment there are no known bugs, but that is as if you come across any bug or functionality that fails, please feel free to reach out.

## Contact Information
~ For any inquiries or future git collaborative projects, reach out to:

Email: <a href="mailto:shemnyatti@gmail.com">shemnyatti@gmail.com</a>

## LICENSE
Copyright 2020 Shem Brooklyne Mwangi

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
