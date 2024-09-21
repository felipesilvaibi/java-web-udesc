package com.udesc.web.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public class RentalDto {

    @NotNull
    private UUID customerId;

    @NotNull
    private UUID stockId;

    @NotNull
    private String rentalDate;

    private String returnDate;

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getStockId() {
        return stockId;
    }

    public void setStockId(UUID stockId) {
        this.stockId = stockId;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
