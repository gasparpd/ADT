package ejemplo01;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        //guardarProfesor();
        //leerProfesor();
        //leerProfesorLoad();
        //modificarProfesor();
        //borrarProfesor();
        //guardarOActualizarProfesor();
        queryClass();
    }

    private static void guardarProfesor() {
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
        } catch (PersistenceException e) {
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
            Profesor profesor = session.get(Profesor.class, 102);
            System.out.println("Profesor: " + profesor.getNombre());
        } catch (NullPointerException e) {
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

        try {
            Profesor profesor = session.load(Profesor.class, 102);
            System.out.println("Profesor: " + profesor.getNombre());
        } catch (org.hibernate.ObjectNotFoundException e) {
            System.out.println("El valor clave no se ha encontrado en la base de datos.");
        }

        tx.commit();
        session.close();
        sessionFactory.close();
    }

    public static void modificarProfesor() {
        //Obtenemos el SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //Abrimos la sesión mediante el SessionFactory
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        try {
            Profesor profesor = session.get(Profesor.class, 101);
            System.out.println("Profesor: " + profesor.getNombre());
            System.out.println("Profesor: " + profesor.getNombre());
            profesor.setNombre("Pedro");
            session.update(profesor);
        } catch (NullPointerException e) {
            System.out.println("El identificador no ha sido encontrado en la base de datos.");
        }

        tx.commit();
        session.close();
        sessionFactory.close();
    }

    public static void borrarProfesor() {
        //Obtenemos el SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //Abrimos la sesión mediante el SessionFactory
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        try {
            Profesor profesor = session.get(Profesor.class, 101);
            System.out.println("Profesor:" + profesor.getNombre());
            session.delete(profesor);
        } catch (NullPointerException e) {
            System.out.println("ID no encontrado en la base de datos.");
        }
        tx.commit();
        session.close();
        sessionFactory.close();
    }

    public static void guardarOActualizarProfesor() {
        //Obtenemos el SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //Abrimos la sesión mediante el SessionFactory
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            //Creamos el objeto
            Profesor profesor = new Profesor(102, "Albeto", "Suárez", "Pérez");
            session.saveOrUpdate(profesor);
        } catch (NullPointerException e) {
            System.out.println("ID no encontrado en la base de datos.");
        }

        tx.commit();
        session.close();
        sessionFactory.close();
    }

    public static void queryClass() {
        //Obtenemos el SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        //Abrimos la sesión mediante el SessionFactory
        Session session = sessionFactory.openSession();

        // CONSULTA CON NOMBRE
        Query query = session.getNamedQuery("findAllProfesores");

        // EJEMPLO CLASE Query
        /*Query query = session.createQuery("SELECT p FROM Profesor p");*/
        List<Profesor> profesores = query.list();
        for (Profesor profesor : profesores) {
            System.out.println(profesor.getNombre());
        }

        session.close();
        sessionFactory.close();
    }
}
