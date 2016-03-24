package chinook.webtier;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import chinook.businesstier.AlbumService;
import chinook.businesstier.ArtistService;
import chinook.entity.Album;
import chinook.entity.Artist;
import helper.JSFHelper;

@Named
@ViewScoped
public class ModifyAlbumController implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private ArtistService artistService;
	
	@Inject
	private AlbumService albumService;
	
	@Inject
	private Logger logger;
	
	// Fields for querying
	private String queryValue;
	private List<Album> queryResultList;		
	private int queryResultCount;

	
	public String getQueryValue() {
		return queryValue;
	}
	public void setQueryValue(String queryValue) {
		this.queryValue = queryValue;
	}
	public List<Album> getQueryResultList() {
		return queryResultList;
	}
	public int getQueryResultCount() {
		return queryResultCount;
	}
	public List<Artist> getAllArtist() {
		return artistService.findAllOrderByName();
	}
		
	public void doSearchForResultList() {
		try {
			queryResultList = albumService.findAllByTitle(queryValue);
			if( queryResultList != null && queryResultList.size() > 0 ) {
				queryResultCount = queryResultList.size();
				String message = String.format("Result Results \"%s\".", queryValue);
				JSFHelper.addInfoMessage(message);
			} else {
				queryResultCount = 0;
				queryResultList = null;
				String message = String.format("We have found %d items that match \"%s\".", queryResultCount, queryValue);
				JSFHelper.addWarningMessage(message);
			}
		} catch( Exception e) {
			JSFHelper.addErrorMessage("An application exception has occurred.");
			logger.log(Level.INFO, e.getMessage());
		}
	}
	
	public void doCancel() {
		queryValue = "";
		queryResultList = null;
		queryResultCount = 0;
	}
	
	public void doUpdateAlbum(Album selectedAlbum) {
		try {
			albumService.update(selectedAlbum);
			JSFHelper.addInfoMessage("Album was updated.");
		} catch( Exception e ) {
			logger.log(Level.WARNING, e.getMessage());
			JSFHelper.addErrorMessage("Album was not updated.");
		}
	}

}
