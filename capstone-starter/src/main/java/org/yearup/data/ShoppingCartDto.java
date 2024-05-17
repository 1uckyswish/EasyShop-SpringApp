package org.yearup.data;

public class ShoppingCartDto {
    private int quantity;

    public ShoppingCartDto() {
    }

    public ShoppingCartDto(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
