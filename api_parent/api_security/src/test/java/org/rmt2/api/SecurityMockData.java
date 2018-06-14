package org.rmt2.api;

import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.AppRole;
import org.dao.mapping.orm.rmt2.Application;
import org.dao.mapping.orm.rmt2.ApplicationAccess;
import org.dao.mapping.orm.rmt2.GroupRoles;
import org.dao.mapping.orm.rmt2.Roles;
import org.dao.mapping.orm.rmt2.UserAppRole;
import org.dao.mapping.orm.rmt2.UserGroup;
import org.dao.mapping.orm.rmt2.UserLogin;
import org.dao.mapping.orm.rmt2.UserResource;
import org.dao.mapping.orm.rmt2.UserResourceAccess;
import org.dao.mapping.orm.rmt2.UserResourceSubtype;
import org.dao.mapping.orm.rmt2.UserResourceType;
import org.dao.mapping.orm.rmt2.VwAppRoles;
import org.dao.mapping.orm.rmt2.VwResource;
import org.dao.mapping.orm.rmt2.VwResourceType;
import org.dao.mapping.orm.rmt2.VwUser;
import org.dao.mapping.orm.rmt2.VwUserAppRoles;
import org.dao.mapping.orm.rmt2.VwUserGroup;
import org.dao.mapping.orm.rmt2.VwUserResourceAccess;
import org.junit.After;
import org.junit.Before;

/**
 * Security testing facility that is mainly responsible for setting up mock data.
 * <p>
 * All derived media related Api unit tests should inherit this class
 * to prevent duplicating common functionality.
 * 
 * @author rterrell
 * 
 */
public class SecurityMockData extends BaseSecurityDaoTest {
    protected List<AppRole> mockAppRoleData;
    protected List<Application> mockApplicationData;
    protected List<ApplicationAccess> mockApplicationAccessData;
    protected List<GroupRoles> mockGroupRolesData;
    protected List<Roles> mockRolesData;
    protected List<UserAppRole> mockUserAppRoleData;
    protected List<UserGroup> mockUserGroupData;
    protected List<UserLogin> mockUserLoginData;
    protected List<UserResource> mockUserResourceData;
    protected List<UserResourceAccess> mockUserResourceAccessData;
    protected List<UserResourceSubtype> mockUserResourceSubtypeData;
    protected List<UserResourceType> mockUserResourceTypeData;
    protected List<VwAppRoles> mockVwAppRolesData;
    protected List<VwResource> mockVwResourceData;
    protected List<VwResourceType> mockVwResourceTypeData;
    protected List<VwUser> mockVwUserData;
    protected List<VwUser> mockVwUserSingleData;
    protected List<VwUserAppRoles> mockVwUserAppRolesData;
    protected List<VwUserGroup> mockVwUserGroupData;
    protected List<VwUserResourceAccess> mockVwUserResourceAccessData;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        this.mockAppRoleData = this.createAppRoleMockData();
        this.mockApplicationData = this.createApplicationMockData();
        this.mockApplicationAccessData = this.createApplicationAccessMockData();
        this.mockGroupRolesData = this.createGroupRolesMockData();
        this.mockRolesData = this.createRolesMockData();
        this.mockUserAppRoleData = this.createUserAppRoleMockData();
        this.mockUserGroupData = this.createUserGroupMockData();
        this.mockUserLoginData = this.createUserLoginMockData();
        this.mockUserResourceData = this.createUserResourceMockData();
        this.mockUserResourceAccessData = this.createUserResourceAccessMockData();
        this.mockUserResourceSubtypeData = this.createUserResourceSubtypeMockData();
        this.mockUserResourceTypeData = this.createUserResourceTypeMockData();
        this.mockVwAppRolesData = this.createVwAppRolesMockData();
        this.mockVwResourceData = this.createVwResourceMockData();
        this.mockVwResourceTypeData = this.createVwResourceTypeMockData();
        this.mockVwUserData = this.createVwUserMockData();
        this.mockVwUserSingleData = this.createVwUserSingleMockData();
        this.mockVwUserAppRolesData = this.createVwUserAppRolesMockData();
        this.mockVwUserGroupData = this.createVwUserGroupMockData();
        this.mockVwUserResourceAccessData = this.createVwUserResourceAccessMockData();
        return;
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }

    private List<AppRole> createAppRoleMockData() {
        List<AppRole> list = new ArrayList<>();
        int appRoleId = SecurityMockDataFactory.TEST_APP_ROLE_ID;
        int roleId = SecurityMockDataFactory.TEST_ROLE_ID;
        AppRole o = SecurityMockDataFactory.createOrmAppRole(appRoleId,
                SecurityMockDataFactory.TEST_APP_ID, roleId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmAppRole(++appRoleId,
                SecurityMockDataFactory.TEST_APP_ID, ++roleId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmAppRole(++appRoleId,
                SecurityMockDataFactory.TEST_APP_ID, ++roleId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmAppRole(++appRoleId,
                SecurityMockDataFactory.TEST_APP_ID, ++roleId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmAppRole(++appRoleId,
                SecurityMockDataFactory.TEST_APP_ID, ++roleId);
        list.add(o);
        
        return list;
    }

    
    private List<Application> createApplicationMockData() {
        List<Application> list = new ArrayList<>();
        int appId = SecurityMockDataFactory.TEST_APP_ID;
        Application o = SecurityMockDataFactory.createOrmApplication(appId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmApplication(++appId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmApplication(++appId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmApplication(++appId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmApplication(++appId);
        list.add(o);
        
        return list;
    }
    
    private List<ApplicationAccess> createApplicationAccessMockData() {
        List<ApplicationAccess> list = new ArrayList<>();
        int appAccessId = SecurityMockDataFactory.TEST_APP_ACCESS_ID;
        int appId = SecurityMockDataFactory.TEST_APP_ID;
        ApplicationAccess o = SecurityMockDataFactory
                .createOrmApplicationAccess(appAccessId, appId,
                        SecurityMockDataFactory.TEST_USER_ID, true);
        list.add(o);
        o = SecurityMockDataFactory.createOrmApplicationAccess(++appAccessId,
                ++appId, SecurityMockDataFactory.TEST_USER_ID, true);
        list.add(o);
        o = SecurityMockDataFactory.createOrmApplicationAccess(++appAccessId,
                ++appId, SecurityMockDataFactory.TEST_USER_ID, true);
        list.add(o);
        o = SecurityMockDataFactory.createOrmApplicationAccess(++appAccessId,
                ++appId, SecurityMockDataFactory.TEST_USER_ID, true);
        list.add(o);
        o = SecurityMockDataFactory.createOrmApplicationAccess(++appAccessId,
                ++appId, SecurityMockDataFactory.TEST_USER_ID, true);
        list.add(o);
        
        return list;
    }
    
    
    private List<GroupRoles> createGroupRolesMockData() {
        List<GroupRoles> list = new ArrayList<>();
        int grpRoleId = SecurityMockDataFactory.TEST_GROUP_ROLD_ID;
        GroupRoles o = SecurityMockDataFactory.createOrmGroupRoles(grpRoleId,
                SecurityMockDataFactory.TEST_GROUP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);
        list.add(o);
        o = SecurityMockDataFactory.createOrmGroupRoles(++grpRoleId,
                SecurityMockDataFactory.TEST_GROUP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);
        list.add(o);
        o = SecurityMockDataFactory.createOrmGroupRoles(++grpRoleId,
                SecurityMockDataFactory.TEST_GROUP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);
        list.add(o);
        o = SecurityMockDataFactory.createOrmGroupRoles(++grpRoleId,
                SecurityMockDataFactory.TEST_GROUP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);
        list.add(o);
        o = SecurityMockDataFactory.createOrmGroupRoles(++grpRoleId,
                SecurityMockDataFactory.TEST_GROUP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID);
        list.add(o);
        
        return list;
    }
    
    private List<Roles> createRolesMockData() {
        List<Roles> list = new ArrayList<>();
        int roleId = SecurityMockDataFactory.TEST_ROLE_ID;
        Roles o = SecurityMockDataFactory.createOrmRoles(roleId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmRoles(++roleId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmRoles(++roleId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmRoles(++roleId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmRoles(++roleId);
        list.add(o);
        
        return list;
    }
    
    private List<UserAppRole> createUserAppRoleMockData() {
        List<UserAppRole> list = new ArrayList<>();
        int userAppRoleId = SecurityMockDataFactory.TEST_USER_APP_ROLE_ID;
        UserAppRole o = SecurityMockDataFactory.createOrmUserAppRole(userAppRoleId,
                SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_USER_ID);
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserAppRole(++userAppRoleId,
                SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_USER_ID);
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserAppRole(++userAppRoleId,
                SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_USER_ID);
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserAppRole(++userAppRoleId,
                SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_USER_ID);
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserAppRole(++userAppRoleId,
                SecurityMockDataFactory.TEST_APP_ROLE_ID,
                SecurityMockDataFactory.TEST_USER_ID);
        list.add(o);
        
        return list;
    }
    
    
    private List<UserGroup> createUserGroupMockData() {
        List<UserGroup> list = new ArrayList<>();
        int groupId = SecurityMockDataFactory.TEST_GROUP_ID;
        UserGroup o = SecurityMockDataFactory.createOrmUserGroup(groupId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserGroup(++groupId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserGroup(++groupId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserGroup(++groupId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserGroup(++groupId);
        list.add(o);
        
        return list;
    }

    private List<UserLogin> createUserLoginMockData() {
        List<UserLogin> list = new ArrayList<>();
        int loginId = SecurityMockDataFactory.TEST_USER_ID;
        UserLogin o = SecurityMockDataFactory.createOrmUserLogin(loginId,
                SecurityMockDataFactory.TEST_GROUP_ID, "UserName_" + loginId,
                "password", "2018-01-01");
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserLogin(++loginId,
                SecurityMockDataFactory.TEST_GROUP_ID, "UserName_" + loginId,
                "password", "2018-01-01");
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserLogin(++loginId,
                SecurityMockDataFactory.TEST_GROUP_ID, "UserName_" + loginId,
                "password", "2018-01-01");
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserLogin(++loginId,
                SecurityMockDataFactory.TEST_GROUP_ID, "UserName_" + loginId,
                "password", "2018-01-01");
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserLogin(++loginId,
                SecurityMockDataFactory.TEST_GROUP_ID, "UserName_" + loginId,
                "password", "2018-01-01");
        list.add(o);
        
        return list;
    }
    
    private List<UserResource> createUserResourceMockData() {
        List<UserResource> list = new ArrayList<>();
        int resourceId = SecurityMockDataFactory.TEST_RESOURCE_ID;
        UserResource o = SecurityMockDataFactory.createOrmUserResource(resourceId,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID,
                "URL_" + resourceId, true);
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserResource(++resourceId,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID,
                "URL_" + resourceId, true);
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserResource(++resourceId,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID,
                "URL_" + resourceId, true);
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserResource(++resourceId,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID,
                "URL_" + resourceId, true);
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserResource(++resourceId,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID,
                "URL_" + resourceId, true);
        list.add(o);
        
        return list;
    }
    
    
    private List<UserResourceAccess> createUserResourceAccessMockData() {
        List<UserResourceAccess> list = new ArrayList<>();
        int resourceAccessId = SecurityMockDataFactory.TEST_RESOURCE_ACCESS_ID;
        UserResourceAccess o = SecurityMockDataFactory
                .createOrmUserResourceAccess(resourceAccessId,
                        SecurityMockDataFactory.TEST_GROUP_ID,
                        SecurityMockDataFactory.TEST_RESOURCE_ID,
                        SecurityMockDataFactory.TEST_USER_ID);
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserResourceAccess(++resourceAccessId,
                SecurityMockDataFactory.TEST_GROUP_ID,
                SecurityMockDataFactory.TEST_RESOURCE_ID,
                SecurityMockDataFactory.TEST_USER_ID);
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserResourceAccess(++resourceAccessId,
                SecurityMockDataFactory.TEST_GROUP_ID,
                SecurityMockDataFactory.TEST_RESOURCE_ID,
                SecurityMockDataFactory.TEST_USER_ID);
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserResourceAccess(++resourceAccessId,
                SecurityMockDataFactory.TEST_GROUP_ID,
                SecurityMockDataFactory.TEST_RESOURCE_ID,
                SecurityMockDataFactory.TEST_USER_ID);
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserResourceAccess(++resourceAccessId,
                SecurityMockDataFactory.TEST_GROUP_ID,
                SecurityMockDataFactory.TEST_RESOURCE_ID,
                SecurityMockDataFactory.TEST_USER_ID);
        list.add(o);
        
        return list;
    }
    
    private List<UserResourceSubtype> createUserResourceSubtypeMockData() {
        List<UserResourceSubtype> list = new ArrayList<>();
        int userResourceSubtypeId = SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID;
        UserResourceSubtype o = SecurityMockDataFactory.createOrmUserResourceSubtype(userResourceSubtypeId,
                        SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserResourceSubtype(++userResourceSubtypeId,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserResourceSubtype(++userResourceSubtypeId,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserResourceSubtype(++userResourceSubtypeId,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserResourceSubtype(++userResourceSubtypeId,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID);
        list.add(o);
        
        return list;
    }
    
    private List<UserResourceType> createUserResourceTypeMockData() {
        List<UserResourceType> list = new ArrayList<>();
        int resourceTypeId = SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID;
        UserResourceType o = SecurityMockDataFactory.createOrmUserResourceType(resourceTypeId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserResourceType(++resourceTypeId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserResourceType(++resourceTypeId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserResourceType(++resourceTypeId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmUserResourceType(++resourceTypeId);
        list.add(o);
        
        return list;
    }
    
    private List<VwAppRoles> createVwAppRolesMockData() {
        List<VwAppRoles> list = new ArrayList<>();
        int appRoleId = SecurityMockDataFactory.TEST_APP_ROLE_ID;
        int roleId = SecurityMockDataFactory.TEST_ROLE_ID;
        VwAppRoles o = SecurityMockDataFactory.createOrmVwAppRoles(appRoleId,
                SecurityMockDataFactory.TEST_APP_ID, roleId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwAppRoles(++appRoleId,
                SecurityMockDataFactory.TEST_APP_ID, ++roleId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwAppRoles(++appRoleId,
                SecurityMockDataFactory.TEST_APP_ID, ++roleId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwAppRoles(++appRoleId,
                SecurityMockDataFactory.TEST_APP_ID, ++roleId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwAppRoles(++appRoleId,
                SecurityMockDataFactory.TEST_APP_ID, ++roleId);
        list.add(o);
        
        return list;
    }
  
    private List<VwResource> createVwResourceMockData() {
        List<VwResource> list = new ArrayList<>();
        int resourceId = SecurityMockDataFactory.TEST_RESOURCE_ID;
        int resourceTypeId = SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID;
        VwResource o = SecurityMockDataFactory.createOrmVwResource(resourceId, "URL_" + resourceId,
                resourceTypeId, SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, true);
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwResource(++resourceId, "URL_" + resourceId,
                ++resourceTypeId, SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, true);
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwResource(++resourceId, "URL_" + resourceId,
                ++resourceTypeId, SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, true);
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwResource(++resourceId, "URL_" + resourceId,
                ++resourceTypeId, SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, true);
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwResource(++resourceId, "URL_" + resourceId,
                ++resourceTypeId, SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, true);
        list.add(o);
        
        return list;
    }
    
    private List<VwResourceType> createVwResourceTypeMockData() {
        List<VwResourceType> list = new ArrayList<>();
        int appRoleId = SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID;
        VwResourceType o = SecurityMockDataFactory.createOrmVwResourceType(
                appRoleId, SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID);
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwResourceType(++appRoleId,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID);
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwResourceType(++appRoleId,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID);
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwResourceType(++appRoleId,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID);
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwResourceType(++appRoleId,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID);
        list.add(o);
        
        return list;
    }
    
    private List<VwUser> createVwUserSingleMockData() {
        List<VwUser> list = new ArrayList<>();
        int loginId = SecurityMockDataFactory.TEST_USER_ID;
        VwUser o = SecurityMockDataFactory.createOrmVwUser(loginId,
                SecurityMockDataFactory.TEST_GROUP_ID, "UserName_" + loginId,
                "password", "2018-01-01", "ShortName_" + loginId);
        list.add(o);
        return list;
    }
    
    private List<VwUser> createVwUserMockData() {
        List<VwUser> list = new ArrayList<>();
        int loginId = SecurityMockDataFactory.TEST_USER_ID;
        VwUser o = SecurityMockDataFactory.createOrmVwUser(loginId,
                SecurityMockDataFactory.TEST_GROUP_ID, "UserName_" + loginId,
                "password", "2018-01-01", "ShortName_" + loginId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwUser(++loginId,
                SecurityMockDataFactory.TEST_GROUP_ID, "UserName_" + loginId,
                "password", "2018-01-01", "ShortName_" + loginId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwUser(++loginId,
                SecurityMockDataFactory.TEST_GROUP_ID, "UserName_" + loginId,
                "password", "2018-01-01", "ShortName_" + loginId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwUser(++loginId,
                SecurityMockDataFactory.TEST_GROUP_ID, "UserName_" + loginId,
                "password", "2018-01-01", "ShortName_" + loginId);
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwUser(++loginId,
                SecurityMockDataFactory.TEST_GROUP_ID, "UserName_" + loginId,
                "password", "2018-01-01", "ShortName_" + loginId);
        list.add(o);
        
        return list;
    }
    
    private List<VwUserAppRoles> createVwUserAppRolesMockData() {
        List<VwUserAppRoles> list = new ArrayList<>();
        int appRoleId = SecurityMockDataFactory.TEST_APP_ROLE_ID;
        VwUserAppRoles o = SecurityMockDataFactory.createOrmVwUserAppRoles(SecurityMockDataFactory.TEST_USER_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID,
                appRoleId,
                SecurityMockDataFactory.TEST_GROUP_ID, "user_name",
                "2018-01-01");
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwUserAppRoles(SecurityMockDataFactory.TEST_USER_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID,
                ++appRoleId,
                SecurityMockDataFactory.TEST_GROUP_ID, "user_name",
                "2018-01-01");
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwUserAppRoles(SecurityMockDataFactory.TEST_USER_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID,
                ++appRoleId,
                SecurityMockDataFactory.TEST_GROUP_ID, "user_name",
                "2018-01-01");
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwUserAppRoles(SecurityMockDataFactory.TEST_USER_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID,
                ++appRoleId,
                SecurityMockDataFactory.TEST_GROUP_ID, "user_name",
                "2018-01-01");
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwUserAppRoles(SecurityMockDataFactory.TEST_USER_ID,
                SecurityMockDataFactory.TEST_APP_ID,
                SecurityMockDataFactory.TEST_ROLE_ID,
                ++appRoleId,
                SecurityMockDataFactory.TEST_GROUP_ID, "user_name",
                "2018-01-01");
        list.add(o);
        
        return list;
    }
    
    private List<VwUserGroup> createVwUserGroupMockData() {
        List<VwUserGroup> list = new ArrayList<>();
        int loginId = SecurityMockDataFactory.TEST_USER_ID;
        VwUserGroup o = SecurityMockDataFactory.createOrmVwUserGroup(loginId,
                SecurityMockDataFactory.TEST_GROUP_ID, "UserName_" + loginId,
                "2018-01-01");
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwUserGroup(++loginId,
                SecurityMockDataFactory.TEST_GROUP_ID, "UserName_" + loginId,
                "2018-01-01");
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwUserGroup(++loginId,
                SecurityMockDataFactory.TEST_GROUP_ID, "UserName_" + loginId,
                "2018-01-01");
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwUserGroup(++loginId,
                SecurityMockDataFactory.TEST_GROUP_ID, "UserName_" + loginId,
                "2018-01-01");
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwUserGroup(++loginId,
                SecurityMockDataFactory.TEST_GROUP_ID, "UserName_" + loginId,
                "2018-01-01");
        list.add(o);
        
        return list;
    }
    
    
    private List<VwUserResourceAccess> createVwUserResourceAccessMockData() {
        List<VwUserResourceAccess> list = new ArrayList<>();
        int loginId = SecurityMockDataFactory.TEST_USER_ID;
        VwUserResourceAccess o = SecurityMockDataFactory
                .createOrmVwUserResourceAccess(loginId, "UserName_" + loginId,
                        SecurityMockDataFactory.TEST_GROUP_ID,
                        SecurityMockDataFactory.TEST_RESOURCE_ID,
                        "URL_" + loginId,
                        SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID,
                        SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, true);
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwUserResourceAccess(++loginId,
                "UserName_" + loginId, SecurityMockDataFactory.TEST_GROUP_ID,
                SecurityMockDataFactory.TEST_RESOURCE_ID, "URL_" + loginId,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, true);
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwUserResourceAccess(++loginId,
                "UserName_" + loginId, SecurityMockDataFactory.TEST_GROUP_ID,
                SecurityMockDataFactory.TEST_RESOURCE_ID, "URL_" + loginId,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, true);
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwUserResourceAccess(++loginId,
                "UserName_" + loginId, SecurityMockDataFactory.TEST_GROUP_ID,
                SecurityMockDataFactory.TEST_RESOURCE_ID, "URL_" + loginId,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, true);
        list.add(o);
        o = SecurityMockDataFactory.createOrmVwUserResourceAccess(++loginId,
                "UserName_" + loginId, SecurityMockDataFactory.TEST_GROUP_ID,
                SecurityMockDataFactory.TEST_RESOURCE_ID, "URL_" + loginId,
                SecurityMockDataFactory.TEST_RESOURCE_TYPE_ID,
                SecurityMockDataFactory.TEST_RESOURCE_SUBTYPE_ID, true);
        list.add(o);
        
        return list;
    }
    
}