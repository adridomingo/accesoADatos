package org.iesch.ad.demoValid.modelo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorResponseDTO {
    private LocalDateTime timeStamp;
    private int status;
    private String message;
//    private String path;
}
