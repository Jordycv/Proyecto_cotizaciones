package com.codigo.msrequerimiento.constantes;

public enum Estado {

    Emitido("Emitido",1), APROBADO("Aprobado",2),
    OBSERVADO("Observado",4), ANULADO("Anulado",7);

    private String nombre;
    private int codigo;

    private Estado (String nombre, int codigo){
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCodigo() {
        return codigo;
    }
}
