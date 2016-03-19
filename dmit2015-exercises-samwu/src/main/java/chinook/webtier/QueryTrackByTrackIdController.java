package chinook.webtier;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import chinook.businesstier.GenreService;
import chinook.businesstier.MediaTypeService;
import chinook.businesstier.TrackService;
import chinook.entity.Genre;
import chinook.entity.MediaType;
import chinook.entity.Track;
import helper.JSFHelper;

@Named
@ViewScoped
public class QueryTrackByTrackIdController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;
	
	private List<Track> searchResults;	// getter

	@Inject
	private TrackService trackService;
	
	@Inject
	private MediaTypeService mediaTypeService;
	
	@Inject
	private GenreService genreService;
	
	public List<MediaType> getAllMediaType() {
		return mediaTypeService.findAllOrderByName();
	}
	
	public List<Genre> getAllGenre() {
		return genreService.findAllOrderByName();
	}
	
	private int selectedMediaTypeId;	// getter/setter
	private int selectedGenreId;		// getter/setter
	
	private int searchValue;	// getter/setter
	
	private Track searchResult;	// getter

	public int getSelectedMediaTypeId() {
		return selectedMediaTypeId;
	}

	public void setSelectedMediaTypeId(int selectedMediaTypeId) {
		this.selectedMediaTypeId = selectedMediaTypeId;
	}

	public int getSelectedGenreId() {
		return selectedGenreId;
	}

	public void setSelectedGenreId(int selectedGenreId) {
		this.selectedGenreId = selectedGenreId;
	}

	public int getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(int searchValue) {
		this.searchValue = searchValue;
	}

	public Track getSearchResult() {
		return searchResult;
	}
	
	public List<Track> getSearchResults() {
		return searchResults;
	}

	public void doSearch() {
		searchResult = trackService.findOneByTrackId(searchValue);			
		if( searchResult == null ) {
			selectedMediaTypeId = 0;
			selectedGenreId = 0;
			JSFHelper.addInfoMessage("No results returned");
		} else {
			selectedMediaTypeId = searchResult.getMediaType().getMediaTypeId();
			selectedGenreId = searchResult.getGenre().getGenreId();
			JSFHelper.addInfoMessage("One result returned");
		}
	}
	
	public void doSearchByMediaType() {
		searchResults = null;
		searchResult = null;
		try {
			MediaType mediaTypeSeachValue = mediaTypeService.findOne(selectedMediaTypeId);
			searchResults = trackService.findAllByMediaType(mediaTypeSeachValue);
			JSFHelper.addInfoMessage(searchResults.size() + " record(s) found.");
		} catch( Exception e ) {
			JSFHelper.addInfoMessage("An error has occurred.");
			logger.log(Level.WARNING, e.getMessage());
		}
	}
	
	public void doSearchByGenre() {
		searchResults = null;
		searchResult = null;
		try {
			Genre genreSearchValue = genreService.findOne(selectedGenreId);
			searchResults = trackService.findAllByGenre(genreSearchValue);
			JSFHelper.addInfoMessage(searchResults.size() + " record(s) found.");
		} catch( Exception e ) {
			JSFHelper.addInfoMessage("An error has occurred.");
			logger.log(Level.WARNING, e.getMessage());
		}
	}
	
	public void doSelectTrack(Track selectedTrack) {
		searchResult = selectedTrack;
		searchResults = null;
	}
	
	public void doCancelSearch() {
		searchResult = null;
		searchResults = null;
		selectedMediaTypeId = 0;
		selectedGenreId = 0;
	}

	public void setSearchResult(Track searchResult) {
		this.searchResult = searchResult;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
