package com.memo.controller;

import com.memo.dto.*;
import com.memo.repository.MemoRepository;
import com.memo.service.MemoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //조회
    @GetMapping("/members")
    // 전체 -> 리스트
    public ResponseEntity<List<MemoGetResponse>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(memoService.findAll());
    }
    // 단건조회
    @GetMapping("/memos/{memoId}")
    public ResponseEntity<MemoGetResponse> getOne(
            @PathVariable Long memoId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(memoService.findOne(memoId));
    }

    // 수정
    @PutMapping("/memos/{memoId}")
    public ResponseEntity<MemoUpdateResponse> update(
            @PathVariable Long memoId,
            @RequestBody MemoUpdateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(memoService.update(memoId, request));
    }

}
