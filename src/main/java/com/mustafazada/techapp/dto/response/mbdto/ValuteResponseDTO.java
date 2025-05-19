package com.mustafazada.techapp.dto.response.mbdto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Valute")
@XmlAccessorType(XmlAccessType.FIELD)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValuteResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "Code")
    String code;
    @XmlElement(name = "Nominal")
    String nominal;
    @XmlElement(name = "Name")
    String name;
    @XmlElement(name = "Value")
    BigDecimal value;

}
