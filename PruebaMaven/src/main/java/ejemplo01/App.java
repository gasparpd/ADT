package ejemplo01;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class App {
    private static void save() {
        //Obtenemos el SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //Abrimos la sesión mediante el SessionFactory
        Session session = sessionFactory.openSession();

        //Creamos el objeto
        Profesor profesor = new Profesor(101, "Juan","Perez","García");
        Transaction tx = session.beginTransaction();

        session.save(profesor);//Aquí guardamos el objeto en la base de datos.

        tx.commit();
        session.close();
        sessionFactory.close();
    }

    public static void main(String [] args) {
        save();
    }
}
