package com.odon.beprepared.dto.request;

import com.odon.beprepared.model.enums.Severity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlertRequestDto {
    @NotBlank(message = "Prencha o titlo")
    private String title;
    @NotBlank
    private String message;
    @NotNull
    private Severity severity;
    @NotNull(message = "Coloque a provincia")
    private Long provinceId;
    @NotNull(message = "Digite a cidade")
    private Long cityId;
}
