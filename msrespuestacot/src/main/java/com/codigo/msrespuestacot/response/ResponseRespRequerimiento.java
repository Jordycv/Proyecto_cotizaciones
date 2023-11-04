package com.codigo.msrespuestacot.response;

import com.codigo.msrespuestacot.entity.RespuestaRequerimiento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRespRequerimiento {
    private String codigo;
    private String message;
    private List<RespuestaRequerimiento> resprequerimiento;
}
