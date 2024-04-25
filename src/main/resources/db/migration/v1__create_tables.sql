CREATE TABLE User (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fullName VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS restaurant (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    owner_id BIGINT,
    name VARCHAR(255),
    description TEXT,
    cuisineType VARCHAR(255),
    address_id BIGINT,
    contactInformation_phoneNumber VARCHAR(255),
    contactInformation_email VARCHAR(255),
    openingHours VARCHAR(255),
    registrationDate TIMESTAMP,
    open BOOLEAN
);


CREATE TABLE IF NOT EXISTS `order` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT,
    restaurant_id BIGINT,
    totalAmount BIGINT,
    orderStatus VARCHAR(255),
    createdAt TIMESTAMP,
    deliveryAddress_id BIGINT,
    totalItem INT,
    totalPrice INT,
    FOREIGN KEY (customer_id) REFERENCES user(id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id),
    FOREIGN KEY (deliveryAddress_id) REFERENCES address(id)
);

CREATE TABLE IF NOT EXISTS cart (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT,
    total BIGINT,
    FOREIGN KEY (customer_id) REFERENCES user(id)
);