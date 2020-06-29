package br.com.telephony.plans.endpoints;

import br.com.telephony.plans.endpoints.dto.PlanDto;
import br.com.telephony.plans.endpoints.form.UpdatePlanForm;
import br.com.telephony.plans.endpoints.form.PlanForm;
import br.com.telephony.plans.models.Plan;
import br.com.telephony.plans.models.Type;
import br.com.telephony.plans.models.converter.StringToEnumConverter;
import br.com.telephony.plans.repository.DDDRepository;
import br.com.telephony.plans.repository.CarrierRepository;
import br.com.telephony.plans.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    private PlanService planService;

    @Autowired
    private CarrierRepository carrierRepository;

    @Autowired
    private DDDRepository dddRepository;

    @GetMapping("/{id}")
    public ResponseEntity<PlanDto> findById(@PathVariable Long id){
        Optional<Plan> plan = planService.findById(id);
        return ResponseEntity.ok(new PlanDto(plan.get()));
    }

    @GetMapping
    public Page<PlanDto> list(
            @RequestParam(required = false) String carrier,
            @RequestParam(required = false) String type,
            @RequestParam Long ddd,
            @PageableDefault(size = 5)Pageable pageable
    ){

        if(carrier!=null) {
            Page<Plan> plans = new PageImpl<Plan>(planService.findByCarrierAndDDD(ddd, carrier));
            return PlanDto.converter(plans);
        }
        if(type!=null) {
            Type typeEnum = new StringToEnumConverter().convert(type);
            System.out.println(typeEnum.toString() + " " + ddd);
            Page<Plan> plans = new PageImpl<>(planService.findByTypeAndDDD(ddd, typeEnum));
            return PlanDto.converter(plans);
        }

        return PlanDto.converter(new PageImpl<>(planService.findByDDD(ddd)));
    }


    @PostMapping
    @Transactional
    public ResponseEntity<PlanDto> register(@RequestBody @Valid PlanForm form, UriComponentsBuilder uriBuilder){
        Plan plan = planService.save(form.converter(carrierRepository, dddRepository));
        URI uri = uriBuilder.path("/plan/{id}").buildAndExpand(plan.getId()).toUri();
        return  ResponseEntity.created(uri).body(new PlanDto(plan));
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PlanDto> update(@PathVariable Long id, @RequestBody @Valid UpdatePlanForm form){
        if(planService.findById(id).isPresent()){
            Plan plan = form.converter(id, planService);
            return new ResponseEntity<>(new PlanDto(plan), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable Long id){
        if(planService.findById(id).isPresent()){
            planService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}