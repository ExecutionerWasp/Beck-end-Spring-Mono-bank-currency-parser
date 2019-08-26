package com.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
@ToString(of = {"id","mnemonics"})
@NoArgsConstructor
@AllArgsConstructor
public class Currency implements Serializable {

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
