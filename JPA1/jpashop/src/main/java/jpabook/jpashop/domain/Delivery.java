package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)//enum 타입 사용할 때 꼭 넣어줘야 한다.
    //Default는 ORDINAL이다.(1,2,3,4 숫자로 들어감 그래서 중간에 다른 상태가 생기면 문제 발생)
    private DeliveryStatus status;//READY,COMP
}
