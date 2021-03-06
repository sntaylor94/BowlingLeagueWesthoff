package contoller;


import java.util.List;

//Sheri Westhoff
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


import model.Team;

public class TeamHelper {

	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("BowlingLeagueWesthoff");

	public void insertItem(Team toAdd) {
		// TODO Auto-generated method stub

		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(toAdd);
		em.getTransaction().commit();
		em.close();

	}
	
	public List<Team> showAllTeams() {
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<Team> allResults = em.createQuery("select t from Team t", Team.class);
		List<Team> allTeams = allResults.getResultList();
		em.close();
		return allTeams;
	}
	
	public Team findTeamByName(String teamName) {
		try {
			EntityManager em = emfactory.createEntityManager();
			TypedQuery<Team> findTeam = em.createQuery("select t from Team t where t.teamName = :selectedName", Team.class);
			findTeam.setParameter("selectedName", teamName);
			findTeam.setMaxResults(1);
			Team foundTeam = findTeam.getSingleResult(); 
			em.close();
			return foundTeam;
		} catch(NoResultException e){
			return null;
		}
	}
}
