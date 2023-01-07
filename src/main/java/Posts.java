import com.fasterxml.jackson.annotation.JsonProperty;

public class Posts {

    private final String id;
    private final String text;
    private final String type;
    private final String user;
    private final Integer upvotes;

    public Posts(
            @JsonProperty("id") String id,
            @JsonProperty("text") String text,
            @JsonProperty("type") String type,
            @JsonProperty("user") String user,
            @JsonProperty("upvotes") Integer upvotes
    ){
       this.id = id;
       this.text = text;
       this.type = type;
       this.user = user;
       this.upvotes = upvotes;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return toString();
    }

    public String getType() {
        return type;
    }

    public String getUser() {
        return user;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    @Override
    public String toString() {
        return  "\n интересный факт: " + text +
                "\n пользователь: " + user +
                "\n проголосовало: " + upvotes +
                "\n";
    }
}
