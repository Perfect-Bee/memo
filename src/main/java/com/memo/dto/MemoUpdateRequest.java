package com.memo.dto;

import lombok.Getter;

@Getter
public class MemoUpdateRequest {
    // id 제외
    private String text;
}
