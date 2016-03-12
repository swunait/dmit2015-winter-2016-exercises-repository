package chinook.businesstier;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import chinook.eistier.GenreDao;
import chinook.entity.Genre;

@Stateless
public class GenreService {

	@Inject
	private GenreDao genreDao;
	
	public Genre findOne(int genreId) {
		return genreDao.findOne(genreId);
	}
	
	public List<Genre> findAllOrderByName() {
		return genreDao.findAllOrderByName();
	}
}
