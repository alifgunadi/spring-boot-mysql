/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.restapi.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restapi.api.models.Privilege;

/**
 *
 * @author alifg
 */
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

}
