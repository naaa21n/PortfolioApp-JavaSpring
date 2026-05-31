package com.example.portfolioapi.controller.health;

import com.example.portfolioapi.entity.health.Activity;
import com.example.portfolioapi.repository.health.ActivityRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@CrossOrigin(origins = "http://localhost:3000")
public class ActivityController {

    private final ActivityRepository activityRepository;

    public ActivityController(
            ActivityRepository activityRepository
    ) {
        this.activityRepository =
                activityRepository;
    }

    @GetMapping
    public List<Activity> getAll() {
        return activityRepository.findAll();
    }

    @GetMapping("/date/{date}")
    public Activity getByDate(
            @PathVariable String date
    ) {
        return activityRepository
                .findByDate(date)
                .orElse(null);
    }

    @PostMapping
    public Activity add(
            @RequestBody Activity activity
    ) {

        System.out.println("id=" + activity.getId());
        System.out.println("date=" + activity.getDate());
        System.out.println("sleep=" + activity.getSleep());
        System.out.println("work=" + activity.getWork());
        System.out.println("study=" + activity.getStudy());
        System.out.println("exercise=" + activity.getExercise());
        System.out.println("hobby=" + activity.getHobby());
        System.out.println("other=" + activity.getOther());

        return activityRepository.save(activity);
    }
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable String id
    ) {
        activityRepository.deleteById(id);
    }
}