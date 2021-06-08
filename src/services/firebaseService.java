package services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    public void addUser(String name) throws Exception {
        DocumentReference docRef = db.collection("spel").document("spelers");
        Map<String, Object> data = new HashMap<>();
        data.put("naam,", name);
        data.put("pion_kleur", "kleur");
        ApiFuture<WriteResult> result = docRef.set(data);
        System.out.println("Update time : " + result.get().getUpdateTime());
    }

    public void givePropertyToUser(Integer propertyId, String userName) throws Exception{
        DocumentReference docRef = db.collection("bord").document("propertyList");
        Map<String, Object> data = new HashMap<>();
        data.put("test", propertyId);
        ApiFuture<WriteResult> result = docRef.set(data);
        System.out.println("Update time : " + result.get().getUpdateTime());
    }

    public List<QueryDocumentSnapshot> getUsers() throws Exception{
        ApiFuture<QuerySnapshot> query = db.collection("spel").get();

        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        return documents;
    }

    public void getUserWorth(){}

    Firestore getDb() {
        return db;
    }

    void run() throws Exception {
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
