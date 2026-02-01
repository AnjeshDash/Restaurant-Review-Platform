# Restaurant Review Platform

A full-stack restaurant review platform built with Spring Boot (Backend) and Next.js (Frontend), featuring Elasticsearch-powered search, geolocation services, OAuth2 authentication via Keycloak, and photo upload capabilities.

## 🏗️ Architecture

### Backend (Spring Boot)
- **Framework**: Spring Boot 3.4.2
- **Java Version**: Java 21
- **Database**: Elasticsearch 8.12.0
- **Authentication**: OAuth2 with Keycloak
- **Key Libraries**: 
  - Spring Security
  - Spring Data Elasticsearch
  - Lombok
  - MapStruct
  - Jakarta Validation

### Frontend (Next.js)
- **Framework**: Next.js 14.2.16
- **UI Library**: React 18 with shadcn/ui components
- **Styling**: Tailwind CSS
- **Authentication**: react-oidc-context
- **HTTP Client**: Axios
- **Maps**: Leaflet (OpenStreetMap)

## ✨ Features

### Restaurant Management
- ✅ Create, Read, Update, Delete restaurants
- ✅ Search restaurants by name, cuisine type, or location
- ✅ Filter by minimum rating
- ✅ Geolocation-based search (radius-based)
- ✅ Upload multiple restaurant photos
- ✅ Operating hours management (per day)
- ✅ Address and contact information

### Review System
- ✅ Write reviews with ratings (1-5 stars)
- ✅ Upload photos with reviews (up to 8 photos)
- ✅ Edit/Update own reviews
- ✅ Delete own reviews
- ✅ One review per user per restaurant
- ✅ Sort reviews by date or rating
- ✅ Automatic average rating calculation

### Authentication & Security
- ✅ OAuth2 authentication via Keycloak
- ✅ JWT-based authorization
- ✅ Public access to restaurant listings
- ✅ Protected endpoints for create/update/delete
- ✅ User-specific review management

### Search & Discovery
- ✅ Full-text search with Elasticsearch
- ✅ Filter by cuisine type
- ✅ Minimum rating filter
- ✅ Geolocation search (nearby restaurants)
- ✅ Pagination support

## 📋 Prerequisites

Before running this project, ensure you have the following installed:

1. **Java Development Kit (JDK) 21**
   - Download: https://adoptium.net/
   - Verify: `java -version`

2. **Apache Maven 3.8+**
   - Download: https://maven.apache.org/download.cgi
   - Verify: `mvn -version`

3. **Node.js 18+ and npm**
   - Download: https://nodejs.org/
   - Verify: `node -v` and `npm -v`

4. **Docker Desktop**
   - Download: https://www.docker.com/products/docker-desktop
   - Required for running Elasticsearch, Keycloak, and Kibana

5. **Git**
   - Download: https://git-scm.com/downloads

## 🚀 Getting Started

### Step 1: Clone the Repository

```bash
git clone <repository-url>
cd restaurant
```

### Step 2: Start Docker Services

Start Elasticsearch, Keycloak, and Kibana using Docker Compose:

```bash
docker-compose up -d
```

This will start:
- **Elasticsearch** on `http://localhost:9200`
- **Keycloak** on `http://localhost:9090`
- **Kibana** on `http://localhost:5601`

Verify services are running:
```bash
docker ps
```

### Step 3: Configure Keycloak

1. **Access Keycloak Admin Console**
   - URL: `http://localhost:9090`
   - Username: `admin`
   - Password: `admin`

2. **Create Realm**
   - Click on "Create Realm"
   - Name: `restaurant-review`
   - Click "Create"

3. **Create Client**
   - Go to "Clients" → "Create Client"
   - Client ID: `restaurant-review-app`
   - Client Type: `OpenID Connect`
   - Click "Next"
   
   **Capability config:**
   - Enable "Standard flow"
   - Enable "Direct access grants"
   - Click "Next"
   
   **Login settings:**
   - Valid redirect URIs: `http://localhost:3000/*`
   - Valid post logout redirect URIs: `http://localhost:3000/*`
   - Web origins: `http://localhost:3000`
   - Click "Save"

4. **Create Test User** (Optional)
   - Go to "Users" → "Create User"
   - Username: `testuser`
   - Email: `testuser@example.com`
   - Click "Create"
   - Go to "Credentials" tab
   - Set password: `password`
   - Disable "Temporary" toggle
   - Click "Set Password"

### Step 4: Run the Backend (Spring Boot)

Navigate to the project root directory:

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

Or use the Maven wrapper:
```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

The backend will start on `http://localhost:8080`

**Verify Backend:**
- Check health: `http://localhost:8080/api/restaurants`

### Step 5: Run the Frontend (Next.js)

Open a new terminal and navigate to the frontend directory:

```bash
cd frontend

# Install dependencies
npm install

# Run development server
npm run dev
```

The frontend will start on `http://localhost:3000`

### Step 6: Access the Application

Open your browser and go to:
```
http://localhost:3000
```

## 🔧 Configuration

### Backend Configuration

File: `src/main/resources/application.properties`

```properties
spring.application.name=restaurant
spring.elasticsearch.uris=http://localhost:9200
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:9090/realms/restaurant-review

spring.servlet.multipart.max-file-size=50MB
spring.servlet.multipart.max-request-size=50MB
```

### Frontend Configuration

File: `frontend/.env.local`

```env
NEXT_PUBLIC_KEYCLOAK_URL=http://localhost:9090
NEXT_PUBLIC_KEYCLOAK_REALM=restaurant-review
NEXT_PUBLIC_KEYCLOAK_CLIENT_ID=restaurant-review-app
NEXT_PUBLIC_API_URL=http://localhost:3000/api
NEXT_PUBLIC_BASE_URL=http://localhost:3000
```

**Note:** The frontend uses Next.js API rewrites to proxy requests to the backend (port 8080).

## 📁 Project Structure

```
restaurant/
├── src/main/java/com/anjesh/restaurant/
│   ├── config/              # Security & app configuration
│   ├── controllers/         # REST API endpoints
│   ├── domain/
│   │   ├── dtos/           # Data Transfer Objects
│   │   ├── entities/       # Elasticsearch entities
│   │   └── ...             # Request models
│   ├── exceptions/         # Custom exceptions
│   ├── mappers/            # MapStruct mappers
│   ├── repositories/       # Elasticsearch repositories
│   └── services/           # Business logic
├── frontend/
│   ├── app/                # Next.js pages (App Router)
│   │   ├── admin/
│   │   ├── restaurants/
│   │   └── page.tsx        # Home page
│   ├── components/         # React components
│   │   └── ui/            # shadcn/ui components
│   ├── domain/            # TypeScript types
│   ├── providers/         # Context providers
│   └── services/          # API services
├── docker-compose.yaml    # Docker services configuration
├── pom.xml               # Maven dependencies
└── README.md
```

## 🌐 API Endpoints

### Restaurants

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/restaurants` | Search restaurants | No |
| GET | `/api/restaurants/{id}` | Get restaurant details | No |
| POST | `/api/restaurants` | Create restaurant | Yes |
| PUT | `/api/restaurants/{id}` | Update restaurant | Yes |
| DELETE | `/api/restaurants/{id}` | Delete restaurant | Yes |

**Search Parameters:**
- `q` - Search query (name, cuisine)
- `minRating` - Minimum rating filter
- `latitude` - Latitude for geo search
- `longitude` - Longitude for geo search
- `radius` - Search radius in km
- `page` - Page number (default: 1)
- `size` - Page size (default: 20)

### Reviews

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/restaurants/{id}/reviews` | Get restaurant reviews | No |
| GET | `/api/restaurants/{id}/reviews/{reviewId}` | Get specific review | No |
| POST | `/api/restaurants/{id}/reviews` | Create review | Yes |
| PUT | `/api/restaurants/{id}/reviews/{reviewId}` | Update review | Yes |
| DELETE | `/api/restaurants/{id}/reviews/{reviewId}` | Delete review | Yes |

**Review Query Parameters:**
- `sort` - Sort order: `datePosted,desc` | `datePosted,asc` | `rating,desc` | `rating,asc`
- `page` - Page number
- `size` - Page size

### Photos

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | `/api/photos/upload` | Upload photo | Yes |
| GET | `/api/photos/{filename}` | Get photo | No |

## 🔐 Authentication Flow

1. User clicks "Sign In" button on frontend
2. Redirected to Keycloak login page
3. After successful login, Keycloak redirects back with authorization code
4. Frontend exchanges code for JWT access token
5. Access token is included in API requests via Authorization header
6. Backend validates JWT against Keycloak

## 🛠️ Development Commands

### Backend

```bash
# Clean and build
mvn clean install

# Run tests
mvn test

# Run application
mvn spring-boot:run

# Package as JAR
mvn package
```

### Frontend

```bash
# Install dependencies
npm install

# Run development server
npm run dev

# Build for production
npm run build

# Start production server
npm start

# Lint code
npm run lint

# Format code
npm run format

# Clean project
npm run clean
```

## 🐳 Docker Commands

```bash
# Start all services
docker-compose up -d

# Stop all services
docker-compose down

# View logs
docker-compose logs -f

# Restart services
docker-compose restart

# Remove volumes (clean data)
docker-compose down -v
```

## 🧪 Testing

### Backend Tests

```bash
mvn test
```

### Frontend Tests

```bash
cd frontend
npm run lint
```

## 📊 Monitoring

### Elasticsearch
- **URL:** `http://localhost:9200`
- **Health Check:** `http://localhost:9200/_cluster/health`

### Kibana (Elasticsearch UI)
- **URL:** `http://localhost:5601`
- View restaurant index: Dev Tools → `GET /restaurants/_search`

### Keycloak Admin
- **URL:** `http://localhost:9090`
- **Username:** `admin`
- **Password:** `admin`

## 🐛 Troubleshooting

### Problem: Backend fails to start - "Connection refused to Elasticsearch"

**Solution:**
```bash
# Check if Elasticsearch is running
docker ps

# Restart Elasticsearch
docker-compose restart elasticsearch

# Wait 30 seconds for Elasticsearch to start, then restart backend
```

### Problem: Frontend can't connect to backend

**Solution:**
1. Verify backend is running on port 8080
2. Check `frontend/.env.local` configuration
3. Check browser console for CORS errors
4. Restart frontend: `npm run dev`

### Problem: Keycloak authentication fails

**Solution:**
1. Verify Keycloak is running: `http://localhost:9090`
2. Check realm name: `restaurant-review`
3. Check client ID: `restaurant-review-app`
4. Verify redirect URIs are set correctly
5. Clear browser cache and cookies

### Problem: Photos not uploading

**Solution:**
1. Check file size (max 50MB)
2. Verify you're authenticated
3. Check backend logs for errors
4. Ensure multipart configuration in `application.properties`

### Problem: Docker services won't start

**Solution:**
```bash
# Check Docker is running
docker info

# Check port conflicts
netstat -ano | findstr :9200
netstat -ano | findstr :9090

# Remove old containers and volumes
docker-compose down -v
docker-compose up -d
```

## 🔄 Data Reset

To reset all data:

```bash
# Stop services
docker-compose down

# Remove volumes (deletes all data)
docker-compose down -v

# Restart services
docker-compose up -d
```

You'll need to reconfigure Keycloak after this.

## 📝 Environment Variables

### Backend
Set in `application.properties` or as system environment variables:
- `SPRING_ELASTICSEARCH_URIS`
- `SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_ISSUER_URI`

### Frontend
Set in `frontend/.env.local`:
- `NEXT_PUBLIC_KEYCLOAK_URL`
- `NEXT_PUBLIC_KEYCLOAK_REALM`
- `NEXT_PUBLIC_KEYCLOAK_CLIENT_ID`
- `NEXT_PUBLIC_API_URL`
- `NEXT_PUBLIC_BASE_URL`

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## 📄 License

This project is for educational purposes.

## 👤 Author

Anjesh

## 🆘 Support

For issues and questions:
1. Check the troubleshooting section
2. Review Docker logs: `docker-compose logs -f`
3. Check backend logs in console
4. Check browser console for frontend errors

## 🎯 Quick Start Checklist

- [ ] Java 21 installed
- [ ] Maven installed
- [ ] Node.js 18+ installed
- [ ] Docker Desktop running
- [ ] Clone repository
- [ ] Run `docker-compose up -d`
- [ ] Configure Keycloak (realm + client)
- [ ] Run backend: `mvn spring-boot:run`
- [ ] Install frontend deps: `cd frontend && npm install`
- [ ] Run frontend: `npm run dev`
- [ ] Open `http://localhost:3000`

---

**Happy Coding! 🚀**
