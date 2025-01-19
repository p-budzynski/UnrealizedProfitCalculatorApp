package pl.kurs.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceUnit;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import pl.kurs.entity.Investment;

@Repository
//@Profile("db")
public class InvestmentDaoImpl implements InvestmentDao {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void save(Investment investment) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(investment);
        entityTransaction.commit();
        entityManager.close();
    }

    @Override
    public Investment get(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Investment investment = entityManager.find(Investment.class, id);
        entityManager.close();
        return investment;
    }

}
