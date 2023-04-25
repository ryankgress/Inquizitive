package com.capstone.Inquizitive.formbeans;

import com.capstone.Inquizitive.database.entity.Team;
import com.capstone.Inquizitive.database.entity.TriviaDetail;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ResultsBean {

    private TriviaDetail triviaDetail;

    // Potentially, add all teams to hidden field on selections
    private String teams;

}
