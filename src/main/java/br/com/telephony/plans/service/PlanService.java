package br.com.telephony.plans.service;

import br.com.telephony.plans.models.Plan;
import br.com.telephony.plans.models.Type;
import br.com.telephony.plans.repository.PlanRepository;
import br.com.telephony.plans.service.interfaces.PlanServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlanService implements PlanServiceInterface {

    private PlanRepository planRepository;

    @Override
    public Optional<Plan> findById(Long id) {
        return planRepository.findById(id);
    }

    @Override
    public Plan save(Plan plan) {
        return planRepository.save(plan);
    }

    @Override
    public List<Plan> findByTypeAndDDD(Long ddd, Type type) {
        return planRepository.findByTypeAndDDD(ddd, type);
    }

    @Override
    public List<Plan> findByCarrierAndDDD(Long ddd, String carrier) {
        return planRepository.findByCarrierAndDDD(ddd, carrier);
    }

    @Override
    public List<Plan> findByDDD(Long ddd) {
        return planRepository.findByDDD(ddd);
    }


    public Plan getOne(Long id) {
        return planRepository.getOne(id);
    }

    public void deleteById(Long id) {
        planRepository.deleteById(id);
    }
}
