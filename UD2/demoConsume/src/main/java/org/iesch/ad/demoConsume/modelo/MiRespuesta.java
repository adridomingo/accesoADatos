package org.iesch.ad.demoConsume.modelo;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class MiRespuesta {

    private List<Fact> factList = new ArrayList<>();

    public void anade(Fact fact) {
        factList.add(fact);
    }
}
