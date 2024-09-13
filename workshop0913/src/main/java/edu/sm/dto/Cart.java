package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {
    private int id; // PK
    private int cId; //  ID (FK)
    private int pId; //  ID (FK)
    private int count; //
}