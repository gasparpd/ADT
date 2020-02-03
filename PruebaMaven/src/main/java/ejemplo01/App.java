package ejemplo01;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;

public class App {
    private static void save() {
        try {
            //Obtenemos el SessionFactory

            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            //Abrimos la sesión mediante el SessionFactory
            Session session = sessionFactory.openSession();

            //Creamos el objeto
            Profesor profesor = new Profesor(101, "Juan", "Perez", "García");
            Transaction tx = session.beginTransaction();

            session.save(profesor);//Aquí guardamos el objeto en la base de datos.

            tx.commit();
            session.close();
            sessionFactory.close();
        } catch (PersistenceException e){
            System.out.println("Clave primaria duplicada.");
        }
    }

    public static void main(String [] args) {
        save();
    }
}
