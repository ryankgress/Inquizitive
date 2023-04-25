package com.capstone.Inquizitive.formbeans;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RsvpBean {
    private Integer id;
    private Integer teamId;
    private Integer triviaId;
}
