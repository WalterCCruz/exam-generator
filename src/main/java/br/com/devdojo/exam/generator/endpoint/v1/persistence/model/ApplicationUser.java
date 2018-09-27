package br.com.devdojo.exam.generator.endpoint.v1.persistence.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class ApplicationUser extends AbstractEntity{

    @NotEmpty(message = "The field username cannot be empty")
    @Column(unique = true)
    private String userName;

    @NotEmpty(message = "The field password cannot be empty")
    private String password;

    @OneToOne
    private Professor professor;

    public ApplicationUser (){

    }

    public ApplicationUser(UserDetails applicationUser) {
        this.userName = applicationUser.userName;
        this.password = applicationUser.password;
        this.professor = applicationUser.professor;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
