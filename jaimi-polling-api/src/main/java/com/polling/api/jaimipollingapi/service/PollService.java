package com.polling.api.jaimipollingapi.service;

import com.polling.api.jaimipollingapi.exception.ResourceNotFoundException;
import com.polling.api.jaimipollingapi.model.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.polling.api.jaimipollingapi.repositories.PollRepository;
import java.util.Optional;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;


    public Iterable<Poll> getPolls(){
       return pollRepository.findAll();

    }


    public Optional<Poll> getPoll(Long pollId){
        verifyPoll(pollId);
        return pollRepository.findById(pollId);

    }
    public void createPoll(Poll poll){

        pollRepository.save(poll);
    }

    public void updatePoll(Poll poll, Long pollId) {
        verifyPoll(pollId);
       pollRepository.save(poll); // .save needs an entity parameter, Poll is an entity, so it works.

    }

    public void deletePoll(Long pollId){
        verifyPoll(pollId);
        pollRepository.deleteById(pollId);

    }

    protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
        if(!(this.pollRepository.existsById(pollId))){ // if the poll given cannot be found/doesn't exist it will return the message below
            throw (new ResourceNotFoundException("The poll with id " + pollId + " does not exist"));
        }
    }




}


