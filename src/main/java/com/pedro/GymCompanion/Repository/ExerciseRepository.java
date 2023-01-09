package com.pedro.GymCompanion.Repository;

import com.pedro.GymCompanion.Domain.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long>  {
}
