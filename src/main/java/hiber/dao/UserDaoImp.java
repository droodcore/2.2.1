package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {


   private SessionFactory sessionFactory;

   @Autowired
   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<User> listUsers() {
      return  sessionFactory.getCurrentSession().createQuery("from User").getResultList();
   }

   @Override
   public List<User> userWithCar(String model, int series) {
      return sessionFactory.getCurrentSession()
              .createQuery("FROM User WHERE car.model = :model AND car.series = :series")
              .setParameter("model", model).setParameter("series", series)
              .getResultList();
   }

}
