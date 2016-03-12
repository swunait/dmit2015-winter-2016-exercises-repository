package chinook.eistier;

import java.util.List;

import javax.persistence.Query;

import chinook.entity.Genre;

public class GenreDao extends AbstractDao<Genre> {
	
	public GenreDao() {
		super(Genre.class);
	}
	
	public Genre findOne(int genreId) {
		return getEntityManager().find(Genre.class, genreId);
	}

	public List<Genre> findAllOrderByName() {
		List<Genre> genres = null;
		
		Query query = getEntityManager().createQuery(
			"SELECT g FROM Genre g ORDER BY g.name");
		genres = query.getResultList();
		
		return genres;
	}
}
