package com.heathcliff.app.ws.ui.model.request;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotNull;
//the data we get
public class UpdateUserDetailsRequestModel {
    @NonNull    
    String firstName;
    @NotNull
    String lastName;
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
}
