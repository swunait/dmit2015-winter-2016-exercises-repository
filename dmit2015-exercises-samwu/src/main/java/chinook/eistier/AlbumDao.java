package chinook.eistier;

import java.util.List;

import chinook.entity.Album;
import chinook.entity.Artist;

public class AlbumDao extends AbstractDao<Album> {
	private static final long serialVersionUID = 1L;

	public AlbumDao() {
		super(Album.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Album> findAllByArtist(Artist artist) {
		return getEntityManager()
				.createQuery("FROM Album WHERE artist = :artistValue")
				.setParameter("artistValue", "%" + artist + "%")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Album> findAllByTitle(String title) {
		return getEntityManager()
				.createQuery("FROM Album WHERE title LIKE :titleValue")
				.setParameter("titleValue", "%" + title + "%")
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Album> findAllOrderByTitle() {
		return getEntityManager()
				.createQuery("SELECT a FROM Album a ORDER BY a.title")
				.getResultList();
	}
}
