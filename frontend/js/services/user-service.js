
let userService;

class UserService {
    currentUser = {};

    constructor()
    {
        this.loadUser();
    }

    getHeader()
    {
        if(this.currentUser.token) {
            return {
                    'Authorization': `Bearer ${this.currentUser.token}`
            };
        }

        return {};
    }

    register(username, password, confirmPassword) {
            const url = `${config.baseUrl}/register`;
            const registerData = {
                username: username,
                password: password,
                confirmPassword: confirmPassword,
                role: 'USER'
            };

            axios.post(url, registerData)
                .then(response => {
                    // Registration successful
                    // Optionally, you can automatically log in the user here
                    // Example:
                    // this.login(username, password);
                    console.log("Registration successful:", response.data);
                })
                .catch(error => {
                    // Registration failed
                    console.error("Registration failed:", error);
                });
        }

    saveUser(user)
    {
        this.currentUser = {
            token: user.token,
            userId: user.user.id,
            username: user.user.username,
            role: user.user.authorities[0].name
        }
        localStorage.setItem('user', JSON.stringify(this.currentUser));
    }

    loadUser()
    {
        const user = localStorage.getItem('user');
        if(user)
        {
            this.currentUser = JSON.parse(user);
            axios.defaults.headers.common = {'Authorization': `Bearer ${this.currentUser.token}`}
        }
    }

    getHeaders()
    {
        const headers = {
            'Content-Type': 'application/json'
        }

        if(this.currentUser.token)
        {
            headers.Authorization = `Bearer ${this.currentUser.token}`;
        }

        return headers;
    }

    getUserName()
    {
        return this.isLoggedIn() ? this.currentUser.username : '';
    }

    isLoggedIn()
    {
        return this.currentUser.token !== undefined;
    }

    getCurrentUser()
    {
        return this.currentUser;
    }

    setHeaderLogin()
    {
        const user = {
                username: this.getUserName(),
                loggedin: this.isLoggedIn(),
                loggedout: !this.isLoggedIn()
            };

        templateBuilder.build('header', user, 'header-user');
    }

    register(username, password, confirmPassword) {
            // Check if passwords match
            if (password !== confirmPassword) {
                const data = {
                    error: "Passwords do not match"
                };
                templateBuilder.append("error", data, "errors");
                return; // Exit the function without registering the user
            }

            // Proceed with user registration if passwords match
            const url = `${config.baseUrl}/register`;
            const registerData = {
                username: username,
                password: password,
                confirmPassword: confirmPassword,
                role: 'USER'
            };

            axios.post(url, registerData)
                .then(response => {
                    // Registration successful
                    // Optionally, you can automatically log in the user here
                    // Example:
                    // this.login(username, password);
                    console.log("Registration successful:", response.data);
                    const data = {
                                           error: "User registration successful. Please log in now."
                                        };
                                        templateBuilder.append("error", data, "errors");
                })
                .catch(error => {
                    // Registration failed
                    console.error("Registration failed:", error);
                    const data = {
                        error: "User registration failed."
                    };
                    templateBuilder.append("error", data, "errors");
                });
        }

    login (username, password)
    {
        const url = `${config.baseUrl}/login`;
        const login = {
            username: username,
            password: password
        };

        axios.post(url, login)
            .then(response => {
                this.saveUser(response.data)
                this.setHeaderLogin();

                axios.defaults.headers.common = {'Authorization': `Bearer ${this.currentUser.token}`}
                productService.enableButtons();
                cartService.loadCart();
            })
            .catch(error => {
                const data = {
                    error: "Login failed."
                };

                templateBuilder.append("error", data, "errors")
            })
    }

    logout()
    {
        localStorage.removeItem('user');
        axios.defaults.headers.common = {'Authorization': `bearer ${this.currentUser.token}`}
        this.currentUser = {};

        this.setHeaderLogin();

        productService.enableButtons();
    }

}

document.addEventListener('DOMContentLoaded', () => {
    userService = new UserService();
    userService.setHeaderLogin();
});
