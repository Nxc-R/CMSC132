package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import tubeVideosManager.Genre;
import tubeVideosManager.Playlist;
import tubeVideosManager.TubeVideosManager;
import tubeVideosManager.Video;

/**
 * 
 * You need student tests if you are asking for help during
 * office hours about bugs in your code. Feel free to use
 * tools available in TestingSupport.java
 * 
 * @author UMCP CS Department
 *
 */
public class StudentTests {

	@Test
	public void playlistConsandgetNameTest() {
		Playlist playlist = new Playlist("Bangers");
		System.out.println(playlist.getName());
		
	}
	@Test
	public void playlistCopyConsandaddToPlaylistTest() {
		Playlist playlist = new Playlist("Useful");
		playlist.addToPlaylist("How to fly a kite");
		System.out.println(playlist);
		Playlist playlist2 = new Playlist(playlist);
		playlist2.addToPlaylist("How to drive a boat");
		System.out.println(playlist);
		System.out.println(playlist2);
	}
	@Test
	public void getPlaylistVideosTitles() {
		Playlist playlist = new Playlist("Useful");
		playlist.addToPlaylist("How to fly a kite");
		playlist.addToPlaylist("How to drive a boat");
		System.out.println(playlist.getPlaylistVideosTitles());
	
	}
	@Test
	public void removeAndshuffleTest() {
		Playlist playlist = new Playlist("Useful");
		playlist.addToPlaylist("How to fly a kite");
		playlist.addToPlaylist("How to drive a boat");
		playlist.addToPlaylist("How to drive a boat");
		System.out.println(playlist);
		playlist.removeFromPlaylistAll("How to drive a boat");
		System.out.println(playlist);
	}
	@Test
	public void videoTest () {
		String title = "How to Draw in Java Tutorial";
		String url = "https://www.youtube.com/embed/ifVf9ejuFWI";
		int durationInMinutes = 17;
		Genre genre = Genre.Educational;
		Video video = new Video(title, url, durationInMinutes, genre);
		System.out.println(video);
		video.addComments("Nice video");
		video.addComments("Recommended");
		System.out.println(video.getComments());
		Video vid = new Video(video);
		vid.addComments("test");
		System.out.println(video.getComments());
		System.out.println(vid.getComments());
	}
	@Test
	public void videogetTitleandUrlandDurationTest() {
		String title = "How to Draw in Java Tutorial";
		String url = "https://www.youtube.com/embed/ifVf9ejuFWI";
		int durationInMinutes = 17;
		Genre genre = Genre.Educational;
		Video video = new Video(title, url, durationInMinutes, genre);
		System.out.println(video);
		System.out.println(video.getTitle() + " " + video.getUrl() + " " + video.getDurationInMinutes());
	}
	@Test 
	public void videoequalstest() {
		Playlist playlist = new Playlist("Useful");
		Playlist playlist2 = new Playlist("Useful");
		Playlist playlist3 = new Playlist("Naw");
		System.out.println(playlist.equals(playlist2));
		System.out.println(playlist.equals(playlist3));
	}
	@Test
	public void tubeVideosManagerAddVideoTest () {
		TubeVideosManager tubeVideosManager = new TubeVideosManager();
		String title = "How to Draw in Java Tutorial";
		String url = "https://www.youtube.com/embed/ifVf9ejuFWI";
		int durationInMinutes = 17;
		Genre genre = Genre.Educational;
		tubeVideosManager.addVideoToDB(title, url, durationInMinutes, genre);
		title = "Git & GitHub Crash Course for Beginners";
		url = "https://www.youtube.com/embed/SWYqp7iY_Tc";
		durationInMinutes = 33;
		genre = Genre.Educational;
		tubeVideosManager.addVideoToDB(title, url, durationInMinutes, genre);
		System.out.println(tubeVideosManager.getAllVideosInDB());
	}
	@Test
	public void tubeVideosManagerfindVideoTest() {
		TubeVideosManager tubeVideosManager = new TubeVideosManager();
		String title = "How to Draw in Java Tutorial";
		String url = "https://www.youtube.com/embed/ifVf9ejuFWI";
		int durationInMinutes = 17;
		Genre genre = Genre.Educational;
		tubeVideosManager.addVideoToDB(title, url, durationInMinutes, genre);
		title = "Git & GitHub Crash Course for Beginners";
		url = "https://www.youtube.com/embed/SWYqp7iY_Tc";
		durationInMinutes = 33;
		genre = Genre.Educational;
		tubeVideosManager.addVideoToDB(title, url, durationInMinutes, genre);
		Video newVid = tubeVideosManager.findVideo("How to Draw in Java Tutorial");
		System.out.println(newVid);
	}
	@Test
	public void tubeVideosManagerAddCommentsTest () {
		TubeVideosManager tubeVideosManager = new TubeVideosManager();
		String title = "How to Draw in Java Tutorial";
		String url = "https://www.youtube.com/embed/ifVf9ejuFWI";
		int durationInMinutes = 17;
		Genre genre = Genre.Educational;
		tubeVideosManager.addVideoToDB(title, url, durationInMinutes, genre);
		tubeVideosManager.addComments(title, "Informative");
		Video newVid = tubeVideosManager.findVideo("How to Draw in Java Tutorial");
		System.out.println(newVid.getComments());
	}
	@Test
	public void tubeVideosManagerAllPlaylistMethodsTest () {
		TubeVideosManager tubeVideosManager = new TubeVideosManager();
		String title = "How to Draw in Java Tutorial";
		String url = "https://www.youtube.com/embed/ifVf9ejuFWI";
		int durationInMinutes = 17;
		Genre genre = Genre.Educational;
		tubeVideosManager.addVideoToDB(title, url, durationInMinutes, genre);
		String playlistName = "ToWatch";
		tubeVideosManager.addPlaylist(playlistName);
		tubeVideosManager.addPlaylist("Bored");
		tubeVideosManager.addPlaylist("Timeout");
		String[] playlistNames = tubeVideosManager.getPlaylistsNames();
		System.out.println(playlistNames.toString());
		tubeVideosManager.addVideoToPlaylist("How to drive", "Bored");
		Playlist playlist = tubeVideosManager.getPlaylist("Bored");
		System.out.println(playlist.getPlaylistVideosTitles());
		tubeVideosManager.clearDatabase();
		System.out.println(tubeVideosManager.getAllVideosInDB());
		System.out.println(tubeVideosManager.getPlaylistsNames().toString());
	}
	@Test
	public void tubeVideosManagerSearchVideoTest() {
		TubeVideosManager tubeVideosManager = new TubeVideosManager();
		String title = "How to Draw in Java Tutorial";
		String url = "https://www.youtube.com/embed/ifVf9ejuFWI";
		int durationInMinutes = 17;
		Genre genre = Genre.Educational;
		tubeVideosManager.addVideoToDB(title, url, durationInMinutes, genre);
		title = "Git & GitHub Crash Course for Beginners";
		url = "https://www.youtube.com/embed/SWYqp7iY_Tc";
		durationInMinutes = 33;
		genre = Genre.Educational;
		tubeVideosManager.addVideoToDB(title, url, durationInMinutes, genre);
		tubeVideosManager.addPlaylist("Instructional");
		tubeVideosManager.addVideoToPlaylist("How to drive", "Instructional");
		Playlist playlist = tubeVideosManager.searchForVideos("Instructional", title, durationInMinutes, genre);
		System.out.println(playlist);
		playlist = tubeVideosManager.searchForVideos("Instructional", null, -1, null);
		System.out.println(playlist);
		playlist = tubeVideosManager.searchForVideos("Instructional", title, -1, null);
		System.out.println(playlist);
		playlist = tubeVideosManager.searchForVideos("Instructional", null, 20, null);
		System.out.println(playlist);
		playlist = tubeVideosManager.searchForVideos("Instructional", null, -1, genre);
		System.out.println(playlist);
		
		
	}

}
