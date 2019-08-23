package com.app.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Alvin
 **/
@Entity
@Table(name = "currency_reference")
@Data
@Builder
@EqualsAndHashCode(of = {"id", "mnemonics"})
@ToString(of = {"id", "description"})
public class Currency implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "mnemonics")
    @NotNull
    private String mnemonics;
}
