package com.keyin.member;

import com.keyin.enums.MembershipType;
import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Entity representing a member in the system
 * It stores member details such as id, name, and address
 */
@Entity
public class Member
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String memberName;
    private String memberAddress;
    private String memberEmail;
    private String memberPhoneNumber;
    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    private MembershipType membershipType;

    public Member()
    {

    }

    public Member(String memberName, String memberAddress, String memberEmail, String memberPhoneNumber, LocalDate startDate, MembershipType membershipType)
    {
        this.memberName = memberName;
        this.memberAddress = memberAddress;
        this.memberEmail = memberEmail;
        this.memberPhoneNumber = memberPhoneNumber;
        this.startDate = startDate;
        this.membershipType = membershipType;
    }

    public Member(Long id, String memberName, String memberAddress, String memberEmail, String memberPhoneNumber, LocalDate startDate, MembershipType membershipType)
    {
        this.id = id;
        this.memberName = memberName;
        this.memberAddress = memberAddress;
        this.memberEmail = memberEmail;
        this.memberPhoneNumber = memberPhoneNumber;
        this.startDate = startDate;
        this.membershipType = membershipType;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getMemberName()
    {
        return memberName;
    }

    public void setMemberName(String memberName)
    {
        this.memberName = memberName;
    }

    public String getMemberAddress()
    {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress)
    {
        this.memberAddress = memberAddress;
    }

    public String getMemberEmail()
    {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail)
    {
        this.memberEmail = memberEmail;
    }

    public String getMemberPhoneNumber()
    {
        return memberPhoneNumber;
    }

    public void setMemberPhoneNumber(String memberPhoneNumber)
    {
        this.memberPhoneNumber = memberPhoneNumber;
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public void setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
    }

    public MembershipType getMembershipType()
    {
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType)
    {
        this.membershipType = membershipType;
    }

    @Override
    public String toString()
    {
        return "Member{" +
                "id=" + id +
                ", memberName='" + memberName + '\'' +
                ", memberAddress='" + memberAddress + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberPhoneNumber='" + memberPhoneNumber + '\'' +
                ", startDate=" + startDate +
                ", membershipType=" + membershipType +
                '}';
    }
}
