package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /*회원가입*/
    public Long join(Member member) {

        //같은 이름이 있는 중복회원은 안된다
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
        //임의로 아이디 반환

    }

    private void validateDuplicateMember(Member member) {
        //리턴으로 바꾸는 단축키: command option v
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        //옵셔널이기 때문에 가능한 문법

        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        //위를 깔끔하게 한 버전, 얘는 함수로 뽑자. control T
    }

    /*전체회원조회*/
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /*회원찾기*/
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findByID(memberId);
    }


}

