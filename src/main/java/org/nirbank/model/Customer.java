package org.nirbank.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
	//SELECT A STRATEGY FOR THE JPA TO GENERATE: native: let the underline DB to generate the sequence value(instead of the JPA ORM)
    @GenericGenerator(name = "native",strategy = "native")
	private int id; 
	private String email; 
	private String pwd; 
	private String role; 
	
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getEmail() {
        return email;
    }

    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

	@Override
	public String toString() {
		return "Customer [id=" + id + ", email=" + email + ", pwd=" + pwd + ", role=" + role + "]";
	}
    
    
}
