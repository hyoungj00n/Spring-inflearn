package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //spring bean으로 관리됨
public class MemberRepository {

    @PersistenceContext //spring이 entity 매니저를 만들어서 injection해준다.
    private EntityManager em;

    public MemberRepository(EntityManager em){//생성자를 통해서
        this.em = em;
    }

    //저장 logic
    public void save(Member member){
        em.persist(member);//jpa가 저장해준다.
    }

    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();//jpql은 sql과 거의 같지만 다르다.(객체를 대상으로 쿼리를 한다.)
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }
}
