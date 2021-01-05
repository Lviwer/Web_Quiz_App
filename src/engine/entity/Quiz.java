package engine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Quiz {


    // @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Text is mandatory")
    private String text;

    @NotNull
    @Size(min = 2, message = "Should contain at least 2 items")
    @ElementCollection
    private List<String> options = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ElementCollection
    private List<Integer> answer = new ArrayList<>();


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    public Quiz() {
    }

    public Quiz(String title, String text, List<String> options, List<Integer> answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


//  public Quiz() {
//  }
//
//  public int getId() {
//      return id;
//  }
//
//  public void setId(int id) {
//      this.id = id;
//  }
//
//  public String getTitle() {
//      return title;
//  }
//
//  public void setTitle(String title) {
//      this.title = title;
//  }
//
//  public String getText() {
//      return text;
//  }
//
//  public void setText(String text) {
//      this.text = text;
//  }
//
//  public String[] getOptions() {
//      return options;
//  }
//
//  public void setOptions(String[] options) {
//      this.options = options;
//  }
//
//  public Answer getAnswer() {
//      return answer;
//  }
//
//  public void setAnswer(Answer answer) {
//      this.answer = answer;
//  }
//
//
//@JsonIgnore// =
// @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //WORK IN THE SAME WAY
//@JsonIgnore

// public int[] getAnswer() {
//     return answer;
// }
//
// public void setAnswer(int[] answer) {
//     Arrays.sort(answer);
//     this.answer = answer;
// }

//}
