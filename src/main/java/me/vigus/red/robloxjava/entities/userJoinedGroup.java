package me.vigus.red.robloxjava.entities;

public class UserJoinedGroup extends Group{

    private int roleId;
    private String roleName;
    private int rank;

    public UserJoinedGroup(int groupId) {
        super(groupId);
    }

    public int getRoleId() {
        return this.roleId;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public int getRank() {
        return this.rank;
    }
}
