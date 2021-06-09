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

    public firebaseService() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("res/ipsene15-monopoly-firebase-adminsdk-d6pd1-05c8da5b71.json");

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
        DocumentReference docRef = db.collection("spel").document("spelers");
        Map<String, Object> data = new HashMap<>();
        data.put("naam,", name);
        data.put("pion_kleur", "kleur");
        data.put("money", 1500);
        data.put("token", "tokenString");
        ApiFuture<WriteResult> result = docRef.set(data);
        System.out.println("Added user to game : " + result.get().getUpdateTime());
    }

    public void getUser(String name) throws Exception{
        ArrayList<String> klueren = new ArrayList<String>(Arrays.asList("geel", "blauw", "rood", "paars"));
        Speler speler = new Speler("", 0, 39000);
        ApiFuture<WriteResult> future = db.collection("cities").document("LA").set(speler);
        System.out.println("Update time : " + future.get().getUpdateTime());
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
            System.out.println("Managed "+ amount +"$ from '"+ name +"' wallet");
            return null;
        });
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
