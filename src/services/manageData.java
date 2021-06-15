package services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.database.annotations.Nullable;
import models.Speler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class manageData extends firebaseService {

    private static int playerCount = 0;
    static manageData manageData;
    public static String token;

    public static manageData getInstance() throws IOException {
        if (manageData == null){
            manageData = new manageData();
        }
        return manageData;
    }

    public manageData() throws IOException {

    }

    /**
     * Creates a new lobby with data inside
     * @param token
     * @author Alavi van Oosterhout
     */
    public void createRoom(String token) throws Exception {
        this.token = token;
        DocumentReference docRef = getDb().collection("spel").document(token);
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> speler = new HashMap<>();
        data.put("token", token);
        data.put("playercount", playerCount);
        data.put("players", speler);
        ApiFuture<WriteResult> result = docRef.set(data);
        System.out.println("Created new lobby : " + result.get().getUpdateTime());
    }

    /**
     * Adds a user to the firestore with username and token
     * @param name
     * @param token
     * @throws Exception
     * @author Alavi van Oosterhout
     */
    public void addUser(String name, String token) {
        DocumentReference docRef = getDb().collection("spel").document(token);
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> speler = new HashMap<>();
        speler.put("name", name);
        speler.put("color", "kleur");
        speler.put("money", 1500);
        speler.put("position", 0);
        speler.put("token", token);
        data.put("speler", speler);

        ApiFuture<WriteResult> result = docRef.set(data);

        try {
            System.out.println("Added user to game : " + result.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        playerCount++;
    }

    public void Listen(String collection, String document){
        DocumentReference docRef = getDb().collection(collection).document(document);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirestoreException e) {
                if (e != null) {
                    System.err.println("Listen failed: " + e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    System.out.println("Current data: " + snapshot.getData());
                } else {
                    System.out.print("Current data: null");
                }
            }
        });
    }


    /**
     * Get the users information in an arraylist
     * @throws Exception
     * @return Speler class obj     Speler informatie als een speler object class
     * @author Alavi van Oosterhout
     */
    public ArrayList<Speler> getUsers() throws Exception{
        ApiFuture<QuerySnapshot> future =
                getDb().collection("spel").get();
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
     * @author Alavi van Oosterhout
     */
    public void givePropertyToUser(Integer propertyId, String userName) throws Exception{
        DocumentReference docRef = getDb().collection("bord").document("propertyList");
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
     * @author Alavi van Oosterhout
     */
    public void manageUserFunds(String name, int amount) throws Exception{
        ApiFuture<Void> futureTransaction = getDb().runTransaction(transaction -> {
            DocumentReference docRef = getDb().collection("spel").document("spelers");
            DocumentSnapshot snapshot = transaction.get(docRef).get();

            long oldValue = snapshot.getLong("money");
            transaction.update(docRef, "money", oldValue += amount);
            System.out.println("Exchanged "+ amount +"$ from '"+ name +"' wallet");
            return null;
        });
    }

    public static String getToken() {
        return token;
    }
}
