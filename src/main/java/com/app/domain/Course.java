package com.app.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
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
@EqualsAndHashCode(of = {"id", "currency", "date"})
@ToString(of = {"currency", "date", "buy", "cell"})
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private Currency currency;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @Column(name = "buy_course")
    private Double buy;

    @Column(name = "cell_course")
    private Double cell;
}
