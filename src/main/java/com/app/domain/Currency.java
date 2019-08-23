package com.app.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Alvin
 **/
@Entity
@Table(name = "currency_reference")
@Data
@Builder
@EqualsAndHashCode(of = {"id", "mnemonics"})
@ToString(of = {"mnemonics", "description"})
public class Currency implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description")
    private String description;

    @ElementCollection(targetClass = Mnemonics.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "mnemonics_type", joinColumns = @JoinColumn(name = "currency_id"))
    @Enumerated(EnumType.STRING)
    private Set<Mnemonics> mnemonics;
}
