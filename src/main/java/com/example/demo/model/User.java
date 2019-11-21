package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "User", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "id"
        })
})

public class User {

        @Id
        private String id;

        @NotBlank
        @Size(max = 40)
        @Email
        private String email;

        @NotBlank
        @Size(min = 7, message = "Should have more than 7 character")
        private String password;

        public User() {
        }

        public User(String id, @NotBlank @Size(max = 40) String email, @NotBlank @Size(max = 50) String password) {
                this.id = id;
                this.email = email;
                this.password = password;
        }


        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
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
