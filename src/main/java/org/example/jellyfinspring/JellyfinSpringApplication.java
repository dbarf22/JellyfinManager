package org.example.jellyfinspring;

import org.JellyfinProject.JellyfinController;
import org.JellyfinProject.dataTypes.Database;
import org.JellyfinProject.dataTypes.Sessions;
import org.JellyfinProject.dataTypes.queueTypes.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;


@SpringBootApplication
@RestController
@CrossOrigin
public class JellyfinSpringApplication {

	static Queue queue = new Queue();
	static Database db = new Database();
	static ArrayList<Episode> episodeList;
	static ArrayList<Movies> movieList;
	static JellyfinController jfc;

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(JellyfinSpringApplication.class, args);
		jfc = new JellyfinController();
		jfc.loadConfig();
		db.populateDB();
		episodeList = db.getTvList();
		movieList = db.getMoviesList();
		queue = db.getContentQueue();

	}

	@CrossOrigin
	@PostMapping("/queue/queueAll")
	public void queueAll(@RequestBody String i)
			throws IOException, InterruptedException {
		Media newMedia = jfc.mediaMake(i);
		queue.addToQueue(newMedia);
		newMedia.printMediaData();
		System.out.println("Position in queue: " + queue.getMediaArray().indexOf(newMedia));
		System.out.println();
	}

	@CrossOrigin
	@PostMapping("/queue/sendQueue")
	public void sendQueue(@RequestBody String session) throws InterruptedException, IOException {
		ArrayList<Media> mediaList = queue.getMediaArray();
		int count = 0;
		for (Media m : mediaList) {
			if (count == 0) {
				jfc.playId(session, m.getId(), "PlayNow", 0);
				System.out.println("Queued ID: " + m.getId());
				Thread.sleep(6000);
				count++;
			} else {
				jfc.playId(session, m.getId(), "PlayNext", 0);
				System.out.println("Queued ID: " + m.getId());
				Thread.sleep(6000);
			}
		}
	}



	@CrossOrigin
	@PostMapping("/queue/removeFromQueue")
	public void removeFromQueue(@RequestBody String i) {
		queue.getMediaArray().remove(Integer.parseInt(i));
	}

	@CrossOrigin
	@PostMapping("/queue/moveItemUp")
	public void moveItemUp(@RequestBody String i) {
		queue.moveUp(queue.getMediaArray().get(Integer.parseInt(i)));
	}

	@CrossOrigin
	@PostMapping("/queue/moveItemDown")
	public void moveItemDown(@RequestBody String i) {
		queue.moveDown(queue.getMediaArray().get(Integer.parseInt(i)));
	}

	@CrossOrigin
	@PostMapping("/queue/clearQueue")
	public void clearQueue() {
		for (Media m : queue.getMediaArray()) {
			queue.getMediaArray().remove(m);
		}
	}

	@CrossOrigin
	@GetMapping("/queue/returnQueue")
	public Queue returnQueue() {
		return queue;
	}

	@CrossOrigin
	@PostMapping("/sessions/command")
	public void sendPlayCommand(@RequestBody String[] sessionPlayCommand) {
		String session = sessionPlayCommand[0];
		String playCommand = sessionPlayCommand[1];
		try {
			jfc.sendPlayCommand(session, playCommand);
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

	@CrossOrigin
	@GetMapping("/sessions/getSessions")
	public ArrayList<Sessions> getSessions() throws IOException, InterruptedException {
		return jfc.getSessions();
	}

	@CrossOrigin
	@GetMapping("/queue/getQueue")
	public ArrayList<Media> getQueue() {
		return queue.getMediaArray();
	}

	@CrossOrigin
	@GetMapping("/database/getEpisodeList")
	public ArrayList<Episode> getEpisodeList() {
		return db.getTvList();
	}

	@CrossOrigin
	@GetMapping("/database/getMovieList")
	public ArrayList<Movies> getMovieList() {
		return db.getMoviesList();
	}



}
