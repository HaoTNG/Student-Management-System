# Student Management System - Advanced Software Engineering Lab Series

## 📚 Thông Tin Nhóm

| STT | Họ và Tên        | MSSV    | Ghi Chú |
|-----|-----------       |------|---------|
| 1   | Nguyễn Trọng Hào | 2310854 | |


## 📖 Lab 1: Khởi Tạo & Kiến Trúc Hệ Thống

### 🎯 Mục Tiêu
- ✅ Hiểu bài toán và kiến trúc hệ thống (MVC / Layered Architecture)
- ✅ Khởi tạo project Spring Boot
- ✅ Cấu hình kết nối cơ sở dữ liệu (SQLite)

### 📋 Mô Tả Bài Toán
Student Management System là một ứng dụng quản lý sinh viên cơ bản. Sinh viên vào vai Fullstack Developer để phát triển toàn diện từ Backend (API), Cơ sở dữ liệu (Database) đến Frontend.

### 🏗️ Kiến Trúc Hệ Thống (Layered Architecture)

Ứng dụng áp dụng mô hình kiến trúc phân lớp (Layered Architecture):

```
User
  ↓
HTTP Request (GET, POST, PUT, DELETE)
  ↓
┌─────────────────────────────────────┐
│  Controller Layer (Presentation)    │ ← Tiếp nhận HTTP Request, Validate dữ liệu
│  vn.edu.hcmut.cse.adse.lab.controller│
└─────────────────────────────────────┘
  ↓ DTO
┌─────────────────────────────────────┐
│  Service Layer (Business Logic)     │ ← Xử lý logic nghiệp vụ cốt lõi
│  vn.edu.hcmut.cse.adse.lab.service  │
└─────────────────────────────────────┘
  ↓ Entity
┌─────────────────────────────────────┐
│  Repository Layer (Data Access)     │ ← Tương tác với Database
│  vn.edu.hcmut.cse.adse.lab.repository│
└─────────────────────────────────────┘
  ↓ SQL
┌─────────────────────────────────────┐
│  Database (SQLite)                  │ ← Lưu trữ dữ liệu
│  student.db                         │
└─────────────────────────────────────┘
```

### 🛠️ Công Nghệ Sử Dụng

| Thành Phần | Công Nghệ | Phiên Bản |
|-----------|-----------|----------|
| Language | Java | 21 |
| Framework | Spring Boot | 4.0.2 |
| Build Tool | Maven | (bundled) |
| Database | SQLite | 3.41.2 |
| ORM | Hibernate / Spring Data JPA | 6.2.4 / 4.0.2 |
| Server | Apache Tomcat | 11.0.15 |

### 📁 Cấu Trúc Project

```
student-management/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── vn/edu/hcmut/cse/adse/lab/
│   │   │       ├── StudentManagementApplication.java    (Main Application)
│   │   │       ├── controller/                          (Presentation Layer)
│   │   │       ├── service/                             (Business Logic Layer)
│   │   │       ├── repository/                          (Data Access Layer)
│   │   │       └── entity/                              (Model/Entity Layer)
│   │   └── resources/
│   │       ├── application.properties                   (Configuration)
│   │       ├── static/                                  (CSS, JS, Images)
│   │       └── templates/                               (HTML Templates)
│   └── test/
│       └── java/
│           └── vn/edu/hcmut/cse/adse/lab/
│               └── StudentManagementApplicationTests.java
├── pom.xml                                              (Maven Configuration)
├── mvnw & mvnw.cmd                                     (Maven Wrapper)
├── student.db                                           (SQLite Database)
└── README.md                                            (This file)
```

### ⚙️ Hướng Dẫn Chạy Dự Án

#### Yêu Cầu Tiên Quyết
- **Java 21** (hoặc mới hơn)
- **Maven 3.6+** (hoặc sử dụng Maven Wrapper)
- **Git**

#### Bước 1: Clone Repository
```bash
git clone <link-repo>
cd student-management
```

#### Bước 2: Cài Đặt Dependencies
```bash
./mvnw clean install
```
Hoặc trên Windows:
```bash
mvnw.cmd clean install
```

#### Bước 3: Chạy Ứng Dụng
```bash
./mvnw spring-boot:run
```

**Kết quả mong đợi**: 
- Ứng dụng khởi động trên port `8080`
- File `student.db` sẽ được tạo tự động tại thư mục gốc project
- Log hiển thị: `Started StudentManagementApplication in ... seconds`

#### Bước 4: Truy Cập Ứng Dụng
- **URL**: `http://localhost:8080`
- **Server Health Check**: `http://localhost:8080/actuator/health` (nếu cấu hình Actuator)

#### Dừng Ứng Dụng
Nhấn `Ctrl + C` trong terminal

### 📊 Cấu Hình Database

**File Configuration**: `src/main/resources/application.properties`

```properties
# Application Name
spring.application.name=student-management

# Database SQLite Configuration
spring.datasource.url=jdbc:sqlite:student.db
spring.datasource.driver-class-name=org.sqlite.JDBC

# JPA/Hibernate Configuration
spring.jpa.database-platform=org.hibernate.community.dialect.SQLiteDialect
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
```

**Giải Thích**:
- `spring.datasource.url`: Đường dẫn tới file SQLite database
- `spring.jpa.hibernate.ddl-auto=create`: Tự động tạo lại bảng mỗi lần khởi động (dùng cho dev)
- `spring.jpa.show-sql=true`: Hiển thị SQL queries trong log (dùng cho debug)

### 📦 Bảng Dữ Liệu

#### Bảng `students`
```sql
CREATE TABLE students (
    id INTEGER PRIMARY KEY,
    name TEXT,
    email TEXT,
    age INTEGER
);
```

**Dữ Liệu Mẫu** (12 sinh viên):

| ID | Name | Email | Age |
|----|------|-------|-----|
| 1 | Nguyen Van A | vana@example.com | 20 |
| 2 | Tran Thi B | thib@example.com | 21 |
| 3 | Le Van C | levanc@example.com | 22 |
| 4 | Pham Thi D | phamthid@example.com | 20 |
| 5 | Hoang Van E | hovane@example.com | 21 |
| 6 | Vu Thi F | vuthif@example.com | 23 |
| 7 | Tran Van G | tranvang@example.com | 19 |
| 8 | Dinh Thi H | dinthih@example.com | 22 |
| 9 | Ngo Van I | ngovani@example.com | 20 |
| 10 | Bui Thi J | buithij@example.com | 21 |
| 11 | Dang Van K | dangvank@example.com | 24 |
| 12 | Ly Thi L | lythil@example.com | 20 |

### ❓ Trả Lời Các Câu Hỏi Lý Thuyết

#### Câu 1: Ràng Buộc Khóa Chính (Primary Key)
**Thử nghiệm**: Insert một sinh viên có id trùng với một sinh viên đã có sẵn
```sql
INSERT INTO students (id, name, email, age) VALUES (1, 'Duplicate ID', 'dup@example.com', 25);
```

**Kết quả**: 
```
Error: stepping, UNIQUE constraint failed: students.id (19)
```

**Giải thích**: 
Database chặn thao tác INSERT vì vi phạm ràng buộc Primary Key. SQLite đảm bảo mỗi ID là **duy nhất** và **không có 2 bản ghi nào có ID giống nhau**. Đây là một cơ chế bảo vệ dữ liệu quan trọng, giúp:
- Tránh dữ liệu trùng lặp
- Đảm bảo toàn vẹn dữ liệu
- Giúp xác định duy nhất mỗi bản ghi

#### Câu 2: Toàn Vẹn Dữ Liệu (Data Integrity) - NULL Values
**Thử nghiệm**: Insert sinh viên mà bỏ trống cột name
```sql
INSERT INTO students (id, email, age) VALUES (13, 'test@example.com', 20);
```

**Kết quả**: 
- Dữ liệu được INSERT thành công
- Cột `name` chứa giá trị `NULL`
- Dữ liệu: `13||test@example.com|20`

**Giải thích**: 
Hiện tại bảng không có ràng buộc `NOT NULL` cho cột `name`. Điều này tạo ra vấn đề:
- **Dữ liệu không nhất quán**: Thông tin sinh viên thiếu tên
- **Lỗi ở tầng Java**: Entity class có thể yêu cầu field `name` không được null, gây `NullPointerException`
- **Logic Business không được thực thi**: Ràng buộc dữ liệu bị bỏ qua

**Khuyến Nghị**: Nên thêm ràng buộc `NOT NULL` vào các cột bắt buộc:
```sql
CREATE TABLE students (
    id INTEGER PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT NOT NULL,
    age INTEGER
);
```

#### Câu 3: Cấu Hình Hibernate - DDL-Auto Strategy
**Thắc mắc**: Tại sao mỗi lần tắt ứng dụng và chạy lại, dữ liệu cũ lại bị mất hết?

**Giải thích**:
Cấu hình `spring.jpa.hibernate.ddl-auto=create` trong `application.properties` có nghĩa:
- Mỗi khi ứng dụng khởi động, Hibernate sẽ:
  1. **DROP (xóa)** tất cả các bảng cũ
  2. **CREATE (tạo)** lại bảng mới dựa trên Entity class

**Các tùy chọn khác**:
| Tùy Chọn | Mô Tả | Khi Nào Dùng |
|---------|--------|------------|
| `create` | Xóa + tạo lại mỗi lần khởi động | Development, Testing |
| `create-drop` | Xóa khi app dừng | Unit Testing |
| `update` | Cập nhật schema mà không mất dữ liệu | Development (bảo tồn dữ liệu) |
| `validate` | Chỉ validate schema, không thay đổi | Production |
| `none` | Không làm gì | Production (manual migration) |

**Ưu/Nhược Điểm**:
- ✅ **Ưu**: Dễ setup, không cần migration script
- ❌ **Nhược**: Mất dữ liệu mỗi lần chạy, không phù hợp production

**Khuyến Nghị cho môi trường khác nhau**:
```properties
# Development
spring.jpa.hibernate.ddl-auto=create

# Production
spring.jpa.hibernate.ddl-auto=validate
# Sử dụng Flyway/Liquibase cho migration
```

### 🔧 Dependencies Chính

```xml
<!-- Spring Boot Starters -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webmvc</artifactId>
</dependency>

<!-- SQLite JDBC Driver -->
<dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.41.2.1</version>
</dependency>

<!-- Hibernate Dialect for SQLite -->
<dependency>
    <groupId>org.hibernate.orm</groupId>
    <artifactId>hibernate-community-dialects</artifactId>
    <version>6.2.4.Final</version>
</dependency>

<!-- Test Dependencies -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa-test</artifactId>
    <scope>test</scope>
</dependency>
```

### 📝 Ghi Chú Quan Trọng

1. **Database Persistence**: 
   - Khi dùng `ddl-auto=create`, dữ liệu sẽ bị xóa mỗi lần khởi động
   - Để bảo tồn dữ liệu trong development, sử dụng `ddl-auto=update`

2. **SQLite Limitations**:
   - SQLite là serverless database (không cần server riêng)
   - Phù hợp cho development và small projects
   - Không hỗ trợ concurrent writes tốt như PostgreSQL/MySQL
   - Sẽ chuyển sang PostgreSQL ở Lab 4

3. **Logging**:
   - Cấu hình `spring.jpa.show-sql=true` để debug SQL queries
   - Hữu ích trong development, nên tắt ở production

4. **Maven Wrapper**:
   - Lệnh `./mvnw` được ưu tiên hơn `mvn` vì không cần cài Maven toàn hệ thống
   - Đảm bảo tất cả developer dùng cùng Maven version

### 🚀 Lab Tiếp Theo
Ở **Lab 2**, chúng ta sẽ:
- Tạo Entity class `Student`
- Tạo Repository interface `StudentRepository`
- Tạo Service class `StudentService`
- Tạo Controller `StudentController`
- Implement CRUD API

---

## 📖 Lab 2: Xây Dựng Backend REST API

### 🎯 Mục Tiêu Lab 2
- ✅ Hiện thực hóa Entity, Repository, Service, Controller
- ✅ Xây dựng API phục vụ truy vấn dữ liệu (Read-only)
- ✅ Kiểm thử REST API

### 📋 Thành Phần Đã Thực Hiện

#### 1. **Entity Layer** - `Student.java`
```java
@Entity
@Table(name = "students")
public class Student {
    @Id
    private String id;  // String ID (manual management)
    private String name;
    private String email;
    private int age;
    // Getters, Setters, Constructors
}
```

#### 2. **Repository Layer** - `StudentRepository.java`
```java
@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    // Spring Data JPA automatically implements CRUD operations
}
```

#### 3. **Service Layer** - `StudentService.java`
```java
@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;
    
    public List<Student> getAll() {
        return repository.findAll();
    }
    
    public Student getById(String id) {
        return repository.findById(id).orElse(null);
    }
}
```

#### 4. **Controller Layer** - `StudentController.java`
```java
@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService service;
    
    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAll();
    }
    
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable String id) {
        return service.getById(id);
    }
}
```

#### 5. **Data Loader** - `DataLoader.java`
- Tự động load 12 sinh viên mẫu khi ứng dụng khởi động
- Implements `CommandLineRunner` interface

### 📌 API Specification

| Chức Năng | Method | Endpoint | Request Body | Response |
|-----------|--------|----------|--------------|----------|
| Lấy danh sách | GET | `/api/students` | - | `List<Student>` (JSON) |
| Lấy chi tiết | GET | `/api/students/{id}` | - | `Student` (JSON) |

### 🧪 Kiểm Thử API

#### Test 1: Get All Students
```bash
curl http://localhost:8080/api/students
```

**Kết quả mong đợi:**
```json
[
  {
    "id": "1",
    "name": "Nguyen Van A",
    "email": "vana@example.com",
    "age": 20
  },
  {
    "id": "2",
    "name": "Tran Thi B",
    "email": "thib@example.com",
    "age": 21
  }
  // ... 10 more students
]
```

#### Test 2: Get Student by ID
```bash
curl http://localhost:8080/api/students/1
```

**Kết quả mong đợi:**
```json
{
  "id": "1",
  "name": "Nguyen Van A",
  "email": "vana@example.com",
  "age": 20
}
```

#### Test 3: Get Non-Existent Student
```bash
curl http://localhost:8080/api/students/999
```

**Kết quả mong đợi:** `null` (hoặc empty response)

### 🏗️ Kiến Trúc Cải Thiện (Dependency Injection)

**Trước (Tightly Coupled):**
```java
StudentService service = new StudentService();  // ❌ Manual instantiation
```

**Sau (Loosely Coupled - Dependency Injection):**
```java
@Autowired
private StudentService service;  // ✅ Spring automatically injects
```

**Lợi ích:**
- Dễ viết Unit Test (có thể mock dependencies)
- Dễ thay đổi implementation
- Quản lý lifecycle tự động

### 📊 Database Schema Update

```sql
CREATE TABLE students (
    age integer not null,
    email varchar(255),
    id varchar(255) not null,
    name varchar(255),
    primary key (id)
);
```

**Thay đổi từ Lab 1:**
- ❌ `id INTEGER PRIMARY KEY` (Lab 1)
- ✅ `id VARCHAR(255) PRIMARY KEY` (Lab 2) - hỗ trợ String IDs

### 🔧 Annotation Giải Thích

| Annotation | Vị Trí | Công Dụng |
|-----------|--------|----------|
| `@Entity` | Class Student | Đánh dấu class ánh xạ với table |
| `@Table` | Class Student | Chỉ định tên table |
| `@Id` | Field id | Chỉ định primary key |
| `@Repository` | Interface StudentRepository | Đánh dấu Data Access Layer |
| `@Service` | Class StudentService | Đánh dấu Business Logic Layer |
| `@RestController` | Class StudentController | Đánh dấu REST API Controller |
| `@RequestMapping` | Class StudentController | Định nghĩa base URL path |
| `@GetMapping` | Method | Ánh xạ HTTP GET request |
| `@PathVariable` | Parameter | Trích xuất giá trị từ URL path |
| `@Autowired` | Field | Dependency Injection |

### 📝 Ghi Chú Quan Trọng

1. **Dependency Injection Pattern:**
   - Spring Container quản lý lifecycle của beans
   - `@Autowired` tự động inject dependencies
   - Giảm coupling, dễ test

2. **Dynamic Proxy (Spring Data JPA):**
   - StudentRepository là interface, không có class implementation
   - Spring tự động tạo implementation class lúc runtime
   - CRUD operations được tự động generate

3. **Data Loading:**
   - `DataLoader` tự động chạy sau startup
   - Kiểm tra database có dữ liệu chưa trước khi load
   - Tránh duplicate data

4. **API Response:**
   - Tất cả responses được convert thành JSON tự động
   - `@RestController` = `@Controller` + `@ResponseBody`

### 🚀 Lab 3: Frontend - Server-Side Rendering (SSR)

### 🎯 Mục Tiêu
- ✅ Hiểu mô hình MVC (Model-View-Controller)
- ✅ Cấu hình và sử dụng Thymeleaf Template Engine
- ✅ Xây dựng Server-Side Rendering (SSR)
- ✅ Thêm chức năng tìm kiếm

### 📋 Mô Tả Lab 3

Ở Lab 2 chúng ta đã xây dựng REST API trả về JSON. Ở Lab 3, chúng ta sẽ:
1. Thêm Thymeleaf Template Engine để tạo HTML động
2. Tạo Web Controller (khác với REST Controller)
3. Xây dựng HTML template hiển thị danh sách sinh viên
4. Thêm tính năng tìm kiếm (search)

### 🏗️ Kiến Trúc MVC (Model-View-Controller)

```
Browser Request (/students)
    ↓
StudentWebController (@Controller)
  ├─ Nhận request từ /students
  ├─ Gọi StudentService để lấy dữ liệu
  ├─ Đóng gói data vào Model
  └─ Return "students" (tên view)
    ↓
Thymeleaf Template Engine
  ├─ Đọc students.html
  ├─ Thay thế ${dsSinhVien} bằng dữ liệu thực
  ├─ Render HTML đầy đủ
  └─ Return HTML to Browser
    ↓
Browser nhận HTML hoàn chỉnh
  └─ Hiển thị bảng sinh viên ngay lập tức
```

### ✨ Tính Năng Chính (Lab 3)

| Tính Năng | Mô Tả | Status |
|-----------|-------|--------|
| Thymeleaf Integration | Template Engine cho SSR | ✅ |
| StudentWebController | Web Controller (@Controller) | ✅ |
| students.html | View template với Thymeleaf syntax | ✅ |
| Search Form | Tìm kiếm theo tên hoặc email | ✅ |
| Data Binding | Hiển thị dữ liệu động từ Backend | ✅ |
| CSS Styling | Giao diện đẹp và responsive | ✅ |
| Status Badges | Hiển thị trạng thái tuổi (≥18) | ✅ |
| Statistics | Hiển thị tổng sinh viên & tuổi trung bình | ✅ |

### 📝 Thymeleaf Syntax

**1. Variable Expression - Biến từ Controller**
```html
<!-- In giá trị của biến -->
<td th:text="${student.name}">Tên mẫu</td>
<!-- Kết quả: <td>Nguyen Van A</td> -->
```

**2. Loop - Lặp qua danh sách**
```html
<!-- Với mỗi student trong dsSinhVien -->
<tr th:each="student : ${dsSinhVien}">
    <td th:text="${student.id}">ID</td>
    <td th:text="${student.name}">Name</td>
</tr>
```

**3. Conditional - Điều kiện**
```html
<!-- Nếu tuổi >= 18 -->
<span th:if="${student.age >= 18}">✓ Đủ 18</span>
<!-- Nếu tuổi < 18 -->
<span th:unless="${student.age >= 18}">⚠ Chưa 18</span>
```

**4. Dynamic Class - Thêm class động**
```html
<!-- Nếu tuổi < 18, thêm class 'text-danger' -->
<tr th:class="${student.age < 18} ? 'text-danger' : ''">
```

**5. Form Binding - Liên kết với form**
```html
<!-- Giữ giá trị tìm kiếm trong input -->
<input type="text" name="keyword" th:value="${keyword}" />
```

### 🚀 Cách Chạy Lab 3

1. **Khởi động ứng dụng:**
```bash
./mvnw spring-boot:run
```

2. **Truy cập giao diện:**
```
http://localhost:8080/students
```

3. **Tìm kiếm:**
```
http://localhost:8080/students?keyword=Nguyen
http://localhost:8080/students?keyword=vana@example.com
```

4. **Test tất cả tính năng:**
```bash
chmod +x test_lab3_ssr.sh
./test_lab3_ssr.sh
```

### 📊 Kết Quả Test (Lab 3)

✅ **14 Test Cases - ALL PASS**

| Test | Status | Mô Tả |
|------|--------|-------|
| Display all students | ✅ | GET /students renders 12 students |
| Page title | ✅ | "Danh Sach Sinh Vien" visible |
| Student in table | ✅ | All 12 students visible in table |
| Search by name | ✅ | keyword=Hoang returns 1 result |
| Search by email | ✅ | keyword=vana returns 1 result |
| Filter results | ✅ | Non-matching students filtered |
| Search indicator | ✅ | Shows search results message |
| HTML table | ✅ | Table, thead, tbody present |
| Thymeleaf namespace | ✅ | xmlns:th declared |
| CSS gradient | ✅ | Background styling applied |
| Font family | ✅ | Typography defined |
| Age badges | ✅ | Status indicators working |
| Clear search | ✅ | Reset returns to all students |
| Average age | ✅ | Statistics calculated correctly |

### 🔄 REST API vs SSR So Sánh

| Đặc Điểm | Lab 2 (REST) | Lab 3 (SSR) |
|---------|-------------|-----------|
| Controller | @RestController | @Controller |
| Response | JSON data | HTML page |
| Rendering | Client-side (JS) | Server-side (Thymeleaf) |
| URL | /api/students | /students |
| Template | N/A | students.html |
| Format | Raw JSON | HTML table |
| Tìm kiếm | N/A | Form submission |
| SEO | Kém | Tốt |
| First Load | JSON only | Complete HTML |

### 📁 File Structure (Lab 3)

```
src/main/java/vn/edu/hcmut/cse/adse/lab/
├── StudentManagementApplication.java
├── DataLoader.java
├── controller/
│   ├── StudentController.java       (Lab 2 - REST)
│   └── StudentWebController.java    (Lab 3 - SSR) ← NEW
├── service/
│   └── StudentService.java          (+ searchByName method)
├── repository/
│   └── StudentRepository.java
└── entity/
    └── Student.java

src/main/resources/
├── application.properties
├── templates/                        ← NEW DIRECTORY
│   └── students.html                ← NEW FILE
├── static/
└── ...

root/
├── pom.xml                          (+ thymeleaf dependency)
└── ...
```

### 💡 Khái Niệm Chính Lab 3

1. **Server-Side Rendering (SSR)**
   - Template engine xử lý trên server
   - Gửi HTML hoàn chỉnh tới browser
   - Tốt hơn cho SEO
   - Nhanh hơn initial load

2. **MVC Architecture**
   - Model: Dữ liệu (Student list)
   - View: Template (students.html)
   - Controller: Logic (StudentWebController)

3. **Template Engine (Thymeleaf)**
   - XML-based syntax
   - Natural templates (valid HTML)
   - Hỗ trợ expressions, loops, conditions
   - No external dependencies for view files

4. **Search Implementation**
   - @RequestParam để lấy query parameter
   - Stream API để filter dữ liệu
   - Case-insensitive matching
   - Multi-field search (name + email)

### 🎓 Learning Outcomes

Sau Lab 3, bạn sẽ hiểu:
- ✅ Mô hình MVC là gì
- ✅ Khác biệt giữa REST API và SSR
- ✅ Cách sử dụng Thymeleaf
- ✅ Cách implement search/filter
- ✅ Model Object để truyền dữ liệu
- ✅ Conditional rendering trong template

---

### 🚀 Lab Tiếp Theo (Lab 4)
Ở Lab 4, chúng ta sẽ:
- Thêm chức năng POST (tạo sinh viên)
- Thêm chức năng PUT (cập nhật sinh viên)
- Thêm chức năng DELETE (xóa sinh viên)
- Migrate database từ SQLite sang PostgreSQL

---

### 📞 Hỗ Trợ & Liên Hệ

Nếu gặp vấn đề:
1. Kiểm tra Java version: `java -version`
2. Kiểm tra Maven: `./mvnw -version`
3. Xóa folder `target` và `~/.m2/repository` rồi rebuild: `./mvnw clean install`
4. Kiểm tra SQLite file: `sqlite3 student.db ".tables"`
5. Xem chi tiết: `LAB3_COMPLETION_NOTES.md`

---

**Repository**: [Public URL sẽ được cập nhật ở Lab 5]  
**Ngôn Ngữ Triển Khai**: Sẽ được cập nhật ở Lab 5  
**Deploy URL**: Sẽ được cập nhật ở Lab 5

