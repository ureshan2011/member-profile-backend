package com.example.membershipmanagementportal.controller;

import com.example.membershipmanagementportal.exception.ResourceNotFoundException;
import com.example.membershipmanagementportal.model.Member;
import com.example.membershipmanagementportal.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/notes")
    public List<Member> getAllNotes() {
        return memberRepository.findAll();
    }

    @PostMapping("/member")
    public Member createMember(@Valid @RequestBody Member memberDataObj) {
        return memberRepository.save(memberDataObj);
    }

    @GetMapping("/notes/{id}")
    public Member getNoteById(@PathVariable(value = "id") Long noteId) {
        return memberRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    @PutMapping("/notes/{id}")
    public Member updateNote(@PathVariable(value = "id") Long noteId,
                                           @Valid @RequestBody Member noteDetails) {

        Member note = memberRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        //note.setTitle(noteDetails.getTitle());
        //note.setContent(noteDetails.getContent());

        Member updatedNote = memberRepository.save(note);
        return updatedNote;
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        Member note = memberRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        memberRepository.delete(note);

        return ResponseEntity.ok().build();
    }
}
