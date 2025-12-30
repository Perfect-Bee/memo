package com.memo.service;

import com.memo.dto.*;
import com.memo.entity.Memo;
import com.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    //저장
    @Transactional
    public MemoCreateResponse save(MemoCreateRequest request){
        Memo memo = new Memo(request.getText());
        Memo savedMemo = memoRepository.save(memo);
        return new MemoCreateResponse(
                savedMemo.getId(),
                savedMemo.getText(),
                savedMemo.getCreatedAt(),
                savedMemo.getModifiedAt()
        );
    }
    
    //조회 : 읽기만 함
    @Transactional(readOnly = true)
    public List<MemoGetResponse> findAll() {
        List<Memo> memoList = memoRepository.findAll();
        List<MemoGetResponse> dtos = new ArrayList<>();
        for (Memo memo : memoList) {
            MemoGetResponse dto = new MemoGetResponse(
                    memo.getId(),
                    memo.getText(),
                    memo.getCreatedAt(),
                    memo.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public MemoGetResponse findOne(Long memoId) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalStateException("없음")
        );
        return new MemoGetResponse(
                memo.getId(),
                memo.getText(),
                memo.getCreatedAt(),
                memo.getModifiedAt()
        );
    }

    @Transactional
    public MemoUpdateResponse update(Long memoId, MemoUpdateRequest request) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 메모입니다.")
        );
        memo.update(request.getText());
        return new MemoUpdateResponse(
                memo.getId(),
                memo.getText(),
                memo.getCreatedAt(),
                memo.getModifiedAt()
        );
    }
}
