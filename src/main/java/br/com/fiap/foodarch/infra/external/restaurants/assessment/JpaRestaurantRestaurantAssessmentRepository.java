package br.com.fiap.foodarch.infra.external.restaurants.assessment;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.assessment.RestaurantAssessmentRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.assessment.RestaurantAssessment;
import br.com.fiap.foodarch.domain.exceptions.restaurants.assessment.AssessmentNotFoundException;
import br.com.fiap.foodarch.domain.exceptions.restaurants.RestaurantNotFound;
import br.com.fiap.foodarch.domain.exceptions.users.UserNotExistsException;
import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntity;
import br.com.fiap.foodarch.infra.external.users.UserEntity;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantAssessment;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantRepository;
import br.com.fiap.foodarch.infra.gateways.persistance.users.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Repository
public class JpaRestaurantRestaurantAssessmentRepository implements RestaurantAssessmentRepository {
    private final IRestaurantAssessment repository;
    private final RestaurantAssessmentEntityMapper mapper;
    private final IRestaurantRepository restaurantRepository;
    private final IUserRepository userRepository;

    @Autowired
    public JpaRestaurantRestaurantAssessmentRepository(
            IRestaurantAssessment repository,
            RestaurantAssessmentEntityMapper mapper,
            IRestaurantRepository restaurantRepository,
            IUserRepository userRepository
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }


    public RestaurantAssessment createRestaurantComment(RestaurantAssessment assessment) {

        restaurantRepository.findById(assessment.getRestaurantId()).orElseThrow(
                () -> new RestaurantNotFound("Restaurant not found.", HttpStatus.BAD_REQUEST)
        );

        userRepository.findById(assessment.getUserId()).orElseThrow(
                () -> new UserNotExistsException("User not found.", HttpStatus.BAD_REQUEST)
        );

        RestaurantEntity restaurantEntity = restaurantRepository.getReferenceById(assessment.getRestaurantId());

        UserEntity userEntity = userRepository.getReferenceById(assessment.getUserId());

        RestaurantAssessmentEntity restaurant = mapper.toEntity(assessment, restaurantEntity, userEntity);

        RestaurantAssessmentEntity toSave = repository.save(restaurant);

        return mapper.toDomain(toSave);
    }


    public Page<RestaurantAssessment> getRestaurantAllAssessments(Pageable pageable) {
        Page<RestaurantAssessmentEntity> listAssessments = this.repository.findAll(pageable);

        return listAssessments.map(mapper::toDomain);
    }

    public RestaurantAssessment getRestaurantAssessmentById(UUID id) {
        RestaurantAssessmentEntity restaurantAssessmentEntity = repository.findByAssessmentId(id);

        return restaurantAssessmentEntity != null ? mapper.toDomain(restaurantAssessmentEntity) : null;
    }

    public RestaurantAssessment updateRestaurantComment(RestaurantAssessment assessment) {

        RestaurantAssessmentEntity toUpdate = repository.findById(assessment.getId()).map(assessmentOld -> {
            assessmentOld.setComment(assessment.getComment());
            assessmentOld.set_like(assessment.isLike());
            assessmentOld.setStars(assessment.getStars());
            return repository.save(assessmentOld);
        }).orElseThrow(() -> new AssessmentNotFoundException("Assessment not found"));

       return mapper.toDomain(toUpdate);
    }

    public void deleteRestaurantComment(UUID id, UUID userId) {
        repository.deleteByIdAndUserId(id, userId);
    }
}
