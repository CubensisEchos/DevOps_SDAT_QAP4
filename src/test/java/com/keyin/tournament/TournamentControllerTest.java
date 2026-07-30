package com.keyin.tournament;

import com.keyin.enums.MembershipType;
import com.keyin.member.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TournamentControllerTest
{
    @Mock
    private TournamentService tournamentService;

    @InjectMocks
    private TournamentController tournamentController;

    private Tournament tournament;
    private Member member;

    @BeforeEach
    void setup()
    {
        tournament = new Tournament(
                LocalDate.of(2026, 8, 15),
                LocalDate.of(2026, 8, 17),
                "St. John's Golf Club",
                50.00,
                100.00
        );

        member = new Member(
                "John Ryan",
                "12 Main Road",
                "johnr@email.com",
                "7091235544",
                LocalDate.of(2026, 5, 21),
                MembershipType.ANNUAL
        );
    }

    @AfterEach
    void tearDown()
    {
        tournament = null;
        member = null;
    }

    @Test
    public void addNewTournament_ReturnsAddedTournament()
    {
        Mockito.when(tournamentService.addNewTournament(any(Tournament.class))).thenReturn(tournament);
        Tournament response = tournamentController.addNewTournament(tournament);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(tournament, response);
        verify(tournamentService).addNewTournament(tournament);
    }

    @Test
    public void getAllTournaments_ReturnAllTournaments()
    {
        List<Tournament> tournaments = List.of(tournament);

        Mockito.when(tournamentService.getAllTournaments()).thenReturn(tournaments);
        List<Tournament> response = tournamentController.getAllTournaments();

        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals(tournament, response.get(0));
        verify(tournamentService).getAllTournaments();
    }

    @Test
    public void getTournamentById_ReturnsOkWhenFound()
    {
        tournament.setId(1L);

        Mockito.when(tournamentService.getTournamentById(1L)).thenReturn(Optional.of(tournament));
        ResponseEntity<Tournament> response = tournamentController.getTournamentById(1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(1L, response.getBody().getId());
    }

    @Test
    public void searchByStartDate_ReturnsApplicableTournaments()
    {
        List<Tournament> tournaments = List.of(tournament);

        Mockito.when(tournamentService.searchByStartDate(LocalDate.of(2026, 8, 15))).thenReturn(tournaments);
        List<Tournament> response = tournamentController.searchByStartDate(LocalDate.of(2026, 8, 15));

        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals(tournament, response.get(0));
        verify(tournamentService).searchByStartDate(LocalDate.of(2026, 8, 15));
    }

    @Test
    public void searchByLocation_ReturnsApplicableTournaments()
    {
        List<Tournament> tournaments = List.of(tournament);

        Mockito.when(tournamentService.searchByLocation("Test City")).thenReturn(tournaments);
        List<Tournament> response = tournamentController.searchByLocation("Test City");

        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals(tournament, response.get(0));
        verify(tournamentService).searchByLocation("Test City");
    }

    @Test
    public void registerMemberForTournament_ReturnsOkWhenRegistered()
    {
        Mockito.when(tournamentService.registerMemberForTournament(1L, 2L)).thenReturn(Optional.of(tournament));
        ResponseEntity<Tournament> response = tournamentController.registerMemberForTournament(1L, 2L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(tournament, response.getBody());
        verify(tournamentService).registerMemberForTournament(1L, 2L);
    }
}
