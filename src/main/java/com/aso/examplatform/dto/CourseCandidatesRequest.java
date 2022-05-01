package com.aso.examplatform.dto;

import com.aso.examplatform.model.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseCandidatesRequest {
    @NotNull
    private Long courseId;
    @NotNull
    private List<Long> tenantUsers;
}
