package com.company.sales.core;

import com.company.sales.entity.Order;
import com.company.sales.entity.OrderDetails;
import com.haulmont.cuba.core.TransactionalDataManager;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.UUID;

@Component(OrderChangeListener.NAME)
public class OrderChangeListener {
    public static final String NAME = "sales_OrderChangeListener";

    @Inject
    private TransactionalDataManager txDataManager;

    @TransactionalEventListener(
            phase = TransactionPhase.BEFORE_COMMIT
    )
    private void onOrderChanged(EntityChangedEvent<Order, UUID> event) {

        if (event.getType() == EntityChangedEvent.Type.DELETED) {
            return;
        }

        Order order = txDataManager.load(event.getEntityId()).view("order-edit").one();

        OrderDetails orderDetails = txDataManager.create(OrderDetails.class);

        orderDetails.setState(event.getType().toString());
        orderDetails.setValue(calculateValue(order));
        order.setDetails(orderDetails);

        txDataManager.save(orderDetails);
        txDataManager.save(order);
    }

    private BigDecimal calculateValue(Order order) {
        return order.getLines().stream()
                .map(orderLine -> orderLine.getProduct().getPrice().multiply(orderLine.getQuantity()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}