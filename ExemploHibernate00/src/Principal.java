
import bean.Sistema;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * @author Logan Michel
 */
public class Principal {

    public static void main(String[] args) {

        Configuration conf = new Configuration();
        StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        SessionFactory factory = new Configuration().buildSessionFactory(sr);
        
        Transaction transaction = null;
        try {
            Session session = factory.openSession();
            transaction = session.getTransaction();
            transaction.begin();
            
            //Aqui ele salva
            Sistema sistemaDoKleiton = new Sistema();
            sistemaDoKleiton.setNome("CCleitom");
            session.save(sistemaDoKleiton);
            
            //Aqui ele altera
            /*Sistema sistemaDoKleiton = new Sistema();
            sistemaDoKleiton.setNome("Cleitom");
            sistemaDoKleiton.setId(1);
            session.update(sistemaDoKleiton);*/
            
            //Aqui ele Lista
            /*List resultados = session.createQuery("FROM Sistema").list();
            for(Sistema sistema : (List<Sistema>)resultados){
                System.out.println(sistema.getNome());
            }*/
            
            //Aqui ele deleta
            /*Sistema sistema = new Sistema();
            sistema.setId(1);
            session.remove(sistema);*/
            
            //INSERT NO BD
            transaction.commit();
            
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }finally{
            factory.close();
        }

    }

}
