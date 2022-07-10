package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//  interface가 interface를 상속받을 때는 implements 가 아니라 extends
//  interface는 다중 상속 가능
//  JpaRepository 클래스에 findByName 같은 메서드가 만들어져 있음. 그걸 MemberRepository에 끌어다 쓰는것
public interface SpringDataJpaMemberRepository extends
        JpaRepository<Member, Long>, MemberRepository {

    //JPQL :  select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);

//    And Or 등등으로 확장 가능
//    @Override
//    Optional<Member> findByNameAndId(String name, Long id);
}