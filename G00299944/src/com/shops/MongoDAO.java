package com.shops;

import java.util.ArrayList;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDAO {

	String mongoDB = "storeHeadOfficeDB";
	String mongoCollection = "storeHeadOffice";

	MongoClient mongoClient;
	MongoDatabase database;
	MongoCollection<Document> collection;

	public MongoDAO() throws Exception {
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase(mongoDB);
		collection = database.getCollection(mongoCollection);
	}

	public ArrayList<HeadOffice> loadHeadOffices() throws Exception {
		ArrayList<HeadOffice> headOffices = new ArrayList<HeadOffice>();
		FindIterable<Document> offices = collection.find();

		for (Document d : offices) {
			HeadOffice office = new HeadOffice();
			office.setId((int) (d.get("_id")));
			office.setLocation((String) d.get("location"));

			headOffices.add(office);
		}
		return headOffices;
	}

	public void addHeadOffice(HeadOffice headOffice) throws Exception {
		Document doc = new Document();
		doc.append("_id", headOffice.getId()).append("location", headOffice.getLocation());
		collection.insertOne(doc);
	}

}
