package chinook.webtier;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import chinook.businesstier.AlbumService;
import chinook.businesstier.ArtistService;
import chinook.entity.Album;
import chinook.entity.Artist;
import helper.JSFHelper;

@Named
@RequestScoped
public class CreateAlbumController {

	@Inject
	private ArtistService artistService;
	
	@Inject
	private AlbumService albumService;
	
	@Inject
	private Logger logger;
	
	private Album currentAlbum;
	
	private int selectedArtistId;

	public Album getCurrentAlbum() {
		return currentAlbum;
	}
	public void setCurrentAlbum(Album currentAlbum) {
		this.currentAlbum = currentAlbum;
	}
	public int getSelectedArtistId() {
		return selectedArtistId;
	}
	public void setSelectedArtistId(int selectedArtistId) {
		this.selectedArtistId = selectedArtistId;
	}
	public List<Artist> getAllArtist() {
		return artistService.findAllOrderByName();
	}
	
	
	@PostConstruct
	public void init() {
		currentAlbum = new Album();
	}
	
	
	public void doCreateAlbum() {
		try {
			Artist albumArtist = artistService.findOneByArtistId(selectedArtistId);
			currentAlbum.setArtist(albumArtist);
			albumService.create(currentAlbum);
			JSFHelper.addInfoMessage("Album was added to the system.");
			currentAlbum = new Album();
		} catch (Exception e) {
			logger.log(Level.WARNING, e.getMessage());
			JSFHelper.addErrorMessage("Album was not aded to the system.");
		}
	}
}
