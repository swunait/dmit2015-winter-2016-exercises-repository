package chinook.webtier;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import chinook.businesstier.ArtistService;
import chinook.entity.Artist;
import helper.JSFHelper;

@Named
@ViewScoped
public class ArtistController implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;
	
	@Inject
	private ArtistService artistService;
	
	// Fields for querying
	private String queryValue;
	private Artist querySingleResult;
	private List<Artist> queryResultList;		
	private int queryResultCount;

	// Properties for querying
	public String getQueryValue() {
		return queryValue;
	}
	public void setQueryValue(String queryValue) {
		this.queryValue = queryValue;
	}
	public Artist getQuerySingleResult() {
		return querySingleResult;
	}
	public void setQuerySingleResult(Artist querySingleResult) {
		this.querySingleResult = querySingleResult;
	}
	public List<Artist> getQueryResultList() {
		return queryResultList;
	}
	public void setQueryResultList(List<Artist> queryResultList) {
		this.queryResultList = queryResultList;
	}
	public int getQueryResultCount() {
		return queryResultCount;
	}
	public void setQueryResultCount(int queryResultCount) {
		this.queryResultCount = queryResultCount;
	}

	@PostConstruct
	public void init() {

	}
	
	public List<Artist> retreiveAllArtist() {
		return artistService.findAllOrderByName();
	}
	
	public void doSearchForSingleResult() {
		try {
			int artistId = Integer.parseInt( queryValue );
			querySingleResult = artistService.findOneByArtistId(artistId);
			if( querySingleResult != null ) {
				queryResultCount = 1;
				String message = String.format("Result Results \"%s\".", queryValue);
				JSFHelper.addInfoMessage(message);
			} else {
				queryResultCount = 0;
				String message = String.format("We have found %d items that match \"%s\".", queryResultCount, queryValue);
				JSFHelper.addWarningMessage(message);
			}
		} catch( NumberFormatException nfe ) {
			JSFHelper.addErrorMessage("Search value must be a number");
			logger.log(Level.INFO, nfe.getMessage());
		} catch( Exception e) {
			JSFHelper.addErrorMessage("An application exception has occurred.");
			logger.log(Level.INFO, e.getMessage());
		}
	}
	
	public void doSearchForResultList() {
		try {
			queryResultList = artistService.findAllByName(queryValue);
			if( queryResultList != null && queryResultList.size() > 0 ) {
				queryResultCount = queryResultList.size();
				if( queryResultCount == 1 ) {
					querySingleResult = queryResultList.get(0);
					queryResultList= null;
				} else {
					querySingleResult = null;
				}
				String message = String.format("Result Results \"%s\".", queryValue);
				JSFHelper.addInfoMessage(message);
			} else {
				queryResultCount = 0;
				querySingleResult = null;
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
		querySingleResult = null;
		queryResultList = null;
		queryResultCount = 0;
	}
	
	public void doSelectArtist(Artist selectedArtist) {
		querySingleResult = selectedArtist;
		queryResultList = null;
	}
	
	public void doUpdateArtist() {
		try {
			artistService.update(querySingleResult);
			JSFHelper.addInfoMessage("Artist was updated.");
		} catch( Exception e ) {
			logger.log(Level.WARNING, e.getMessage());
			JSFHelper.addErrorMessage("Artist was not updated.");
		}
	}

	public void doUpdateArtist(Artist selectedArtist) {
		try {
			artistService.update(selectedArtist);
			JSFHelper.addInfoMessage("Artist was updated.");
		} catch( Exception e ) {
			logger.log(Level.WARNING, e.getMessage());
			JSFHelper.addErrorMessage("Artist was not updated.");
		}
	}

	public void doDeleteArtist() {
		doDeleteArtist(querySingleResult);
	}
	
	public void doDeleteArtist(Artist selectedArtist) {
		try {
			artistService.delete(selectedArtist);
			JSFHelper.addInfoMessage("Artist was deleted.");
			selectedArtist = null;
			doSearchForResultList();
		} catch( Exception e ) {
			logger.log(Level.WARNING, e.getMessage());
			JSFHelper.addErrorMessage("Artist was not deleted.");
		}
	}
	
}
