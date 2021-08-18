/**
 * (C) Copyright 2021 Araf Karsh Hamid 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.fusion.air.microservice.adapters.controllers;

import io.fusion.air.microservice.domain.models.*;
import io.fusion.air.microservice.server.config.ServiceConfiguration;
import io.fusion.air.microservice.server.config.ServiceHelp;
import io.fusion.air.microservice.server.controller.AbstractController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * App Controller for the Service
 * 
 * @author arafkarsh
 * @version 1.0
 * 
 */
@Configuration
@RestController
// "/api/v1/cart"
@RequestMapping("${service.api.path}")
@RequestScope
@Tag(name = "Cart", description = "Cart Service ")
public class AppControllerImpl extends AbstractController {

	// Set Logger -> Lookup will automatically determine the class name.
	private static final Logger log = getLogger(lookup().lookupClass());

	@Autowired
	private ServiceConfiguration serviceConfig;

	private String serviceName;

	/**
	 * Retrieve the Shopping Cart
	 *
	 * @return
	 */
	@Operation(summary = "Get the Cart")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "Get the Cart",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Invalid Cart Reference No.",
					content = @Content)
	})
	@GetMapping("/fetch/{cartId}")
	@ResponseBody
	public ResponseEntity<CartEntity> getCart(@PathVariable("cartId") String _cartId) throws Exception {
		log.info("|" + name() + "|Get Cart ... "+_cartId);
		return ResponseEntity.ok(new CartEntity());
	}

	/**
	 * Add to the Cart
	 */
	@Operation(summary = "Add to Cart")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "Add to the Cart",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Unable to add item to the Cart",
					content = @Content)
	})
	@PostMapping("/additem")
	public ResponseEntity<Map<String,Object>>  addToCart(@RequestBody CartItem _cartItem) {
		log.info("|" + name() + "|Add Item to Cart ... ");
		HashMap<String,Object> status = new HashMap<String,Object>();
		status.put("Code", 200);
		status.put("Status", true);
		status.put("Message","Item added to Cart!");
		return ResponseEntity.ok(status);
	}

	/**
	 * Add to the Cart
	 */
	@Operation(summary = "Add Cart")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "Add to the Cart",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Unable to add item to the Cart",
					content = @Content)
	})
	@PostMapping("/add")
	public ResponseEntity<Map<String,Object>>  addCart(@RequestBody CartEntity _cartEntity) {
		log.info("|" + name() + "|Add New Cart ... ");
		HashMap<String,Object> status = new HashMap<String,Object>();
		status.put("Code", 200);
		status.put("Status", true);
		status.put("Message","Cart added!");
		return ResponseEntity.ok(status);
	}

	/**
	 * Delete from the Cart
	 */
	@Operation(summary = "Delete Item from Cart")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "Item deleted from the Cart",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Unable to Delete Item from the Cart",
					content = @Content)
	})
	@DeleteMapping("/delete/{itemId}")
	public ResponseEntity<Map<String,Object>> deleteItem(@PathVariable("itemId") String _itemId) {
		log.info("|" + name() + "|Delete Item from the Cart ... "+_itemId);
		HashMap<String,Object> status = new HashMap<String,Object>();
		status.put("Code", 200);
		status.put("Status", true);
		status.put("Message","Item "+_itemId+" deleted!");
		return ResponseEntity.ok(status);
	}

	/**
	 * Update the Cart Item
	 */
	@Operation(summary = "Update Cart")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "Update the cart",
					content = {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
					description = "Unable to Update the cart",
					content = @Content)
	})
	@PutMapping("/update/")
	public ResponseEntity<Map<String,Object>> updateItem(@RequestBody CartItem _cartItem) {
		log.info("|" + name() + "|Request to Update cart item... ");
		HashMap<String,Object> status = new HashMap<String,Object>();
		status.put("Code", 200);
		status.put("Status", true);
		status.put("Message","Cart is updated!");
		return ResponseEntity.ok(status);
	}
}
