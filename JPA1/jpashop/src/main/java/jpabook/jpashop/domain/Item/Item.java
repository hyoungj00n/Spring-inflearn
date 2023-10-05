package jpabook.jpashop.domain.Item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)// 상속관계 전략을 부모 클래스에 정해 줘야 한다.
//JoINED는 가장 정규화된 스타일, SINGLE_TABLE은 한 테이블에 다 넣기, TABLE_PER_CLASS는 테이블 별로 나누기
@DiscriminatorColumn(name = "dtype")//SINGLE_TABLE이기 때문에 저장해 줄때 구분이 필요해서
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
}
