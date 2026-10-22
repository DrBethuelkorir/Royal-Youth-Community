package org.roya.royalyouthcommunity.Repositories;

import org.roya.royalyouthcommunity.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<Payment,Long> {

}
