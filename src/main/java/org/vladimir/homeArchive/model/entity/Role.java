package org.vladimir.homeArchive.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;

@Data
//@Table("roles")
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private Long roleId;

    private String roleName;
    private Date lastModifiedDate;
    private User lastModifiedUser;

    public void setLastModifiedUser(User lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }
}
