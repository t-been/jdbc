package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Csv {
    private int serialNumber;       // 연번
    private String businessType;    // 업종명
    private String businessName;    // 업소명
    private String addressRoad;     // 소재지(도로명)
    private String addressJibun;    // 소재지(지번)
    private String dataDate;        // 데이터기준일
}
