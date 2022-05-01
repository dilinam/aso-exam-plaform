package com.aso.examplatform.dto;

import com.aso.examplatform.model.Exam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCandidate {
    @NotNull
    private List<Long> tenantUserID;
    @Valid
    private Exam exam;
}
