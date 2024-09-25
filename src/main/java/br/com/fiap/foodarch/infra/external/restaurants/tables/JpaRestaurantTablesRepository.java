package br.com.fiap.foodarch.infra.external.restaurants.tables;

import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.restaurantTables.RestaurantTablesRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.tables.RestaurantTables;
import br.com.fiap.foodarch.infra.external.restaurants.RestaurantEntity;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantRepository;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantTablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class JpaRestaurantTablesRepository implements RestaurantTablesRepository {
    private final IRestaurantTablesRepository tablesRepository;
    private final IRestaurantRepository restaurantRepository;
    private final RestaurantTablesMapper tablesMapper;

    @Autowired
    public JpaRestaurantTablesRepository(IRestaurantTablesRepository tablesRepository, IRestaurantRepository restaurantRepository, RestaurantTablesMapper tablesMapper) {
        this.tablesRepository = tablesRepository;
        this.restaurantRepository = restaurantRepository;
        this.tablesMapper = tablesMapper;
    }

    @Override
    public RestaurantTables createRestaurantTables(UUID restaurantId, RestaurantTables restaurantTables) {
        RestaurantEntity restaurantRef = this.restaurantRepository.getReferenceById(restaurantId);
        RestaurantTablesEntity restaurantTablesEntity = tablesMapper.toEntity(restaurantTables, restaurantRef);
        RestaurantTablesEntity save = tablesRepository.save(restaurantTablesEntity);
        return tablesMapper.toDomain(save);
    }

    @Override
    public RestaurantTables updateRestaurantTables(RestaurantTables restaurantTables, UUID restaurantId) {
        RestaurantEntity restaurant = this.restaurantRepository.getReferenceById(restaurantId);
        RestaurantTablesEntity restaurantTablesEntityToUpdate = this.tablesRepository.findByRestaurantAndTableId(restaurant.getId(), restaurantTables.getId());

        if (restaurantTablesEntityToUpdate == null) {
            return null; // pode ser um throw de exceção
        }

        RestaurantTablesEntity restaurantTablesEntity = tablesMapper.toEntity(restaurantTables, restaurant);
        return tablesMapper.toDomain(tablesRepository.save(restaurantTablesEntity));
    }


    @Override
    public RestaurantTables findByRestaurantTableNumber(int tableNumber) {
        RestaurantTablesEntity restaurantTablesEntity = this.tablesRepository.findByTableNumber(tableNumber);
        if (restaurantTablesEntity == null) {
            return null;
        }

        return tablesMapper.toDomain(restaurantTablesEntity);
    }

    @Override
    public RestaurantTables findByRestaurantId(UUID restaurantTablesId, UUID restaurantId) {
        RestaurantTablesEntity restaurantTablesEntity = this.tablesRepository.findByRestaurantAndTableId(restaurantId, restaurantTablesId);
        if (restaurantTablesEntity == null) {
            return null;
        }

        return tablesMapper.toDomain(restaurantTablesEntity);
    }

    @Override
    public void deleteById(UUID id) {
        this.tablesRepository.deleteById(id);

    }

}
