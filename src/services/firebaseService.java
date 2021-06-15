package services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import models.Speler;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


public class firebaseService {

    private Firestore db;
    private static int playerCount = 0;

    public firebaseService() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("src/resources/ipsene15-monopoly-firebase-adminsdk-d6pd1-05c8da5b71.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://ipsene15-monopoly.firebaseio.com/")
                .setProjectId("ipsene15-monopoly")
                .build();
        FirebaseApp.initializeApp(options);

        Firestore db = FirestoreClient.getFirestore();
        this.db = db;
    }

    /**
     * Adds a user to the firestore with username and token
     * @param name
     * @param token
     * @throws Exception
     */
    public void addUser(String name, String token) throws Exception {
        DocumentReference docRef = db.collection("spelers").document(name);
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("color", "kleur");
        data.put("money", 1500);
        data.put("position", 0);
        data.put("number", playerCount);
        data.put("token", token);
        ApiFuture<WriteResult> result = docRef.set(data);
        System.out.println("Added user to game : " + result.get().getUpdateTime());
        playerCount++;
    }


    /**
     * Get the users information in an arraylist
     * @throws Exception
     * @return Speler class obj     Speler informatie als een speler object class
     */
    public ArrayList<Speler> getUsers() throws Exception{
        ApiFuture<QuerySnapshot> future =
                db.collection("spelers").get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        ArrayList<Speler> spelers = new ArrayList<Speler>();
        for (DocumentSnapshot document : documents) {
            System.out.println(document);
            if(document.exists()){
                spelers.add(document.toObject(Speler.class));
            }
        }
        return spelers;
    }

    /**
     * Gives property to an player
     * @param propertyId
     * @param userName
     * @throws Exception
     */
    public void givePropertyToUser(Integer propertyId, String userName) throws Exception{
        DocumentReference docRef = db.collection("bord").document("propertyList");
        Map<String, Object> data = new HashMap<>();
        data.put("test", propertyId);
        ApiFuture<WriteResult> result = docRef.set(data);
        System.out.println("Update time : " + result.get().getUpdateTime());
    }

    /**
     * Takes an amount of money from a users account
     * @param name
     * @param amount
     * @throws Exception
     */
    public void manageUserFunds(String name, int amount) throws Exception{
        ApiFuture<Void> futureTransaction = db.runTransaction(transaction -> {
            DocumentReference docRef = db.collection("spel").document("spelers");
            DocumentSnapshot snapshot = transaction.get(docRef).get();

            long oldValue = snapshot.getLong("money");
            transaction.update(docRef, "money", oldValue += amount);
            System.out.println("Exchanged "+ amount +"$ from '"+ name +"' wallet");
            return null;
        });
    }


    /**
     *
     * @param token
     */
    public void createRoom(String token) {
        System.out.println("Function for creating lobby");
    }

    Firestore getDb() {
        return db;
    }

    void run() throws Exception {
        System.out.println("Running firebaseService.java");
    }

    void close() throws Exception {
        db.close();
    }

    public static void main(String[] args) throws Exception {
        firebaseService quickStart = new firebaseService();
        quickStart.run();
        quickStart.close();
    }

}
