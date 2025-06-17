package chinook.businesstier;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import chinook.eistier.MediaTypeDao;
import chinook.entity.MediaType;

@Stateless
public class MediaTypeService {

	@Inject
	private MediaTypeDao mediaTypeDao;
	
	public MediaType findOne(int mediaTypeId) {
		return mediaTypeDao.findOne(mediaTypeId);
	}
	
	public List<MediaType> findAllOrderByName() {		
		return mediaTypeDao.findAllOrderByName();
	}
}
