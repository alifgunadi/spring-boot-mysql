package com.example.restapi.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.api.models.Privilege;
import com.example.restapi.api.repositories.PrivilegeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class PrivilegeController {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @GetMapping(value = "/list/privilege")
    public List<Privilege> listPrivilege() {
        return privilegeRepository.findAll();
    }

    @GetMapping(value = "/detail/privilege/{id}")
    public Privilege detailPrivilege(@PathVariable long id) {
        return privilegeRepository.findById(id).get();
    }

    @PostMapping(value = "/create/privilege")
    public String savePrivilege(@RequestBody Privilege privilege) {
        try {
            Privilege createdPrivilege = privilegeRepository.save(privilege);

            Map<String, Object> result = new HashMap<>();
            result.put("status", "OK");
            result.put("message", "");
            result.put("data", createdPrivilege);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResult = objectMapper.writeValueAsString(result);

            return jsonResult;
        } catch (JsonProcessingException e) {
            return "Error.." + e;
        }
    }

    @PostMapping(value = "/update/privilege")
    public String updatePrivilege(@RequestBody Privilege privilege) {
        try {
            long id = privilege.getId();

            Privilege updatedPrivilege = privilegeRepository.findById(id).get();

            updatedPrivilege.setName(privilege.getName());

            privilegeRepository.save(updatedPrivilege);

            Map<String, String> result = new HashMap<>();
            result.put("status", "OK");
            result.put("message", "Updated successfully.");
            result.put("result", "{}");

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResult = objectMapper.writeValueAsString(result);

            return jsonResult;
        } catch (JsonProcessingException e) {
            return "Error.." + e;
        }
    }

    @DeleteMapping(value = "/delete/privilege/{id}")
    public String deletePrivilege(@RequestBody Privilege privilege, @PathVariable long id) {
        Privilege privilegeId = privilegeRepository.findById(id).get();

        privilegeRepository.delete(privilegeId);

        return "Deleted successfully..";
    }
}
