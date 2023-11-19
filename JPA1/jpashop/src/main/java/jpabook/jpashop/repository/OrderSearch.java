package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter@Setter

public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;//주문 상태[ORDER, CANCEL]
}
