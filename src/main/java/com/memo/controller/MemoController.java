package com.memo.controller;

import com.memo.dto.MemoCreateRequest;
import com.memo.dto.MemoCreateResponse;
import com.memo.repository.MemoRepository;
import com.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    // 생성
    @PostMapping("/members")
    // 응답이 필요함 : dto에 있음(만들기)
    public ResponseEntity<MemoCreateResponse> create(
        // 포스트맨에서 body에 저장 : 요청 body
        // dto에 있음(만들기)
        @RequestBody MemoCreateRequest request
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(memoService.save(request));
    }
}
