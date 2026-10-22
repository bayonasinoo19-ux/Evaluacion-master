package com.gym.gimnasio;

public class MiembroEstudiante extends Miembro {

    private String local;

    public MiembroEstudiante(String nombre, int edad, String local) {
        super(nombre, edad);
        this.local = local;
    }

    public String getlocal() {
        return local;
    }

    @Override
    public double cuotaMensual() {
        return super.cuotaMensual() - 20000.0;
    }
}