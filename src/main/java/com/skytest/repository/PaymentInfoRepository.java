package com.skytest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skytest.model.PaymentInfo;

public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, Integer>{

}
