package com.pedro.GymCompanion.Controller;

import com.pedro.GymCompanion.Domain.ExerciseBase;
import com.pedro.GymCompanion.Repository.ExerciseBaseRepository;
import com.pedro.GymCompanion.Service.ExerciseBaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("/exercise-base")
public class ExerciseBaseController {

    private final ExerciseBaseRepository exerciseBaseRepository;
    private final ExerciseBaseService exerciseBaseService;

    public ExerciseBaseController(ExerciseBaseRepository exerciseBaseRepository, ExerciseBaseService exerciseBaseService) {
        this.exerciseBaseRepository = exerciseBaseRepository;
        this.exerciseBaseService = exerciseBaseService;
    }

    @PostMapping
    public ExerciseBase create(@RequestBody ExerciseBase exerciseBase) {
        return this.exerciseBaseService.save(exerciseBase);
    }

    @GetMapping("/{id}")
    public ExerciseBase getExerciseBaseById(@PathVariable Long id) {
        return exerciseBaseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ExerciseBase update(@PathVariable Long id, @RequestBody ExerciseBase exerciseBase) {
        return this.exerciseBaseService.update(id, exerciseBase);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ExerciseBase exerciseBaseToDelete = exerciseBaseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        exerciseBaseRepository.delete(exerciseBaseToDelete);
        return ResponseEntity.ok().build();
    }
}