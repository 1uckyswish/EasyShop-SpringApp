# EasyShop E-Commerce Application

## Overview

Welcome to the EasyShop E-Commerce Application! This repository showcases the comprehensive enhancements and upgrades I made to an existing e-commerce platform's backend. Built with Spring Boot and MySQL, the application now features robust functionality, crucial bug fixes, and several new features that improve user experience and application performance.

## Table of Contents

- [Project Description](#project-description)
- [Features Implemented](#features-implemented)
  - [Categories Management](#categories-management)
  - [Products Management](#products-management)
- [Bug Fixes](#bug-fixes)
  - [Search and Filter Functionality](#search-and-filter-functionality)
  - [Product Duplication](#product-duplication)
- [New Features](#new-features)
  - [Shopping Cart](#shopping-cart)
  - [User Profile](#user-profile)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [Demo](#demo)

## Project Description

In this project, I assumed the role of a backend developer for EasyShop's online store. The primary tasks included fixing bugs, implementing new features, and ensuring that the API is well-tested and functional. The backend server uses a Spring Boot API, and the data is managed in a MySQL database.

## Features Implemented

### Categories Management
Implemented complete CRUD operations for categories:
- **GET /categories**: Retrieve all categories.
- **GET /categories/{id}**: Retrieve a category by ID.
- **POST /categories**: Create a new category.
- **PUT /categories/{id}**: Update an existing category.
- **DELETE /categories/{id}**: Delete a category.

### Products Management
Enhanced product management to ensure only admins can modify products:
- **GET /products**: Retrieve all products.
- **GET /products/{id}**: Retrieve a product by ID.
- **POST /products**: Add a new product.
- **PUT /products/{id}**: Update a product.
- **DELETE /products/{id}**: Remove a product.

## Bug Fixes

### Search and Filter Functionality
Resolved issues with the search and filter functionality to ensure accurate results:
- Corrected logic to return appropriate products based on search criteria such as category, price range, and color.
- Ensured the search functionality operates as expected with diverse filter combinations.

### Product Duplication
Eliminated product duplication issues:
- Identified and fixed the root cause of duplicate product entries.
- Implemented safeguards to maintain data integrity and prevent recurrence of duplicates.

## New Features

### Shopping Cart
Developed a comprehensive shopping cart feature:
- **GET /cart**: Retrieve the current user's shopping cart.
- **POST /cart/products/{productId}**: Add a product to the cart.
- **PUT /cart/products/{productId}**: Update the quantity of a product in the cart.
- **DELETE /cart/products/{productId}**: Remove a product from the cart.
- **DELETE /cart**: Clear the entire shopping cart.

### User Profile
Implemented user profile management:
- **GET /profile**: View the user's profile.
- **PUT /profile**: Update the user's profile.
- Ensured that user profiles are created upon registration and can be updated by the user.

## API Endpoints

### Categories
- **GET /categories**
- **GET /categories/{id}**
- **POST /categories**
- **PUT /categories/{id}**
- **DELETE /categories/{id}**

### Products
- **GET /products**
- **GET /products/{id}**
- **POST /products**
- **PUT /products/{id}**
- **DELETE /products/{id}**

### Shopping Cart
- **GET /cart**
- **POST /cart/products/{productId}**
- **PUT /cart/products/{productId}**
- **DELETE /cart/products/{productId}**
- **DELETE /cart**

### User Profile
- **GET /profile**
- **PUT /profile**

## Testing

- Extensively used **Postman** to test all API endpoints.
- Developed unit tests to ensure the correctness of search and filter functionalities.
- Verified bug fixes and new features through comprehensive testing scenarios.

## Demo

During the final presentation, I demonstrated the project by:
- Running the front-end web application to showcase new features.
- Highlighting bug fixes and their impact on the application.
- Presenting Postman scripts used to test the API endpoints and logic.
