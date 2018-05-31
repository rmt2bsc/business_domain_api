package org.rmt2.api;

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
 * Audio/Video Media testing facility that is mainly responsible for setting up mock data.
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
    protected List<VwUserAppRoles> mockVwUserAppRolesData;
    protected List<VwUserGroup> mockVwUserGroupData;
    protected List<VwUserResourceAccess> mockVwUserResourceAccessData;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        this.mockAppRoleData = null;
        this.mockApplicationData = null;
        this.mockApplicationAccessData = null;
        this.mockGroupRolesData = null;
        this.mockRolesData = null;
        this.mockUserAppRoleData = null;
        this.mockUserGroupData = null;
        this.mockUserLoginData = null;
        this.mockUserAppRoleData = null;
        this.mockUserResourceData = null;
        this.mockUserResourceAccessData = null;
        this.mockUserResourceSubtypeData = null;
        this.mockUserResourceTypeData = null;
        this.mockVwAppRolesData = null;
        this.mockVwResourceData = null;
        this.mockVwResourceTypeData = null;
        this.mockVwUserData = null;
        this.mockVwUserAppRolesData = null;
        this.mockVwUserGroupData = null;
        this.mockVwUserResourceAccessData = null;
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

//    private List<AvGenre> createAvGenreMockData() {
//        List<AvGenre> list = new ArrayList<>();
//        int ndx = SecurityMockDataFactory.TEST_GENRE_ID;
//        AvGenre o = SecurityMockDataFactory.createOrmAvGenre(ndx, "Genre" + ndx);
//        list.add(o);
//        
//        o = SecurityMockDataFactory.createOrmAvGenre(++ndx, "Genre" + ndx);
//        list.add(o);
//        o = SecurityMockDataFactory.createOrmAvGenre(++ndx, "Genre" + ndx);
//        list.add(o);
//        o = SecurityMockDataFactory.createOrmAvGenre(++ndx, "Genre" + ndx);
//        list.add(o);
//        o = SecurityMockDataFactory.createOrmAvGenre(++ndx, "Genre" + ndx);
//        list.add(o);
//        
//        return list;
//    }

}