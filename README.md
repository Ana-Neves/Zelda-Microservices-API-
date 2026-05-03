# Zelda Microservices API - GC24 🍺💻


---

## **Sobre o Projeto**

## 📌 Pitch & Project Context
The **Zelda Fan Portal** is a scalable, microservices-based backend platform developed as the final challenge ("Boss Final") for the GC24 cohort. 

Built collaboratively under a strict deadline by team "Se Beber Não Code", this software connects enthusiasts of *The Legend of Zelda* franchise by integrating user management with real-time data fetched from the public Zelda API. The project demonstrates a strong understanding of distributed systems, robust API architecture, and agile teamwork.

## 🚀 Key Features & Services
The ecosystem is divided into three core microservices, ensuring modularity and scalability:
* **API Gateway (`gateway-service`):** Acts as the unified entry point for all client requests, routing them securely to the appropriate downstream services.
* **User Service (`user-service`):** A dedicated microservice handling full CRUD operations for user profiles and data persistence in PostgreSQL.
* **Zelda Service (`zelda-service`):** A specialized service that seamlessly integrates with the external Zelda Public API to fetch and serve franchise data to the users.
  
---

## 🛠️ Tech Stack & Architecture
* **Language:** Java 21
* **Framework:** Spring Boot 3.3.5
* **Architecture:** Microservices & API Gateway
* **Database:** PostgreSQL
* **Dependency Management:** Maven
* **Testing:** JUnit & Mockito
* **Version Control:** Git & GitHub

---

## 👩‍💻 My Contributions

As a Backend Developer on this project, my core responsibilities included:
* **Microservices Integration:** Developed and routed endpoints within the `[gateway-service ou user-service]`, ensuring smooth communication between the internal database and the external API.
* **Unit Testing:** Wrote comprehensive automated tests using JUnit and Mockito to validate the business logic and prevent regressions before the final delivery.
* **Agile Collaboration:** Managed code reviews and branch merges via GitHub, ensuring our codebase remained stable during the fast-paced development cycles.

## ⚙️ How to Run Locally

### Prerequisites
* Java 21
* Spring Boot 3.3.5
* PostgreSQL
* Maven

### Setup Instructions

1. **Clone the repository:**
   ```bash
  `git clone https://github.com/GabrielFleischmann/gc24-SeBeberNaoCode.git`


- Install the dependencies by running the Maven package located in the pom.xml file;

- Run the project in your preferred IDE; we used IntelliJ IDEA throughout the process;

- Finally, test the API using one of the available tools, such as Swagger or Postman.

## 👥 The Team

![Ana](https://avatars.githubusercontent.com/u/97240075?v=4)

**Ana Caroline Neves**
Fullstack Developer - Contributor

- GitHub: [Ana-Neves](https://github.com/Ana-Neves)
- LinkedIn: [Ana](https://www.linkedin.com/in/ana-caroline-neves-fullstack/)


**Gabriel Fleischmann**
Fullstack Developer - Contributor

- LinkedIn: [Gabriel](https://www.linkedin.com/in/gabriel-fleischmann-funck-010278244/)


**Carolina Oliveira**
Fullstack Developer - Contributor

- LinkedIn: [Ana](https://www.linkedin.com/in/carolina-oliveira-da-silva-b61012320/)
  

**Gabriela Poock**
Fullstack Developer - Contributor


**Gustavo Cigaran**
Fullstack Developer - Contributor
