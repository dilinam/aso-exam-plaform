package com.aso.examplatform.repository;

import com.aso.examplatform.model.Candidate;
import org.springframework.data.repository.CrudRepository;

public interface CandidateRepository extends CrudRepository<Candidate,Long> {
}
