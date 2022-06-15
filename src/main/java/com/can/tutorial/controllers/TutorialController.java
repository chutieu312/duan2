package com.can.tutorial.controllers;

import com.can.tutorial.models.Tutorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.can.tutorial.repos.TutorialRepo;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class TutorialController {
    @Autowired
    TutorialRepo tutorialRepo;

    @GetMapping("/all")
    ResponseEntity<List<Tutorial>> getAllTutorials(){
        List<Tutorial> tutorials = tutorialRepo.findAll();
        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id){
        Tutorial tutorial = tutorialRepo.findById(id).get();
        return new ResponseEntity<>(tutorial, HttpStatus.OK);
    }

// get tutorial by published // http://localhost:8080/api/find/tutorial?published=true
    @GetMapping("/find/tutorial")
    public ResponseEntity<List<Tutorial>> getAllTutorialsByPublished( @RequestParam("published") boolean isPublished)
    {
        return new ResponseEntity<>(tutorialRepo.findByPublished(isPublished ), HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    ResponseEntity<List<Tutorial>> getTutorialById(@PathVariable("title") String tt){
        List<Tutorial> tutorialL = tutorialRepo.findByTitleContainingIgnoreCase(tt);
        return new ResponseEntity<>(tutorialL, HttpStatus.OK);
    }

    @PostMapping("/add")
    ResponseEntity<Tutorial> addTutorial(@RequestBody Tutorial tutorial){
        //tutorial.setTitle(tutorial.getTitle().toLowerCase());
        Tutorial newTuto = tutorialRepo.save(tutorial);
        return new ResponseEntity<>(newTuto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial){
        System.out.println("Id of update tutorial: "+ id);
        Tutorial updateTuto = tutorialRepo.save(tutorial);
        return new ResponseEntity<>(updateTuto, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteAllTutorials(@PathVariable("id") long id) {
        tutorialRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        tutorialRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
