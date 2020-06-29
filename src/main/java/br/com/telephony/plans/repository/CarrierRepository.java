package br.com.telephony.plans.repository;

import br.com.telephony.plans.models.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrierRepository extends JpaRepository<Carrier, Long> {
}
