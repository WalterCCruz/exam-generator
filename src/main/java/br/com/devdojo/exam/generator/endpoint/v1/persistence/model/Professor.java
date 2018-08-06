package br.com.devdojo.exam.generator.endpoint.v1.persistence.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Professor extends AbstractEntity {

    @NotEmpty (message = "The field name cannot be empty")
    private String nam;

    @Email(message = "This email is not valid")
    @NotEmpty( message = "The field email cannot be empty")
    @Column(unique = true)
    private String email;


    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
