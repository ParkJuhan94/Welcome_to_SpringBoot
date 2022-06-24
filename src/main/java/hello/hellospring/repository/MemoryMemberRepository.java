package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    //  실무에서는 동시성 문제를 해결하기 위해 멤버 필드에 HashMap 대신 ConcurrentHashMap을 사용해야 합니다.
    //  실무에서는 동시성 문제를 해결하기 위해 멤버 필드에 기본 타입 변수 대신 Atomic 변수를 사용해야 합니다.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //  get을 했을때 null일 수 있어서 Optional로 감싸고 return 한다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        //  Java8에 나온 람다기능입니다.
        //  store의 value값들을 모두 돌면서 name이 일치하는 값만 필터링 합니다.
        //  findAny는 필터링된 결과를 1개 찾으면 바로 Optional로 감싸서 반환해줍니다.
        //  없어도 null을 반환하지 않고 Optional로 반환합니다.
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        //  values가 모두 담긴 ArrayList가 생성됩니다.
        return new ArrayList<>(store.values());
    }
}
