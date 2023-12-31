package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter// lombok 이용해서 가독성 높이기
public class Member {

    @Id //PK를 나타내기 위한 annotation
    @GeneratedValue // 생성 전략 설정 기본 auto_increment
    @Column(name = "member_id")//Column 이용해서 pk 이름 정하기 , 관례상 table+id로 사용
    Long id;

    private String name;

    @Embedded//내장 타입을 포함했다는 annotation으로 mapping 해주기
    private Address address;

    @OneToMany(mappedBy = "member")//mapping 관계 설정 해주기, mappedby는 연관관계에서 거울이라는 의미(mapping 됨)
    private List<Order> orders = new ArrayList<>();// collection은 field에 하는게 안전하다.. (null방지, hibernate가 collection을 한번 감싸서 hibernater가 지원하는 collection으로 바꿈)
    //가급적 변경하면 안된다.




}
