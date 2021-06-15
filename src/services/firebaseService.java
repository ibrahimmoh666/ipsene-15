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
    public String token;
    static firebaseService firebaseService;

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

    public static firebaseService getInstance() throws IOException {
        if (firebaseService == null){
            firebaseService = new firebaseService();
        }
        return firebaseService;
    }

    public Firestore getDb() {
        return db;
    }

    void run() throws Exception {
        System.out.println("Running firebaseService.java");
    }

    void close() throws Exception {
        db.close();
    }

    public static void main(String[] args) throws Exception {
//        firebaseService quickStart = new firebaseService();
//        quickStart.run();
//        quickStart.close();
    }

}
