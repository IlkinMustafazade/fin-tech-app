package com.mustafazada.techapp.dto.mbdto;

import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@XmlRootElement(name = "Valute")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValuteResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "Code")
    private String code;
    @XmlElement(name = "Nominal")
    private String nominal;
    @XmlElement(name = "Name")
    private String name;
    @XmlElement(name = "Value")
    private BigDecimal value;

    public ValuteResponseDTO() {
    }

    public ValuteResponseDTO(String code, String nominal, String name, BigDecimal value) {
        this.code = code;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
    }
}
