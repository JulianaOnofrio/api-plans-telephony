package br.com.telephony.plans.endpoints.dto;

import br.com.telephony.plans.models.Plan;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

@Getter
public class PlanDto {
    public PlanDto(Plan plan){
        this.id = plan.getId();
        this.minutes = plan.getMinutes();
        this.franchise = plan.getFranchise();
        this.value = plan.getValue();
    }

    private Long id;
    private Long minutes;
    private String franchise;
    private BigDecimal value;

    public static Page<PlanDto> converter(Page<Plan> plans){
        return plans.map(PlanDto::new);
    }

}
