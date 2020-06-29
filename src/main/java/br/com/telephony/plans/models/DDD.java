package br.com.telephony.plans.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class DDD {

    @Id
    private Long code;

    @NotNull
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "ddd", cascade = CascadeType.PERSIST)
    private List<Plan> plan;

}
