package com.polling.api.jaimipollingapi.controller;

import com.polling.api.jaimipollingapi.model.Vote;
import com.polling.api.jaimipollingapi.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class VoteController {

    @Autowired
    private VoteRepository voteRepository;

    @PostMapping(value="/polls/{pollId}/votes")
    public ResponseEntity<?> createVote(@PathVariable Long pollId, @RequestBody Vote vote){
        vote = voteRepository.save(vote);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vote.getId()).toUri()); // setLocation sets the location and specifies the url, fromCurrentRequest creates an SUCB instance from the current request (in the name) path selects the path we want it to be in, buildAndExpand replaces the placeholder with the voteId value and toUri converts the Uri that we just built
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED); //this shows if it was correctly created
    }

    @GetMapping(value="/polls/{pollId}/votes")
    public Iterable<Vote> getAllVotes(@PathVariable Long pollId){
        return voteRepository.findByPoll(pollId);
    }





}
