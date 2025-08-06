package models_pojo_withoutBuilder.models_pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Posts {
    private String id;
    private String title;
    private int views;
    private String author;


    public Posts(){

    }

    public Posts(String id,String title,int views,String author){
        this.id=id;
        this.title=title;
        this.views=views;
        this.author=author;
    }


    public String getId() {return id;}
    public int getViews() {return views;}
    public String getAuthor() {return author;}


    public String getTitle() {
        return title;
    }

    //Builder Patterns
public static class Builder{
        private String id;
        private int views;
        private String title;
        private String author;

        public Builder(){}

    public Builder setId(String id){
            this.id=id;
            return this;
    }
        public Builder setViews(int views){
            this.views=views;
            return this;
        }


    public Builder setTitle(String title){
        this.title=title;
        return this;
    }

    public Builder setAuthor(String author){
        this.author=author;
        return this;
    }

    public Posts build(){return new Posts( id,title,views,author);}
}

}
