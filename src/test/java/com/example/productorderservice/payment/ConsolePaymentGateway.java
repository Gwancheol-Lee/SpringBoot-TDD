package com.example.productorderservice.payment;

class ConsolePaymentGateway implements PaymentGateway {

	@Override
	public void excute(final Payment payment) {
		System.out.println("상품 결제");
	}
	
}
