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

### 📞 Hỗ Trợ & Liên Hệ

Nếu gặp vấn đề:
1. Kiểm tra Java version: `java -version`
2. Kiểm tra Maven: `./mvnw -version`
3. Xóa folder `target` và `~/.m2/repository` rồi rebuild: `./mvnw clean install`
4. Kiểm tra SQLite file: `sqlite3 student.db ".tables"`

---

**Repository**: [Public URL sẽ được cập nhật ở Lab 5]  
**Ngôn Ngữ Triển Khai**: Sẽ được cập nhật ở Lab 5  
**Deploy URL**: Sẽ được cập nhật ở Lab 5
