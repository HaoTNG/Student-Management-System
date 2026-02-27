# 🐳 LAB 5 - DOCKER & DEPLOYMENT GUIDE

## ✅ Status: READY FOR DEPLOYMENT

All files are prepared for containerization and cloud deployment!

---

## 📋 Lab 5 Objectives

- ✅ Hiểu Docker (Image, Container, Dockerfile)
- ✅ Dockerize Spring Boot application
- ✅ Setup PostgreSQL trên Neon.tech
- ✅ Deploy lên Render.com
- ✅ Configure Environment Variables
- ✅ Setup CI/CD auto-deployment

---

## 🏗️ Files Created for Lab 5

### 1. **Dockerfile** - Multi-stage Build
```dockerfile
# Stage 1: Build with Maven
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run with JRE
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**Ưu điểm:**
- 🔹 Multi-stage: Stage 1 build app, Stage 2 run app
- 🔹 Giảm size image (chỉ JRE, không Maven)
- 🔹 Tối ưu hóa layer caching

### 2. **.dockerignore** - Exclude unnecessary files
```
target/
.m2/
.git/
.env
.idea/
node_modules/
*.md
```

### 3. **application.properties** - Environment variables support
```properties
server.port=${PORT:8080}
spring.datasource.url=${DATABASE_URL:jdbc:postgresql://localhost:5432/student_management}
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD:postgres}
```

**Ý nghĩa:**
- `${PORT:8080}` - Lấy từ env var, mặc định 8080
- `${DATABASE_URL:...}` - Lấy từ env var, mặc định local
- Cho phép chạy cùng trên dev & prod

### 4. **docker-compose.yml** - Local multi-container setup
```yaml
services:
  postgres:
    image: postgres:18-alpine
    environment:
      POSTGRES_PASSWORD: postgres
      POSTGRES_DB: student_management
    
  app:
    build: .
    environment:
      DATABASE_URL: jdbc:postgresql://postgres:5432/student_management
```

---

## 🚀 Phase 1: Local Testing with Docker Compose

### Bước 1: Build & Run locally
```bash
# Build Docker image
docker build -t student-management:latest .

# Run with docker-compose (includes PostgreSQL)
docker-compose up --build

# Check logs
docker-compose logs -f app

# Stop all
docker-compose down
```

### Bước 2: Test endpoints
```bash
# List students
curl http://localhost:8080/students

# API endpoint
curl http://localhost:8080/api/students
```

### Bước 3: View in browser
```
http://localhost:8080/students
```

---

## 🌐 Phase 2: Deploy Database to Neon.tech

### Bước 1: Tạo tài khoản Neon
1. Truy cập: https://neon.com/
2. Đăng ký (free tier)
3. Verify email

### Bước 2: Tạo Project & Database
1. Click **New Project**
2. Neon tự động tạo:
   - Database name: `neondb`
   - User: `neondb_owner`
   - Host: `xxxxx.neon.tech`

### Bước 3: Lấy Connection String
1. Tại Dashboard → **Connect**
2. Chọn **Connection string** tab
3. Copy chuỗi kết nối (dạng: `postgresql://...`)

### Bước 4: Modify cho Spring Boot
Neon cung cấp:
```
postgresql://neondb_owner:xxxxx@host/neondb?sslmode=require
```

Spring Boot cần:
```
jdbc:postgresql://neondb_owner:xxxxx@host/neondb?sslmode=require
```

**Lưu ý:** Thêm tiền tố `jdbc:`

### Bước 5: Test Connection (optional)
```bash
# Test kết nối (nếu có psql)
psql postgresql://neondb_owner:xxxxx@host/neondb?sslmode=require
```

---

## 🚀 Phase 3: Deploy lên Render.com

### Bước 1: Chuẩn bị GitHub Repository
```bash
# Đảm bảo push tất cả code lên GitHub main branch
git add .
git commit -m "Lab 5: Docker & Deployment"
git push origin main
```

**Files cần có trong repo:**
- ✅ `Dockerfile`
- ✅ `docker-compose.yml`
- ✅ `src/main/resources/application.properties`
- ✅ `pom.xml`
- ✅ Tất cả source code

### Bước 2: Tạo Render Account
1. Truy cập: https://render.com/
2. Đăng ký với GitHub account (easy login)
3. Verify email

### Bước 3: Tạo Web Service
1. Click **New +** → **Web Service**
2. Chọn **Build and deploy from a Git repository**
3. Kết nối GitHub:
   - Authorize Render
   - Chọn repository: `Student-Management-System`
   - Chọn branch: `main`

### Bước 4: Cấu Hình Service
| Trường | Giá Trị |
|--------|--------|
| **Name** | `student-management-api` |
| **Branch** | `main` |
| **Runtime** | `Docker` |
| **Build Command** | (để trống - auto) |
| **Start Command** | (để trống - Dockerfile ENTRYPOINT) |
| **Instance Type** | `Free` |

### Bước 5: Cấu Hình Environment Variables
**Kéo xuống Environment phần, thêm 3 biến:**

| Key | Value | Mô Tả |
|-----|-------|-------|
| `DATABASE_URL` | `jdbc:postgresql://user:pass@host/neondb?sslmode=require` | Connection string từ Neon |
| `DB_USERNAME` | `neondb_owner` | User từ Neon |
| `DB_PASSWORD` | `xxxxx` | Password từ Neon |

**🔴 QUAN TRỌNG:**
- Đừng commit `.env` lên GitHub
- Luôn dùng Environment Variables trên Render
- DATABASE_URL phải có tiền tố `jdbc:`

### Bước 6: Deploy
1. Click **Create Web Service**
2. Render sẽ:
   - Pull code từ GitHub
   - Build Docker image
   - Push lên Docker registry
   - Start container
3. Xem logs trong **Logs** tab (vài phút)
4. Khi thấy "Your service is live" → Deploy thành công!

### Bước 7: Access Application
Render cung cấp URL tự động, ví dụ:
```
https://student-management-api.onrender.com
```

Test endpoints:
```
https://student-management-api.onrender.com/students
https://student-management-api.onrender.com/api/students
```

---

## 📊 Architecture Diagram

```
┌─────────────────────────────────────────────────────────┐
│                   Internet / Browser                     │
│          https://student-management-api.onrender.com   │
└──────────────────────┬──────────────────────────────────┘
                       │
        ┌──────────────┴──────────────┐
        │                             │
    ┌───▼────────────────────┐   ┌───▼─────────────┐
    │  Render Web Service    │   │  Neon.tech DB   │
    │  (Docker Container)    │   │  PostgreSQL     │
    │  ├─ Spring Boot App    │   │  (serverless)   │
    │  ├─ Port 8080          │   └─────────────────┘
    │  └─ Auto-deploy        │
    └────────────────────────┘
           △
           │ (GitHub Webhook)
           │
    ┌──────┴──────────────┐
    │  GitHub Repository  │
    │  (Student Mgmt)     │
    │  main branch        │
    └─────────────────────┘
```

---

## 🧪 Testing Checklist

### Local Testing (Before Deploy)
- [ ] `docker build -t student-management:latest .` → Success
- [ ] `docker-compose up` → All services start
- [ ] `http://localhost:8080/students` → Page loads
- [ ] Create/Edit/Delete student → Works
- [ ] Search student → Works
- [ ] Check database → Data persists

### Render Testing (After Deploy)
- [ ] Build completed in Render Logs
- [ ] Container is running (green status)
- [ ] `https://xxx.onrender.com/students` → Page loads
- [ ] API endpoint `/api/students` → Returns JSON
- [ ] List students → Shows data
- [ ] Create new student → Works
- [ ] Neon database updated → Check in Neon SQL editor

---

## 🔧 Common Issues & Solutions

### Issue 1: Build fails - "Maven not found"
**Cause:** Dockerfile path incorrect
**Fix:**
```bash
# Ensure Dockerfile is in root
ls -la Dockerfile

# Rebuild
docker build -t student-management:latest .
```

### Issue 2: Connection refused to database
**Cause:** DATABASE_URL format wrong
**Fix:** Must start with `jdbc:`
```
❌ WRONG: postgresql://user:pass@host/db
✅ CORRECT: jdbc:postgresql://user:pass@host/db?sslmode=require
```

### Issue 3: Render deploy fails - "Git authentication"
**Cause:** Not authorized with GitHub
**Fix:**
1. Disconnect GitHub in Render settings
2. Re-authorize with GitHub
3. Grant repository access

### Issue 4: Application runs but no data
**Cause:** `ddl-auto=update` needs time to create tables
**Fix:**
1. Check Render logs for Hibernate messages
2. Wait 30-60 seconds
3. Refresh browser
4. Check Neon SQL editor for tables

### Issue 5: Slow startup
**Cause:** Free tier instances have limited resources
**Fix:**
- Normal for free tier (1-2 mins)
- Paid tier is faster
- Check Render logs for progress

---

## 📝 Environment Variables Reference

### For Local Development (.env)
```env
DATABASE_URL=jdbc:postgresql://localhost:5432/student_management
DB_USERNAME=postgres
DB_PASSWORD=postgres
PORT=8080
```

### For Render Deployment
```
DATABASE_URL = jdbc:postgresql://user:pass@neon-host/neondb?sslmode=require
DB_USERNAME = neondb_owner
DB_PASSWORD = xxxxx
PORT = 8080 (optional, Render sets automatically)
```

**Key Difference:**
- Local: `localhost`
- Render: Host từ Neon.tech (external)

---

## 🎯 Submission Requirements

### For Lab 5 Submission:
1. **Public URL** của deployed app
   - ✅ Example: `https://student-management-api.onrender.com`

2. **Verification:**
   - [ ] URL accessible từ browser
   - [ ] List students endpoint works
   - [ ] Can create/edit/delete students
   - [ ] Data persists after page refresh

3. **Optional - Screenshot/Evidence:**
   - Render dashboard showing "Your service is live"
   - Neon dashboard showing connected database
   - Browser screenshot of app running

---

## 📚 Lab Progression (Complete)

| Lab | Focus | Status |
|-----|-------|--------|
| Lab 1 | Setup, Database, Architecture | ✅ COMPLETE |
| Lab 2 | REST API (GET endpoints) | ✅ COMPLETE |
| Lab 3 | SSR with Thymeleaf, Search | ✅ COMPLETE |
| Lab 4 | CRUD + PostgreSQL + Validation | ✅ COMPLETE |
| Lab 5 | Docker + Deployment (THIS) | 🔄 IN PROGRESS |

---

## 🎓 Learning Outcomes (Lab 5)

After Lab 5, bạn sẽ biết:
- ✅ Docker fundamentals (Image, Container, Dockerfile)
- ✅ Multi-stage Docker builds
- ✅ Environment variables & configuration management
- ✅ Database as a Service (Neon)
- ✅ PaaS deployment (Render)
- ✅ CI/CD auto-deployment từ GitHub
- ✅ Production-ready application

---

## 💡 Next Steps (Beyond Course)

Nếu muốn mở rộng:
- Kubernetes deployment
- Docker Swarm
- Load balancing
- Monitoring & logging
- API documentation (Swagger)
- Testing (Unit, Integration)
- Performance optimization

---

## 📞 Support Resources

### Docker
- Official Docs: https://docs.docker.com/
- Docker Hub: https://hub.docker.com/

### Render
- Docs: https://render.com/docs
- Support: https://render.com/support

### Neon
- Docs: https://neon.tech/docs
- Connection String: https://neon.tech/docs/reference/connection-string

---

## ✨ Key Takeaways

```
┌──────────────────────────────────────────────────────────┐
│  Lab 5 Transform: Local Development → Production Ready  │
├──────────────────────────────────────────────────────────┤
│  ✅ Dockerfile     → Reproducible environment            │
│  ✅ docker-compose → Easy local development              │
│  ✅ Environment    → Flexible configuration              │
│  ✅ Neon.tech      → Managed PostgreSQL                  │
│  ✅ Render.com     → One-click deployment                │
│  ✅ CI/CD          → Auto-deploy on git push             │
│  ✅ Public URL     → Access anywhere on internet          │
└──────────────────────────────────────────────────────────┘
```

---

**Status**: ✅ **READY TO DEPLOY**  
**Docker**: ✅ **CONFIGURED**  
**Environment Variables**: ✅ **SETUP**  
**Documentation**: ✅ **COMPLETE**  
**Next Action**: Follow Phase 2 & Phase 3 steps above
