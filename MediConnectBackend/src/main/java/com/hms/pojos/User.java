package com.hms.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@NotNull
	@Column(name = "user_name", unique = true, nullable = false, length = 100)
	private String userName;
	
	@NotNull
	@Email(message = "Do Enter the Email...!!!")
	private String userEmail;
	
	@NotNull
	private String password;
	
	@NotNull
	private String phone_no;
	
	@NotNull
	private USER_ROLE user_role;

}
