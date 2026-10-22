package com.gym.gimnasio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ControllerGimnasio {

    private List<Miembro> miembros;
    private Map<String, String> horarios;

    public ControllerGimnasio() {
        miembros = new ArrayList<>();
        miembros.add(new Miembro("Dilan", 20));
        miembros.add(new MiembroPremium("Damian", 19, "Bayona Suarez"));
        miembros.add(new MiembroEstudiante("Brandon", 21, "Sena Av 1 Mayo"));
        miembros.add(new MiembroPremium("Daniel", 22, "Alonso"));

        horarios = new HashMap<>();
        horarios.put("Biceps", "Lunes y Viernes - 9:00 AM a 11:00 AM");
        horarios.put("Flexiones de pecho", "Martes y Jueves - 6:00 PM a 7:00 PM");
        horarios.put("Bicicleta estatica", "Lunes a Viernes - 8:00 PM a 9:00 PM");
    }

    @GetMapping("/miembros")
    public List<Miembro> obtenerMiembros() {
        return miembros;
    }

    @GetMapping("/miembros/buscar/{nombre}")
    public String buscarMiembro(@PathVariable String nombre) {
        for (Miembro m : miembros) {
            if (m.getNombre().equalsIgnoreCase(nombre)) {
                return m.descripcion() + " - cuota mensual: $" + m.cuotaMensual();
            }
        }
        return "No se encuentra el usuario " + nombre;
    }

    @GetMapping("/miembros/cuota/{minima}")
    public List<Miembro> filtrarPorCuota(@PathVariable double minima) {
        List<Miembro> resultado = new ArrayList<>();
        for (Miembro m : miembros) {
            if (m.cuotaMensual() >= minima) {
                resultado.add(m);
            }
        }
        return resultado;
    }

    @GetMapping("/clases/{nombre}")
    public String consultarClase(@PathVariable String nombre) {
        for (Map.Entry<String, String> entrada : horarios.entrySet()) {
            if (entrada.getKey().equalsIgnoreCase(nombre)) {
                return entrada.getValue();
            }
        }
        return "Clase faltante";
    }
}