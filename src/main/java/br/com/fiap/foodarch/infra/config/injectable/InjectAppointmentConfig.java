package br.com.fiap.foodarch.infra.config.injectable;

import br.com.fiap.foodarch.application.controller.restaurants.appoiment.CreateAppointmentController;
import br.com.fiap.foodarch.application.controller.restaurants.appoiment.DeleteAppointmentController;
import br.com.fiap.foodarch.application.controller.restaurants.appoiment.GetAppointmentByIdController;
import br.com.fiap.foodarch.application.controller.restaurants.appoiment.UpdateAppointmentController;
import br.com.fiap.foodarch.application.gateways.interfaces.restaurants.appointment.RestaurantAppointmentRepository;
import br.com.fiap.foodarch.domain.usecases.restaurants.appointment.CreateAppointment;
import br.com.fiap.foodarch.domain.usecases.restaurants.appointment.DeleteAppointment;
import br.com.fiap.foodarch.domain.usecases.restaurants.appointment.GetAppointmentById;
import br.com.fiap.foodarch.domain.usecases.restaurants.appointment.UpdateAppointment;
import br.com.fiap.foodarch.infra.external.restaurants.appointment.RestaurantAppointmentMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InjectAppointmentConfig {

  @Bean
  public RestaurantAppointmentMapper restaurantAppointmentMapper() {
    return new RestaurantAppointmentMapper();
  }

  @Bean
  public CreateAppointment createAppointment(RestaurantAppointmentRepository restaurantAppointmentRepository) {
    return new CreateAppointment(restaurantAppointmentRepository);
  }

  @Bean
  public DeleteAppointment deleteAppointment(RestaurantAppointmentRepository restaurantAppointmentRepository) {
    return new DeleteAppointment(restaurantAppointmentRepository);
  }

  @Bean
  public UpdateAppointment updateAppointment(RestaurantAppointmentRepository restaurantAppointmentRepository) {
    return new UpdateAppointment(restaurantAppointmentRepository);
  }

  @Bean
  public GetAppointmentById getAppointmentById(RestaurantAppointmentRepository restaurantAppointmentRepository) {
    return new GetAppointmentById(restaurantAppointmentRepository);
  }




  @Bean
  public CreateAppointmentController createAppointmentController(CreateAppointment createAppointment) {
    return new CreateAppointmentController(createAppointment);
  }

  @Bean
  public DeleteAppointmentController deleteAppointmentController(DeleteAppointment deleteAppointment) {
    return new DeleteAppointmentController(deleteAppointment);
  }

  @Bean
  public GetAppointmentByIdController getAppointmentByIdController(GetAppointmentById getAppointmentById) {
    return new GetAppointmentByIdController(getAppointmentById);
  }

  @Bean
  public UpdateAppointmentController updateAppointmentController(UpdateAppointment updateAppointment) {
    return new UpdateAppointmentController(updateAppointment);
  }

}
