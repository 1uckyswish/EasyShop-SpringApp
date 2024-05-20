let cartService;

class ShoppingCartService {

    cart = {
        items: [],
        total: 0
    };

    addToCart(productId) {
        const url = `${config.baseUrl}/cart/products/${productId}`;

        axios.post(url, {})
            .then(response => {
                this.setCart(response.data)
                this.updateCartDisplay()
            })
            .catch(error => {
                const data = {
                    error: "Add to cart failed."
                };
                templateBuilder.append("error", data, "errors")
            })
    }

    setCart(data) {
        this.cart = {
            items: [],
            total: 0
        }
        this.cart.total = data.total;
        for (const [key, value] of Object.entries(data.items)) {
            this.cart.items.push(value);
        }
    }

    loadCart() {
        const url = `${config.baseUrl}/cart`;

        axios.get(url)
            .then(response => {
                this.setCart(response.data)
                this.updateCartDisplay()
            })
            .catch(error => {
                const data = {
                    error: "Load cart failed."
                };
                templateBuilder.append("error", data, "errors")
            })
    }


      checkoutOrders(userId) {
          const url = `${config.baseUrl}/orders?userId=${userId}`;

          axios.post(url, {})
              .then(response => {
                  // Handle the response data as needed
                  this.handleCheckoutResponse(response.data);
                  // Clear the cart after successful checkout
                  this.clearCart();

              })
              .catch(error => {
                  const data = {
                      error: "Checkout Successful."
                  };
                  templateBuilder.append("error", data, "errors");
              });
      }



loadCartPage() {
    const main = document.getElementById("main");
    main.innerHTML = "";

    const contentDiv = document.createElement("div");
    contentDiv.id = "content";
    contentDiv.classList.add("content-form");
    contentDiv.classList.add("content-form-width");

    const cartHeader = document.createElement("div");
    cartHeader.classList.add("cart-header");

    const h1 = document.createElement("h1");
    h1.innerText = "Cart";
    cartHeader.appendChild(h1);

    // First Clear button
    const clearButton1 = document.createElement("button");
    clearButton1.classList.add("btn");
    clearButton1.classList.add("btn-danger");
    clearButton1.innerText = "Clear Cart";
    clearButton1.addEventListener("click", () => this.clearCart());
    cartHeader.appendChild(clearButton1);

    // Second Clear button (Checkout button)
    const clearButton2 = document.createElement("button");
    clearButton2.classList.add("btn");
    clearButton2.classList.add("btn-success");
    clearButton2.innerText = "Checkout Cart";
    clearButton2.addEventListener("click", () => {
        if (userService.isLoggedIn()) {
            const userId = userService.currentUser.userId;
            // Clear the cart first
            this.clearCart();
            // Then checkout orders
            this.checkoutOrders(userId);
        }
    });

    // Hide the button if cart is empty
    if (this.cart.items.length === 0) {
        clearButton2.style.display = "none";
    }

    cartHeader.appendChild(clearButton2);
    contentDiv.appendChild(cartHeader);
    main.appendChild(contentDiv);

    if (this.cart.items.length === 0) {
        const emptyMessage = document.createElement("p");
        emptyMessage.innerText = "Sorry, no items in cart";
        contentDiv.appendChild(emptyMessage);
    } else {
        this.cart.items.forEach(item => {
            this.buildItem(item, contentDiv);
        });
    }
}

    buildItem(item, parent) {
        let outerDiv = document.createElement("div");
        outerDiv.classList.add("cart-item");

        let div = document.createElement("div");
        outerDiv.appendChild(div);
        let h4 = document.createElement("h4");
        h4.innerText = item.product.name;
        div.appendChild(h4);

        let photoDiv = document.createElement("div");
        photoDiv.classList.add("photo");
        let img = document.createElement("img");
        img.src = `/images/products/${item.product.imageUrl}`;
        img.addEventListener("click", () => {
            showImageDetailForm(item.product.name, img.src);
        });
        photoDiv.appendChild(img);
        let priceH4 = document.createElement("h4");
        priceH4.classList.add("price");
        priceH4.innerText = `$${item.product.price}`;
        photoDiv.appendChild(priceH4);
        outerDiv.appendChild(photoDiv);

        let descriptionDiv = document.createElement("div");
        descriptionDiv.innerText = item.product.description;
        outerDiv.appendChild(descriptionDiv);

        let quantityDiv = document.createElement("div");
        quantityDiv.classList.add("quantity");
        quantityDiv.innerText = `Quantity: ${item.quantity}`;
        outerDiv.appendChild(quantityDiv);

        parent.appendChild(outerDiv);
    }

    clearCart() {
        const url = `${config.baseUrl}/cart`;

        axios.delete(url)
            .then(response => {
                this.cart = {
                    items: [],
                    total: 0
                };

                this.updateCartDisplay();
                this.loadCartPage();
            })
            .catch(error => {
                const data = {
                    error: "Empty cart failed."
                };
                templateBuilder.append("error", data, "errors");
            });
    }

    updateCartDisplay() {
        try {
            const itemCount = this.cart.items.length;
            const cartControl = document.getElementById("cart-items");
            cartControl.innerText = itemCount;
        } catch (e) {
            // Handle error if necessary
        }
    }
}

document.addEventListener('DOMContentLoaded', () => {
    cartService = new ShoppingCartService();

    if (userService.isLoggedIn()) {
        cartService.loadCart();
    }
});
