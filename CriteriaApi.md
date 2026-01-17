Criteria API в Hibernate предоставляет программный способ создания запросов к базе данных, используя объектно-ориентированный подход. Вместо написания запросов на языке HQL (Hibernate Query Language) или SQL, вы строите запросы с использованием API и типобезопасных конструкций.

Основные компоненты Criteria API:
Session.createCriteria(Class) (Устаревший в Hibernate 5):

Метод создает объект Criteria для указанного класса сущности, позволяя дальше строить запрос.

CriteriaBuilder (Введено в JPA 2.0 и Hibernate 5):

CriteriaBuilder является фабрикой для создания объектов критериев (CriteriaQuery, Predicate, Order, и т.д.). С его помощью строятся различные части запроса.

CriteriaQuery (Введено в JPA 2.0 и Hibernate 5):

CriteriaQuery представляет собой корневой объект для построения критериев запроса. Он содержит информацию о том, какие сущности мы ищем и какие условия накладываются на запрос.

Root (Введено в JPA 2.0 и Hibernate 5):

Root является корневым псевдо-объектом, связанным с классом сущности. Он используется для указания корневой сущности, относительно которой строится запрос.

Predicate (Введено в JPA 2.0 и Hibernate 5):

Predicate представляет условие (или несколько условий), которое используется для фильтрации результатов запроса.

```java
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.query.criteria.internal.CriteriaQueryImpl;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class CriteriaApiExample {
    public static void main(String[] args) {
        // Настройка Hibernate
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Создание сессии
        try (Session session = sessionFactory.openSession()) {
            // Начало транзакции
            Transaction transaction = session.beginTransaction();

            // Создание CriteriaBuilder и CriteriaQuery
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);

            // Создание корневого элемента и указание сущности для запроса
            Root<Product> root = criteriaQuery.from(Product.class);

            // Создание предиката (условия) для запроса
            Predicate predicate = criteriaBuilder.equal(root.get("name"), "Laptop");

            // Добавление предиката к CriteriaQuery
            criteriaQuery.where(predicate);

            // Выполнение запроса
            List<Product> products = session.createQuery(criteriaQuery).getResultList();

            // Вывод результатов
            for (Product product : products) {
                System.out.println(product.getName() + " - " + product.getPrice());
            }

            // Фиксация транзакции
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Закрытие фабрики сессий при завершении приложения
            sessionFactory.close();
        }
    }
}
```