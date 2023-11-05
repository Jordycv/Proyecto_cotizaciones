package com.codigo.msrespuestacot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Requerimiento {

    private int idreq;

    @OneToMany(mappedBy = "requerimiento", cascade = CascadeType.ALL)
    private List<RequerimientoDetalle> detalleReq;

}
