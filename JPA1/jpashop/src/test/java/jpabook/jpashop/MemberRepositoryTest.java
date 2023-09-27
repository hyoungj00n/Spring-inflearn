package jpabook.jpashop;

import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializer;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)//Junit에 알려줘야함
@SpringBootTest//Springboot로 테스트를 돌려야함
public class MemberRepositoryTest {


    @Autowired MemberRepository memberRepository; //memberRepository test 해보기 위함

    @Test
    @Transactional//entity manager를 통한 모든 데이터 변경은 transaction 안에서 이뤄져야한다.
    //spring framework를 사용하기 때문에 springframework 라이브러리 권장
    //Test에 Transactional이 있으면 test가 끝나면 rollback을 해버린다.
    @Rollback(false)//Rollback 안하는 방법
    public void testMember() throws Exception{
        //given
        Member member = new Member();
        member.setUsername("memberA");

        //when
        //memberRepository.save(member); 코드에 Ctrl+Alt+V하면 Extract에서 변수 뽑아오는 코드로 바꿔준다.
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());//검증은 스프링에 assertj 라이브러리를 가지고 있기
        // 때문에 Assertions 바로 사용가능
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member); // 영속성 context에서 식별자가 같으면 같은 entity로 인식한다.

    }


}