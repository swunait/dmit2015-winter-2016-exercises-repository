package chinook.businesstier;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import chinook.eistier.ArtistDao;
import chinook.entity.Artist;

@Stateless
public class ArtistService {

	@Inject
	private ArtistDao artistDao;
	
	public List<Artist> findAllOrderByName() {
		return artistDao.findAllOrderByName();
	}

	public Artist findOneByArtistId(int artistId) {
		return artistDao.findOneByArtistId(artistId);
	}

	public List<Artist> findAllByName(String name) {
		return artistDao.findAllByName(name);
	}

	public void create(Artist artist) {
		artistDao.persist(artist);
	}
	
	public void update(Artist artist) {
		artistDao.edit(artist);
	}
	
	public void delete(Artist artist) {
		artistDao.remove(artist);
	}
}
