package chinook.eistier;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.inject.Inject;
import jakarta.persistence.Query;

import chinook.entity.Artist;

public class ArtistDao extends AbstractDao<Artist> {
	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;
	
	public ArtistDao() {
		super(Artist.class);
	}
	
	public Artist findOneByArtistId(int artistId) {
		Artist singleArtist = null;
		
		Query query = getEntityManager().createQuery("SELECT a FROM Artist a WHERE a.artistId = :artistIdValue");
		query.setParameter("artistIdValue", artistId);
		try {
			singleArtist = (Artist) query.getSingleResult();
		} catch ( Exception e ) {
			singleArtist = null;
			logger.log(Level.INFO, e.getMessage());
		}
		
		return singleArtist;
	}
	
	@SuppressWarnings("unchecked")
	public List<Artist> findAllOrderByName() {
		return getEntityManager().createQuery("FROM Artist a ORDER BY a.name").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Artist> findAllByName(String name) {
		List<Artist> resultList; 
		
		Query query = getEntityManager().createQuery("SELECT a FROM Artist a WHERE a.name LIKE :nameValue ORDER BY a.name ");
		query.setParameter("nameValue", "%" + name + "%");
		try {
			resultList = query.getResultList();
		} catch( Exception e ) {
			resultList = null;
			logger.log(Level.INFO, e.getMessage());
		}
		
		return resultList;
	}

}
