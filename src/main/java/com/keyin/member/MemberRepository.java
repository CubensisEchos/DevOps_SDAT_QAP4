package com.keyin.member;

import com.keyin.enums.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>
{
    List<Member> findByMemberNameContainingIgnoreCase(String memberName);

    List<Member> findByMembershipType(MembershipType membershipType);

    List<Member> findByMemberPhoneNumber(String phoneNumber);
}
