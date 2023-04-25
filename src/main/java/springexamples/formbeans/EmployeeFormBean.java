package springexamples.formbeans;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import springexamples.validation.EmailUnique;

@Getter
@Setter
@ToString
public class EmployeeFormBean {
    private Integer id;

    @NotEmpty(message = "First name is required")
    @Length(max = 45, message = "First name cannot be longer than 45 characters.")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    @Length(max = 45, message = "Last name cannot be longer than 45 characters.")
    private String lastName;

    @NotEmpty(message = "Email is required")
    @Length(max = 45, message = "Email cannot be longer than 45 characters.")
    @Pattern(regexp=".+@.+\\.[a-z]+", message = "Invalid email format")
    @EmailUnique(message = "Email already exists in the database.")
    private String email;

    private String extension;

    private String jobTitle;

    @Max(value = 100, message = "Can not exceed 100 vacation hours")
    @Min(value = 0, message = "Can not have less than 0 vacation hours")
    private Integer vacationHours;

    private Integer officeId;

    private String profileImage;

}
