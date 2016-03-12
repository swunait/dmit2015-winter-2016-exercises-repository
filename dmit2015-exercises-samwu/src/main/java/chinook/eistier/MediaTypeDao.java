package chinook.eistier;

import java.util.List;

import javax.persistence.Query;

import chinook.entity.MediaType;

public class MediaTypeDao extends AbstractDao<MediaType> {

	public MediaTypeDao() {
		super(MediaType.class);
	}
	
	public MediaType findOne(int mediaTypeId) {
		return getEntityManager().find(MediaType.class, mediaTypeId);
	}
	
	public List<MediaType> findAllOrderByName() {		
		List<MediaType> mediaTypes = null;
		
		Query query = getEntityManager().createQuery(
			"SELECT mt FROM MediaType mt ORDER BY mt.name");
		mediaTypes = query.getResultList();
		
		return mediaTypes;
	}
	
}
