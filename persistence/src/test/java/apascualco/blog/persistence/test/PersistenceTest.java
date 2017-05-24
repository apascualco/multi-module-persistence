package apascualco.blog.persistence.test;

import apascualco.blog.persistence.model.Rol;
import apascualco.blog.persistence.model.User;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/apascualco/blog/test/spring-persistence-test.xml"})
public class PersistenceTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @PersistenceContext
    private EntityManager entityManager;

    private TypedQuery<User> typedQuery;

    private <E> CriteriaQuery<?> getCriteriaRoot(Class<?> entity) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(entity);
        Root<?> rootEntry = criteriaQuery.from(entity);
        return criteriaQuery.select(rootEntry);
    }

    @org.junit.Test
    public void UsersTest(){
        CriteriaQuery<User> select = (CriteriaQuery<User>) this.getCriteriaRoot(User.class);
        TypedQuery<User> typedQuery = entityManager.createQuery(select);
        assertTrue("El número de registro no coinciden", typedQuery.getResultList().size() == 2);
    }

    @org.junit.Test
    public void RolesTest(){
        CriteriaQuery<Rol> select = (CriteriaQuery<Rol>) this.getCriteriaRoot(Rol.class);
        TypedQuery<Rol> typedQuery = entityManager.createQuery(select);
        assertTrue("El número de registro no coinciden", typedQuery.getResultList().size() == 3);
    }

    @org.junit.Test
    @Transactional(propagation = Propagation.NEVER)
    public void UserLazyRoles(){
        CriteriaQuery<User> select = (CriteriaQuery<User>) this.getCriteriaRoot(User.class);
        TypedQuery<User> typedQuery = entityManager.createQuery(select);
        List<User> userList = typedQuery.getResultList();
        for(User user: userList) {
            assertNotNull("El usuario " + user.getUsername() + " no tienes roles", user.getRoles());
        }
    }
}
