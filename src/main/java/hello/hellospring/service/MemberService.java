package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    //  @Autowired
    //참고: 생성자에 @Autowired 를 사용하면 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아서
    //주입한다. 생성자가 1개만 있으면 @Autowired 는 생략할 수 있다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * MemberService에 @Service 어노테이션을 붙여주고, 생성자에 @Autowired를 붙여줍니다.
     */

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); // 같은 이름의 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}

//result.ifPresent(m -> { throw new IllegalStateException("이미 존재하는 회원입니다."); });
//        ifPresent는 Optional에 null이 아닌 객체가 존재하는 경우 어떠한 로직을 수행합니다.
//Service 클래스의 메소드 명은 비즈니스에 가까운 메소드 명을 사용해야 합니다.

/*
* 스프링은 해당 클래스의 메서드를 실행할 때 트랜잭션을 시작하고,
메서드가 정상 종료되면 트랜잭션을 커밋합니다. 만약 런타임 예외가 발생하면 롤백합니다.
JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 합니다.
* */