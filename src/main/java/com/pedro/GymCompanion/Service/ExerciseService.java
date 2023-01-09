package com.pedro.GymCompanion.Service;


import com.pedro.GymCompanion.Domain.Exercise;
import com.pedro.GymCompanion.Repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public Exercise updateExercise(Exercise exerciseDetails, Long id) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        exercise.setReps(exerciseDetails.getReps());
        exercise.setExerciseBase(exerciseDetails.getExerciseBase());
        exercise.setWeight(exerciseDetails.getWeight());
        return exerciseRepository.save(exercise);
    }

}
