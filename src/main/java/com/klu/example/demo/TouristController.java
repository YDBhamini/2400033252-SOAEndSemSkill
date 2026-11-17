package com.klu.example.demo;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.util.*;

@RestController
@RequestMapping("/tourist")
public class TouristController {

	    Map<Integer, Tourist> data = new HashMap<>();

	    TouristController() {
	        Tourist t = new Tourist();
	        t.id = 1;
	        t.name = "Ravi";
	        t.place = "Tirupati";
	        data.put(1, t);
	    }

	    boolean validate(String token) {
	        return "mytoken123".equals(token);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<?> get(
	        @PathVariable int id,
	        @RequestHeader(value="token_value_header",
	        required=false) String token) {

	        if (!validate(token))
	            return ResponseEntity.status(401).body("Invalid Token");

	        if (!data.containsKey(id))
	            return ResponseEntity.notFound().build();

	        return ResponseEntity.ok(data.get(id));
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<?> update(
	        @PathVariable int id,
	        @RequestHeader(value="token_value_header",
	        required=false) String token,
	        @RequestBody Tourist t) {

	        if (!validate(token))
	            return ResponseEntity.status(401).body("Invalid Token");

	        if (!data.containsKey(id))
	            return ResponseEntity.notFound().build();

	        t.id = id;
	        data.put(id, t);

	        return ResponseEntity.ok("Updated Successfully");
	    }
	}