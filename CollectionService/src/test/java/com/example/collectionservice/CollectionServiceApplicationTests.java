package com.example.collectionservice;

import com.example.collectionservice.controllers.CollectionController;
import com.example.collectionservice.dto.CollectionPostCreateDTO;
import com.example.collectionservice.infrastructure.PostService;
import com.example.collectionservice.models.Collection;
import com.example.collectionservice.models.CollectionVisibilityType;
import com.example.collectionservice.repositories.CollectionRepository;
import com.example.collectionservice.repositories.CollectionVisibilityTypeRepository;
import com.example.collectionservice.services.CollectionPostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Form;
import org.json.JSONException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
class CollectionServiceApplicationTests {

    private MockRestServiceServer mockServer;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private CollectionVisibilityTypeRepository collectionVisibilityTypeRepository;

    @Autowired
    private CollectionController collectionController;

    @BeforeEach
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void contextLoads() throws URISyntaxException, JsonProcessingException {

        Optional<CollectionVisibilityType> optionalVisibilityType = collectionVisibilityTypeRepository.findOneByType("PRIVATE");
        CollectionVisibilityType visibilityType = new CollectionVisibilityType();

        if(optionalVisibilityType.isPresent())
            visibilityType = optionalVisibilityType.get();

        Collection c1 = new Collection();
        c1.setName("Collection 1");
        c1.setNumOfPosts(0);
        c1.setCreatedAt(LocalDate.now());
        c1.setCollectionVisibilityType(visibilityType);
        c1 = collectionRepository.save(c1);


        this.mockServer.expect(ExpectedCount.once(),
                        requestTo(new URI("http://localhost:8082/api/collections/" + c1.getId())))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(c1))
                );

        ResponseEntity responseEntity = collectionController.GetDetails(c1.getId());
        this.mockServer.verify();

    }

}
