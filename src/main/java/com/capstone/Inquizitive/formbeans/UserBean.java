package com.capstone.Inquizitive.formbeans;

import com.capstone.Inquizitive.validation.UsernameUnique;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserBean {
    private Integer id;

    @NotEmpty(message = "Name is Required.")
    @Length(max = 45, message = "Cannot exceed 45 characters.")
    private String name;

    @NotEmpty(message = "Username is Required.")
    @Length(max = 45, message = "Cannot exceed 45 characters.")
    @UsernameUnique(message = "Username already exists.")
    private String username;

    @NotEmpty(message = "Email is required")
    @Length(max = 45, message = "Email cannot be longer than 45 characters.")
    @Pattern(regexp=".+@.+\\.[a-z]+", message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Password is required")
    @Length(max = 45, message = "Password cannot be longer than 45 characters.")
    private String password;

    @NotEmpty(message = "Confirm Password is required")
    @Length(max = 45, message = "Password cannot be longer than 45 characters.")
    private String confirmPassword;

    private String profilePic;
    private String host;
    private MultipartFile profilePicture;

}
