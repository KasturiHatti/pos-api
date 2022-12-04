package com.anymind.pos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anymind.pos.entities.Payment;

@Repository
public interface PaymentRepository  extends JpaRepository<Payment, String> {
   /*  private static Set<Payment> payments = new HashSet<>();

    public void save(Payment payment) {
        payments.add(payment);
    }

    public List<Payment> getPaymentHistory(ZonedDateTime from, ZonedDateTime till) {
        return Collections.emptyList();
    } */
    
}
