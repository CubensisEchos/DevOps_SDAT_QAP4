package com.keyin.tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * REST controller for managing tournament operations.
 * Handles creating, retrieving, searching, and adding members to tournaments
 */
@RestController
@CrossOrigin
public class TournamentController
{
    @Autowired
    private TournamentService tournamentService;

    @PostMapping("/tournaments")
    public Tournament addNewTournament(@RequestBody Tournament tournament)
    {
        return tournamentService.addNewTournament(tournament);
    }

    @GetMapping("/tournaments")
    public List<Tournament> getAllTournaments()
    {
        return tournamentService.getAllTournaments();
    }

    @GetMapping("/tournaments/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable Long id)
    {
        return tournamentService.getTournamentById(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tournaments/search/date/{startDate}")
    public List<Tournament> searchByStartDate(@PathVariable LocalDate startDate)
    {
        return tournamentService.searchByStartDate(startDate);
    }

    @GetMapping("/tournaments/search/location/{location}")
    public List<Tournament> searchByLocation(@PathVariable String location)
    {
        return tournamentService.searchByLocation(location);
    }

    @PostMapping("/tournaments/{tournamentId}/register/{memberId}")
    public ResponseEntity<Tournament> registerMemberForTournament(@PathVariable Long tournamentId, @PathVariable Long memberId)
    {
        return tournamentService.registerMemberForTournament(tournamentId, memberId)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
