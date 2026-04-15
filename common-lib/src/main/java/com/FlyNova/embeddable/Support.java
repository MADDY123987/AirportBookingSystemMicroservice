package com.FlyNova.embeddable;


import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Support {
    String Email;
    String Phone;
    String Hours;
}
