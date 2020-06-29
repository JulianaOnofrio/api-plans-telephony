package br.com.telephony.plans.repository;

import br.com.telephony.plans.models.Plan;
import br.com.telephony.plans.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findByCarrierName(String name);

    Plan save(Plan plan);

    @Query(value = "select * from Plan plan " +
            "INNER JOIN DDD_PLAN dp " +
            "ON plan.id = dp.plan_id " +
            "INNER JOIN DDD d " +
            "ON d.code=dp.ddd_code " +
            "where d.code=:ddd " +
            "and plan.type like %:type%", nativeQuery = true)
    List<Plan> findByTypeAndDDD(@Param("ddd") Long ddd, @Param("type") Type type);

    @Query(value = "select * from Plan plan " +
            "INNER JOIN DDD_PLAN dp " +
            "ON plan.id = dp.plan_id " +
            "INNER JOIN DDD d " +
            "ON d.code=dp.ddd_code " +
            "where d.code=:ddd ", nativeQuery = true)
    List<Plan> findByDDD(@Param("ddd") Long ddd);

    @Query(value = "select * from Plan plan " +
                    "INNER JOIN DDD_PLAN dp " +
                    "ON plan.id = dp.plan_id "+
                    "INNER JOIN DDD d " +
                    "ON d.code=dp.ddd_code " +
                    "INNER JOIN carrier c " +
                    "on plan.carrier_id=c.id " +
                    "where d.code=:ddd and c.name like %:carrier%", nativeQuery = true)
    List<Plan> findByCarrierAndDDD(@Param("ddd") Long ddd,@Param("carrier") String carrier);

}
