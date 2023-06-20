package com.example.notificationservice.controllers;

import com.example.notificationservice.dto.NotificationOpenOnlyDTO;
import com.example.notificationservice.models.Notification;
import com.example.notificationservice.models.NotificationType;
import com.example.notificationservice.repositories.NotificationRepository;
import com.example.notificationservice.services.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller // This means that this class is a Controller
@RequestMapping(path="/api") // This means URL's start with /api/notification (after Application path)
public class NotificationController {

    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private NotificationService notificationService;


    @PostMapping(path="/notifications") // Map ONLY POST Requests
    public @ResponseBody ResponseEntity AddNotification (@Valid @RequestBody Notification requestBody) {
        Notification notification = notificationService.Create(requestBody);
        return ResponseEntity.status(201).body(notification);

    }


    @GetMapping(path="/notifications")
    public @ResponseBody ResponseEntity GetAllNotifications() {
        Iterable<Notification> notificationList = notificationService.List();
        return ResponseEntity.status(200).body(notificationList);
    }

    @GetMapping(path="/notifications/user/{id}")
    public @ResponseBody ResponseEntity GetNotificationsForUser(@PathVariable("id") Integer id) {
        Iterable<Notification> notificationList = notificationService.GetNotificationsForUser(id);
        return ResponseEntity.status(200).body(notificationList);
    }

    @GetMapping(path="/notifications/open")
    public @ResponseBody ResponseEntity Get10NotificationsByOpen() {
        Iterable<Notification> notificationList = notificationService.List10NotificationsByOpen();
        return ResponseEntity.status(200).body(notificationList);
    }

    @GetMapping(path="/notifications/{id}")
    public @ResponseBody ResponseEntity GetDetails( @PathVariable("id") Integer id) {
        Notification notification = notificationService.Details(id);
        return ResponseEntity.status(200).body(notification);
    }

    @DeleteMapping(path="/notifications/{id}")
    public @ResponseBody ResponseEntity Delete(@PathVariable("id") Integer id) {
        notificationService.Delete(id);
        return ResponseEntity.status(204).build();

    }

//    @PutMapping("/notifications/{id}")
//    public @ResponseBody ResponseEntity Update(@PathVariable("id") Integer id, @Valid @RequestBody Notification requestBody) {
//        Notification updated = notificationService.Update(id, requestBody);
//        return ResponseEntity.status(200).body(updated);
//
//    }

    @PatchMapping(path = "/notifications/{id}", consumes = "application/json-patch+json")
    public @ResponseBody ResponseEntity Update(@PathVariable("id") Integer id, @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        Notification notification = notificationService.Details(id);
        Notification notificationPatched = applyPatchToNotification(patch, notification);
        notificationService.Update(notificationPatched);
        return ResponseEntity.status(200).body(notificationPatched);

    }

//    Pitati irfana
//    [
//    {"op":"replace","path":"/open","value":true}
//    ]

    private Notification applyPatchToNotification(JsonPatch patch, Notification targetNotification) throws JsonProcessingException, JsonPatchException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetNotification, JsonNode.class));
        return objectMapper.treeToValue(patched, Notification.class);
    }

    @PatchMapping("/notifications")
    public @ResponseBody ResponseEntity partialUpdateOpen(
            @RequestBody NotificationOpenOnlyDTO partialUpdate) {
        notificationService.partialUpdateOpen(partialUpdate.getId(), partialUpdate.getOpen());
        return ResponseEntity.status(200).body("Resource open updated");
    }



    @GetMapping(path="/notificationtypes")
    public @ResponseBody ResponseEntity GetAllNotificationTypes() {
        Iterable<NotificationType> notificationTypeList = notificationService.ListNotificationTypes();
        return ResponseEntity.status(200).body(notificationTypeList);

    }


}