package chinook.eistier;

import chinook.entity.Album;
import chinook.entity.Genre;
import chinook.entity.MediaType;
import chinook.entity.Track;
import jakarta.inject.Inject;
import jakarta.persistence.Query;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrackDao extends AbstractDao<Track> {
	private static final long serialVersionUID = -5735611737033280696L;
	
	@Inject
	private Logger logger;

	public TrackDao() {
		super(Track.class);
	}

	public Track findOneByTrackId(int trackId) {
		Track track = null;
		
		Query query = getEntityManager().createQuery(
			"SELECT t FROM Track t WHERE t.trackId = :trackIdValue");
		query.setParameter("trackIdValue", trackId);
		try {
			track = (Track) query.getSingleResult();
		} catch ( Exception e ) {
			track = null;
			logger.log(Level.INFO, e.getMessage());
		}
		
		return track;
	}
	
	public List<Track> findAllByGenre(Genre genre) {
		List<Track> tracks;
		
		Query query = getEntityManager().createQuery(
			"SELECT t FROM Track t WHERE t.genre = :genreValue");
		query.setParameter("genreValue", genre);
		try {
			tracks = query.getResultList();
		} catch( Exception e ) {
			tracks = null;
			logger.log(Level.INFO, e.getMessage());
		}
		
		return tracks;
	}
	
	public List<Track> findAllByMediaType(MediaType mediaType) {
		List<Track> tracks;
		
		Query query = getEntityManager().createQuery(
			"SELECT t FROM Track t WHERE t.mediaType = :mediaTypeValue");
		query.setParameter("mediaTypeValue", mediaType);
		try {
			tracks = query.getResultList();
		} catch( Exception e ) {
			tracks = null;
			logger.log(Level.INFO, e.getMessage());
		}
		
		return tracks;
	}
	
	public List<Track> findAllByAlbum(Album album) {
		List<Track> tracks;
		
		Query query = getEntityManager().createQuery(
			"SELECT t FROM Track t WHERE t.album = :albumValue");
		query.setParameter("albumValue", album);
		try {
			tracks = query.getResultList();
		} catch( Exception e ) {
			tracks = null;
			logger.log(Level.INFO, e.getMessage());
		}
		
		return tracks;
	}
	
	
	public List<Track> findAllByName(String name) {
		List<Track> tracks;
		
		Query query = getEntityManager().createQuery(
			"SELECT t FROM Track t WHERE t.name LIKE :nameValue");
		query.setParameter("nameValue", "%" + name + "%");
		try {
			tracks = query.getResultList();
		} catch( Exception e ) {
			tracks = null;
			logger.log(Level.INFO, e.getMessage());
		}
		
		return tracks;
	}
	
	public List<Track> findAllByComposer(String composer) {
		List<Track> tracks;
		
		Query query = getEntityManager().createQuery(
			"SELECT t FROM Track t WHERE t.composer LIKE :composerValue");
		query.setParameter("composerValue", "%" + composer + "%");
		try {
			tracks = query.getResultList();
		} catch( Exception e ) {
			tracks = null;
			logger.log(Level.INFO, e.getMessage());
		}
		
		return tracks;
	}
	
	
	
}
