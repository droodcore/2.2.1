package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      Car firstCar = new Car("Лада", 112);
      Car secondCar = new Car("Жигули", 6);
      Car thirdCar = new Car("Москвич", 408);

      userService.add(new User("User5", "Lastname5", "user5@mail.ru", firstCar));
      userService.add(new User("User6", "Lastname6", "user6@mail.ru", secondCar));
      userService.add(new User("User7", "Lastname7", "user7@mail.ru", thirdCar));
      userService.add(new User("User8", "Lastname8", "user8@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         if (user.getCar() != null) {
            System.out.println(user.getCar());
         } else {
            System.out.println("NO CAR");
         }
         System.out.println();
      }


      userService.userWithCar("Лада", 112).forEach(System.out::println);
      userService.userWithCar("Лада", 11).forEach(System.out::println);

      context.close();
   }
}
