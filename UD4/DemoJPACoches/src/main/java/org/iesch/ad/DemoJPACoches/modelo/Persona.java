package org.iesch.ad.DemoJPACoches.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nombre;
    String apellido;

    @OneToMany
    @JoinColumn(name = "persona_id")
    List<Coche> coches;


}
