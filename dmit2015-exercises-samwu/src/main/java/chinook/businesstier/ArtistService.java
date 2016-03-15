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

}
