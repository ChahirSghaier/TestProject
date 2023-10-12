package tn.esprit.idev.Entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Response {
    private String statusText;
    private Integer status;

}
