package com.hms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DoctorDTO {



	@NotNull
    @Size(min = 5, max = 50, message = "The Size Should Be More Than 5 and Less Than 50")
    private String specialization;

    @NotNull
    @Positive(message = "The Experience should be in Positive Term...")
    private int experience;

    @NotNull
    private String qualification;

    @NotNull
    private int capacity;

    // Extra Cols
    private String name;
    private String email;
    private String phone;
	
}
