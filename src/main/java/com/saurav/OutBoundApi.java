package com.saurav;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OutBoundApi {

	@Autowired
	DataOutBoundService dataOutBoundService;

	@PostMapping("/send/{count}")
	public ResponseEntity<HttpStatus> process(@PathVariable("count") int count) {
		for (int i = 0; i < count; i++) {
			dataOutBoundService.produce();
		}
		return ResponseEntity.accepted().build();
	}
}
