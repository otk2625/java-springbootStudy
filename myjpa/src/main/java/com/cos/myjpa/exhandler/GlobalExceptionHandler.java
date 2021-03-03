package com.cos.myjpa.exhandler;

import java.sql.SQLDataException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.myjpa.web.dto.CommonRespDto;

@RestController //데이터를 리턴할 수 있음
@ControllerAdvice //모든 익셉션을 낚아챔!
public class GlobalExceptionHandler {

	// 그중에 IllegalArgumentException 이 발생하면 해당 함수 실행됨
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public CommonRespDto<?> dataIntegrityViolation(Exception e){ // e 는 오류난 정보를 다 들고있음!
		return new CommonRespDto<>(-1, e.getMessage(), null);
	}
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public CommonRespDto<?> illegalArgument(Exception e){ // e 는 오류난 정보를 다 들고있음!
		return new CommonRespDto<>(-1, e.getMessage(), null);
	}
	
	@ExceptionHandler(value = EmptyResultDataAccessException.class)
	public CommonRespDto<?> emptyResultDataAccess(Exception e){ // e 는 오류난 정보를 다 들고있음!
		return new CommonRespDto<>(-1, e.getMessage(), null);
	}
}
