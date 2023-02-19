package com.example.productorderservice.payment;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import com.example.productorderservice.order.Order;
import com.example.productorderservice.product.DiscountPolicy;
import com.example.productorderservice.product.Product;

class PaymentServiceTest {

	private PaymentService paymentService;
	private PaymentPort paymentPort;
	
	@BeforeEach
	void setUp() {
		final PaymentGateway paymentGateway = new ConsolePaymentGateway();
		final PaymentRepository paymentRepository = new PaymentRepository();
		paymentPort = new PaymentAdapter(paymentGateway, paymentRepository);
		paymentService = new PaymentService(paymentPort);
	}
	
	@Test
	void 상품주문() {
		final Long orderId = 1L;
		final String cardNumber = "1234-1234-1234-1234";
		final PaymentRequest request = new PaymentRequest(orderId, cardNumber);
		
		paymentService.payment(request);
	}
	
	private record PaymentRequest(Long orderId, String cardNumber) {
		private PaymentRequest {
			Assert.notNull(orderId, "주문 ID는 필수입니다.");
			Assert.hasText(cardNumber, "카드 번호는 필수입니다.");
		}
	}
	
	private class PaymentService {
		
		private final PaymentPort paymentPort;

		private PaymentService(PaymentPort paymentPort) {
			this.paymentPort = paymentPort;
		}
		
		public void payment(PaymentRequest request) {
			Order order = paymentPort.getOrder(request.orderId());
			
			final Payment payment = new Payment(order, request.cardNumber());
			
			paymentPort.pay(payment);
			paymentPort.save(payment);
		}
	}
	
	private class Payment {
		private Long id;
		private final Order order;
		private final String cardNumber;
		
		public Payment(final Order order, final String cardNumber) {
			Assert.notNull(order, "주문은 필수입니다.");
			Assert.hasText(cardNumber, "카드 번호는 필수입니다.");
			this.order = order;
			this.cardNumber = cardNumber;
		}
		
		public void assignId(final Long id) {
			this.id = id;
		}
		
		public Long getId() {
			return id;
		}
	}
	
	private interface PaymentPort {
		Order getOrder(Long orderId);
		void pay(Payment payment);
		void save(Payment payment);
	}
	
	private class PaymentAdapter implements PaymentPort {
		
		private final PaymentGateway paymentGateway;
		private final PaymentRepository paymentRepository;
		
		private PaymentAdapter(PaymentGateway paymentGateway, PaymentRepository paymentRepository) {
			this.paymentGateway = paymentGateway;
			this.paymentRepository = paymentRepository;
		}
		
		@Override
		public Order getOrder(final Long orderId) {
			return new Order(new Product("상품1", 1000, DiscountPolicy.NONE), 2);
		}

		@Override
		public void pay(final Payment payment) {
			paymentGateway.excute(payment);
		}

		@Override
		public void save(final Payment payment) {
			paymentRepository.save(payment);
			
		}
	}
	private interface PaymentGateway {
		void excute(Payment payment);
	}
	
	private class ConsolePaymentGateway implements PaymentGateway {

		@Override
		public void excute(final Payment payment) {
			System.out.println("상품 결제");
		}
		
	}
	
	private class PaymentRepository {
		
		private Map<Long, Payment> persistence = new HashMap<>();
		private Long sequence = 0L;
		
		public void save(final Payment payment) {
			payment.assignId(++sequence);
			persistence.put(payment.getId(), payment);
		}
	}
}
