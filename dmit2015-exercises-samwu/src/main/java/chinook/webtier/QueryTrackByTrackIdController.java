package chinook.webtier;

import java.util.List;

import javax.enterprise.context.RequestScoped;
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
@RequestScoped
public class QueryTrackByTrackIdController {

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
	
}
