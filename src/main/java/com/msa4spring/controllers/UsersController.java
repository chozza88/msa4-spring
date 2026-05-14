package com.msa4spring.controllers;

import com.msa4spring.requests.PostsFilterRequest;
import com.msa4spring.requests.UsersPaginationRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsersController {
    @GetMapping("/users")
    public String index(
            // value를 거의 안씀 ex) value = "pa"
            // 유저가 값을 보내지 않을 때 required = false로 null값으로 받을 수 있는데,
            // 이 때 null값이 아니라, 기본 값을 defalutValue = "해당 값"으로 지정한다.

            @RequestParam(required = false, defaultValue = "1") String page
            ,@RequestParam String limit
    ){
        // 쿼리 파라미터 획득 방법 : @RequestParam 어노테이션을 통해 획득
        return "GET Users: " + page + ", " + limit;
    }

    //세그먼트 파라미터 : path부분을 분절하여 받을 수 있음.
    // GetMapping에서 분절되는 부분에 값을 받을 때 {해당 키}를 설정
    // URL 경로(path)의 일부를 변수처럼 받음
    // {id} 부분을 @PathVariable 로 바인딩
    @GetMapping("/users/{id}")
    public String show(
            @PathVariable String id
    ){
        // 세그먼트 파라미터 획득 방법 : @PathVariable
        return "GET users show: " + id;
    }

    @PostMapping("/users")
    public String store (){
        return "Post/api/users";
    }
    // -------------------------
    // DTO를 활용하여 파라미터 획득
    // -------------------------
    @GetMapping("/users/dto-param")
    public String dtoParam(
            UsersPaginationRequest usersPaginationRequest
    ){
        return String.format("GET dtoParam: %d, %d"
                , usersPaginationRequest.page()
                , usersPaginationRequest.limit());
    }

    // --- 세그먼트 파라미터 || Form Data를 DTO로 획득
    @GetMapping("/posts/{id}/filter/{categoryId}")
    public String postFilter(
            // @ModelAttribute : DTO로 받을 때 사용(폼 형식, 세그먼트 형식으로 넘어 올 때 사용) - 전제조건은 DTO로 받을 때
            @ModelAttribute PostsFilterRequest postsFilterRequest
            ){
        return String.format("postFilter: %d, %d"
                , postsFilterRequest.id()
                , postsFilterRequest.categoryId()
        );
    }

    // JSON 데이터를 DTO로 획득
    @GetMapping("/posts/json")
    public String postsJson(
            @RequestBody PostsFilterRequest postsFilterRequest
    ){
        return String.format("postFilter: %d, %d"
                , postsFilterRequest.id()
                , postsFilterRequest.categoryId()
        );
    }
}
