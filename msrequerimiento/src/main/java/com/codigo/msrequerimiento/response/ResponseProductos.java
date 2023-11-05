package com.codigo.msrequerimiento.response;

import com.codigo.msrequerimiento.entity.Productos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProductos {
    private String codigo;
    private String message;
    private List<Productos>productos;
}
