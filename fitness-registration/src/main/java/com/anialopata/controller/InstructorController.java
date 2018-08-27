package com.anialopata.controller;

import com.anialopata.api.v1.model.ExerciseDTO;
import com.anialopata.api.v1.model.InstructorDTO;
import com.anialopata.api.v1.model.InstructorListDTO;
import com.anialopata.service.InstructorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Ania on 2018-08-01.
 */
@Api(description = "Instructor Controller")
@RestController
@RequestMapping("/api/v1/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @ApiOperation(value = "List all Instructors")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public InstructorListDTO getInstructors(){
        return new InstructorListDTO(instructorService.getAllInstructors());
    }

    @ApiOperation(value = "Get Instructor by ID value")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public InstructorDTO getInstructorById(@PathVariable Long id) {
        return instructorService.getInstructorById(id);
    }

    @ApiOperation(value = "Create new Instructor")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InstructorDTO createNewInstructor(@RequestBody InstructorDTO instructorDTO){
        return instructorService.createInstructor(instructorDTO);
    }

    @ApiOperation(value = "Update existing Instructor")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public InstructorDTO updateInstructor(@PathVariable Long id, @RequestBody InstructorDTO instructorDTO){
        return instructorService.saveInstructorByDTO(id, instructorDTO);
    }

    @ApiOperation(value = "Delete Instructor")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteInstructor(@PathVariable Long id){
        instructorService.deleteInstructor(id);
    }

    @ApiOperation(value = "List all Exercises for Instructor")
    @GetMapping("/{instructorId}/exercises")
    public Set<ExerciseDTO> findStudentsExercises(@PathVariable Long instructorId) {
        return instructorService.findInstructorExercises(instructorId);
    }

    @ApiOperation(value = "Add Exercise to Instructor")
    @PostMapping("/{instructorId}/exercises")
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseDTO addExercise(@PathVariable Long instructorId, @RequestBody ExerciseDTO exerciseDTO){
        return instructorService.addExerciseToInstructor(instructorId, exerciseDTO);

    }
}
