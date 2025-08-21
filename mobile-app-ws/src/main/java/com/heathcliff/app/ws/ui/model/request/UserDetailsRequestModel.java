package com.heathcliff.app.ws.ui.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDetailsRequestModel {
        @NotNull(message="FirstName cannot be null")
        private String firstName;
        @NotNull(message="lastName cannot be null")
        private String lastName;
        @NotNull(message="email cannot be null")
        @Email
        private String email;
        @NotNull(message="password cannot be null")
        @Size(min=4,max=4,message="Password lenght=4")
        private String password;

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
        
}
