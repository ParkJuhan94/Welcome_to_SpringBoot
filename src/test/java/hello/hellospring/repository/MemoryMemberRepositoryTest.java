package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

//다른데서 가져다 쓸 class 가 아니기 때문에 public 으로 지정할 필요 없음
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //첫번째 방법 : 출력하는 방법은 개발자가 계속 볼 수 없기 때문에 assertion을 쓴다.
        //System.out.println("member = " + (result == member));

        //2번째 방법 : 쓰기 불편함
        //Assertions.assertEquals(member, result);    //(기댓값, 결괏값)

        //3번째 방법 : 제일 많이 씀
        //Assertions.assertThat(member).isEqualTo(result);
        //Assertions에서 alt + enter 로 static import 해주면 아래처럼 바로 assertThat 호출 가능
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //refactor 단축키 : shift + F6
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
        //assertThat(result).isEqualTo(member2);    //이렇게 하면 error
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //refactor 단축키 : shift + F6
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}

//Optonal은 get()으로 해당 객체를 가져올 수 있습니다.
//실무에서는 junit의 Assertions보다 assertj의 Assertions를 더 많이 사용합니다.

//@AfterEach: 한번에 여러 테스트를 실행하면 메모리 DB에 직전 테스트의 결과가 남을 수 있습니다.
// 이렇게 되면 이전 테스트 때문에 다음 테스트가 실패할 가능성이 있습니다.
// @AfterEach를 사용하면 각 테스트가 종료될 때 마다 이 기능을 실행합니다.

//테스트는 각각 독립적으로 실행되어야 합니다. 테스트 순서에 의존관계가 있는 것은 좋은 테스트가 아닙니다.
//실무에서는 모든 테스트 코드가 통과하지 않으면 빌드 자체가 실패하게 됩니다.

//테스트 코드를 먼저 작성하고 이후에 구현 코드를 작성하는 것을 테스트 주도 개발(TDD)이라고 합니다. 위에서 작업한 것은 TDD가 아닙니다.