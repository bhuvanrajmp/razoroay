package com.MyFirstSpringbootApp.razoroay.merchant.repository;

import com.MyFirstSpringbootApp.razoroay.merchant.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;


public interface MerchantRepository extends JpaRepository<Merchant, UUID> {

    boolean existsByEmail(String email);

}
