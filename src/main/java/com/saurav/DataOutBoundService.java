package com.saurav;

import java.security.SecureRandom;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@EnableBinding(OutBoundChannel.class)
public class DataOutBoundService {

	@Autowired
	@Qualifier(OutBoundChannel.OUTBOUND_DATE)
	private MessageChannel outboundChannel;

	@Autowired
	private ObjectMapper objectMapper;

	final SecureRandom random = new SecureRandom();

	public void produce() {
		final DataModel dataModel = new DataModel(UUID.randomUUID().toString(), UUID.randomUUID().toString(),
				random.nextDouble());
		try {
			String payload = objectMapper.writeValueAsString(dataModel);
			System.out.println("[x][x] :: Payload to sent :: " + payload);
			boolean sent = outboundChannel.send(MessageBuilder.withPayload(payload)
					.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE).build());
			System.out.println("[*][*] :: Payload to sent successfully ?? :: " + sent);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}

class DataModel {

	@JsonProperty("asset_id")
	private String assetId;

	@JsonProperty("tag_id")
	private String tagId;

	@JsonProperty("tag_value")
	private double value;

	public DataModel(String assetId, String tagId, double value) {
		this.assetId = assetId;
		this.tagId = tagId;
		this.value = value;
	}

	/**
	 * @return the assetId
	 */
	public String getAssetId() {
		return assetId;
	}

	/**
	 * @param assetId
	 *            the assetId to set
	 */
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	/**
	 * @return the tagId
	 */
	public String getTagId() {
		return tagId;
	}

	/**
	 * @param tagId
	 *            the tagId to set
	 */
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}

}