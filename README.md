# EasyShop E-Commerce Application

## Overview

Welcome to the EasyShop E-Commerce Application! This repository showcases the comprehensive enhancements and upgrades I made to an existing e-commerce platform's backend. Built with Spring Boot and MySQL, the application now features robust functionality, crucial bug fixes, and several new features that improve user experience and application performance. Additionally, I have added an enhanced, user-friendly UI that is vibrant and engaging, along with new detailed images of products to improve the store's visual appeal and help boost sales.

In this project, I assumed the role of a backend developer for EasyShop's online store. The primary tasks included fixing bugs, implementing new features, and ensuring that the API is well-tested and functional. The backend server uses a Spring Boot API, and the data is managed in a MySQL database.


## UI Enhancements: Before and After

### Before:
The UI of the EasyShop E-Commerce Application was functional but lacked visual appeal and user engagement. The layout was basic, and product images were limited in detail, hindering the overall shopping experience. Navigation was straightforward but lacked vibrancy and modern design elements.

<img width="1280" alt="Screenshot 2024-05-18 at 10 44 21 PM" src="https://github.com/1uckyswish/EasyShop-SpringApp/assets/107442415/54d34b5e-a043-45a7-bba8-63c4b4c92da8">


### After:
Following comprehensive enhancements, the EasyShop E-Commerce Application now boasts an enriched UI experience. The layout is dynamic and user-friendly, featuring vibrant colors and modern design elements. Product images have been updated with high-quality, detailed visuals, enhancing the visual appeal of the store and elevating the overall shopping experience. Navigation is seamless and intuitive, ensuring users can easily browse and interact with the platform.

<img width="1440" alt="Screenshot 2024-05-20 at 7 56 01 PM" src="https://github.com/1uckyswish/EasyShop-SpringApp/assets/107442415/f8182587-6d58-43da-8021-3a5d9cd1b5a1">


## Features Implemented

### Categories Management
Implemented complete CRUD operations for categories:
- **GET /categories**: Retrieve all categories.
- **GET /categories/{id}**: Retrieve a category by ID.
- **POST /categories**: Create a new category.
- **PUT /categories/{id}**: Update an existing category.
- **DELETE /categories/{id}**: Delete a category.

<img width="1440" alt="Screenshot 2024-05-20 at 7 33 48 PM" src="https://github.com/1uckyswish/EasyShop-SpringApp/assets/107442415/21b6818e-2454-4cdb-81cd-abadb683acfd">


### Products Management
Enhanced product management to ensure only admins can modify products:
- **GET /products**: Retrieve all products.
- **GET /products/{id}**: Retrieve a product by ID.
- **POST /products**: Add a new product.
- **PUT /products/{id}**: Update a product.
- **DELETE /products/{id}**: Remove a product.

<img width="1440" alt="Screenshot 2024-05-20 at 7 34 50 PM" src="https://github.com/1uckyswish/EasyShop-SpringApp/assets/107442415/d22d1efa-472b-4393-9526-5c654b83ffbc">

## Bug Fixes

### Search and Filter Functionality
Resolved issues with the search and filter functionality to ensure accurate results:
- Corrected logic to return appropriate products based on search criteria such as category, price range, and color.
- Ensured the search functionality operates as expected with diverse filter combinations.

<img width="1440" alt="Screenshot 2024-05-20 at 7 35 03 PM" src="https://github.com/1uckyswish/EasyShop-SpringApp/assets/107442415/9dfe5143-c408-42a8-8ef8-e0639465099b">

![Screenshot 2024-05-20 at 7 35 32 PM](https://github.com/1uckyswish/EasyShop-SpringApp/assets/107442415/4ec591ba-f7f9-4396-8f4d-5969ac905268)

### Product Duplication
Eliminated product duplication issues:
- Identified and fixed the root cause of duplicate product entries.
- Implemented safeguards to maintain data integrity and prevent recurrence of duplicates.

![Screenshot 2024-05-20 at 7 36 13 PM](https://github.com/1uckyswish/EasyShop-SpringApp/assets/107442415/dcbd5eda-4f6a-4982-b4c0-42ae52c3f6a0)


## New Features

### Shopping Cart
Developed a comprehensive shopping cart feature:
- **GET /cart**: Retrieve the current user's shopping cart.
- **POST /cart/products/{productId}**: Add a product to the cart.
- **PUT /cart/products/{productId}**: Update the quantity of a product in the cart.
- **DELETE /cart/products/{productId}**: Remove a product from the cart.
- **DELETE /cart**: Clear the entire shopping cart.

![Screenshot 2024-05-20 at 7 37 07 PM](https://github.com/1uckyswish/EasyShop-SpringApp/assets/107442415/e7445379-f53c-46f6-94ad-310ebfcba858)


### User Profile
Implemented user profile management:
- **GET /profile**: View the user's profile.
- **PUT /profile**: Update the user's profile.
- Ensured that user profiles are created upon registration and can be updated by the user.

![Screenshot 2024-05-20 at 7 37 40 PM](https://github.com/1uckyswish/EasyShop-SpringApp/assets/107442415/4f25256b-0193-4871-8637-7ca66aa5af38)


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

<img width="1440" alt="Screenshot 2024-05-20 at 7 43 16 PM" src="https://github.com/1uckyswish/EasyShop-SpringApp/assets/107442415/620be74b-cb06-4450-82f0-adb2135bc9e6">
<img width="1440" alt="Screenshot 2024-05-20 at 7 41 44 PM" src="https://github.com/1uckyswish/EasyShop-SpringApp/assets/107442415/2286f2bb-2d64-4030-a230-4ee1853d007e">
<img width="1440" alt="Screenshot 2024-05-20 at 7 41 52 PM" src="https://github.com/1uckyswish/EasyShop-SpringApp/assets/107442415/bd440413-9cd5-4daf-8e1b-4e456f0a4d11">
<img width="1440" alt="Screenshot 2024-05-20 at 7 42 04 PM" src="https://github.com/1uckyswish/EasyShop-SpringApp/assets/107442415/c6de2210-720b-463b-b768-40e9038a77b4">


## Demo

During the final presentation, I demonstrated the project by:
- Running the front-end web application to showcase new features.
- Highlighting bug fixes and their impact on the application.
- Presenting Postman scripts used to test the API endpoints and logic.

<img width="1440" alt="Screenshot 2024-05-20 at 7 38 14 PM" src="https://github.com/1uckyswish/EasyShop-SpringApp/assets/107442415/6804884b-e35c-4b6a-a50c-6745e030154e">

<img width="1440" alt="Screenshot 2024-05-20 at 7 38 32 PM" src="https://github.com/1uckyswish/EasyShop-SpringApp/assets/107442415/49622c6a-6f6e-44e5-82df-bb0fc8963901">

<img width="1440" alt="Screenshot 2024-05-20 at 7 40 44 PM" src="https://github.com/1uckyswish/EasyShop-SpringApp/assets/107442415/b9910951-9307-4186-b871-5c0aa0afff18">

<img width="1440" alt="Screenshot 2024-05-20 at 7 41 05 PM" src="https://github.com/1uckyswish/EasyShop-SpringApp/assets/107442415/190430d4-6a90-4539-852f-4d8c22dbc44e">



### Interesting Piece of Code: Frontend Development

In this project, I leveraged my frontend developer skills and experience to enhance the user interface of the EasyShop E-Commerce Application. Using HTML, Bootstrap JS, and CSS, I revamped the frontend to create a visually appealing and user-friendly experience. One notable aspect of this implementation is the integration of a new color scheme, which adds vibrancy and modernity to the UI.

Additionally, I utilized DOM manipulation techniques to seamlessly populate the frontend with data from the backend API routes. This allowed for dynamic content generation, ensuring that users receive up-to-date information about products and categories as they interact with the application.

![Screenshot 2024-05-20 at 7 53 51 PM](https://github.com/1uckyswish/EasyShop-SpringApp/assets/107442415/7baba4b1-590b-4a47-b4f5-25667bcd1f26)
![Screenshot 2024-05-20 at 7 54 13 PM](https://github.com/1uckyswish/EasyShop-SpringApp/assets/107442415/5a049199-e9ec-40e0-a6b4-88dbb4460e1d)


