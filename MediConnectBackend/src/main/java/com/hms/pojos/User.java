package com.hms.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor

@Inheritance(strategy = InheritanceType.JOINED) // Strategy for inheritance
public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long Id;
	
	@NotNull
	@Column(name = "user_name",  nullable = false, length = 100)
	private String userName;
	
	@NotNull
	@Column(name = "user_email", unique = true, nullable = false, length = 100)
	@Email(message = "Do Enter the Email...!!!")
	private String userEmail;
	
	@NotNull
	private String password;
	
	@NotNull
	private String phone_no;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	
	private User_role user_role;

}
