package jpabook.jpashop.domain.Item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    //실무에서는 필드를 더 넣지 못해서 사용 못한다.
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//
    //도메인 주도 설계(엔티티 안에 비즈니스 로직을 넣는다.) 응집도를 높임

    /**
     * stock 증가
     */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }
    /**
     * stock 감소
     */
    public void removeStock(int quantity){
        int restStock = this.stockQuantity-quantity;
        if(restStock < 0){
            throw new NotEnoughStockException("need more stock");

        }
        this.stockQuantity = restStock;
    }

}
