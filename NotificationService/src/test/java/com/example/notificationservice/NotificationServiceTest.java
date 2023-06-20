package com.example.notificationservice;


import com.example.notificationservice.models.Notification;
import com.example.notificationservice.models.NotificationType;
import com.example.notificationservice.repositories.NotificationRepository;
import com.example.notificationservice.repositories.NotificationTypeRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;


import java.util.Optional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("Test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationTypeRepository notificationTypeRepository;

    private Notification n1, n2, n3;
    @BeforeAll
    void setup() {
        notificationRepository.deleteAll();

        Optional<NotificationType> optionalNotificationType = notificationTypeRepository.findOneByType("LIKED");
        NotificationType notificationType = new NotificationType();

        if(optionalNotificationType.isPresent())
            notificationType = optionalNotificationType.get();

        n1 = new Notification();
        n1.setOpen(false);
        n1.setContent("User1 Liked your Comment.");
        n1.setLikedComment(0);
        n1.setActionUserId(1);
        n1.setUserId(3);
        n1.setNotificationType(notificationType);

        optionalNotificationType = notificationTypeRepository.findOneByType("FOLLOWED");
        notificationType = new NotificationType();

        if(optionalNotificationType.isPresent())
            notificationType = optionalNotificationType.get();

        n2 = new Notification();
        n2.setOpen(false);
        n2.setContent("User1 started following you.");
        n2.setActionUserId(1);
        n2.setUserId(3);
        n2.setNotificationType(notificationType);

        optionalNotificationType = notificationTypeRepository.findOneByType("PINNED");
        notificationType = new NotificationType();

        if(optionalNotificationType.isPresent())
            notificationType = optionalNotificationType.get();

        n3 = new Notification();
        n3.setOpen(false);
        n3.setContent("User1 pinned your post.");
        n3.setActionUserId(1);
        n3.setUserId(3);
        n3.setPinnedPost(2);
        n3.setNotificationType(notificationType);

        n1 =notificationRepository.save(n1);
        n2 = notificationRepository.save(n2);
        n3 = notificationRepository.save(n3);
    }

    @Test
    void getNotificationSuccessfully1() throws Exception {
        mockMvc.perform(get(String.format("/api/notifications/%d", n1.getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("User1 Liked your Comment."));
    }

    @Test
    void getNotificationUnSuccessfully1() throws Exception {
        mockMvc.perform(get(String.format("/api/notifications/%d", -100))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Not found Notification with id = -100"));
    }

    @Test
    void addNotificationSuccessfully1() throws Exception {
        mockMvc.perform(post(String.format("/api/notifications"))
                        .content("{\n" +
                                "    \"notificationType\": {\"id\": 2, \"type\": \"LIKED\"},\n" +
                                "    \"open\":false,\n" +
                                "    \"userId\":2,\n" +
                                "    \"actionUserId\": 1,\n" +
                                "    \"pinnedPost\": null,\n" +
                                "    \"likedComment\": 1,\n" +
                                "    \"sharedCollection\": null,\n" +
                                "    \"content\": \"User2 Liked your Comment.\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.content").value("User2 Liked your Comment."));
    }

    @Test
    void addNotificationUnSuccessfully1() throws Exception {
        mockMvc.perform(post(String.format("/api/notifications"))
                .content("{\n" +
                        "    \"notificationType\": {\"id\": 2, \"type\": \"LIKED\"},\n" +
                        "    \"open\":null,\n" +
                        "    \"userId\":3,\n" +
                        "    \"actionUserId\": 1,\n" +
                        "    \"pinnedPost\": null,\n" +
                        "    \"likedComment\": 1,\n" +
                        "    \"sharedCollection\": null,\n" +
                        "    \"content\": \"User2 Liked your Comment.\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Field open must not be null"));
    }

    @Test
    void addNotificationUnSuccessfully2() throws Exception {
        mockMvc.perform(post(String.format("/api/notifications"))
                        .content("{\n" +
                                "    \"notificationType\": {\"id\": 2, \"type\": \"LIKED\"},\n" +
                                "    \"open\":null,\n" +
                                "    \"userId\":3,\n" +
                                "    \"actionUserId\": 1,\n" +
                                "    \"pinnedPost\": null,\n" +
                                "    \"likedComment\": 1,\n" +
                                "    \"sharedCollection\": null,\n" +
                                "    \"content\": \"\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message[*]", containsInAnyOrder("Content is mandatory", "Field open must not be null")));
    }

    @Test
    void addNotificationUnSuccessfully3() throws Exception {
        mockMvc.perform(post(String.format("/api/notifications"))
                        .content("{\n" +
                                "    \"notificationType\": {\"id\": 2, \"type\": \"LIKED\"},\n" +
                                "    \"open\":null,\n" +
                                "    \"userId\":3,\n" +
                                "    \"actionUserId\": 1,\n" +
                                "    \"pinnedPost\": null,\n" +
                                "    \"likedComment\": 1,\n" +
                                "    \"sharedCollection\": null,\n" +
                                "    \"content\": \"\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message[*]", containsInAnyOrder("Content is mandatory", "Field open must not be null")));
    }


}
