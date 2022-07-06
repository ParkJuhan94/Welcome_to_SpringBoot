package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        //System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members" )
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}

/**
 생성자에 @Autowired가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어줍니다.
 이렇게 객체 의존관계를 외부에서 넣어주는 것을 DI(Dependency Injection), 의존성 주입이라 합니다.

 memberService와 memberRepository가 스프링 컨테이너에 스프링 빈으로 등록되었습니다.
 이제 의존관계를 모두 설정해줬으므로 실행 시 오류가 발생하지 않습니다.

 스프링 빈을 등록하는 2가지 방법
 1.컴포넌트 스캔과 자동 의존관계 설정
 2.자바 코드로 직접 스프링 빈 등록하기

 컴포넌트 스캔 원리
 @Component 애노테이션이 있으면 스프링 빈으로 자동 등록됩니다.
 @Controller 컨트롤러가 스프링 빈으로 자동 등록되는 이유도 컴포넌트 스캔 때문입니다.
 @Component를 포함하는 다음 애노테이션도 스프링 빈으로 자동 등록됩니다.
    1.@Controller
    2.@Service
    3.@Repository
 @SpringBootApplication 어노테이션이 선언된 패키지를 포함한 하위 패키지의 어노테이션만 스캔합니다.
 (여기서는 main 메서드가 포함된 HelloSpringApplication class가 선언된 패키지인
 hello.hellospring 패키지 하위에서만 어노테이션을 스캔)

 참고: 생성자에 @Autowired를 사용하면 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아서 주입합니다.
 생성자가 1개만 있으면 @Autowired는 생략할 수 있습니다.

 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글톤으로 등록합니다(유일하게 하나만 등록해서 공유합니다).
 따라서 같은 스프링 빈이면 모두 같은 인스턴스입니다.
 설정으로 싱글톤이 아니게 설정할 수 있지만, 특별한 경우를 제외하면 대부분 싱글톤을 사용합니다.
 */