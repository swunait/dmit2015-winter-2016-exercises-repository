package chinook.businesstier;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

import chinook.eistier.AlbumDao;
import chinook.entity.Album;
import chinook.entity.Artist;

@Stateless
public class AlbumService {
	
	@Resource
	private SessionContext context;
	
	public void updateAllAlbum(List<Album> albums) {
		for(Album album : albums) {
			albumDao.edit(album);
			if( album.getTitle().length() < 3) {
				context.setRollbackOnly();
			}
		}
	}
	
	@Inject
	private AlbumDao albumDao;

	public List<Album> findAll() {
		return albumDao.findAll();
	}
	
	public List<Album> findAllOrderByTitle() {
		return albumDao.findAllOrderByTitle();
	}
	
	public List<Album> findAllByArtist(Artist artist) {
		return albumDao.findAllByArtist(artist);
	}

	public List<Album> findAllByTitle(String title) {
		return albumDao.findAllByTitle(title);
	}

	public Album findOne(int albumId) {
		return albumDao.find(albumId);
	}

	public void create(Album album) {
		albumDao.persist(album);
	}
	
	public void update(Album album) {
		albumDao.edit(album);
	}
	
	public void delete(Album album) throws Exception {
		try {
			albumDao.remove(album);
		} catch (PersistenceException pe) {
			throw new Exception("This record is being referenced from another table and cannot be deleted.");
		} catch(Exception e) {
			throw new Exception(":( Failed to delete this record.");
		}
	}

}
