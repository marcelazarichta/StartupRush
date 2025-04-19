//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import org.bson.Document;
//
public class DatabaseManager {
//
//    private MongoCollection<Document> startupsCollection;
//    private MongoCollection<Document> roundsCollection;
//
//    // Construtor para inicializar as coleções
//    public DatabaseManager() {
//        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
//        MongoDatabase database = mongoClient.getDatabase("startupRush");
//        startupsCollection = database.getCollection("startups");
//        roundsCollection = database.getCollection("rounds");
//    }
//
//    // Método para registrar uma nova startup
//    public void registerStartup(String startupName, String slogan, int foundedYear) {
//        Document startup = new Document("name", startupName)
//                .append("slogan", slogan)
//                .append("foundedYear", foundedYear)
//                .append("points", 70); // Inicializando com 70 pontos
//        startupsCollection.insertOne(startup);
//    }
//
//    // Método para salvar o resultado de uma rodada
//    public void saveRoundResult(String startup1, String startup2, String winner, int pointsStartup1, int pointsStartup2) {
//        Document round = new Document("startup1", startup1)
//                .append("startup2", startup2)
//                .append("winner", winner)
//                .append("pointsStartup1", pointsStartup1)
//                .append("pointsStartup2", pointsStartup2);
//        roundsCollection.insertOne(round);
//
//        // Atualiza a pontuação das startups após a rodada
//        updateStartupPoints(startup1, pointsStartup1);
//        updateStartupPoints(startup2, pointsStartup2);
//    }
//
//    // Método para atualizar os pontos de uma startup
//    private void updateStartupPoints(String startupName, int newPoints) {
//        Document query = new Document("name", startupName);
//        Document update = new Document("$set", new Document("points", newPoints));
//        startupsCollection.updateOne(query, update);
//    }
}
