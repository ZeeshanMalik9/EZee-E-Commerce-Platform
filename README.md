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

Zeemerse exposes a comprehensive RESTful API for all e-commerce operations. Below is a detailed list of all endpoints grouped by functionality. Most endpoints require authentication via JWT token in the `Authorization` header. Roles include CUSTOMER, SELLER, and ADMIN, with access restricted based on roles.

### Authentication
- `POST /auth/signup`: Register a new user (body: SighnupRequest)
- `POST /auth/signin`: User login (body: LonginRequest)
- `POST /auth/sent/login-signup-otp`: Send OTP for login/signup (body: LoginOtpRequest)

### User Management
- `GET /users/profile`: Get authenticated user's profile (header: Authorization)

### Product Management
- `GET /products/{productId}`: Get product by ID
- `GET /products/search`: Search products (query param: query)
- `GET /products`: Get all products with filters (query params: category, brand, color, size, minPrice, maxPrice, minDiscount, sort, stock, pageNumber)

### Cart Management
- `GET /api/cart`: Get authenticated user's cart (header: Authorization)
- `PUT /api/cart/add`: Add item to cart (body: AddItemRequest, header: Authorization)
- `DELETE /api/cart/item/{cartItemId}`: Remove item from cart (header: Authorization)
- `PUT /api/cart/item/{cartItemId}`: Update cart item quantity (body: CartItem, header: Authorization)

### Wishlist Management
- `GET /api/wishlist`: Get authenticated user's wishlist (header: Authorization)
- `POST /api/wishlist/add-product/{productId}`: Add product to wishlist (header: Authorization)

### Order Management
- `POST /api/orders`: Create order (body: Address, query param: paymentMethod, header: Authorization)
- `GET /api/orders/user`: Get authenticated user's order history (header: Authorization)
- `GET /api/orders/{orderId}`: Get order by ID (header: Authorization)
- `GET /api/orders/item/{orderItemId}`: Get order item by ID (header: Authorization)
- `PUT /api/orders/{orderId}/cancel`: Cancel order (header: Authorization)

### Payment Management
- `GET /api/payment/{paymentId}`: Handle payment success (query params: paymentLinkedId, header: Authorization)

### Seller Management
- `POST /sellers/login`: Seller login (body: LonginRequest)
- `PATCH /sellers/verify/{otp}`: Verify seller email with OTP
- `POST /sellers`: Create new seller (body: Seller)
- `GET /sellers/{id}`: Get seller by ID
- `GET /sellers/profile`: Get authenticated seller's profile (header: Authorization)
- `GET /sellers/report`: Get authenticated seller's report (header: Authorization)
- `GET /sellers`: Get all sellers (query param: status)
- `PATCH /sellers`: Update authenticated seller's details (body: Seller, header: Authorization)
- `DELETE /sellers/{id}`: Delete seller by ID

### Seller Product Management
- `GET /sellers/products`: Get products by authenticated seller (header: Authorization)
- `POST /sellers/products`: Create new product (body: CreateProductRequest, header: Authorization)
- `DELETE /sellers/products/{productId}`: Delete product by ID
- `PUT /sellers/products/{productId}`: Update product (body: Product)

### Seller Order Management
- `GET /api/seller/orders`: Get orders for authenticated seller (header: Authorization)
- `PATCH /api/seller/orders/{orderId}/status/{orderStatus}`: Update order status (header: Authorization)

### Review Management
- `GET /api/products/{productId}/review`: Get reviews for a product
- `POST /api/products/{productId}/review`: Write a review (body: CreateReviewRequest, header: Authorization)
- `PATCH /api/reviews/{reviewId}`: Update review (body: CreateReviewRequest, header: Authorization)
- `DELETE /api/revviews/{reviewId}`: Delete review (header: Authorization) [Note: Typo in endpoint, should be /reviews]

### Coupon Management
- `POST /api/coupon/apply`: Apply or remove coupon (query params: apply, code, orderValue, header: Authorization)
- `POST /api/coupon/admin/create`: Create coupon (admin only, body: Coupon)
- `DELETE /api/coupon/admin/delete/{id}`: Delete coupon (admin only)
- `GET /api/coupon/admin/all`: Get all coupons (admin only)

### Deal Management
- `POST /admin/deals`: Create deal (admin only, body: Deal)
- `PATCH /admin/deals/{id}`: Update deal (admin only, body: Deal)
- `DELETE /admin/deals/{id}`: Delete deal (admin only)

### Home Category Management
- `POST /home/categories`: Create home categories (body: List<HomeCatagory>)
- `GET /admin/home-category`: Get all home categories (admin only)
- `PATCH /admin/home-category/{id}`: Update home category (admin only, body: HomeCatagory)

### Transaction Management
- `GET /api/transactions/seller`: Get transactions for authenticated seller (header: Authorization)
- `GET /api/transactions`: Get all transactions (admin only)

### Admin Management
- `PATCH /api/seller/{id}/status/{status}`: Update seller account status (admin only)

### Home
- `GET /`: Welcome message for the application

**Notes**:
- Authentication: Use JWT tokens in the `Authorization` header for protected endpoints.
- Roles: Access is role-based (CUSTOMER, SELLER, ADMIN).
- Request/Response: Most endpoints use JSON for request bodies and responses.
- Pagination: Product listing supports pagination with `pageNumber`.
- Filters: Product search supports multiple filters like category, price range, etc.
- Payment Methods: Supported methods include RAZORPAY, STRIPE, and COD (Cash on Delivery).
- For full API specs, use Postman or integrate Swagger UI if configured.

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
