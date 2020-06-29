package br.com.telephony.plans.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class Plan {
    public Plan(Long minutes,
                String franchise,
                BigDecimal value,
                Type type,
                Carrier carrier,
                List<DDD> ddd) {
        this.minutes = minutes;
        this.franchise = franchise;
        this.value = value;
        this.type = type;
        this.carrier = carrier;
        this.ddd = ddd;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @PositiveOrZero
    private Long minutes;

    @NotNull
    @NotBlank(message = "necessary to inform franchise")
    private String franchise;

    @NotNull
    @PositiveOrZero
    private BigDecimal value;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Type type;

    @JsonIgnore
    @ManyToOne
    private Carrier carrier;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDate = LocalDateTime.now();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinTable(name = "DDD_PLAN",
            joinColumns = {@JoinColumn(name="PLAN_ID")},
            inverseJoinColumns = {@JoinColumn(name = "DDD_CODE")})
    private List<DDD> ddd;


}
