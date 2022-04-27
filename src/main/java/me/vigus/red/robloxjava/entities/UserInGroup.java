package me.vigus.red.robloxjava.entities;

public class UserInGroup extends Group{

    private int roleId;
    private String roleName;
    private int roleRank;
    
    private User user;


    public UserInGroup(long id) {
        super(id);
    }


    public int getRoleId() {
        return this.roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getRoleRank() {
        return this.roleRank;
    }

    public void setRoleRank(int roleRank) {
        this.roleRank = roleRank;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
