package com.keyin.member;

import com.keyin.enums.MembershipType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * REST controller for managing member operations.
 * Handles creating, retrieving, and searching
 */
@RestController
@CrossOrigin
public class MemberController
{
    @Autowired
    private MemberService memberService;

    @PostMapping("/members")
    public Member addNewMember(@RequestBody Member member)
    {
        return memberService.addNewMember(member);
    }

    @GetMapping("/members")
    public List<Member> getAllMembers()
    {
        return memberService.getAllMembers();
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id)
    {
        return memberService.getMemberById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/members/search/name/{name}")
    public List<Member> searchByName(@PathVariable String name)
    {
        return memberService.searchByName(name);
    }

    @GetMapping("/members/search/type/{membershipType}")
    public List<Member> searchByMembershipType(@PathVariable MembershipType membershipType)
    {
        return memberService.searchByMembershipType(membershipType);
    }

    @GetMapping("/members/search/phone/{phoneNumber}")
    public List<Member> searchByPhoneNumber(@PathVariable String phoneNumber)
    {
        return memberService.searchByPhoneNumber(phoneNumber);
    }

    @GetMapping("/members/search/tournament-date/{startDate}")
    public List<Member> searchByTournamentStartDate(@PathVariable LocalDate startDate)
    {
        return memberService.searchByTournamentStartDate(startDate);
    }
}
