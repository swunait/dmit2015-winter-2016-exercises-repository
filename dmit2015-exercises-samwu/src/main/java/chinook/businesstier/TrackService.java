package chinook.businesstier;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

import chinook.eistier.TrackDao;
import chinook.entity.Album;
import chinook.entity.Genre;
import chinook.entity.MediaType;
import chinook.entity.Track;

@Stateless
public class TrackService {

	@Inject
	private TrackDao trackDao;
	
	public Track findOneByTrackId(int trackId) {
		return trackDao.findOneByTrackId(trackId);
	}
	
	public List<Track> findAllByGenre(Genre genre) {
		return trackDao.findAllByGenre(genre);
	}
	
	public List<Track> findAllByMediaType(MediaType mediaType) {
		return trackDao.findAllByMediaType(mediaType);
	}
	
	public List<Track> findAllByAlbum(Album album) {
		return trackDao.findAllByAlbum(album);
	}
	
	public List<Track> findAllByName(String name) {
		return trackDao.findAllByName(name);
	}
	
	public List<Track> findAllByComposer(String composer) {
		return trackDao.findAllByComposer(composer);
	}
	
	public void create(Track track) {
		trackDao.persist(track);
	}
	
	public void update(Track track) {
		trackDao.edit(track);
	}
	
	public void delete(Track track) throws Exception {
		try {
			trackDao.remove(track);
		} catch (PersistenceException pe) {
			throw new Exception("This record is being referenced from another table and cannot be deleted.");
		} catch(Exception e) {
			throw new Exception(":( Failed to delete this record.");
		}
	}
}
