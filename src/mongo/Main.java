package mongo;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

import static com.mongodb.client.model.Filters.*;

public class Main {

	public static void main(String[] args) {
		MongoClientOptions.Builder build = MongoClientOptions.builder();
		MongoClient mongoClient = new MongoClient("192.168.1.144");
		MongoDatabase mongoDatabase = mongoClient.getDatabase("dasyn");
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("queue");
		
		FindIterable<Document> iterable = mongoCollection.find(eq("dataId", "hb"));
		System.out.println(iterable.first());
		System.out.println(JSON.serialize(iterable.first()));
		iterable.forEach(new Block<Document>() {
		    public void apply(final Document document) {
		        System.out.println(document);
		    }
		});
	}

}
