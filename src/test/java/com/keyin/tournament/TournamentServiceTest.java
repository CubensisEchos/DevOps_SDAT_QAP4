package com.keyin.tournament;

import com.keyin.enums.MembershipType;
import com.keyin.member.Member;
import com.keyin.member.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TournamentServiceTest
{
    @Mock
    private TournamentRepository tournamentRepository;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private TournamentService tournamentService;

    private Tournament tournament;
    private Member member;

    @BeforeEach
    void setup()
    {
        tournament = new Tournament(
                LocalDate.of(2026, 8, 15),
                LocalDate.of(2026, 8, 17),
                "Test City",
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
    public void addNewTournament_ReturnsNewTournament()
    {
        Mockito.when(tournamentRepository.save(tournament)).thenReturn(tournament);
        Tournament savedTournament = tournamentService.addNewTournament(tournament);

        Assertions.assertEquals(tournament, savedTournament);
        verify(tournamentRepository).save(tournament);
    }

    @Test
    public void getAllTournaments_ReturnsAllTournaments()
    {
        List<Tournament> tournaments = List.of(tournament);

        Mockito.when(tournamentRepository.findAll()).thenReturn(tournaments);
        List<Tournament> response = tournamentService.getAllTournaments();

        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals(tournament, response.get(0));
        verify(tournamentRepository).findAll();
    }

    @Test
    public void getTournamentById_ReturnsTournamentWhenFound()
    {
        Mockito.when(tournamentRepository.findById(1L)).thenReturn(Optional.of(tournament));
        Optional<Tournament> response = tournamentService.getTournamentById(1L);

        Assertions.assertTrue(response.isPresent());
        Assertions.assertEquals(tournament, response.get());
    }

    @Test
    public void searchByStartDate_ReturnsApplicableTournaments()
    {
        List<Tournament> tournaments = List.of(tournament);

        Mockito.when(tournamentRepository.findByStartDate(LocalDate.of(2026, 8, 15))).thenReturn(tournaments);
        List<Tournament> response = tournamentService.searchByStartDate(LocalDate.of(2026, 8, 15));

        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals(tournament, response.get(0));
    }

    @Test
    public void searchByLocation_ReturnsApplicableTournaments()
    {
        List<Tournament> tournaments = List.of(tournament);

        Mockito.when(tournamentRepository.findByLocationContainingIgnoreCase("Test city")).thenReturn(tournaments);
        List<Tournament> response = tournamentService.searchByLocation("Test city");

        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals(tournament, response.get(0));
    }

    @Test
    public void registerMemberForTournament_ReturnsUpdatedTournament()
    {
        tournament.setId(1L);
        member.setId(2L);

        Mockito.when(tournamentRepository.findById(1L)).thenReturn(Optional.of(tournament));
        Mockito.when(memberRepository.findById(2L)).thenReturn(Optional.of(member));
        Mockito.when(tournamentRepository.save(tournament)).thenReturn(tournament);

        Optional<Tournament> response = tournamentService.registerMemberForTournament(1L, 2L);

        Assertions.assertTrue(response.isPresent());
        Assertions.assertEquals(tournament, response.get());
        Assertions.assertEquals(1, tournament.getParticipatingMembers().size());
        Assertions.assertEquals(member, tournament.getParticipatingMembers().get(0));
        verify(tournamentRepository).save(tournament);
    }
}
