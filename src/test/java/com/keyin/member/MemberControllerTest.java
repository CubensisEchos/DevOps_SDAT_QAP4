package com.keyin.member;

import com.keyin.enums.MembershipType;
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

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MemberControllerTest
{
    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberController memberController;

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
    public void addNewMember_ReturnsCreatedMember()
    {
        Mockito.when(memberService.addNewMember(Mockito.any(Member.class))).thenReturn(member);

        Member response = memberController.addNewMember(new Member());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(member, response);
        verify(memberService).addNewMember(Mockito.any(Member.class));
    }

    @Test
    public void getAllMembers_ReturnsAllMembers()
    {
        List<Member> members = List.of(member);

        Mockito.when(memberService.getAllMembers()).thenReturn(members);
        List<Member> response = memberController.getAllMembers();

        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals(member, response.get(0));
        verify(memberService).getAllMembers();
    }

    @Test
    public void getMemberById_ReturnsOkWhenFound()
    {
        member.setId(1L);

        Mockito.when(memberService.getMemberById(1L)).thenReturn(Optional.of(member));
        ResponseEntity<Member> response = memberController.getMemberById(1L);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(member, response.getBody());
        Assertions.assertEquals(1L, response.getBody().getId());
    }

    @Test
    public void searchByName_ReturnsApplicableMembers()
    {
        List<Member> members = List.of(member);

        Mockito.when(memberService.searchByName("John")).thenReturn(members);
        List<Member> response = memberController.searchByName("John");

        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals(member, response.get(0));
        verify(memberService).searchByName("John");
    }

    @Test
    public void searchByMembershipType_ReturnsApplicableMembers()
    {
        List<Member> members = List.of(member);

        Mockito.when(memberService.searchByMembershipType(MembershipType.ANNUAL)).thenReturn(members);
        List<Member> response = memberController.searchByMembershipType(MembershipType.ANNUAL);

        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals(member, response.get(0));
        verify(memberService).searchByMembershipType(MembershipType.ANNUAL);
    }

    @Test
    public void searchByPhoneNumber_ReturnsApplicableMembers()
    {
        List<Member> members = List.of(member);

        Mockito.when(memberService.searchByPhoneNumber("7091235544")).thenReturn(members);
        List<Member> response = memberController.searchByPhoneNumber("7091235544");

        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals(member, response.get(0));
        verify(memberService).searchByPhoneNumber("7091235544");
    }
}
