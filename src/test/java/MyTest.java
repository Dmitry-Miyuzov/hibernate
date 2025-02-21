import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import ru.test.ryazan.dao.tasks.schema.tasks.DictionaryDao;
import ru.test.ryazan.dao.tasks.schema.tasks.entity.DictionaryEntity;
import ru.test.ryazan.repository._DatabaseRepository;

public class MyTest {


    @Test
    public void testik() {

        System.out.println();
        DictionaryDao dictionaryDao = _DatabaseRepository.TASKS_REPOSITORY().SCHEMA_DICTIONARIES
                .dictionaryDao;
        Session session = dictionaryDao.sessionFactory.openSession();
        DictionaryEntity dictionaryEntity = session.get(DictionaryEntity.class, 1);
        System.out.println();
    }
}
