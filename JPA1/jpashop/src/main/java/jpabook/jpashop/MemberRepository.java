package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;
//스프링이 제공하는 기본 annotation
@Repository
public class MemberRepository {

    //스프링 부트가 Entity Manager를 생성하는 작업을 다 해준다.
    @PersistenceContext
    private EntityManager em;

    //저장을 하고 나면 command성이기 때문에 return 값을 만들지 않는다.(id정도만 있으면 조회가능)
    public Long save(Member member){
        em.persist(member);
        return member.getId();// command와 query를 분리시킨다.
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }


}
