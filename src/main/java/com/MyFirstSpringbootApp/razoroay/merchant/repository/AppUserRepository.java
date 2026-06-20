package com.MyFirstSpringbootApp.razoroay.merchant.repository;

import com.MyFirstSpringbootApp.razoroay.merchant.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
}
