
function showLoginForm()
{
    templateBuilder.build('login-form', {}, 'login');
}

function showRegisterForm()
{
    templateBuilder.build('registration-form', {}, 'register');
}

function hideModalForm()
{
    templateBuilder.clear('login');
}

function hideModalForm2() {
    templateBuilder.clear('register');
}


function login() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    userService.login(username, password);
    hideModalForm(); // Hide the login modal after updating the button
}

function showImageDetailForm(product, imageUrl)
{
    const imageDetail = {
        name: product,
        imageUrl: imageUrl
    };

    templateBuilder.build('image-detail',imageDetail,'login')
}

function createAdminButton() {
    if (userService.isLoggedIn() && userService.getCurrentUser().username === "admin") {
        // Check if the "make product" button already exists
        const existingButton = document.getElementById("adminButton");
        if (!existingButton) {
            // Create the "make product" button dynamically
            const adminButton = document.createElement("a");
            adminButton.href = "product.html";
            adminButton.id = "adminButton";
            adminButton.textContent = "Make Product";

            // Find the div where you want to append the button
            const linksDiv = document.getElementById("links");

            // Append the button to the div
            linksDiv.appendChild(adminButton);
        }
    }
}

function loadHome() {
    templateBuilder.build('home', {}, 'main');
    productService.search();
    categoryService.getAllCategories(loadCategories);

    // Create the admin button
    createAdminButton();
}

function editProfile() {
    profileService.loadProfile();
    createAdminButton();
}

document.addEventListener('DOMContentLoaded', () => {
    userService = new UserService();
    userService.setHeaderLogin();
    loadHome();
});


function saveProfile()
{
    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    const phone = document.getElementById("phone").value;
    const email = document.getElementById("email").value;
    const address = document.getElementById("address").value;
    const city = document.getElementById("city").value;
    const state = document.getElementById("state").value;
    const zip = document.getElementById("zip").value;

    const profile = {
        firstName,
        lastName,
        phone,
        email,
        address,
        city,
        state,
        zip
    };

    profileService.updateProfile(profile);
}

function showCart() {
    cartService.loadCartPage();
}


function clearCart()
{
    cartService.clearCart();
    cartService.loadCartPage();
}

function setCategory(control)
{
    productService.addCategoryFilter(control.value);
    productService.search();

}

function setColor(control)
{
    productService.addColorFilter(control.value);
    productService.search();

}

function setMinPrice(control)
{
    // const slider = document.getElementById("min-price");
    const label = document.getElementById("min-price-display")
    label.innerText = control.value;

    const value = control.value != 0 ? control.value : "";
    productService.addMinPriceFilter(value)
    productService.search();

}

function setMaxPrice(control)
{
    // const slider = document.getElementById("min-price");
    const label = document.getElementById("max-price-display")
    label.innerText = control.value;

    const value = control.value != 1500 ? control.value : "";
    productService.addMaxPriceFilter(value)
    productService.search();

}

function closeError(control)
{
    setTimeout(() => {
        control.click();
    },3000);
}


function register() {
    const username = document.getElementById("reg-username").value;
    const password = document.getElementById("reg-password").value;
    const confirmPassword = document.getElementById("reg-confirm-password").value;

    userService.register(username, password, confirmPassword, (user) => {
        // Registration successful
        // Hide the registration modal
        hideModalForm2(); // This should close the registration modal
    }, (error) => {
        // Registration failed
        console.error('Registration failed:', error);
        // You can display an error message to the user if needed
    });
}

