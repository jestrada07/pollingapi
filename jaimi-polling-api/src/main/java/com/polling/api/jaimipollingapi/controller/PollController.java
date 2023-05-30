package com.polling.api.jaimipollingapi.controller;

import com.polling.api.jaimipollingapi.model.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.polling.api.jaimipollingapi.service.PollService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PollController {
    @Autowired
    private PollService pollService;

    //Gets All the Polls - get retrieves data
    @GetMapping(value = "/polls")
    public Iterable<Poll> getAllPolls(){
        return pollService.getPolls();

    }

    @RequestMapping(value = "/polls/{pollId}", method=RequestMethod.GET)
    public Optional<Poll> getPoll(@PathVariable Long pollId){
       return pollService.getPoll(pollId);
    }

    @PostMapping(value="/polls") //post submits data
    public void createPoll(@Valid @RequestBody Poll poll){
        pollService.createPoll(poll);
    }

    @PutMapping(value="/polls/{pollId}")// put is employed to update and puts the new data in
    public void updatePoll(@RequestBody Poll poll, @PathVariable Long pollId){
        pollService.updatePoll(poll, pollId);
    }

    @DeleteMapping(value="/polls/{pollId}")
    public void deletePoll( @PathVariable Long pollId){
        pollService.deletePoll( pollId);
    }


}
