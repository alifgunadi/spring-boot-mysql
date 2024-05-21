package com.example.restapi.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.restapi.api.models.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

}
