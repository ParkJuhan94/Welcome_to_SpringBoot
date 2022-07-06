package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    //굳이 BeforeEach 에서 객체를 생성해서 test 하지 말고,
    //Autowired 로 빋은 field 객체를 써서 test 해도 아무 상관 없음(제일 간단)
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

//    @BeforeEach
//    public void beforeEach() {
//        memberRepository = new MemoryMemberRepository();
//        memberService = new MemberService(memberRepository);
//    }

//    @AfterEach
//    public void afterEach() {
//        memberRepository.clearStore();
//    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }

}


/*
* @SpringBootTest: 스프링 컨테이너와 테스트를 함께 실행합니다.
*
* @Transactional: 테스트 케이스에 이 애노테이션이 있으면,
* 테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후에 항상 롤백합니다.
* 이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않습니다.
* (기존 test의 AfterEach 불필요)
*
* 이런 스프릥 컨테이너와 테스트를 함께 실행하면 매우 오래 걸린다. (기존의 단위 테스트보다)
* 기존의 단위 테스트가 더 좋은 테스트이다.
* 스프링 컨테이너가 필요한 테스트라면 잘못된 테스트일 확률이 높다.
* */