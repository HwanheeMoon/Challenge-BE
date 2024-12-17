package com.challenge.challenge.controller;

import com.challenge.challenge.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/challenge")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ChallengeController {
    private final ChallengeService challengeService;

    @GetMapping("/get-all/{page}")
    public ResponseEntity<?> getAll(@PathVariable int page) {
        return ResponseEntity.ok(
                challengeService.getAllChallenges(page)
        );
    }

    @GetMapping("/get-all/best/{page}")
    public ResponseEntity<?> findAllBestChallenges(@PathVariable int page) {

        return ResponseEntity.ok(
                challengeService.getAllBestChallenges(page)
        );
    }

    @GetMapping("/get-all/new/{page}")
    public ResponseEntity<?> findAllNewChallenges(@PathVariable int page) {
        return ResponseEntity.ok(
                challengeService.getAllNewChallenges(page)
        );
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> findChallengeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(challengeService.getChallenge(id));
    }

}
