package br.com.telephony.plans.endpoints.form;

import br.com.telephony.plans.models.Carrier;
import br.com.telephony.plans.models.DDD;
import br.com.telephony.plans.models.Plan;
import br.com.telephony.plans.models.Type;
import br.com.telephony.plans.models.converter.StringToEnumConverter;
import br.com.telephony.plans.repository.DDDRepository;
import br.com.telephony.plans.repository.CarrierRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlanForm {

    @NotNull(message = "{not.null}")
    @PositiveOrZero(message = "{positive.or.zero}")
    private Long minutes;

    @Length(min = 3)
    @NotBlank(message = "{not.blank}")
    private String franchise;

    @NotNull(message = "{not.null}")
    @PositiveOrZero(message = "{positive.or.zero}")
    private BigDecimal value;

    @NotNull(message = "{not.null}")
    private String type;

    @NotNull(message = "{not.null}")
    private Long carrier_id;

    @NotNull(message = "{not.null}")
    private List<Long> dddList;

    public Plan converter(
            CarrierRepository operatorRopository,
            DDDRepository dddRepository){

        Type typeEnum = new StringToEnumConverter().convert(type);
        Carrier carrier = operatorRopository
                                    .findById(carrier_id)
                                    .orElseThrow(NullPointerException::new);
        List<DDD> ddd = new ArrayList<>();
        dddList.forEach(id->dddRepository.findById(id).ifPresent(ddd::add));

        return new Plan(minutes, franchise, value, typeEnum, carrier, ddd);
    }
}
