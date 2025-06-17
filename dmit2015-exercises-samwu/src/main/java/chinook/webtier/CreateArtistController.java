package chinook.webtier;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import chinook.businesstier.ArtistService;
import chinook.entity.Artist;
import helper.JSFHelper;

@Named
@RequestScoped
public class CreateArtistController {

	@Inject
	private ArtistService artistService;
	
	@Inject
	private Logger logger;
	
	private Artist currentArtist;;
	

	public Artist getCurrentArtist() {
		return currentArtist;
	}
	public void setCurrentArtist(Artist currentArtist) {
		this.currentArtist = currentArtist;
	}

	@PostConstruct
	public void init() {
		currentArtist = new Artist();
	}
	
	public void doCreateArtist() {
		try {
			artistService.create(currentArtist);
			JSFHelper.addInfoMessage("Artist was added to the system.");
			currentArtist = new Artist();
		} catch (Exception e) {
			logger.log(Level.WARNING, e.getMessage());
			JSFHelper.addErrorMessage("Artist was not aded to the system.");
		}
	}
}
