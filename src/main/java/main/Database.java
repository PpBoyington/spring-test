package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;

import controller.FishController;
import model.Fish;

/**
 * Firebase Database quickstart sample for the server Java SDK.
 * See: https://firebase.google.com/docs/server/setup#add_firebase_to_your_app
 */
public class Database {

	private static final String DATABASE_URL = "https://fishhub-134c2.firebaseio.com/";

	// @Autowired
	private static FirebaseDatabase database;

	private static FishController fishController;

    public static void main(String[] args) {
		// [START initialize]
		FirebaseOptions fireBaseOptions;
		try {
			fireBaseOptions = new FirebaseOptions.Builder().setDatabaseUrl(DATABASE_URL)
					.setServiceAccount(new FileInputStream("google-service.json")).build();
			FirebaseApp.initializeApp(fireBaseOptions);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		database = FirebaseDatabase.getInstance();

		Map<String, Fish> fishes = new HashMap<String, Fish>();
		fishes.put("Macro", new Fish("1ea458s", "Macro", 1.2, 1.3));
		fishes.put("Colin", new Fish("45fez81", "Colin", 2, 1.3));

		fishes.forEach(
				(uid, fish) -> fishController.writeNewFish(fish.getName(), fish.getAvgHeight(), fish.getAvgWidth()));
	}

}
