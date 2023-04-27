package com.capstone.Inquizitive.database.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ResultDAOTest {

    @Autowired
    private ResultDAO resultDao;

    @ParameterizedTest
    @CsvSource({"11,6,3rd", "10,7,3rd", "8,14,1st"})
    public void getResultFromTeamIdAndTriviaIdTest(Integer teamId, Integer triviaId, String expectedPlacement) {

        // When
        String actualPlacement = resultDao.getResultFromTeamIdAndTriviaId(teamId,triviaId).getPlacement();

        // Then
        Assertions.assertEquals(actualPlacement, expectedPlacement);
    }
}
