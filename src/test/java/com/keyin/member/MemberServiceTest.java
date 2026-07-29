package com.keyin.member;

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

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest
{
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    private Member member;

    @BeforeEach
    void setup()
    {
        member = new Member(
                "John Ryan",
                "12 Main Road",
                "johnr@email.com",
                "7091235544",
                LocalDate.of(2026, 5, 21),
                MembershipType.ANNUAL);
    }

    @AfterEach
    void tearDown()
    {
        member = null;
    }

    @Test
    public void addNewMember_ReturnsNewMember()
    {
        Mockito.when(memberRepository.save(member)).thenReturn(member);
        Member savedMember = memberService.addNewMember(member);

        Assertions.assertEquals(member, savedMember);
        Mockito.verify(memberRepository).save(member);
    }

    @Test
    public void returnFullMemberList()
    {
        List<Member> members = List.of(member);

        Mockito.when(memberRepository.findAll()).thenReturn(members);

        List<Member> result = memberService.getAllMembers();

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(member, result.get(0));
        Mockito.verify(memberRepository).findAll();
    }

    @Test
    public void getMemberById_ReturnsMemberWhenFound()
    {
        Mockito.when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        Optional<Member> result = memberService.getMemberById(1L);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(member, result.get());
        Mockito.verify(memberRepository).findById(1L);
    }

    @Test
    public void searchByName_ReturnsMemberWhenFound()
    {
        List<Member> members = List.of(member);

        Mockito.when(memberRepository.findByMemberNameContainingIgnoreCase("John")).thenReturn(members);

        List<Member> result = memberService.searchByName("John");

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(member, result.get(0));
        Mockito.verify(memberRepository).findByMemberNameContainingIgnoreCase("John");
    }

    @Test
    public void searchByMembershipType_ReturnApplicableMembers()
    {
        List<Member> members = List.of(member);

        Mockito.when(memberRepository.findByMembershipType(MembershipType.ANNUAL)).thenReturn(members);

        List<Member> result = memberService.searchByMembershipType(MembershipType.ANNUAL);

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(member, result.get(0));
        Mockito.verify(memberRepository).findByMembershipType(MembershipType.ANNUAL);
    }

    @Test
    public void searchByPhoneNumber_ReturnApplicableMembers()
    {
        List<Member> members = List.of(member);

        Mockito.when(memberRepository.findByMemberPhoneNumber("7091235544")).thenReturn(members);

        List<Member> result = memberService.searchByPhoneNumber("7091235544");

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(member, result.get(0));
        Mockito.verify(memberRepository).findByMemberPhoneNumber("7091235544");
    }


}
