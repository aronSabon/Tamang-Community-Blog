package com.skytest.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skytest.model.PaymentInfo;
import com.skytest.repository.PaymentInfoRepository;
import com.skytest.service.PaymentInfoService;



@Service
public class PaymentInfoServiceImpl implements PaymentInfoService {
	@Autowired
	PaymentInfoRepository paymentInfoRepository;
		@Override
		public void addPaymentInfo(PaymentInfo paymentInfo) {
			// TODO Auto-generated method stub
			paymentInfoRepository.save(paymentInfo);
		}

		@Override
		public List<PaymentInfo> getAllPaymentInfo() {
			// TODO Auto-generated method stub
			return paymentInfoRepository.findAll();
		}

		@Override
		public void deletePaymentInfoById(int id) {
			// TODO Auto-generated method stub
			paymentInfoRepository.deleteById(id);
		}

		@Override
		public PaymentInfo getPaymentInfoById(int id) {
			// TODO Auto-generated method stub
			return paymentInfoRepository.findById(id).get();
		}

		@Override
		public void updatePaymentInfo(PaymentInfo paymentInfo) {
			// TODO Auto-generated method stub
			paymentInfoRepository.save(paymentInfo);
		}

}
