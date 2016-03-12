package chinook.businesstier;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

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
}
