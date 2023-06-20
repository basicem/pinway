package com.example.userservice;

import com.example.userservice.models.User;
import com.example.userservice.models.UserVisibilityType;
import com.example.userservice.repositories.UserRepository;
import com.example.userservice.repositories.UserVisibilityTypeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("Test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)

public class UserServiceTest {
    @Mock
    private RestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserVisibilityTypeRepository userVisibilityTypeRepository;

    private User user1, user2, user3;
    @BeforeAll
    void setup() {
        userRepository.deleteAll();

        Optional<UserVisibilityType> optionalUserVisibilityType = userVisibilityTypeRepository.findOneByType("PUBLIC");
        UserVisibilityType userVisibilityType = new UserVisibilityType();

        if(optionalUserVisibilityType.isPresent())
            userVisibilityType = optionalUserVisibilityType.get();

        User user1 = new User();
        user1.setId(3);
        user1.setName("Emina");
        user1.setSurname("Basic");
        user1.setUsername("ebasic");
        user1.setEmail("ebasic@etf.unsa.ba");
        user1.setPassword("emina123");
        user1.setCreatedAt(LocalDate.now());
        user1.setUserVisibilityType(userVisibilityType);

        optionalUserVisibilityType = userVisibilityTypeRepository.findOneByType("FOLLOWED");
        userVisibilityType = new UserVisibilityType();

        if(optionalUserVisibilityType.isPresent())
            userVisibilityType = optionalUserVisibilityType.get();

        User user2 = new User();
        user2.setId(0);
        user2.setName("Minela");
        user2.setSurname("Basic");
        user2.setUsername("aamer");
        user2.setEmail("amer@etf.unsa.ba");
        user2.setPassword("amer123");
        user2.setCreatedAt(LocalDate.now());
        user2.setUserVisibilityType(userVisibilityType);

        optionalUserVisibilityType = userVisibilityTypeRepository.findOneByType("PRIVATE");
        userVisibilityType = new UserVisibilityType();

        if(optionalUserVisibilityType.isPresent())
            userVisibilityType = optionalUserVisibilityType.get();

        User user3 = new User();
        user2.setId(1);
        user3.setName("Sajra");
        user3.setSurname("Turko");
        user3.setUsername("sturko");
        user3.setEmail("sturko@etf.unsa.ba");
        user3.setPassword("sajra123");
        user3.setCreatedAt(LocalDate.now());
        user3.setUserVisibilityType(userVisibilityType);

        user1 = userRepository.save(user1);
        user2 = userRepository.save(user2);
        user3 = userRepository.save(user3);
    }


    @Test
    void addUserSuccessfully1() throws Exception {
        mockMvc.perform(post(String.format("/api/users"))
                        .content("{\n" +
                                "    \"name\":\"Sajra\",\n" +
                                "    \"surname\": \"Turko\",\n" +
                                "    \"username\": \"sturko\",\n" +
                                "    \"email\": \"sturko@etf.unsa.ba\",\n" +
                                "    \"password\": \"sturko\",\n" +
                                "    \"createdAt\": \"2023-04-04\",\n" +
                                "     \"userVisibilityType\": {\"id\": 2, \"type\": \"PRIVATE\"}\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void addUserUnSuccessfully1() throws Exception {
        mockMvc.perform(post(String.format("/api/users"))
                        .content("{\n" +
                                "    \"name\":\"Sajra\",\n" +
                                "    \"surname\": \"\",\n" +
                                "    \"username\": \"sturko\",\n" +
                                "    \"password\": \"sturko\",\n" +
                                "    \"createdAt\": \"2023-04-05\",\n" +
                                "     \"userVisibilityType\": {\"id\": 2, \"type\": \"PRIVATE\"}\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Surname is mandatory"));
    }

    @Test
    void addUserUnSuccessfully2() throws Exception {
        mockMvc.perform(post(String.format("/api/users"))
                        .content("{\n" +
                                "    \"name\":\"Sajra\",\n" +
                                "    \"surname\": \"Turko\",\n" +
                                "    \"username\": \"sturko\",\n" +
                                "    \"password\": \"stu\",\n" +
                                "    \"createdAt\": \"2023-04-07\",\n" +
                                "     \"userVisibilityType\": {\"id\": 2, \"type\": \"PRIVATE\"}\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Password must contain minimum 5 characters"));
    }

    @Test
    void addUserUnSuccessfully3() throws Exception {
        mockMvc.perform(post(String.format("/api/users"))
                        .content("{\n" +
                                "    \"name\":\"Sajra\",\n" +
                                "    \"surname\": \"Turko\",\n" +
                                "    \"username\": \"sturko\",\n" +
                                "    \"password\": \"sturko\",\n" +
                                "    \"createdAt\": \"2023-04-04\",\n" +
                                "     \"userVisibilityType\": {\"id\": 2, \"type\": \"PRIVATE\"}\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    @Test
    void getUserUnSuccessfully1() throws Exception {
        mockMvc.perform(get(String.format("/api/users/%d", -1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Not found User with id = -1"));
    }



    @Test
    void deleteUserUnSuccessfully() throws Exception{
        mockMvc.perform(delete(String.format("/api/users/%d", 1053))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.message").value("Not found User with id = 1053"));
    }



}
