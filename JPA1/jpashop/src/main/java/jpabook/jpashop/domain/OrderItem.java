package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Item.Item;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; // 주문 가격

    private int count;//주문 수량

    //==생성 메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice,int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);//Item이 order될 때 재고를 줄여주는 로직
        return orderItem;
    }

    //===비즈니스 로직==//
    public void cancel(){
        getItem().addStock(count);//재고수량 원상복구 시킴

    }

    public int getTotalPrice(){
        return getOrderPrice() * count;
    }
}
