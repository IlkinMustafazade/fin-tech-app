package com.mustafazada.techapp.dto.mbdto;

import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@XmlRootElement(name = "ValType")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValTypeResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "Type")
    private String type;

    @XmlElement(name = "Valute")
    private List<ValuteResponseDTO> valuteResponseDTOList;

    public ValTypeResponseDTO() {
    }

    public ValTypeResponseDTO(String type, List<ValuteResponseDTO> valuteResponseDTOList) {
        this.type = type;
        this.valuteResponseDTOList = valuteResponseDTOList;
    }
}
