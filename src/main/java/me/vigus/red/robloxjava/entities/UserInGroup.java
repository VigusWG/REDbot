package me.vigus.red.robloxjava.entities;

public class UserInGroup extends Group{

    private Integer roleId;
    private String roleName;
    private Integer roleRank;
    
    private User user;


    public UserInGroup(long id) {
        super(id);
    }


    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleRank() {
        return this.roleRank;
    }

    public void setRoleRank(Integer roleRank) {
        this.roleRank = roleRank;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
