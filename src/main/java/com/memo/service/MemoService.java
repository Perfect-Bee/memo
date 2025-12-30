package com.memo.service;

import com.memo.dto.MemoCreateRequest;
import com.memo.dto.MemoCreateResponse;
import com.memo.entity.Memo;
import com.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
