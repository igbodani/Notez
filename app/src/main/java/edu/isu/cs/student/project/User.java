package edu.isu.cs.student.project;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
public class User {
    @NonNull
    @Getter
    @Setter
    private String firstName;
    @NonNull
    @Getter
    @Setter
    private String email;
    @NonNull
    @Getter
    @Setter
    private String lastName;
    @NonNull
    @Getter
    @Setter
    private String passWord;
    @NonNull
    @Getter
    @Setter
    private String phoneNumber;


}
