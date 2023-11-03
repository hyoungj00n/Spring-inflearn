package jpabook.jpashop.service;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service//spring bean component로 등록이 된다.
@Transactional // transaction안에서 data 변경이 이뤄지도록, 대부분 읽기 전용이면 여기서 readOnly해주고 쓰기에 transactional추가해주기
//@AllArgsConstructor//lombok이 자동으로 아래 생성자 만들어 준다.
@RequiredArgsConstructor//final이 있는 필드만 생성자를 만들어준다.
public class MemberService {

    //@Autowired //repository injection하기
    private final MemberRepository memberRepository;

    /*@Autowired //위와 같이 작성하면 테스트할 때 잠깐 변경이 불가하므로 setter injection 사용
    //동작 중에 웬만하면 바꿀일 없어서 거의 사용x(누군가가 바꾸면 문제가 되기도 함)
    public void setMemberRepository(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }*/

    //생성자에서 injection하는 방법이 제일 안전하다. 스프링이 생성자가 하나만 있으면 자동으로 injection해준다.
    /*@Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }*/

    //회원 가입
    @Transactional
    public Long join(Member member){

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //중복 검사 로직
    private void validateDuplicateMember(Member member){
        List<Member> findMembers = memberRepository.findByName(member.getName());//멀티 thread 상황을 고려해서 Member에 name을 unique 제약 조건을 잡아줘야함
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    //회원 전체 조회
    @Transactional(readOnly = true)// 영속성 context를 더티 체킹을 안하고 읽기 전용 트랜젝션이라고 DB에 알려주는 역할도 한다.
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
