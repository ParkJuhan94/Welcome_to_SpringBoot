package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
/**
 * 자바 코드로 직접 스프링 빈 등록하기 -> @Configyration 어노테이션 등록
 * memberService와 memberRepository의 컴포넌트 등록과 @Autowired는 지워준다.
 * memberController는 컴포넌트로 스프링 빈 등록을 해야만한다. 설정으로 불가능하다.
 *
 * 애플리케이션이 실행될 때 memberService와 memberRepository를 스프링 빈으로 등록하고
 * memberService에 memberRepository를 주입해줍니다.
 *
 * 참고: XML로 설정하는 방식도 있지만 최근에는 잘 사용하지 않습니다.
 *
 * 참고: DI에는 필드 주입, setter 주입, 생성자 주입 이렇게 3가지 방법이 있습니다.
 * 의존관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장합니다.
 *
 * 참고: 실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용합니다.
 * 그리고 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록합니다.
 *
 * 주의: @Autowired를 통한 DI는 스프링이 관리하는 객체에서만 동작합니다.
 * 스프링 빈으로 등록하지 않고 내가 직접 생성한 객체에서는 동작하지 않습니다.
 */