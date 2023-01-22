package com.pedro.GymCompanion.Service;


import com.pedro.GymCompanion.Domain.ExerciseBase;
import com.pedro.GymCompanion.Repository.ExerciseBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.sql.Date;

@Service
public class ExerciseBaseService {


    private final ExerciseBaseRepository exerciseBaseRepository;

    @Autowired
    public ExerciseBaseService(ExerciseBaseRepository exerciseBaseRepository) {
        this.exerciseBaseRepository = exerciseBaseRepository;
    }

    public ExerciseBase save(ExerciseBase exerciseBase) {
        return exerciseBaseRepository.save(exerciseBase);
    }

    public ExerciseBase update( Long id, ExerciseBase exerciseBase) {
        ExerciseBase exerciseBaseToUpdate = exerciseBaseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        exerciseBaseToUpdate.setName(exerciseBase.getName());
        exerciseBaseToUpdate.setDescription(exerciseBase.getDescription());
        return exerciseBaseRepository.save(exerciseBaseToUpdate);
    }
}
