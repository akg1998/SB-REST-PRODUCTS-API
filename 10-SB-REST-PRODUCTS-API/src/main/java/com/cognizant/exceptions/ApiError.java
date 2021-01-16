package com.cognizant.exceptions;

import java.util.Date;

import lombok.Data;

@Data
public class ApiError {
	private String errorCode;
	private String errorMsg;
	private Date date;
}
