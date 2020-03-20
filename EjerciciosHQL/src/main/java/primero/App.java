package primero;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		//consultaBorrar();
		consultaSencilla();
	}

	public static void consulta() {
		//Obtenemos el SessionFactory
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		//Abrimos la sesión mediante el SessionFactory
		Session session = sessionFactory.openSession();

		Query<Equipos> query = session.createQuery("SELECT e FROM Equipos e", primero.Equipos.class);
		List<Equipos> equipos = query.list();
		for (Equipos equipo : equipos) {
			System.out.println(equipo.getNombre()

			);
		}

		session.close();
		sessionFactory.close();
	}

	public static void consultaSencilla() {
		//Obtenemos el SessionFactory
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		//Abrimos la sesión mediante el SessionFactory
		Session session = sessionFactory.openSession();

		Query<Equipos> query = session.createQuery("SELECT e FROM Equipos e where e.nombre like 'D%' order by e.n"
				+ "ombre", primero.Equipos.class);
		List<Equipos> equipos = query.list();
		for (Equipos equipo : equipos) {
			System.out.println(equipo.getNombre());
		}

		session.close();
		sessionFactory.close();
	}


	public static void consultasMayuscula() {
		//Obtenemos el SessionFactory
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		//Abrimos la sesión mediante el SessionFactory
		Session session = sessionFactory.openSession();

		Object obj = session.createQuery("SELECT e.nombre FROM Equipos e WHERE nombre='Dallas Mavericks'").uniqueResult();
		System.out.println(obj);

		session.close();
		sessionFactory.close();
	}

	public static void consultaFiltrar() {
		//Obtenemos el SessionFactory
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		//Abrimos la sesión mediante el SessionFactory
		Session session = sessionFactory.openSession();

		Query<Equipos> query = session.createQuery("SELECT e FROM Equipos e WHERE ciudad like 'C%'", primero.Equipos.class);
		List<Equipos> equipos = query.list();
		for (Equipos equipo : equipos) {
			System.out.println(equipo.getNombre());
		}

		session.close();
		sessionFactory.close();
	}

	public static void consultaBorrar() {
		//Obtenemos el SessionFactory
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		//Abrimos la sesión mediante el SessionFactory
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("delete Equipos e where e.division = :division");
		query.setParameter("division", "Atlántico");
		int numBorrados = query.executeUpdate();
		System.out.println(numBorrados);
		tx.commit();
		session.close();
		sessionFactory.close();
	}

	public static void consultaBorrar2() {
		//Obtenemos el SessionFactory
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		//Abrimos la sesión mediante el SessionFactory
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("delete Equipos e where e.division = 'Pacífico'");
		//query.setParameter("division", "Atlántico");
		int numBorrados = query.executeUpdate();
		System.out.println(numBorrados);
		tx.commit();
		session.close();
		sessionFactory.close();
	}

	public static void consultaActualizar() {
		//Obtenemos el SessionFactory
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		//Abrimos la sesión mediante el SessionFactory
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("update Equipos e set e.division='Pacífico' where division='Central'");
		//query.setParameter("division", "Atlántico");
		int numBorrados = query.executeUpdate();
		System.out.println(numBorrados);
		tx.commit();
		session.close();
		sessionFactory.close();
	}

	public static void consultaInsertar() {
		//Obtenemos el SessionFactory
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		//Abrimos la sesión mediante el SessionFactory
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("insert into Equipos e set e.division='Pacífico' where division='Central'");
		//query.setParameter("division", "Atlántico");
		int numBorrados = query.executeUpdate();
		System.out.println(numBorrados);
		tx.commit();
		session.close();
		sessionFactory.close();
	}
}
