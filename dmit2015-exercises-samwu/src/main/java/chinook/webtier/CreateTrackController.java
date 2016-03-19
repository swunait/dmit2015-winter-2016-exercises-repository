package chinook.webtier;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import chinook.businesstier.AlbumService;
import chinook.businesstier.GenreService;
import chinook.businesstier.MediaTypeService;
import chinook.businesstier.TrackService;
import chinook.entity.Album;
import chinook.entity.Genre;
import chinook.entity.MediaType;
import chinook.entity.Track;
import helper.JSFHelper;

@Named
@ViewScoped
public class CreateTrackController implements Serializable {
	private static final long serialVersionUID = 3570773454596574910L;

	@Inject
	private TrackService trackService;
	
	@Inject
	private MediaTypeService mediaTypeService;
	
	@Inject
	private GenreService genreService;
	
	@Inject
	private AlbumService albumService;
	
	@Inject
	private Logger logger;
	
	private Track currentTrack;	// getter
	
	private int selectedMediaTypeId;	// getter/setter
	private int selectedGenreId;		// getter/setter
	private int selectedAlbumId;		// getter/setter
	
	
	public Track getCurrentTrack() {
		return currentTrack;
	}
	public void setCurrentTrack(Track currentTrack) {
		this.currentTrack = currentTrack;
	}
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
	public int getSelectedAlbumId() {
		return selectedAlbumId;
	}
	public void setSelectedAlbumId(int selectedAlbumId) {
		this.selectedAlbumId = selectedAlbumId;
	}
	
	@PostConstruct
	public void init() {
		currentTrack = new Track();
	}
	
	public List<MediaType> getAllMediaType() {
		return mediaTypeService.findAllOrderByName();
	}
	
	public List<Genre> getAllGenre() {
		return genreService.findAllOrderByName();
	}
	
	public List<Album> getAllAlbum() {
		return albumService.findAllOrderByTitle();
	}
	
	public void doCreate() {
		try {
			if( selectedMediaTypeId > 0 ) {
				MediaType mediaType = mediaTypeService.findOne(selectedMediaTypeId);
				currentTrack.setMediaType(mediaType);
			}
			if( selectedGenreId > 0 ) {
				Genre genre = genreService.findOne(selectedGenreId);
				currentTrack.setGenre(genre);
			}
			if( selectedAlbumId > 0 ) {
				Album album = albumService.findOne(selectedAlbumId);
				currentTrack.setAlbum(album);
			}
			trackService.create(currentTrack);
			JSFHelper.addInfoMessage("Add was successful");
			currentTrack = new Track();
		} catch( Exception e ) {
			JSFHelper.addErrorMessage("Add was not successful");
			logger.log(Level.WARNING, e.getMessage());
		}
	}
	
}
