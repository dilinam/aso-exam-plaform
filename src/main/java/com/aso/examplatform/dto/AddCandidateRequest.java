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
public class AddCandidateRequest {
    @NotNull
    private List<Long> tenantUserIds;
    @NotNull
    private Long examId;
    @NotNull
    private boolean forAll;
}
