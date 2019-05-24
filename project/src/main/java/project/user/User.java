package project.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	@NotNull
	@NotEmpty
	@Email
	private String email;
	@NotNull
	@NotEmpty
	private String firstName;
	
	private String lastName;
	@NotNull
	@NotEmpty
	private String password;
	
	private Integer balance=0;

	private Integer loanAmt=0;
	
	private Integer payment=0;
	
	public Integer getPayment() {
		return payment;
	}
	public void setPayment(Integer payment) {
		this.payment = payment;
	}
	public void setRole(String role) {
		this.role = role;
	}
	private String role = "USER";
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getRole() {
		return role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getBalance() {
		int loanAmount= this.loanAmt;
		balance = this.balance + loanAmount;
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public Integer getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(Integer loanAmt) {
		this.loanAmt = loanAmt;
	}
	
	
}
