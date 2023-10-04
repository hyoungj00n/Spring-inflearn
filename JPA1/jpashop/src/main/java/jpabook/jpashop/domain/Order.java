package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "orders")//엔티티와 매핑할 테이블 이름을 정해주지 않으면 Order가 된다.
@Getter @Setter
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")//mapping을 어떤 것과 할 것인지 member_id가 foreign key가 된다.
    //연관관계 주인 설정 해주기 (Orders나 Member중에 값이 변경 되었을 때 Orders에 있는 Member foreign key를 바꾼다는 의미)
    private Member member;

}
