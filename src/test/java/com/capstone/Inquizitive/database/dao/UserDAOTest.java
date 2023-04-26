package com.capstone.Inquizitive.database.dao;

import com.capstone.Inquizitive.database.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOTest {

    @Autowired
    private UserDAO userDao;

    public void findByUsernameTest() {
        User user = userDao.findByUsername("ryankgress");

        Assertions.assertEquals(user.getEmail(), "ryankgress@gmail.com");
        Assertions.assertEquals(user.getName(), "Ryan Gress");
    }

    @Test
    @Order(0)
    public void CreateUserTest() {
        // Given
        User given = new User();
        given.setUsername("testUser");
        given.setEmail("test@test.com");
        given.setPassword("password");
        given.setProfilePic("/pub/images/default-pfp.png");
        given.setName("Test Testerson");

        // When
        userDao.save(given);

        // Then
        User actual = userDao.findByUsername("testUser");

        Assertions.assertEquals(given.getUsername(), actual.getUsername());
        Assertions.assertEquals(given.getEmail(), actual.getEmail());
        Assertions.assertEquals(given.getPassword(), actual.getPassword());
        Assertions.assertEquals(given.getProfilePic(), actual.getProfilePic());
        Assertions.assertEquals(given.getName(), actual.getName());
    }

    @Test
    @Order(1)
    public void UpdateUserTest() {
        // Given
        User given = userDao.findByUsername("testUser");

        given.setEmail("test123@test.com");
        given.setName("Testo Testerson");

        // When
        userDao.save(given);

        // Then
        User actual = userDao.findByUsername("testUser");

        Assertions.assertEquals(given.getEmail(), actual.getEmail());
        Assertions.assertEquals(given.getName(), actual.getName());
    }

    @Test
    @Order(2)
    public void DeleteUserTest() {
        // Given
        User given = userDao.findByUsername("testUser");

        // When
        userDao.delete(given);

        // Then
        User actual = userDao.findByUsername("testUser");
        Assertions.assertNull(actual);
    }

}
