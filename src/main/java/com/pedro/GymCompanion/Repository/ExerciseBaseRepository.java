package com.pedro.GymCompanion.Repository;

import com.pedro.GymCompanion.Domain.ExerciseBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseBaseRepository extends JpaRepository<ExerciseBase, Long> {
}
