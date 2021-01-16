package com.cognizant.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.entity.Product;
import com.cognizant.exceptions.ProductNotFoundException;
import com.cognizant.repository.ProductRepository;

import io.swagger.annotations.ApiOperation;

@RestController
public class ProductRestController {
	@Autowired
	ProductRepository productRepo;
	/* resource method to save a New Product */
	@PostMapping(
			value="/save",
			consumes= {MediaType.APPLICATION_JSON_VALUE}
			)
	@ApiOperation("API to Save a New Product")
	public ResponseEntity<String> addProduct(@RequestBody Product product) {
		System.out.println(product);
		Product prod= productRepo.save(product);
		System.out.println(prod);
		return new ResponseEntity<>("Product with ID: "+product.getProductId()+" Saved Successfully",HttpStatus.CREATED);
	}
	/* resource method to get Product based its Product ID 
	 * Product ID is given as a Path Parameter
	 */
	@ApiOperation("API to search for a Product based on product ID")
	@GetMapping(
				value="/search/{pid}",
				produces= {MediaType.APPLICATION_JSON_VALUE}
			)
	public ResponseEntity<Product> searchProductById(@PathVariable Integer pid){
		Product product=null;
		Optional<Product> optProduct=productRepo.findById(pid);
		if(optProduct.isPresent()) {
			product=optProduct.get();
		}
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	/* resource method to update a Product
	 * Product is coming as Product JSON object 
	 */
	@PutMapping(
				value="/update",
				consumes="application/json"
			)
	public ResponseEntity<String> updateProduct(@RequestBody Product product){
		
	//	productRepo.save(product); // It will create duplicate product after entering wrong product ID
		
		 String responseMsg="No Matching Product Found";
		Optional<Product> optProd=productRepo.findById(product.getProductId());
		if(optProd.isPresent()) {
			productRepo.save(product);
			responseMsg="Product with ID: "+product.getProductId()+" updated successfully";	
		}
		return new ResponseEntity<>(responseMsg,HttpStatus.OK);
	}
	/* resource method to delete a Product 
	 * Product id is coming as a Path Parameter as id
	 */
	@DeleteMapping(
					value="/delete/{pid}"
			)
	public ResponseEntity<String> deleteProductById(@PathVariable Integer pid ){
		Optional<Product> prod=productRepo.findById(pid);
		if(prod.isPresent()) {
		productRepo.deleteById(pid);
		return new ResponseEntity<String>("Product with ID: "+pid+" Deleted !",HttpStatus.OK);
		}
		throw new ProductNotFoundException("No matching Product Found");
	}
	/* resource method to display all products
	 * 
	 */
	@GetMapping(
				value="/display"
			)
	public ResponseEntity<List<Product>> displayAll(){
		List<Product> products=productRepo.findAll();
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
}
