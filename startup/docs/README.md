# BDSO Challange Details

## What are we building
1. A collection of services to scrape, process, ingest and display celebrity information. 
* The input list of celebrities is provided
* Scrape information from wikipedia (2-level deep), IMDB and one other news sources

2. Implementation of AI/ML algorithms to match and combine celebrity information 
* Match celebrities using variation of names 
* Provide celebrity recommendation

3. Build User interface 
  * Roles include product owner, Business User, and Business Supervisor 
  * Provide search to look for celebrity in dimensions of celebrity, movie, news etc.
  * Display graph of celebrity and movies 
  * Provide celebrity biographic and biometric information
    - Celebrity face
    - Celebrity names and nicknames
    - Date of Birth
    - Country of birth
    - Currenty place of residence
    - Relatives
* Build infographics about celebrities 
  - Movies
  - Revenue
  - Timeline of the movies
## User Stories
1. As a Product Owner, I need to be able to review classifications using an infinite scroll HTML web page. The web page shall include data visualization, analysis and insight of the type of roles for the movies and character names with recommended list of celebrities and their character names based on 10 years of historical data.
2. As a Business User, I need the ability to do a fuzzy-style search for an celebrity profile using any combination information.
3. As a Business User, I need the system to pull data from all current data sources and provide a single record view of celebrity that displays all of the associated BIOGRAPHICAL and BIOMETRIC data elements for that celebrity.
4. As a Business Supervisor, I need the ability to view and update celebrity data when needed.
5. As a DevSecOps engineer, I need to create an AWS environment that will consolidate data from multiple data sources and allow for data preparation.
6. As a DevSecOps engineer, I need to implement Machine Learning/Artificial Intelligence to ensure celebrity records that may differ in spelling or by context are recognized as being the same employee and are used to create the single employee record view.
7. As a DevSecOps engineer, I need maintain referential integrity when creating the single celebrity record view from each of the source databases.
8. As a DevSecOps engineer, I need the ability to implement, demonstrate and output results of automated security scans and code testing as part of my pipeline.
9. As a DevSecOps engineer, I need to be able to stand up entire infrastructure using a single 100% automated scripts with a minimum number of commands.
10. As a data scientist, I need to be able to have an infinity scroll infographic to view the raw data.
11. As a data scientist, I need to be able to understand the classification and grouping of companies in sectors and industries.
## Tools/Technology
| Tool/Technology   |      Categories      |  Purpose |
|--------|----------------------|----------|
|Angular| Front End | User interface |
|Okta| User Authentication | Login users| 
|RDS Postgres| Database | Celebrity DB| 
|AWS Neputune| GraphDB | Celebrity Network|
|AWS ElasticSearch| Search|  Fuzzy Search| 
|Spring Boot| Microservice Framework| Celebrity API| 
|Lambda| Serverless| Scraping, Search & Graph API|
|Jupyter Notebook| Datascience | Develop models|

## Team
### Infrastructure/Devops
### Backend
### Frontend
### Data Ingession
### UI/UX
### Data Science
### Documentation

## Practices

