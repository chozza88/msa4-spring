package com.msa4spring.requests;
// 유효성 검사를 위해 DTO record를 만들어 사용
public record UsersPaginationRequest(
        Integer page
        ,Integer limit
) {
   public UsersPaginationRequest(Integer page, Integer limit){
       this.page = (page == null) ? 1 : page;
       this.limit = (limit == null) ? 10 : limit;
   }
}
