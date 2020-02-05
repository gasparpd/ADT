package ejemplo01;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;

public class App {
    public static void main(String [] args) {
        //save();
        //leerProfesor();
        leerProfesorLoad();
    }

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

    public static void leerProfesor() {
        //Obtenemos el SessionFactory

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        //Abrimos la sesión mediante el SessionFactory

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Profesor profesor = (Profesor) session.get(Profesor.class, 102);
            System.out.println("Profesor: " + profesor.getNombre());
        }catch (NullPointerException e){
            System.out.println("El identificador no ha sido encontrado en la base de datos.");
        }

        tx.commit();
        session.close();
        sessionFactory.close();
    }

    public static void leerProfesorLoad() {
        //Obtenemos el SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //Abrimos la sesión mediante el SessionFactory
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        Profesor profesor = new Profesor();
        try {
            profesor = (Profesor) session.load(Profesor.class, 102);
            System.out.println("Profesor: " + profesor.getNombre());
        } catch (org.hibernate.ObjectNotFoundException e) {
            System.out.println("El valor clave no se ha encontrado en la base de datos.");
        }

        tx.commit();
        session.close();
        sessionFactory.close();
    }
}
