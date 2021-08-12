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

package io.fusion.air.microservice.domain.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.fusion.air.microservice.utils.DateJsonSerializer;

/**
 * Cart Entity
 * 
 * @author arafkarsh
 *
 */
public class CartEntity {

	private String orderId;
	
	@JsonSerialize(using = DateJsonSerializer.class)
	private LocalDateTime orderDate;
	
	@NotNull
	private Customer customer;
	
	@NotNull
	private ArrayList<CartItem> orderItems;
	
	/**
	 * Create Cart
	 */
	public CartEntity() {
		orderItems = new ArrayList<CartItem>();
	}
	
	/**
	 * Adds the Customer
	 * @param _customer
	 */
	protected void addCustomer(Customer _customer) {
		customer = _customer;
		customer.validate();
	}
	
	/**
	 * Add Cart Item
	 * @param _item
	 */
	protected void addCartItem(CartItem _item) {
		if(_item != null) {
			orderItems.add(_item);
		}
	}
	
	/**
	 * Returns true 
	 * @return
	 */
	public boolean isCustomerAvailable()  {
		return (customer != null) ? true : false;
	}
	
	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @return the orderItems
	 */
	public ArrayList<CartItem> getCartItems() {
		return orderItems;
	}
	
	/**
	 * Returns the Total Items in the Cart
	 * @return
	 */
	public int getTotalItems() {
		return orderItems.size();
	}
	
	/**
	 * Calculate the Total Cart Value
	 * @return
	 */
	public double getTotalValue() {
		double totalValue = 0.00;
		for(CartItem item : orderItems) {
			totalValue += item.getItemValue();
		}
		return totalValue;
	}

	/**
	 * @return the orderId
	 */
	public String getCartId() {
		return orderId;
	}

	/**
	 * @return the orderDate
	 */
	public LocalDateTime getCartDate() {
		return orderDate;
	}
	
	/**
	 * Shows Cart ID + Cart Status
	 */
	public String toString() {
		return orderId;
	}
	
}
