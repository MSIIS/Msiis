package com.test.demo;

import com.util.model.PageInfo;
import com.web.service.OrganizationService;
import com.web.service.RollService;
import com.web.soupe.roll.Roll;
import com.web.soupe.util.TreeEntityUtils;
import com.web.soupe.web.Organization;
import com.web.soupe.web.Role;
import com.web.soupe.web.User;
import com.web.soupe.web.UserRoleOrgRelation;
import org.junit.Test;

import java.util.*;

/**
 * Created by nlf on 2015-7-7.
 */
public class Test1 extends BaseTestCase {

    @Test
    public void rollServiceTest() {
        RollService ssqService = this.getServiceManager().getRollService();
        System.out.println(ssqService.findNums("1", "kill"));
        System.out.println(ssqService);

    }

    @Test
    public void int2Char() {
        System.out.println((char) 63);
        Map<String, String> map = new HashMap<String, String>();
        map.put("pageNo", 2 + "");
        map.put("pageSize", 3 + "");
        PageInfo<User> pageInfo = this.getServiceManager().getUserService().findPageInfoRule(map);
        System.out.println(pageInfo);
    }

    @Test
    public void getMax() {
        System.out.println(Long.MAX_VALUE + ":" + Long.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE + ":" + Integer.MIN_VALUE);
    }

    @Test
    public void testLists() {
        List<Roll> rolls = this.getServiceManager().getRollService().findListByJdbcSql(1L);
        this.showInfoForCollection(rolls);
    }

    @Test
    public void testUserInsertBatch() {
        List<User> users = new ArrayList<User>();
        Organization o = new Organization();
        o.setId(1);
        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setId(Long.parseLong(i + 1 + ""));
            user.setPassword("123");
            user.setUserName("admin" + i);
            user.setRealName("大侠");
            user.setNickName("菜苗 " + i);
            user.setSalt("abc");
            user.setStatus(1);
            user.setDeleted(false);
            user.setOrganization(o);
            users.add(user);
        }
        this.getServiceManager().getUserService().insertBatch(users);
        User u = this.getServiceManager().getUserService().findById(1L);
        System.out.println(u.getNickName());
    }

    @Test
    public void testRelations() {
        User user = this.getServiceManager().getUserService().findById(1L);
        List<Long> ids = new ArrayList<Long>();
        ids.add(1L);
        ids.add(2L);
        List<Role> roles = this.getServiceManager().getRoleService().findListByIds(ids);
        List<UserRoleOrgRelation> relations = new ArrayList<UserRoleOrgRelation>();
        for (Role rle : roles) {
            UserRoleOrgRelation s = new UserRoleOrgRelation();
            s.setRole(rle);
            s.setUser(user);
            relations.add(s);
        }
        this.getServiceManager().getUserRoleOrgRelationService().save(relations);

    }

    @Test
    public void testuserReal() {

        List<UserRoleOrgRelation> roleRelations = this.getServiceManager().getUserService().findById(1L).getUserRoleOrgRelations();
        this.showInfoForCollection(roleRelations);

    }

    @Test
    public void testOrgs(){
        OrganizationService organizationService=this.getServiceManager().getOrganizationService();

        organizationService.saveAll((List<Organization>)TreeEntityUtils.sortTreeEntity(organizationService.getListForSort()));;


    }

}
