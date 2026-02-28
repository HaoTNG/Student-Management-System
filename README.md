# Student Management System

## 📋 Nhóm / Group Information

| STT | Thành Viên | MSSV | Vai Trò |
|-----|-----------|------|---------|
| 1 | Nguyễn Trọng Hào | 2310854 | Scrum Master |


---

## 🌐 Public URL

**Web Service đã deploy:**
https://student-management-system-cj3d.onrender.com




## 🚀 Hướng Dẫn Chạy Dự Án

### Yêu Cầu Hệ Thống
- Java 21+
- Maven 3.9+
- PostgreSQL 18+
- Docker (tùy chọn, để chạy với docker-compose)

### Chạy Trên Máy Local

#### 1. Clone Repository
```bash
git clone https://github.com/HaoTNG/Student-Management-System.git
cd Student-Management-System
```

#### 2. Cấu Hình Database

**Tạo database PostgreSQL:**
```bash
# Đăng nhập vào PostgreSQL
sudo -u postgres psql

# Tạo database
CREATE DATABASE student_management;

# Thoát
\q
```

**Cấu hình credentials (application.properties):**
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/student_management
spring.datasource.username=postgres
spring.datasource.password=postgres
```

#### 3. Build & Run Application

**Sử dụng Maven:**
```bash
# Build ứng dụng
./mvnw clean package

# Chạy ứng dụng
./mvnw spring-boot:run
```

**Hoặc chạy JAR file:**
```bash
# Build
./mvnw clean package

# Run
java -jar target/student-management-0.0.1-SNAPSHOT.jar
```

#### 4. Truy Cập Ứng Dụng
```
http://localhost:8080/students
```

### Chạy Với Docker Compose (Kèm PostgreSQL)

```bash
# Build & run ứng dụng + database
docker-compose up --build

# Truy cập
http://localhost:8080/students

# Dừng
docker-compose down
```

---

## 📚  Lab

### Lab 1: Xây Dựng Cơ Sở Dự Án

**Mục tiêu:**
- Thiết lập Spring Boot project
- Cấu hình PostgreSQL database
- Tạo entity `Student` model
- Cấu hình Hibernate & JPA

**Công nghệ:**
- Spring Boot 4.0.2
- PostgreSQL 18
- Hibernate 6.2.4
- Maven

**Kết quả:**
- ✅ Project structure được thiết lập
- ✅ Database connection hoạt động
- ✅ Student entity được tạo

---

### Lab 2: REST API - GET Endpoints

**Mục tiêu:**
- Xây dựng REST API endpoints
- Implement GET methods
- Tạo StudentController

**Endpoints tạo được:**
```
GET /api/students           - Lấy danh sách tất cả sinh viên
GET /api/students/{id}      - Lấy chi tiết một sinh viên
GET /api/students?name=xxx  - Tìm kiếm sinh viên theo tên
```

**Công nghệ:**
- Spring Web
- Spring Data JPA
- REST principles

**Kết quả:**
- ✅ REST API hoạt động
- ✅ JSON response hợp lệ

---

### Lab 3: Server-Side Rendering (SSR) với Thymeleaf

**Mục tiêu:**
- Xây dựng giao diện web
- Render HTML server-side
- Implement search functionality

**Features:**
- Hiển thị danh sách sinh viên
- Tìm kiếm theo tên/email
- Thymeleaf templates

**Files:**
- `students.html` - Trang danh sách
- `StudentWebController.java` - Web controller

**Kết quả:**
- ✅ Web UI hoạt động
- ✅ Search feature hoạt động

---

### Lab 4: CRUD Operations & Validation

**Mục tiêu:**
- Implement đầy đủ CRUD (Create, Read, Update, Delete)
- Form validation
- Database migration sang PostgreSQL
- Environment configuration

**Features Được Implement:**

#### 1. Create (Thêm Mới)
```
GET  /students/create     - Hiển thị form thêm mới
POST /students/create     - Xử lý thêm mới sinh viên
```

#### 2. Read (Xem Chi Tiết)
```
GET  /students            - Danh sách sinh viên
GET  /students/{id}       - Chi tiết sinh viên
```

#### 3. Update (Cập Nhật)
```
GET  /students/{id}/edit  - Hiển thị form chỉnh sửa
POST /students/edit       - Xử lý cập nhật sinh viên
```

#### 4. Delete (Xóa)
```
GET  /students/delete     - Xóa sinh viên
```

**Validation Rules:**
| Field | Rule | Error Message |
|-------|------|---------------|
| Name | 3-100 characters | "Họ và tên phải có ít nhất 3 ký tự" |
| Email | Valid format | "Email không hợp lệ" |
| Age | 1-100 | "Tuổi phải nằm trong khoảng 1-100" |

**Database Migration:**
- ✅ Removed SQLite
- ✅ Added PostgreSQL (v42.7.1)
- ✅ Updated Hibernate dialect

**Configuration:**
- Environment variables support
- application.properties with fallback defaults

**Kết quả:**
- ✅ CRUD hoạt động hoàn toàn
- ✅ Form validation hoạt động
- ✅ PostgreSQL kết nối thành công

---

### Lab 5: Docker & Deployment

**Mục tiêu:**
- Dockerize ứng dụng Spring Boot
- Deploy lên cloud (Render)
- Cấu hình database serverless (Neon)
- Implement CI/CD auto-deployment

**Docker Setup:**
- Multi-stage build (Maven → JRE)
- Optimized for production

**Files:**
- `Dockerfile` - Docker image definition
- `.dockerignore` - Exclude unnecessary files
- `docker-compose.yml` - Local development setup

**Deployment Process:**

1. **Setup Database (Neon.tech):**
   - Tạo account Neon
   - Tạo PostgreSQL project
   - Lấy connection string

2. **Push to GitHub:**
   - Commit tất cả code
   - Push lên branch main

3. **Deploy to Render:**
   - Connect GitHub repo
   - Setup Web Service
   - Configure environment variables
   - Deploy app

4. **Verification:**
   - Check public URL
   - Test CRUD operations
   - Verify database connection

**Kết quả:**
- ✅ Docker image được build
- ✅ App deployed trên Render
- ✅ Database trên Neon
- ✅ Auto-deploy enabled
- ✅ Public URL accessible

---

## �� Project Structure

```
student-management/
├── src/
│   ├── main/
│   │   ├── java/vn/edu/hcmut/cse/adse/lab/
│   │   │   ├── StudentManagementApplication.java
│   │   │   ├── DataLoader.java
│   │   │   ├── controller/
│   │   │   │   ├── StudentController.java (REST API)
│   │   │   │   └── StudentWebController.java (Web)
│   │   │   ├── service/
│   │   │   │   └── StudentService.java
│   │   │   ├── repository/
│   │   │   │   └── StudentRepository.java
│   │   │   └── entity/
│   │   │       └── Student.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── templates/
│   │       │   ├── students.html
│   │       │   ├── form.html
│   │       │   └── detail.html
│   │       └── static/
│   └── test/
│       └── StudentManagementApplicationTests.java
├── Dockerfile
├── docker-compose.yml
├── .dockerignore
├── pom.xml
├── README.md (this file)
└── documentation files
```

---

## 🔧 Công Nghệ Sử Dụng

| Công Nghệ | Phiên Bản | Mục Đích |
|-----------|---------|---------|
| Java | 21 | Ngôn ngữ lập trình |
| Spring Boot | 4.0.2 | Framework web |
| Spring Data JPA | 4.0.2 | ORM & database |
| Hibernate | 6.2.4 | JPA implementation |
| PostgreSQL | 18 | Database |
| Thymeleaf | Latest | Template engine |
| Maven | 3.9.4 | Build tool |
| Docker | Latest | Containerization |

---

## 📊 API Endpoints Summary

### REST API (Lab 2 & 4)
```
GET    /api/students         - List all students (JSON)
GET    /api/students/{id}    - Get student detail (JSON)
GET    /api/students?name=   - Search students (JSON)
```

### Web Interface (Lab 3 & 4)
```
GET    /students             - List students (HTML)
GET    /students?keyword=    - Search students (HTML)
GET    /students/create      - Show create form
POST   /students/create      - Create new student
GET    /students/{id}        - Show student detail
GET    /students/{id}/edit   - Show edit form
POST   /students/edit        - Update student
GET    /students/delete      - Delete student
```

---

## 📝 Câu Trả Lời Lý Thuyết

### 1. Docker là gì? Lợi ích chính của Docker?

**Định nghĩa:**
Docker là nền tảng mở cho phép đóng gói, vận chuyển và chạy các ứng dụng trong các container cô lập.

**Lợi ích:**
- **Consistency:** Ứng dụng hoạt động giống nhau trên máy local, server test, và production
- **Isolation:** Container cô lập, không ảnh hưởng đến hệ thống khác
- **Portability:** Easy to move between environments
- **Scalability:** Dễ mở rộng ứng dụng
- **Resource Efficiency:** Sử dụng ít tài nguyên hơn VM

---

### 2. Phân biệt Docker Image vs Docker Container

**Docker Image:**
- Là template (bản thiết kế)
- Read-only
- Được build từ Dockerfile
- Có thể dùng để tạo nhiều container
- Ví dụ: `student-management:latest`

**Docker Container:**
- Là runtime instance
- Writable
- Được tạo từ image
- Chạy ứng dụng thực tế
- Ví dụ: `docker run -p 8080:8080 student-management:latest`

**So sánh:**
| Aspect | Image | Container |
|--------|-------|-----------|
| Type | Bản thiết kế | Instance thực tế |
| State | Immutable | Mutable |
| Size | Nhỏ hơn | Lớn hơn |
| Chế độ | Build time | Runtime |

---

### 3. Multi-stage Docker build có ích gì?

**Lợi ích:**
- **Giảm kích thước image:** Chỉ copy artifacts, không copy Maven/build tools
- **Tối ưu hóa:** Stage 1 build app, Stage 2 chỉ run
- **Security:** Production image không chứa source code hay build tools
- **Speed:** Final image nhỏ → faster deployment & pull

**Ví dụ:**
```dockerfile
# Stage 1: Build (có Maven, có source)
FROM maven:3.9.4-eclipse-temurin-21 AS build
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Stage 2: Run (chỉ JRE, chỉ JAR)
FROM eclipse-temurin:21-jre
COPY --from=build /app/target/*.jar app.jar
```

**So sánh kích thước:**
- Single-stage: ~800MB (Maven + build output)
- Multi-stage: ~300MB (chỉ JRE + JAR)

---

### 4. Environment Variables có vai trò gì trong deployment?

**Vai trò:**
- **Configuration Management:** Tách cấu hình khỏi code
- **Security:** Không hard-code passwords
- **Flexibility:** Dùng cùng image cho multiple environments
- **Scalability:** Dễ thay đổi mà không cần rebuild

**Ví dụ:**
```properties
server.port=${PORT:8080}
spring.datasource.url=${DATABASE_URL:jdbc:postgresql://localhost:5432/student_management}
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD:postgres}
```

**Sử dụng:**
- Local: Dùng default values
- Production (Render): Set via dashboard

---

### 5. Tại sao cần phải sử dụng PaaS như Render thay vì VPS?

**PaaS Advantages (Render):**
- **Easy Deployment:** Chỉ cần push Git code
- **Auto-scaling:** Tự động scale based on load
- **CI/CD Built-in:** Auto-deploy on git push
- **Managed Infrastructure:** Không lo OS, patches, security
- **Cost-effective:** Pay per use, free tier available

**VPS Disadvantages:**
- **Manual Setup:** Phải cấu hình OS, runtime, database
- **Manual Scaling:** Phải tự manage servers
- **Maintenance:** Phải update OS, patches, security
- **Higher Cost:** Cần trả phí cố định

---

### 6. Database as a Service (Neon) khác gì so với self-hosted PostgreSQL?

**Neon Advantages:**
- **Zero-ops:** Không phải manage backups, patches, updates
- **Serverless:** Pay only for what you use
- **Branching:** Tạo copy database nhanh để test
- **Auto-scaling:** Tự động scale resources
- **Availability:** Built-in HA, backups, monitoring

**Self-hosted Disadvantages:**
- **Maintenance:** Phải lo updates, patches, security
- **Backups:** Phải tự backup, verify restore
- **Scaling:** Phải tự handle increasing data/users
- **High availability:** Phức tạp, cost cao

---

## 📸 Screenshots - Lab 4 Modules

### Module 1: List Students
> Hiển thị danh sách sinh viên với action buttons (View, Edit, Delete)
<img width="1109" height="1196" alt="image" src="https://github.com/user-attachments/assets/0751cfd6-3b66-4a11-bb58-5766b7c75b82" />

### Module 2: Search Students
> Tìm kiếm sinh viên theo tên hoặc email
<img width="653" height="623" alt="image" src="https://github.com/user-attachments/assets/c195faad-45c5-4386-bef4-12974d7e76df" />

### Module 3: Create Student
> Form thêm mới sinh viên với validation
<img width="735" height="840" alt="image" src="https://github.com/user-attachments/assets/b1c5c700-8a52-4bc4-b790-5328ca982729" />

### Module 4: Edit Student
> Form chỉnh sửa thông tin sinh viên
<img width="845" height="931" alt="image" src="https://github.com/user-attachments/assets/f29b52db-2143-4b4c-9baf-672e3542d0d6" />

### Module 5: View Detail
> Xem chi tiết thông tin một sinh viên
<img width="936" height="919" alt="image" src="https://github.com/user-attachments/assets/a24cf569-bcba-4d6c-b04c-ed74b14a0124" />


### Module 6: Delete Student
> Xác nhận trước khi xóa sinh viên
<img width="681" height="475" alt="image" src="https://github.com/user-attachments/assets/a629dcdc-ff07-45f5-aef6-6e93365d194d" />


---


## 📞 Troubleshooting

### PostgreSQL connection failed
```bash
# Ensure PostgreSQL is running
sudo systemctl start postgresql

# Create database
sudo -u postgres psql -c "CREATE DATABASE student_management;"
```

### Docker build fails
```bash
# Check Dockerfile exists
ls -la Dockerfile

# Build image
docker build -t student-management:latest .
```

### Render deployment fails
- Check Render Logs tab
- Ensure DATABASE_URL starts with "jdbc:"
- Verify GitHub connection

### 502 Bad Gateway on Render
- Application is starting (cold start: 1-2 min)
- Wait and refresh browser

---

## 📚 References

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Docker Documentation](https://docs.docker.com/)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [Thymeleaf Documentation](https://www.thymeleaf.org/documentation.html)
- [Render Documentation](https://render.com/docs)
- [Neon Documentation](https://neon.tech/docs)

---

**Last Updated:** February 27, 2026  
**Status:** ✅ All Labs Complete & Ready for Deployment
