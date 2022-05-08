package com.store.retailstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class User {
    private String userName;
    private String mobileNumber;
    private LocalDateTime registeredDateAndTime;
    private LocalDateTime purchaseDateAndTime;
    private UserType userType;
}
