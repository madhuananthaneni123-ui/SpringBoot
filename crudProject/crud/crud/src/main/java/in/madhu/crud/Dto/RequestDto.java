package in.madhu.crud.Dto;

import jakarta.validation.constraints.*;

public class RequestDto {
    @NotBlank(message = "name should not be Null")
    @Size(min = 5,max = 50,message = "Size should be B/w 5 to 50")
    private String name;

    @Email(message = "Enter Correct Email")
    private String email;

    private int rollno;

    @NotBlank
    private String subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
