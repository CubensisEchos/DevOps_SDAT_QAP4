package com.keyin.tournament;

import com.keyin.member.Member;
import com.keyin.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing tournament operations
 * Handles creation, retrieval, searching, and registering members to tournaments
 */
@Service
public class TournamentService
{
    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Tournament addNewTournament(Tournament tournament)
    {
        return tournamentRepository.save(tournament);
    }

    public List<Tournament> getAllTournaments()
    {
        return tournamentRepository.findAll();
    }

    public Optional<Tournament> getTournamentById(Long id)
    {
        return tournamentRepository.findById(id);
    }

    public List<Tournament> searchByStartDate(LocalDate startDate)
    {
        return tournamentRepository.findByStartDate(startDate);
    }

    public List<Tournament> searchByLocation(String location)
    {
        return tournamentRepository.findByLocationContainingIgnoreCase(location);
    }

    public Optional<Tournament> registerMemberForTournament(Long tournamentId, Long memberId)
    {
        Optional<Tournament> tournamentOptional = tournamentRepository.findById(tournamentId);
        Optional<Member> memberOptional = memberRepository.findById(memberId);

        if (tournamentOptional.isPresent() && memberOptional.isPresent())
        {
            Tournament tournament = tournamentOptional.get();
            Member member = memberOptional.get();

            tournament.getParticipatingMembers().add(member);

            return Optional.of(tournamentRepository.save(tournament));
        }

        return Optional.empty();
    }
}
