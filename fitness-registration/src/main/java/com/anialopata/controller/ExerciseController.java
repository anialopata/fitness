package com.anialopata.controller;

import com.anialopata.api.v1.model.ExerciseDTO;
import com.anialopata.api.v1.model.ExerciseListDTO;
import com.anialopata.service.ExerciseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ania on 2018-07-22.
 */
@Api(description = "Exercise Controller")
@RestController
@RequestMapping("/api/v1/exercises")
@CrossOrigin(origins = "http://localhost:4200")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @ApiOperation(value = "List all Exercises")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ExerciseListDTO getExercises(){
        return new ExerciseListDTO(exerciseService.getAllExercises());
    }

    @ApiOperation(value = "Get Exercise by ID value")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ExerciseDTO getExerciseById (@PathVariable Long id) {
        return exerciseService.getExerciseById(id);
    }

    @ApiOperation(value = "Create new Exercise")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseDTO createNewExercise(@RequestBody ExerciseDTO exerciseDTO){
        return exerciseService.createExercise(exerciseDTO);
    }

    @ApiOperation(value = "Update existing Exercise")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ExerciseDTO updateExercise(@PathVariable Long id, @RequestBody ExerciseDTO exerciseDTO){
        return exerciseService.saveExerciseByDTO(id, exerciseDTO);
    }

    @ApiOperation(value = "Delete Exercise")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteExercise(@PathVariable Long id){
        exerciseService.deleteExercise(id);
    }

    @ApiOperation(value = "List all Exercises for requested date")
    @GetMapping({"/show/date/{date}"})
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciseDTO> getExercisesByDate(@PathVariable String date) {
        return exerciseService.getExerciseByDate(date);
    }

    @ApiOperation(value = "List all Exercises for requested date")
    @GetMapping({"/show/day/{day}"})
    public List<ExerciseDTO> getExercisesByDay(@PathVariable String day) {
        return exerciseService.getExerciseByDay(day);
    }

}