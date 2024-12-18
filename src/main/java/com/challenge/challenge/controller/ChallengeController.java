package com.challenge.challenge.controller;

import com.challenge.challenge.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/get-all")
    public ResponseEntity<?> findAllBestChallenges(@RequestParam("tab") String tab, @RequestParam("category") String category, @RequestParam("page") int page) {

        return ResponseEntity.ok(
                challengeService.getAllChallenges(tab, category, page)
        );
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getChallengeDetail(@PathVariable Long id) {
        return ResponseEntity.ok(
                challengeService.getChallenge(id)
        );
    }


}
