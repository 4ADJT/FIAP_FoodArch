package br.com.fiap.foodarch.infra.external.restaurants.assessment;

import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntity;
import br.com.fiap.foodarch.infra.external.users.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "assessment")
public class RestaurantAssessmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
    private UserEntity user;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false, unique = true)
    private RestaurantEntity restaurant;

    @NotEmpty
    private String comment;

    private boolean is_like;

    @PositiveOrZero
    private int stars;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    public RestaurantAssessmentEntity(final UserEntity user, final RestaurantEntity restaurant, final String comment, final boolean is_like, final int stars) {
        this.user = user;
        this.restaurant = restaurant;
        this.comment = comment;
        this.is_like = is_like;
        this.stars = stars;
    }
}
