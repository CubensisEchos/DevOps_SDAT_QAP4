package com.keyin.tournament;

import com.keyin.member.Member;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a tournament in the system
 * It stores details such as the start and end date, as well as a list of attending members
 */
@Entity
public class Tournament
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String Location;
    private double entryFee;
    private double prizeAmount;

    @OneToMany
    private List<Member> participatingMembers = new ArrayList<>();

    public Tournament()
    {

    }

    public Tournament(LocalDate startDate, LocalDate endDate, String location, double entryFee, double prizeAmount)
    {
        this.startDate = startDate;
        this.endDate = endDate;
        Location = location;
        this.entryFee = entryFee;
        this.prizeAmount = prizeAmount;
    }

    public Tournament(Long id, LocalDate startDate, LocalDate endDate, String location, double entryFee, double prizeAmount, List<Member> participatingMembers)
    {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        Location = location;
        this.entryFee = entryFee;
        this.prizeAmount = prizeAmount;
        this.participatingMembers = participatingMembers;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public void setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }

    public void setEndDate(LocalDate endDate)
    {
        this.endDate = endDate;
    }

    public String getLocation()
    {
        return Location;
    }

    public void setLocation(String location)
    {
        Location = location;
    }

    public double getEntryFee()
    {
        return entryFee;
    }

    public void setEntryFee(double entryFee)
    {
        this.entryFee = entryFee;
    }

    public double getPrizeAmount() {
        return prizeAmount;
    }

    public void setPrizeAmount(double prizeAmount)
    {
        this.prizeAmount = prizeAmount;
    }

    public List<Member> getParticipatingMembers()
    {
        return participatingMembers;
    }

    public void setParticipatingMembers(List<Member> participatingMembers)
    {
        this.participatingMembers = participatingMembers;
    }

    @Override
    public String toString()
    {
        return "Tournament{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", Location='" + Location + '\'' +
                ", entryFee=" + entryFee +
                ", prizeAmount=" + prizeAmount +
                ", participatingMembers=" + participatingMembers +
                '}';
    }
}
