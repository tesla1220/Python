package com.ohgiraffers.mapping.section01.entity;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberRegistService {

    private MemberRepository memberRepository;

    @Autowired // @Autowired 는 생성자 하나기 때문에 선택 사항
    public MemberRegistService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void registMember(MemberRegistDTO newMember) {
                                                            // 검증이 필요한 로직은 엔티티에 넣기 전 이 위치에 추가

        Member member = new Member(
                newMember.getMemberId(),
                newMember.getMemberPwd(),
                newMember.getMemberName(),
                newMember.getPhone(),
                newMember.getAddress(),
                newMember.getEnrollDate(),
                newMember.getMemberRole(),
                newMember.getStatus()
        );

        memberRepository.save(member);

    }

    @Transactional
    public String registMemberAndFindName(MemberRegistDTO newMember) {

        registMember(newMember);

        return memberRepository.findNameById(newMember.getMemberId());

    }
}


