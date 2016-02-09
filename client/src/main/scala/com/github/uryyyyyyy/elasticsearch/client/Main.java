package com.github.uryyyyyyy.elasticsearch.client;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class Main {

	public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
		Settings settings = Settings.settingsBuilder()
				.put("cluster.name", "elasticsearch").build();
		Client client = TransportClient.builder().settings(settings).build()
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.17.0.2"), 9300));

// on shutdown

		IndexResponse response = client.prepareIndex("twitter", "tweet", "1")
				.setSource(jsonBuilder()
						.startObject()
						.field("user", "kimchy")
						.field("postDate", new Date())
						.field("message", "trying out Elasticsearch")
						.endObject()
				)
				.get();

		UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.index("twitter");
		updateRequest.type("tweet");
		updateRequest.id("1");
		updateRequest.doc(jsonBuilder()
				.startObject()
				.field("user", "male")
				.endObject());
		client.update(updateRequest).get();

		GetResponse response2 = client.prepareGet("twitter", "tweet", "1").get();
		System.out.println(response2.getSource());


		client.close();
	}
}
