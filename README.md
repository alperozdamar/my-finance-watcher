
# My Finance Watcher

A personal finance tracking web application for managing assets, expenses, and incomes, with historical charts and yearly/monthly breakdowns.

## Features
- Asset tracking with detailed breakdowns
- Yearly and monthly expense & income tracker
- Dynamic charts for asset and money-ready trends
- User-based data 
- CI/CD with GitHub Actions and Docker

## Tech Stack
- Java 21, Spring Boot
- Thymeleaf (HTML templates)
- MySQL (database)
- Docker (containerization)
- GitHub Actions (CI/CD) 

---

## Running Locally

### 1. Clone the Repository
```sh
git clone https://github.com/yourusername/my-finance-watcher.git
cd my-finance-watcher
````

### 2. Start MySQL with Docker

You can use the provided `docker-compose.local.yml` or run MySQL manually:

**Using Docker Compose:**

```sh
docker-compose -f docker-compose.local.yml up -d
```

**Or, run MySQL manually:**

```sh
docker run --name mysql-standalone -e MYSQL_ROOT_PASSWORD=yourpassword -e MYSQL_DATABASE=myfinance -p 3306:3306 -d mysql:8
```

### 3. Configure Database Connection

Edit `src/main/resources/application.properties` if needed:

```
spring.datasource.url=jdbc:mysql://localhost:3306/myfinance
spring.datasource.username=root
spring.datasource.password=yourpassword
```

### 4. Build the Application

```sh
mvn clean package -DskipTests
```

### 5. Run the Application

**With Maven:**

```sh
mvn spring-boot:run
```

**Or, with the JAR:**

```sh
java -jar target/my-finance-watcher-<version>.jar
```

The app will be available at [http://localhost:8089](http://localhost:8089)

### 6. (Optional) Run with Docker

Build and run the app in Docker:

```sh
docker build -t my-finance-watcher:local .
docker run --name my-finance-watcher --link mysql-standalone:mysql -p 8089:8089 my-finance-watcher:local
```

---

## Database Initialization

* The app will auto-create tables on first run if the database is empty.
* To import sample data:

  ```sh
  mysql -u root -p myfinance < myfinance_2025-05-12.sql
  ```

---

## CI/CD & Deployment

* CI/CD is handled via GitHub Actions (`.github/workflows/deploy.yml`).
* On push to `main`, the app is built, Dockerized, pushed to Docker Hub, and deployed to your EC2 instance.

---

## Versioning Strategy

* **Version Source:**
  The `pom.xml` file defines the current version.
* **Image Tagging:**
  Docker images are tagged with the `pom.xml` version and `latest`.
* **Release Tags:**
  GitHub tags are created matching the `pom.xml` version (e.g., `v2.0.0`).

### How to Publish a New Release

1. **Trigger Manual Release from GitHub Actions**

  * Go to the **Actions** tab.
  * Select **Manual Release**.
  * Click **Run workflow**.
  * Choose version bump type:

    * `major`
    * `minor`
    * `patch`
    * Or specify exact version like `2.1.0`

2. **What Happens Next**

  * The workflow bumps the `pom.xml` version.
  * Creates a Git commit and tag (e.g., `v2.1.0`).
  * Pushes the updated code and tag to GitHub.
  * Triggers the **Build, Push, and Deploy** workflow:

    * Builds Docker image.
    * Tags it with `2.1.0` and `latest`.
    * Pushes to Docker Hub.
    * Deploys to your EC2 instance.

3. **Verify**

  * Check your EC2 instance:

    ```sh
    docker ps
    ```
  * Access the app at your deployed URL.

---

## License

MIT

