package com.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Alvin
 **/
@Entity
@Table(name = "course_journal")
@Data
@Builder
@EqualsAndHashCode(of = {"currencyA", "currencyB", "date"})
@ToString(of = {"currencyA", "date", "buy", "cell"})
@NoArgsConstructor
@AllArgsConstructor
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    /**
     * Celling currency
     * */
    @JoinColumn(name = "currencyCodeA")
    @OneToOne(fetch = FetchType.EAGER)
    @JsonProperty(value = "cellingCurrency")
    private Currency currencyA;

    /**
     * Cost currency
     * */
    @JoinColumn(name = "currencyCodeB")
    @OneToOne(fetch = FetchType.EAGER)
    @JsonProperty(value = "costCurrency")
    private Currency currencyB;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @Column(name = "buy_course")
    private Double buy;

    @Column(name = "cell_course")
    private Double cell;
}
