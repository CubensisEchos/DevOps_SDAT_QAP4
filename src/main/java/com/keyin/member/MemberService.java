package com.keyin.member;

import com.keyin.enums.MembershipType;
import com.keyin.tournament.Tournament;
import com.keyin.tournament.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing Member operations
 * Handles creation, retrieval, and searching
 */
@Service
public class MemberService
{
    @Autowired MemberRepository memberRepository;

    @Autowired TournamentRepository tournamentRepository;

    public Member addNewMember(Member member)
    {
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers()
    {
        return memberRepository.findAll();
    }

    public java.util.Optional<Member> getMemberById(Long id)
    {
        return memberRepository.findById(id);
    }

    public List<Member> searchByName(String name)
    {
        return memberRepository.findByMemberNameContainingIgnoreCase(name);
    }

    public List<Member> searchByMembershipType(MembershipType membershipType)
    {
        return memberRepository.findByMembershipType(membershipType);
    }

    public List<Member> searchByPhoneNumber(String phoneNumber)
    {
        return memberRepository.findByMemberPhoneNumber(phoneNumber);
    }

    public List<Member> searchByTournamentStartDate(LocalDate startDate)
    {
        List<Tournament> tournaments = tournamentRepository.findByStartDate(startDate);

        return tournaments.stream().flatMap(tournament -> tournament.getParticipatingMembers().stream()).toList();
    }
}
