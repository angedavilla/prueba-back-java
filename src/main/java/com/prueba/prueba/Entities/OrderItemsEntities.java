package com.prueba.prueba.Entities;

import com.prueba.prueba.Listener.AuditEntityListener;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class, AuditEntityListener.class})
@EqualsAndHashCode(exclude = "order")
public class OrderItemsEntities extends AuditLogEntities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntities order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductsEntities product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    public void setId(Long id) { this.id = id; }

    public OrderEntities getOrder() { return order; }
    public void setOrder(OrderEntities order) { this.order = order; }

    public ProductsEntities getProduct() { return product; }
    public void setProduct(ProductsEntities product) { this.product = product; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

}
