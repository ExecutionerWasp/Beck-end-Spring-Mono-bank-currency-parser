package com.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
@ToString(of = {"id","mnemonics"})
@NoArgsConstructor
@AllArgsConstructor
public class Currency implements Serializable {

    /**
     * It takes from json currency code
     * */
    @Id
    @Column(name = "id")
    @NotNull
    @JsonIgnore
    private Long id;

    @Column(name = "mnemonics")
    private String mnemonics;

    @Column(name = "description")
    @JsonIgnore
    private String description;
}
