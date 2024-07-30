package com.odon.beprepared.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatsResponse {
    long citizens;
    long totalAlerts;
    long activeAlerts;
}
