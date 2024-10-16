package com.appsoft.tmgsamaj.service;

import java.util.List;

import com.appsoft.tmgsamaj.model.PaymentInfo;



public interface PaymentInfoService {
	void addPaymentInfo(PaymentInfo paymentInfo);
	List<PaymentInfo> getAllPaymentInfo();
	void deletePaymentInfoById(int id);
	PaymentInfo getPaymentInfoById(int id);
	void updatePaymentInfo(PaymentInfo paymentInfo);

}
