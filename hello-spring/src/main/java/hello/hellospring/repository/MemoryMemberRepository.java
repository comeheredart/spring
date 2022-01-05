package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    //현업에는 동시성 문제 때문에 이렇게 쓰지 않지만 지금은 예제니까!
    private static Map<Long, Member> store = new HashMap<>();
    //ID 무작위로 만드는 아이. 시스템이 정해주는 애
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
        //id 값을 올린다음 세팅하고, store에 저장하기.
    }

    @Override
    public Optional<Member> findByID(Long id) {
        return Optional.ofNullable(store.get(id));
        //null이 반환될 가능성이 있으니까!
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        //람다! 루프를 돌면서 맵에서 이름이 같으면 반환. 결과는 옵셔널!
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        //전체 회원을 반환. member 들을 반환한다.
    }

}
