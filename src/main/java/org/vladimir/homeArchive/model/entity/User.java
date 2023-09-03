package org.vladimir.homeArchive.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;

@Data
//@Table("users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
//    @Id
    private Long userId;
    private String userName;


    private Role role;
    private Date lastModifiedDate;
    private User lastModifiedUser;

    public void setRole(Role role) {
        this.role = role;
    }

    public void setLastModifiedUser(User lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }
}
