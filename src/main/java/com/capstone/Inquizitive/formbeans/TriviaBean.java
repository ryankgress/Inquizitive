package com.capstone.Inquizitive.formbeans;

import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TriviaBean {
    private Integer id;

    @NotEmpty(message = "Trivia Name is Required.")
    @Length(max = 45, message = "Cannot exceed 45 characters.")
    private String triviaName;

    @NotEmpty(message = "Location name is Required.")
    @Length(max = 45, message = "Cannot exceed 45 characters.")
    private String locationName;

    @NotEmpty(message = "Address is Required.")
    @Length(max = 45, message = "Cannot exceed 45 characters.")
    private String address1;

    @Length(max = 45, message = "Cannot exceed 45 characters.")
    private String address2;

    @NotEmpty(message = "City is Required.")
    @Length(max = 45, message = "Cannot exceed 45 characters.")
    private String city;

    @NotEmpty(message = "State is Required.")
    @Length(max = 3, message = "Use 2 letter abbrev.")
    private String state;

    @NotEmpty(message = "Zip Code is Required.")
    @Length(max = 10, message = "Improper zip format.")
    private String zip;

    @NotEmpty(message = "Date is Required.")
    @Length(max = 100, message = "Cannot exceed 100 characters.")
    private String date;

    @NotEmpty(message = "Start Time is Required.")
    @Length(max = 100, message = "Cannot exceed 100 characters.")
    private String time;



    private Integer hostId;

}
