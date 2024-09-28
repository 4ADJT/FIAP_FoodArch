package br.com.fiap.foodarch.infra.config.injectable;

import br.com.fiap.foodarch.application.controller.restaurants.tables.CreateRestaurantTablesController;
import br.com.fiap.foodarch.application.controller.restaurants.tables.GetRestauranttablesController;
import br.com.fiap.foodarch.application.controller.restaurants.tables.UpdateRestaurantTableController;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.RestaurantRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.restaurantTables.RestaurantTablesRepository;
import br.com.fiap.foodarch.application.gateways.interfaces.users.UserRepository;
import br.com.fiap.foodarch.domain.entities.restaurants.CreateRestaurantFactory;
import br.com.fiap.foodarch.domain.entities.restaurants.tables.CreateRestaurantTablesFactory;
import br.com.fiap.foodarch.domain.entities.restaurants.tables.UpdateRestaurantTableFactory;
import br.com.fiap.foodarch.domain.usecases.restaurants.tables.CreateRestaurantTables;
import br.com.fiap.foodarch.domain.usecases.restaurants.tables.GetRestaurantTablesById;
import br.com.fiap.foodarch.domain.usecases.restaurants.tables.UpdateRestaurantTable;
import br.com.fiap.foodarch.infra.external.restaurants.tables.JpaRestaurantTablesRepository;
import br.com.fiap.foodarch.infra.external.restaurants.tables.RestaurantTablesMapper;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantRepository;
import br.com.fiap.foodarch.infra.gateways.persistance.restaurants.IRestaurantTablesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InjectRestaurantTablesConfig {

    @Bean
    public RestaurantTablesMapper restaurantTablesMapper() {
        return new RestaurantTablesMapper();
    }

    @Bean
    public CreateRestaurantTablesFactory createRestaurantTablesFactory() {
        return new CreateRestaurantTablesFactory();
    }

    @Bean
    public JpaRestaurantTablesRepository jpaRestaurantTablesRepository(
        IRestaurantTablesRepository tablesRepository,
        IRestaurantRepository restaurantRepository,
        RestaurantTablesMapper tablesMapper
    ) {
        return new JpaRestaurantTablesRepository(tablesRepository, restaurantRepository, tablesMapper);
    }

    @Bean
    public CreateRestaurantTables restaurantTables(
            RestaurantTablesRepository tablesRepository,
            UserRepository userRepository,
            RestaurantRepository restaurantRepository,
            CreateRestaurantTablesFactory createRestaurantTablesFactory
    ) {
        return new CreateRestaurantTables(userRepository, restaurantRepository, tablesRepository, createRestaurantTablesFactory);
    }

    @Bean
    public GetRestaurantTablesById getRestaurantTablesById(RestaurantTablesRepository restaurantTablesRepository) {
        return new GetRestaurantTablesById(restaurantTablesRepository);
    }

    @Bean
    public GetRestauranttablesController getRestauranttablesController(GetRestaurantTablesById getRestaurantTablesById) {
        return new GetRestauranttablesController(getRestaurantTablesById);
    }

    @Bean
    public UpdateRestaurantTableFactory restaurantTableFactory() {
        return new UpdateRestaurantTableFactory();
    }

    @Bean
    public UpdateRestaurantTable updateRestaurantTable(
            RestaurantTablesRepository tablesRepository,
            UserRepository userRepository,
            RestaurantRepository restaurantRepository,
            UpdateRestaurantTableFactory updateRestaurantTableFactory
    ) {
        return new UpdateRestaurantTable(tablesRepository, userRepository, restaurantRepository, updateRestaurantTableFactory);
    }

    @Bean
    public UpdateRestaurantTableController updateRestaurantTableController(UpdateRestaurantTable updateRestaurantTable) {
        return new UpdateRestaurantTableController(updateRestaurantTable);
    }

    @Bean
    public CreateRestaurantTablesController createRestaurantTablesController(CreateRestaurantTables createRestaurantTables) {
        return new CreateRestaurantTablesController(createRestaurantTables);
    }
}
