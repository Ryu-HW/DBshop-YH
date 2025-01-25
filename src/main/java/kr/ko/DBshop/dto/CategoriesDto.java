package kr.ko.DBshop.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriesDto {

    private Integer categoryId;

    @NotBlank(message = "카테고리 이름은 필수입니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣][a-zA-Z0-9가-힣]*$",
            message = "카테고리 이름을 확인해주세요.")
    @Size(min = 2, max = 30, message = "상품명은 2-30자 사이어야 합니다")
    private String categoryName;


    private String createdAt;

}
