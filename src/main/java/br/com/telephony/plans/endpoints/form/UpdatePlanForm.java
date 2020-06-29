package br.com.telephony.plans.endpoints.form;

import br.com.telephony.plans.models.Plan;
import br.com.telephony.plans.service.PlanService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePlanForm {

    @NotNull(message = "{not.null}")
    @PositiveOrZero(message = "{positive.or.zero}")
    private Long minutes;

    @Length(min = 3)
    @NotBlank(message = "{not.blank}")
    private String franchise;

    @NotNull(message = "{not.null}")
    @PositiveOrZero(message = "{positive.or.zero}")
    private BigDecimal value;

    public Plan converter(Long id, PlanService planService){
        Plan plan = planService.getOne(id);
        plan.setMinutes(this.minutes);
        plan.setFranchise(this.franchise);
        plan.setValue(this.value);

        return plan;
    }
}
