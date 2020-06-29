package br.com.telephony.plans.repository;

import br.com.telephony.plans.models.DDD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DDDRepository extends JpaRepository<DDD, Long> {
}
