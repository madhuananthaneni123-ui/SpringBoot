package in.madhu.crud.Dto;

import jakarta.validation.constraints.*;

public class RequestDto {
    @NotBlank(message = "name should not be Null")
    @Size(min = 5,max = 50,message = "Size should be B/w 5 to 50")
    private String name;

    @Email(message = "Enter Correct Email")
    private String email;
    @Min(value = 18,message = "Rollno should be greater than 18")
    private Integer rollno;

    @NotBlank
    private String subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRollno() {
        return rollno;
    }

    public void setRollno(Integer rollno) {
        this.rollno = rollno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
