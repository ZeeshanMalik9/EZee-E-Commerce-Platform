# Zeemerse E-Commerce Backend

## Overview

Zeemerse is a robust, scalable e-commerce platform backend developed using Spring Boot. This project provides a complete Java-based solution for managing an online marketplace, including user authentication, product catalog, order processing, payment integrations, seller management, and administrative controls. The application leverages modern Java technologies to ensure high performance, security, and maintainability.

The project started on 9/7/2025 and is designed to support a full-fledged e-commerce ecosystem with features like JWT-based authentication, multi-role user management (customers, sellers, admins), real-time payment processing via Razorpay and Stripe, and comprehensive order tracking.

## Features

Zeemerse offers a wide range of features essential for an e-commerce platform:

- **User Authentication and Authorization**: Secure login/signup with OTP verification, JWT token-based authentication, and role-based access control (Customer, Seller, Admin).
- **User Management**: Profile management, address handling, and user verification.
- **Product Catalog Management**: CRUD operations for products, advanced search and filtering (by category, brand, price, discount, etc.), pagination, and inventory tracking.
- **Shopping Cart and Wishlist**: Add/remove items, manage cart contents, and save favorite products.
- **Order Processing and Management**: Place orders, track order status, handle order items, and manage order history.
- **Payment Integration**: Secure payment processing using Razorpay and Stripe, with support for multiple payment methods and status tracking.
- **Seller Management**: Seller onboarding, product management for sellers, seller reports, and order handling for sellers.
- **Review and Rating System**: User reviews and ratings for products.
- **Coupon and Deal Management**: Admin-controlled coupons and deals for promotions.
- **Admin Controls**: Administrative oversight for users, products, orders, coupons, and platform analytics.
- **Transaction Tracking**: Detailed transaction logs for payments and orders.
- **Email Notifications**: Automated emails for order confirmations, OTPs, and updates using Spring Boot Mail.
- **Home Categories and Deals**: Dynamic home page content management for categories and featured deals.

## Tech Stack

- **Java**: Version 17
- **Spring Boot**: Version 3.5.5 (for rapid application development and microservices support)
- **Spring Data JPA**: For ORM and database interactions
- **Spring Security**: For authentication and authorization
- **Spring Web**: For building RESTful APIs
- **Database**: MySQL (with MySQL Connector/J)
- **Lombok**: For reducing boilerplate code
- **JWT (JJWT)**: For secure token-based authentication
- **Payment Gateways**: Razorpay (v1.4.6) and Stripe (v26.12.0) for payment processing
- **Validation**: Spring Boot Starter Validation for input validation
- **Mail**: Spring Boot Starter Mail for email services
- **Build Tool**: Maven
- **Testing**: Spring Boot Starter Test with JUnit

## Prerequisites

Before running the application, ensure you have the following installed:

- **Java**: JDK 17 or higher
- **Maven**: Version 3.6 or higher
- **MySQL**: Version 8.0 or higher (for database)
- **Git**: For cloning the repository
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code (recommended for development)

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/zeemerse.git
   cd zeemerse
   ```

2. **Configure the Database**:
   - Create a MySQL database (e.g., `zeemerse_db`).
   - Update the database configuration in `src/main/resources/application.properties` (or `application.yml` if using YAML):
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/zeemerse_db
     spring.datasource.username=your-username
     spring.datasource.password=your-password
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     ```

3. **Configure Payment Gateways** (Optional for full functionality):
   - Obtain API keys from Razorpay and Stripe.
   - Add them to `application.properties`:
     ```properties
     razorpay.api.key=your-razorpay-key
     razorpay.api.secret=your-razorpay-secret
     stripe.api.key=your-stripe-key
     ```

4. **Install Dependencies**:
   ```bash
   mvn clean install
   ```

## Running the Application

1. **Build the Project**:
   ```bash
   mvn clean compile
   ```

2. **Run the Application**:
   - Using Maven:
     ```bash
     mvn spring-boot:run
     ```
   - Or, package and run the JAR:
     ```bash
     mvn clean package
     java -jar target/zeemerse-0.0.1-SNAPSHOT.jar
     ```

3. **Access the Application**:
   - The application will start on `http://localhost:8080` by default.
   - Use tools like Postman or Swagger UI (if configured) to interact with the APIs.

## API Documentation

Zeemerse exposes a RESTful API for all operations. Below is a high-level overview of key endpoints. For detailed API documentation, refer to the Swagger UI (if enabled) or use Postman collections.

### Authentication
- `POST /auth/signup`: Register a new user
- `POST /auth/signin`: User login
- `POST /auth/sent/login-signup-otp`: Send OTP for login/signup

### User Management
- `GET /users/profile`: Get user profile
- `PUT /users/profile`: Update user profile

### Product Management
- `GET /products/{id}`: Get product by ID
- `GET /products/search`: Search products
- `GET /products`: Get all products with filters (category, brand, price, etc.)

### Cart and Wishlist
- `POST /cart/add`: Add item to cart
- `GET /cart`: Get cart contents
- `POST /wishlist/add`: Add to wishlist

### Orders
- `POST /orders`: Place an order
- `GET /orders/{id}`: Get order details
- `GET /orders/user`: Get user orders

### Payments
- `POST /payments/create-order`: Create payment order
- `POST /payments/verify`: Verify payment

### Sellers
- `POST /sellers/products`: Add product (seller)
- `GET /sellers/orders`: Get seller orders

### Admin
- `GET /admin/users`: Get all users
- `POST /admin/coupons`: Create coupon

### Reviews
- `POST /reviews`: Add review

### Other
- `GET /deals`: Get active deals
- `GET /home/categories`: Get home categories

**Note**: All endpoints requiring authentication use JWT tokens in the Authorization header. Roles (CUSTOMER, SELLER, ADMIN) determine access levels.

## Database Configuration

- **Database**: MySQL
- **ORM**: JPA with Hibernate
- **Schema**: Auto-generated based on entities (models like User, Product, Order).
- Ensure the database is running and accessible. Use `spring.jpa.hibernate.ddl-auto=update` for development (creates/updates tables automatically).

## Security

- **Authentication**: JWT-based with custom provider (JwtProvider.java).
- **Authorization**: Role-based access control using Spring Security.
- **Password Handling**: Secure password encoding.
- **OTP Verification**: For signup/login via email.
- **Constants**: JWT configuration in JwtConstant.java.

## Testing

- Run unit and integration tests using Maven:
  ```bash
  mvn test
  ```
- Tests are located in `src/test/java/` and cover services, controllers, and repositories.

## Contributing

We welcome contributions to Zeemerse! To contribute:

1. Fork the repository.
2. Create a feature branch: `git checkout -b feature/your-feature`.
3. Commit your changes: `git commit -m 'Add your feature'`.
4. Push to the branch: `git push origin feature/your-feature`.
5. Open a Pull Request.

Please follow Java coding standards and include tests for new features.

## License

This project is licensed under the MIT License. See the LICENSE file for details (if available).

## Contact

For questions or support, please contact the development team or open an issue on GitHub.

---

**Note**: This README is based on the current state of the codebase as of the analysis. Ensure to update configurations and dependencies as needed for production deployment.
