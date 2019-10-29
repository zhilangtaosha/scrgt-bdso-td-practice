# Approach.md

## _Agile Architecture & Technical Approach_
### Understanding
When a government agency or member of the public requests an immigrant’s information, USCIS employees perform searches in multiple systems and manually pull the information. Searches include immigrant files in several USCIS systems, e.g., CIS, RAILS, and MiDAS. Important information may also be in non-USCIS systems, such as Ancestry.com or social media, or may still be in paper format. This represents a great deal of manual work to compare and consolidate the information. It can take several days to several weeks to fulfill a typical information request.

In this challenge scenario, the BioCeleb Entity Corporation has been hired to collect Biographical and Biometric information about 100 celebrities, create a consolidated celebrity profile, and conduct entity resolution and analytics across the group. This is analogous to USCIS requirements to pull information from USCIS and non-USCIS systems and use advanced technology capabilities such as NLP, AI and/or ML to rapidly analyze this information, to create a dossier record for each immigrant. When one immigrant’s information is requested, the system would provide a curated view of the immigrant’s information ready for examination. The solution we developed has the basic framework and functionality to solve this problem. Our AI-based facial recognition approach could identify and relate information based on comparative facial features to assist in compiling a dossier for examination. Significant benefits of this system would be USCIS’s ability to:

* Rapidly examine new data sources and content for relevancy
* Fulfill information requests much more quickly, and
* Retire legacy systems with less capable data retrieval and analysis services.

### Agile Architecture Approach
Salient CRGT (SCRGT) organized our BDSO code challenge team into role groups: Data Science, App Dev (which included Front End, Back End, and Test Automation), and DevSecOps. Figure 1 shows key responsibilities of each role within our Agile AI/ML solution methodology. [Solution.pdf](<./docs/Solution.pdf>) provides details about our role groups. Our work started immediately with sprint/project planning when the BDSO RFP was released. We used an Agile AI/ML solution methodology to manage concurrent, complex efforts. During iterations the teams worked in parallel to establish the infrastructure, acquire data, stand up environments, and work on user stories.

*Figure 1. SCRGT’s Agile AI/ML solution methodology leverages self-organizing, cross-functional teams.*
![Agile](<./img/6818_07_01_Agile%20AI-ML%20Solution%20Methodology.png>)

For collaboration, we used Slack, Trello, and Git with an Agile methodology, conducting Agile ceremonies and processes, such as stand-ups at the start of the workday. During the day, team members shared accomplishments, escalation points and blockers in Slack (our primary collaboration tool) and on whiteboards in the technology lab. The team used Trello to track tasks in addition to physical boards in the technology lab. We ended each day with a review/retrospective, helping the team maintain a shared context and awareness of each other’s statuses, and discussing issues, risks, and challenges.

We used pair programming and behavior driven development (BDD). Pull requests into GitHub required a peer review prior to being approved for merging. Our team included actively cross-functional team members, with several resources being shared between the back end, testing and DevSecOps teams. Governance of development activities was a critical concern, so we included formal cross checks in the daily cadence of the team. This connection was essential to identify blockers early, and work through dependencies.

*Figure 2. SCRGT’s code challenge team communicated during morning stand-ups, afternoon stand-downs, and demos.*
![Team Photo](<./img/BDSO%20Team%20in%20Tech%20Lab.jpg>)

### Technical Approach
**Overview of Application Features**
Figure 3 shows the basic functionality path (blue boxes) for the login, search, and celebrity profile pages for Business Supervisor and Business User personas (see Solution.pdf). We developed microservices and APIs (orange boxes) to access, extract and deliver the data that populates the celebrity profile pages and enable the edit data functionality.

*Figure 3. SCRGT used a combination of NLP, AI and ML models including a facial recognition model to identify and recommend alternate actors.*
![Search Screen](<./img/6818_19_01_Search%20Screen%20Functionality%20and%20APIs.png>)

When the Data Scientist persona logs in to the application, they are directed to an infinity scroll web page with several representations of the raw data collected from our data sources. The following figure shows the functionality path for the Data Scientist persona.

*Figure 4. The Data Scientist persona is taken directly to the raw data infographic after login.*
![Raw Data](<./img/6818_18_01_Raw%20Data%20Screen%20and%20APIs.png>)


**Overview of Solution Architecture**

*Figure 6. SCRGT’s full stack cloud solution architecture includes AWS tools, OpenShift, and numerous open source tools.*
![Solution Architecture](<./img/6818_17_01_Full%20Stack%20Cloud%20Solution%20Architecture.png>)


The BioCeleb application is a web application and an interactive notebook using containerized services and serverless components of AWS. Key components are AWS S3 storage/static web server, OpenShift Cluster, EC2, ECS, PostgreSQL RDS, Elasticsearch and Keycloak IAM.  Solution.pdf shows our complete 100% automated CI/CD pipeline. All code is stored in GitHub and Jupyter interactive notebooks. All tools are open source or listed in USCIS’s technology landscape.

### Collaboration Sites

We used [Slack](<./docs/scrgt-bdso_Slack_export_Aug_16_2019-Sep_15_2019.zip>) and [Trello](<./docs/BDSO_Trello.json>) for collaboration, plus 4 Git repositories:

[Main repo, technical documentation](https://github.com/SalientCRGT/scrgt-bdso-cc-startup)

[Web application OpenShift service](https://github.com/SalientCRGT/scrgt-bdso-cc-frontend)

[Data science OpenShift services](https://github.com/SalientCRGT/scrgt-bdso-cc-datascience)

[Celebrity and backend load API OpenShift services](https://github.com/SalientCRGT/scrgt-bdso-cc-ms-celebrity)
