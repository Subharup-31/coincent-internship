# Student Course Management System

A simple and beginner-friendly Student Course Management System built with Java Spring Boot and Thymeleaf.

## Features

- **Student Management**: Add, update, delete, and view students
- **Course Management**: Add, update, delete, and view courses  
- **Enrollment Management**: Assign/unassign courses to students
- **Many-to-Many Relationship**: Students can be enrolled in multiple courses
- **Modern UI**: Bootstrap-styled responsive web interface
- **H2 Database**: In-memory database for easy setup and testing

## Technology Stack

- **Java 17+**
- **Spring Boot 3.2.0** (Latest stable version)
- **Spring Web** - Web layer
- **Spring Data JPA** - Data access layer
- **Thymeleaf** - Template engine
- **H2 Database** - In-memory database
- **Bootstrap 5** - Frontend styling
- **Font Awesome** - Icons

## Project Structure

```
src/
├── main/
│   ├── java/com/studentmanagement/
│   │   ├── entity/          # JPA Entities
│   │   │   ├── Student.java
│   │   │   └── Course.java
│   │   ├── repository/      # Data Access Layer
│   │   │   ├── StudentRepository.java
│   │   │   └── CourseRepository.java
│   │   ├── service/         # Business Logic Layer
│   │   │   ├── StudentService.java
│   │   │   └── CourseService.java
│   │   ├── controller/      # Web Layer
│   │   │   ├── HomeController.java
│   │   │   ├── StudentController.java
│   │   │   ├── CourseController.java
│   │   │   └── EnrollmentController.java
│   │   └── StudentCourseManagementApplication.java
│   └── resources/
│       ├── application.properties
│       └── templates/       # Thymeleaf Templates
│           ├── index.html
│           ├── students/
│           ├── courses/
│           └── enrollments/
└── test/
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven (or use the included Maven wrapper)

### Running the Application

1. **Clone or download the project**

2. **Run the application using Maven wrapper:**
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Access the application:**
   - Main application: http://localhost:8080
   - H2 Database Console: http://localhost:8080/h2-console
     - JDBC URL: `jdbc:h2:mem:studentdb`
     - Username: `sa`
     - Password: `password`

### Using the Application

1. **Home Page**: View system overview and quick actions
2. **Students**: 
   - View all students
   - Add new students
   - Edit existing students
   - Delete students
   - View student details
3. **Courses**:
   - View all courses
   - Add new courses
   - Edit existing courses
   - Delete courses
   - View course details
4. **Enrollments**:
   - Manage student-course enrollments
   - Enroll students in courses
   - Unenroll students from courses

## Database Schema

### Student Entity
- `id` (Long) - Primary key
- `name` (String) - Student's full name
- `email` (String) - Student's email address (unique)

### Course Entity
- `id` (Long) - Primary key
- `name` (String) - Course name
- `description` (String) - Course description

### Student-Course Relationship
- Many-to-many relationship between Student and Course
- Managed through JPA `@ManyToMany` annotation
- Join table: `student_courses`

## Key Features

### Layered Architecture
- **Controller Layer**: Handles HTTP requests and responses
- **Service Layer**: Contains business logic
- **Repository Layer**: Data access using Spring Data JPA
- **Entity Layer**: JPA entities representing database tables

### User Interface
- Responsive Bootstrap 5 design
- Font Awesome icons
- Flash messages for user feedback
- Confirmation dialogs for destructive actions

### Database
- H2 in-memory database for easy setup
- Automatic table creation on startup
- H2 console for database inspection

## Development Notes

- No Spring Security (as requested for simplicity)
- No complex configuration
- Beginner-friendly code structure
- Comprehensive error handling
- Clean separation of concerns

## API Endpoints

### Students
- `GET /students` - List all students
- `GET /students/new` - Show add student form
- `POST /students` - Create new student
- `GET /students/{id}` - View student details
- `GET /students/{id}/edit` - Show edit student form
- `POST /students/{id}` - Update student
- `POST /students/{id}/delete` - Delete student

### Courses
- `GET /courses` - List all courses
- `GET /courses/new` - Show add course form
- `POST /courses` - Create new course
- `GET /courses/{id}` - View course details
- `GET /courses/{id}/edit` - Show edit course form
- `POST /courses/{id}` - Update course
- `POST /courses/{id}/delete` - Delete course

### Enrollments
- `GET /enrollments/student/{id}` - Manage student enrollments
- `POST /enrollments/student/{studentId}/enroll/{courseId}` - Enroll student
- `POST /enrollments/student/{studentId}/unenroll/{courseId}` - Unenroll student

## License

This project is created for educational purposes.
