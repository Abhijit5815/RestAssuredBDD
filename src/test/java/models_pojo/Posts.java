package models_pojo;

import lombok.Builder;
import lombok.Data;

@Data
//@Builder
public class Posts {
    private String id;
    private String title;
    private int views;
    private String author;

}
