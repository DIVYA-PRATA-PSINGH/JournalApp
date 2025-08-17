package com.example.demo.service;

import com.example.demo.entity.JournalEntry;
import com.example.demo.entity.User;
import com.example.demo.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;



    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try{
        User user =userService.findByUserName(userName);
        JournalEntry saved=journalEntryRepository.save(journalEntry);
        //added to this user journal entries
        user.getJournalEntries().add(saved);
        // user.setUserName(null);
        userService.saveNewUser(user);
    } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occured while saving the entry",e);
        }
    }
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);

    }
    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        User user=userService.findByUserName(userName);
        try {
            boolean removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        }catch(Exception e){
            log.error("Error",e);
            throw new RuntimeException("An error occured while deleting the entry .",e);
        }
    }

  /*  public List<JournalEntry> findByUserName(String userName){

    }*/
}

//controller-->service-->repository





